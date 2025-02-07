package nl.duckacademy.rest_api.rest;

import nl.duckacademy.rest_api.model.User;
import nl.duckacademy.rest_api.model.UserPatch;
import nl.duckacademy.rest_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//@RestController
//@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.getUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam(required = true) String name) {
        var users = this.userService.findByName(name);
        return ResponseEntity.ok(users);
    }
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        var userResult = this.userService.createUser(user);
        return ResponseEntity.ok(userResult);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        var userResult = this.userService.updateUser(id, user);
        return ResponseEntity.ok(userResult);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable int id, @RequestBody UserPatch userPatch) {
        var userResult = this.userService.patchUser(id, userPatch);
        return ResponseEntity.ok(userResult);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        var userResult = this.userService.deleteUser(id);
        return ResponseEntity.ok(userResult);
    }
}
