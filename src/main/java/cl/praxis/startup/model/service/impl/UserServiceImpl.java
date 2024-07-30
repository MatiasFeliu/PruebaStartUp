package cl.praxis.startup.model.service.impl;

import cl.praxis.startup.model.dao.UserDao;
import cl.praxis.startup.model.models.UserDTO;
import cl.praxis.startup.model.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDTO selectUser(String email) {
        return userDao.selectUser(email);
    }

    @Override
    public List<UserDTO> selectAllUsers() {
        return userDao.selectAllUsers();
    }

    @Override
    public UserDTO insertUser(UserDTO user) {
        return userDao.insertUser(user);
    }

    @Override
    public UserDTO insertUser(UserDTO user, String role) {
        // Primero insertamos el usuario
        UserDTO insertedUser = userDao.insertUser(user);
        // Luego insertamos el rol del usuario
        userDao.insertUserRole(insertedUser, role);
        return insertedUser;
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public UserDTO login(String email) {
        return userDao.login(email);
    }
}
