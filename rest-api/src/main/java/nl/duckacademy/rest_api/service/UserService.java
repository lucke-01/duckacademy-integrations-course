package nl.duckacademy.rest_api.service;

import nl.duckacademy.rest_api.model.User;
import nl.duckacademy.rest_api.model.UserPatch;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import nl.duckacademy.rest_api.customexception.UserNotFoundException;

@Service
public class UserService {
    private static final List<User> USER_DOMAINS = new ArrayList<>(List.of(
            new User(1, "Juan", "Perez", LocalDate.now(), BigDecimal.valueOf(200.2), true),
            new User(2, "Juan", "Perez", LocalDate.now(), BigDecimal.valueOf(200.2), true),
            new User(3, "Lopez", "Perez", LocalDate.now(), BigDecimal.valueOf(200.2), true)
    ));

    public List<User> getUsers() {
        return USER_DOMAINS;
    }

    public User findById(int id) {
        return USER_DOMAINS.stream()
                .filter(u->u.id() == id)
                .findFirst()
                .orElseThrow(()->new UserNotFoundException("User not found", "User could not find in the ArrayList", HttpStatus.NOT_FOUND));
    }
    public List<User> findByName(String name) {
        return USER_DOMAINS.stream()
                .filter(u->name.equals(u.name()))
                .toList();
    }
    public synchronized User createUser(User user) {
        USER_DOMAINS.add(user);
        return user;
    }
    public synchronized User updateUser(int id, User user) {
        USER_DOMAINS.stream()
                .filter(u->u.id() == id)
                .findFirst()
                .ifPresent(u-> {
                    USER_DOMAINS.set(USER_DOMAINS.indexOf(u), user);
                });
        return user;
    }
    public synchronized User patchUser(int id, UserPatch userPatch) {
        AtomicReference<User> user = new AtomicReference<>(null);

        USER_DOMAINS.stream()
                .filter(u->u.id() == id)
                .findFirst()
                .ifPresent(u-> {
                    User newUser = new User(u.id(), userPatch.name(), u.lastName(), u.birthDay(), userPatch.salary(), true);
                    USER_DOMAINS.set(USER_DOMAINS.indexOf(u), newUser);
                    user.set(newUser);
                });

        return user.get();
    }
    public synchronized User deleteUser(int id) {
        AtomicReference<User> user = new AtomicReference<>(null);

        USER_DOMAINS.stream()
            .filter(u->u.id() == id)
            .findFirst()
            .ifPresent(u-> {
                USER_DOMAINS.remove(u);
                user.set(u);
            });

        return user.get();
    }
}
