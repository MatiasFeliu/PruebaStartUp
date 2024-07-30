package cl.praxis.startup.model.service;

import cl.praxis.startup.model.models.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO selectUser(String email);

    public List<UserDTO> selectAllUsers();

    public UserDTO insertUser(UserDTO user);

    UserDTO insertUser(UserDTO user, String role);

    public UserDTO updateUser(UserDTO user);

    public boolean deleteUser(int id);

    public UserDTO login(String email);

}
