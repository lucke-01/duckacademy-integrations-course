package nl.duckacademy.rest_api.rest.mapper;

import com.swagger.client.codegen.rest.model.UserDTO;
import com.swagger.client.codegen.rest.model.UserPatchDTO;
import nl.duckacademy.rest_api.model.User;
import nl.duckacademy.rest_api.model.UserPatch;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO userFromUserDomain(User user) {
        UserDTO userDTOResult = new UserDTO();
        userDTOResult.setId(user.id());
        userDTOResult.setName(user.name());
        userDTOResult.setLastName(user.lastName());
        userDTOResult.setBirthDay(user.birthDay());
        userDTOResult.setSalary(user.salary());
        userDTOResult.setActive(user.active());

        return userDTOResult;
    }
    public User userToUser(UserDTO UserDTO) {
        User userResult = new User(
                UserDTO.getId(), UserDTO.getName(), UserDTO.getLastName(),
                UserDTO.getBirthDay(), UserDTO.getSalary(),
                UserDTO.getActive());

        return userResult;
    }
    public UserPatch userPatchDtoToDomain(UserPatchDTO userPatchDTO) {
        return new UserPatch(userPatchDTO.getName(), userPatchDTO.getSalary());
    }
}
