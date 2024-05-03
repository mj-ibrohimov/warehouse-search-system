package warehouse.services;

import warehouse.dao.UserDao;
import warehouse.domains.User;
import warehouse.domains.UserRole;
import warehouse.utils.BaseUtils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class AuthService {

    private final UserDao userDao = new UserDao();
    private final UserService userService = new UserService();
    private final Random random = new Random();

    public User signIn() {
        try {
            List<User> users = userDao.findAll();
            UserService userService = new UserService();
            return userService.login(users);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void signUp() {
        try {
            User user = new User();
            List<User> oldUsers = userDao.findAll();
            System.out.println("Enter fullName");
            String fullName = BaseUtils.readText();

            System.out.println("Enter username");
            String username = BaseUtils.readText();

            System.out.println("Enter password");
            String password = BaseUtils.readText();

            System.out.println("Enter phoneNumber");
            String phoneNumber = BaseUtils.readText();

            user.setId(random.nextLong(1000) + 1);
            user.setPassword(password);
            user.setUsername(username);
            user.setFullName(fullName);
            user.setPhoneNumber(phoneNumber);
            user.setRole(UserRole.USER);
            userService.register(user, oldUsers);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }
}
