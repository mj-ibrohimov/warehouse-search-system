package warehouse.ui;

import warehouse.controller.BookController;
import warehouse.controller.MagazineController;
import warehouse.controller.PosterController;
import warehouse.domains.MessageHelper;
import warehouse.domains.User;
import warehouse.domains.UserRole;
import warehouse.services.AuthService;
import warehouse.services.UserService;
import warehouse.utils.BaseUtils;

import java.util.InputMismatchException;
import java.util.Objects;

public class AppUI {
    private final MagazineController magazineController = new MagazineController();
    private final BookController bookController = new BookController();
    private final PosterController posterController = new PosterController();
    private final AuthService authService = new AuthService();
    private final UserService userService = new UserService();

    public void run() {
        boolean active = true;
        while (active) {
            try {
                System.out.println("1=> Sign In. 2=> Sign Up. 0=> Exit");
                System.out.print("Choose option: ");
                int option = BaseUtils.readInteger();
                switch (option) {
                    case 1: {
                        User user = authService.signIn();
                        if (user != null) {
                            if (user.getRole().equals(UserRole.ADMIN)) {
                                System.out.println("Admin " + user.getUsername());
                                secondStep();
                            } else {
                                userService.userMenu(user);
                            }

                        } else {
                            System.out.println(MessageHelper.USER_NOT_FOUND);
                        }
                        break;
                    }
                    case 2: {
                        authService.signUp();
                        break;
                    }
                    case 0: {
                        active = false;
                        break;
                    }
                    default: {
                        System.out.println(MessageHelper.WRONG_OPTION);
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                throw new RuntimeException("You entered input in the wrong format!");
            }
        }
    }

    public void secondStep() {
        try {
            BaseUtils.println("\n1 -> Magazine");
            BaseUtils.println("2 -> Book");
            BaseUtils.println("3 -> Poster");
            BaseUtils.println("4 -> User");
            BaseUtils.println("q -> Back");

            BaseUtils.print("-- Select operation: ");
            switch (BaseUtils.readText()) {
                case "1" -> magazineUI();
                case "2" -> bookUI();
                case "3" -> posterUI();
                case "4" -> userUI();
                case "q" -> run();
                default -> BaseUtils.println(MessageHelper.WRONG_OPTION);
            }
            secondStep();
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    private void userUI() {
        boolean adminactive = true;
        while (adminactive) {
            try {
                System.out.println("1=> User List. 2=> User Settings.");
                System.out.println("3=> Access admin role. 4=>Back");
                System.out.print("Choose option: ");
                int adminOption = BaseUtils.readInteger();
                switch (adminOption) {
                    case 1: {
                        userService.userList();
                        break;
                    }
                    case 2: {
                        boolean userSettings = true;
                        while (userSettings) {
                            System.out.println("\n1=>Add User. 2=>UpdateUser. 3=>DeleteUser. 4=>Back");
                            System.out.print("Choose option: ");
                            int userOption = BaseUtils.readInteger();
                            switch (userOption) {
                                case 1: {
                                    userService.addUser();
                                    break;
                                }
                                case 2:
                                    userService.updateUser();
                                    break;
                                case 3:
                                    userService.deleteUser();
                                    break;
                                case 4:
                                    userSettings = false;
                                    break;
                                default:
                                    System.out.println(MessageHelper.WRONG_OPTION);
                            }
                        }
                        break;
                    }
                    case 3: {
                        String s = userService.userList();
                        if (s != null)
                            userService.accessAdminRole();
                        break;
                    }
                    case 4: {
                        secondStep();
                        break;
                    }
                    default: {
                        System.out.println(MessageHelper.WRONG_OPTION);
                        break;
                    }

                }
            } catch (InputMismatchException e) {
                throw new RuntimeException("You entered input in the wrong format!");
            }
        }
    }

    public String baseUI() {
        try {
            BaseUtils.println("1 -> Show all");
            BaseUtils.println("2 -> Element Settings");
            BaseUtils.println("3 -> Find by id");
            BaseUtils.println("4 -> Find by price");
            BaseUtils.println("5 -> Filter by price");
            BaseUtils.println("6 -> Find by AuthorName");
            BaseUtils.println("7 -> Find by pageCount");
            BaseUtils.println("0 -> Back");

            BaseUtils.print("Select operation: ");
            return BaseUtils.readText();
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    private void bookUI() {
        try {
            BaseUtils.println("\n\n8 -> Find by Color");
            BaseUtils.println("9 -> Find by width and height");
            switch (baseUI()) {
                case "1" -> showAllBook();
                case "2" -> userService.bookSetting();
                case "3" -> bookController.findById();
                case "4" -> bookController.findByPrice();
                case "5" -> bookController.filterByPrice();
                case "6" -> bookController.findByAuthorName();
                case "7" -> bookController.findByPageCount();
                case "8" -> bookController.findByColor();
                case "9" -> bookController.findByWidthAndHeight();
                case "0" -> secondStep();
                default -> BaseUtils.println("Wrong choice!");
            }
            bookUI();
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    private String showUI() {
        try {
            BaseUtils.println("\n\n1 -> Sort by id");
            BaseUtils.println("2 -> Sort by price");
            BaseUtils.println("0 -> Back");

            BaseUtils.print("-- Select operation: ");
            return BaseUtils.readText();
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    private void showAllBook() {
        String operation = showUI();
        if (Objects.equals(operation, "0")) {
            bookUI();
        }
        bookController.showAll(operation);
        showAllBook();
    }

    private void magazineUI() {
        try {
            BaseUtils.println("\n\n8 -> Find by front_of_book_content");
            BaseUtils.println("9 -> Find by type");
            switch (baseUI()) {
                case "1" -> showAllMagazine();
                case "2" -> userService.magazineSetting();
                case "3" -> magazineController.findById();
                case "4" -> magazineController.findByPrice();
                case "5" -> magazineController.filterByPrice();
                case "6" -> magazineController.findByAuthorName();
                case "7" -> magazineController.findByPageCount();
                case "8" -> magazineController.findfront_of_book_content();
                case "9" -> magazineController.findByType();
                case "0" -> secondStep();
                default -> BaseUtils.println("Wrong choice!");
            }
            magazineUI();
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    private void showAllMagazine() {
        String operation = showUI();
        if (Objects.equals(operation, "0")) {
            magazineUI();
        }
        magazineController.showAll(operation);
        showAllMagazine();
    }

    private void posterUI() {
        try {
            BaseUtils.println("\n\n8 -> Find by title");
            BaseUtils.println("9 -> Find by length");
            switch (baseUI()) {
                case "1" -> showAllPoster();
                case "2" -> userService.posterSetting();
                case "3" -> posterController.findById();
                case "4" -> posterController.findByPrice();
                case "5" -> posterController.filterByPrice();
                case "6" -> posterController.findByAuthorName();
                case "7" -> posterController.findByPageCount();
                case "8" -> posterController.findByTitle();
                case "9" -> posterController.findByLength();
                case "0" -> secondStep();
                default -> BaseUtils.println("Wrong choice!");
            }
            posterUI();
        } catch (InputMismatchException e) {
            throw new RuntimeException("You entered input in the wrong format!");
        }
    }

    private void showAllPoster() {
        String operation = showUI();
        if (Objects.equals(operation, "0")) {
            posterUI();
        }
        posterController.showAll(operation);
        showAllPoster();
    }
}
