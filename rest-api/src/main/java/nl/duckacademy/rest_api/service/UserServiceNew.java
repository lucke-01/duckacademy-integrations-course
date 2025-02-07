package nl.duckacademy.rest_api.service;

import nl.duckacademy.rest_api.customexception.UserNotFoundException;
import com.swagger.client.codegen.rest.model.User;
import com.swagger.client.codegen.rest.model.UserPatch;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceNew {

    private static final List<User> USERS = new ArrayList<>(List.of(

    ));
    static {
        User user = new User();
        user.setId(1);
        USERS.add(user);
    }
    public List<User> getUsers() {
        return USERS;
    }

    public User findById(int id) {
        return USERS.stream()
                .filter(u->u.getId() == id)
                .findFirst()
                .orElseThrow(()->new UserNotFoundException("User not found", "User could not find in the ArrayList", HttpStatus.NOT_FOUND));
    }
    public List<User> findByName(String name) {
        return USERS.stream()
                .filter(u->name.equals(u.getName()))
                .toList();
    }
    public synchronized User createUser(User user) {
        USERS.add(user);
        return user;
    }
    public synchronized User updateUser(int id, User user) {
        USERS.stream()
                .filter(u->u.getId() == id)
                .findFirst()
                .ifPresent(u-> {
                    USERS.set(USERS.indexOf(u), user);
                });
        return user;
    }
    public synchronized User patchUser(int id, UserPatch userPatch) {
        AtomicReference<User> user = new AtomicReference<>(null);

        USERS.stream()
                .filter(u->u.getId() == id)
                .findFirst()
                .ifPresent(u-> {
                    User newUser = new User();
                    //User newUser = new User(u.getId(), userPatch.getName(), u.getLastName(), u.getBirthDay(),userPatch.getSalary(), true);
                    USERS.set(USERS.indexOf(u), newUser);
                    user.set(newUser);
                });

        return user.get();
    }
    public synchronized User deleteUser(int id) {
        AtomicReference<User> user = new AtomicReference<>(null);

        USERS.stream()
            .filter(u->u.getId() == id)
            .findFirst()
            .ifPresent(u-> {
                USERS.remove(u);
                user.set(u);
            });

        return user.get();
    }
}
