package warehouse.services;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import warehouse.controller.BookController;
import warehouse.controller.MagazineController;
import warehouse.controller.PosterController;
import warehouse.dao.UserDao;
import warehouse.domains.Book;
import warehouse.domains.MessageHelper;
import warehouse.domains.User;
import warehouse.domains.UserRole;
import warehouse.dtos.AppErrorDto;
import warehouse.dtos.DataDto;
import warehouse.dtos.ResponseEntity;
import warehouse.exceptions.DaoException;
import warehouse.exceptions.GenericNotFoundException;
import warehouse.ui.AppUI;
import warehouse.utils.BaseUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserService {

    private final UserDao userDao = new UserDao();
    private final PosterController posterController = new PosterController();
    private final MagazineController magazineController = new MagazineController();
    private final BookController bookController = new BookController();
    private final Random random = new Random();

    public void userMenu(User user) {
        System.out.println(MessageHelper.LOGIN + " " + user.getUsername());
        boolean userMenu = true;
        while (userMenu) {
            try {
                System.out.println("\n1=> Book Settings. 2=> Poster Settings. 3=>Magazine Settings. 4.Back");
                System.out.print("Choose option: ");
                int userOption = BaseUtils.readInteger();
                switch (userOption) {
                    case 1:
                        bookSetting();
                        break;
                    case 2:
                        posterSetting();
                        break;
                    case 3:
                        magazineSetting();
                        break;
                    case 4:
                        userMenu = false;
                        break;
                    default:
                        System.out.println(MessageHelper.WRONG_OPTION);
                        break;
                }
            } catch (InputMismatchException e) {
                throw new RuntimeException("You entered input in the wrong format!");
            }
        }
    }

    public void magazineSetting() {
        boolean magazineSettings = true;
        while (magazineSettings) {
            try {
                System.out.println("\n1=>Add Magazine. 2=>Update Magazine. 3=>Delete Magazine. 4=>Back");
                System.out.print("Choose option: ");
                int magazineOption = BaseUtils.readInteger();
                switch (magazineOption) {
                    case 1: {
                        magazineController.addMagazine();
                        break;
                    }
                    case 2:
                        magazineController.updateMagazine();
                        break;
                    case 3:
                        magazineController.deleteMagazine();
                        break;
                    case 4:
                        magazineSettings = false;
                        break;
                    default:
                        System.out.println(MessageHelper.WRONG_OPTION);
                }
            } catch (InputMismatchException e) {
                throw new RuntimeException("You entered input in the wrong format!");
            }
        }
    }

    public void posterSetting() {
        boolean posterSettings = true;
        while (posterSettings) {
            try {
                System.out.println("\n1=>Add Poster. 2=>Update Poster. 3=>Delete Poster. 4=>Back");
                System.out.print("Choose option: ");
                int posterOption = BaseUtils.readInteger();
                switch (posterOption) {
                    case 1: {
                        posterController.addPoster();
                        break;
                    }
                    case 2:
                        posterController.updatePoster();
                        break;
                    case 3:
                        posterController.deletePoster();
                        break;
                    case 4:
                        posterSettings = false;
                        break;
                    default:
                        System.out.println(MessageHelper.WRONG_OPTION);
                }
            } catch (InputMismatchException e) {
                throw new RuntimeException("You entered input in the wrong format!");
            }
        }
    }

    public void bookSetting() {
        boolean bookSettings = true;
        while (bookSettings) {
            try {
                System.out.println("\n1=>Add Book. 2=>Update Book. 3=>Delete Book. 4=>Back");
                System.out.print("Choose option: ");
                int bookOption = BaseUtils.readInteger();
                switch (bookOption) {
                    case 1: {
                        bookController.addBook();
                        break;
                    }
                    case 2:
                        bookController.updateBook();
                        break;
                    case 3:
                        bookController.deleteBook();
                        break;
                    case 4:
                        bookSettings = false;
                        break;
                    default:
                        System.out.println(MessageHelper.WRONG_OPTION);
                }
            } catch (InputMismatchException e) {
                throw new RuntimeException("You entered input in the wrong format!");
            }
        }
    }

    public User login(List<User> lists) {
        try {
            System.out.println("Enter username ==>");
            String username = BaseUtils.readText();
            System.out.println("Enter password ==>");
            String password = BaseUtils.readText();

            FourFunction<User, String> returnUser = (userName, passWord, userlist) -> {
                User user = null;
                user = userlist.stream()
                        .filter(user1 -> user1.getUsername().equalsIgnoreCase(userName) && user1.getPassword().equals(passWord))
                        .findFirst()
                        .orElse(null);
                return user;
            };
            return returnUser.fourFunction(username, password, lists);
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    public String userList() {
        try {
            List<User> users = null;
            users = userDao.findAll();
            String userList = "";
            int i = 0;
            if (users.isEmpty()) {
                System.out.println("User not found!");
                return null;
            }
            for (User user : users) {
                String str = "";
                if (user.getRole().equals(UserRole.USER)) {
                    str = i + ")" + "| id: " + user.getId() + "| userName: " + user.getUsername() + " | roleName: " + user.getRole() + " |";
                    System.out.println(str);
                    userList += str;
                    i++;
                }
            }
            if (userList == "")
                System.out.println(MessageHelper.USER_NOT_FOUND);

            return userList;
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void accessAdminRole() {
        System.out.print("Select user(id): ");
        long id = BaseUtils.readInteger();
        try {
            List<User> users = userDao.findAll();
            for (User user : users) {
                if (user.getId() == id) {
                    if (user.getRole().equals(UserRole.USER)) {
                        user.setRole(UserRole.ADMIN);
                        userDao.removeUser(id);
                        UserBuilder<User> admin = User::new;
                        User user1 = admin.create(id, user.getUsername(), user.getPassword(), user.getFullName(), user.getPhoneNumber(), user.getRole());
                        userDao.saveUserCsvFile(user1);
                        System.out.println("Successfully established Admin role!");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(User user, List<User> oldUsers) {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/user.csv", true)) {
            if (!checkUser(oldUsers, user.getUsername())) {
                fileWriter.write(user.getId() + "," + user.getUsername() + "," +
                        user.getPassword() + "," + user.getFullName() + "," + user.getPhoneNumber() + "," + user.getRole() + "\n");
                System.out.println(MessageHelper.CREATED_USER);
            } else {
                System.out.println(MessageHelper.USER_ALERTEST);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUser(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username))
                return true;
        }
        return false;
    }

    public void addUser() {
        try {
            System.out.print("Enter fullName of User:");
            String fullName = BaseUtils.readText();
            System.out.print("Enter username: ");
            String username = BaseUtils.readText();
            System.out.print("Enter password: ");
            String password = BaseUtils.readText();
            System.out.print("Enter phoneNumber: ");
            String phoneNumber = BaseUtils.readText();
            List<User> users = userDao.findAll();
            for (User user : users) {
                if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)) {
                    throw new RuntimeException("User already exist!");
                }
            }
            User user = User.builder()
                    .id(random.nextLong(1000) + 1)
                    .username(username)
                    .password(password)
                    .fullName(fullName)
                    .phoneNumber(phoneNumber)
                    .role(UserRole.USER)
                    .build();
//            String[] userArray = {String.valueOf(random.nextLong(1000) + 1), username, password, fullName, phoneNumber, String.valueOf(UserRole.USER)};
            userDao.saveUserCsvFile(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    public void updateUser() {
        try {
            userList();
            System.out.println("Select User(id): ");
            Long userId = BaseUtils.readLong();
            System.out.print("Enter new username: ");
            String newusername = BaseUtils.readText();
            System.out.print("Enter new password: ");
            String newpassword = BaseUtils.readText();
            System.out.print("Enter new fullname: ");
            String newfullname = BaseUtils.readText();
            userDao.removeUser(userId);
            User userData = User.builder()
                    .id(userId)
                    .username(newusername)
                    .password(newpassword)
                    .fullName(newfullname)
                    .phoneNumber(null)
                    .role(UserRole.USER)
                    .build();
            userDao.saveUserCsvFile(userData);
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    private User findById(Long userId) {
        try {
            User user = userDao.findAll().stream().filter(user1 ->
                    user1.getId().equals(userId)).findFirst().orElse(null);
            if (user == null) {
                throw new GenericNotFoundException("User not found!");
            }
            return user;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public void deleteUser() {
        try {
            userList();
            System.out.print("Select User(id): ");
            Long userId = BaseUtils.readLong();
            userDao.removeUser(userId);
            System.out.println("Deleted successfully!");
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }


}
