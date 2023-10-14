package quanvan.todoapp.service;

import quanvan.todoapp.dto.UserDto;
import quanvan.todoapp.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
