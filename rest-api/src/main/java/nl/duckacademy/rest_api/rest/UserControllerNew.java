package nl.duckacademy.rest_api.rest;

import com.swagger.client.codegen.rest.UsersApi;
import nl.duckacademy.rest_api.service.UserService;
import nl.duckacademy.rest_api.service.UserServiceNew;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.swagger.client.codegen.rest.model.User;
import com.swagger.client.codegen.rest.model.UserPatch;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserControllerNew implements UsersApi {
    private final UserServiceNew userService;

    public UserControllerNew(UserServiceNew userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.getUsers();
        return ResponseEntity.ok(users);
    }
    @Override
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @Override
    public ResponseEntity<List<User>> getUsersByName(@RequestParam(required = true) String name) {
        var users = this.userService.findByName(name);
        return ResponseEntity.ok(users);
    }
    @Override
    public ResponseEntity<User> createUser(@RequestBody User user) {
        var userResult = this.userService.createUser(user);
        return ResponseEntity.ok(userResult);
    }
    @Override
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        var userResult = this.userService.updateUser(id, user);
        return ResponseEntity.ok(userResult);
    }
    @Override
    public ResponseEntity<User> patchUser(@PathVariable Integer id, @RequestBody UserPatch userPatch) {
        var userResult = this.userService.patchUser(id, userPatch);
        return ResponseEntity.ok(userResult);
    }
    @Override
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        var userResult = this.userService.deleteUser(id);
        return ResponseEntity.ok(userResult);
    }
}
