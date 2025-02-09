package nl.duckacademy.rest_api.rest;

import com.swagger.client.codegen.rest.UsersApi;
import com.swagger.client.codegen.rest.model.UserDTO;
import com.swagger.client.codegen.rest.model.UserPatchDTO;
import nl.duckacademy.rest_api.model.User;
import nl.duckacademy.rest_api.model.UserPatch;
import nl.duckacademy.rest_api.rest.mapper.UserMapper;
import nl.duckacademy.rest_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController implements UsersApi {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOS = this.userService.getUsers()
                .stream().map(userMapper::userFromUserDomain)
                .toList();
        return ResponseEntity.ok(userDTOS);
    }
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserDTO userDTO = userMapper.userFromUserDomain(this.userService.findById(id));
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/search")
    @Override
    public ResponseEntity<List<UserDTO>> getUsersByName(@RequestParam(required = true) String name) {
        var users = this.userService.findByName(name).stream().map(userMapper::userFromUserDomain)
                .toList();;
        return ResponseEntity.ok(users);
    }
    @PostMapping()
    @Override
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = this.userMapper.userToUser(userDTO);
        UserDTO userDTOResult = userMapper.userFromUserDomain(this.userService.createUser(user));
        return ResponseEntity.ok(userDTOResult);
    }
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        User user = this.userMapper.userToUser(userDTO);
        UserDTO userDTOResult = userMapper.userFromUserDomain(this.userService.updateUser(id, user));
        return ResponseEntity.ok(userDTOResult);
    }
    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> patchUser(@PathVariable Integer id, @RequestBody UserPatchDTO userPatchDTO) {
        UserPatch userPatch = userMapper.userPatchDtoToDomain(userPatchDTO);
        UserDTO userDTOResult = userMapper.userFromUserDomain(this.userService.patchUser(id, userPatch));
        return ResponseEntity.ok(userDTOResult);
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer id) {
        UserDTO userDTOResult = userMapper.userFromUserDomain(this.userService.deleteUser(id));
        return ResponseEntity.ok(userDTOResult);
    }
}
