package cl.praxis.startup.model.dao;

import cl.praxis.startup.model.models.UserDTO;

import java.util.List;

public interface UserDao {

    public UserDTO selectUser(String email);

    public List<UserDTO> selectAllUsers();

    public UserDTO insertUser(UserDTO user);

    public UserDTO updateUser(UserDTO user);

    public boolean deleteUser(int id);

    public void insertUserRole(UserDTO user, String role);

    public UserDTO login(String email);

}
