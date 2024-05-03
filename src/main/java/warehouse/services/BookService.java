package warehouse.dao;

import warehouse.config.CustomFileReader;
import warehouse.domains.User;
import warehouse.domains.UserRole;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements BaseDao<User> {
    private final String userFile = "src/main/resources/user.csv";
    private final CustomFileReader fileReader = new CustomFileReader();

    @Override
    public List<User> findAll() throws IOException {
        return readUserFile();
    }

    public List<User> readUserFile() throws IOException {
        List<User> user = new ArrayList<>();
        List<String> strings = fileReader.readFile(userFile);
        strings.forEach(s -> user.add(toUser(s)));
        return user;
    }

    private User toUser(String line) {
        String[] strings = line.split(",");
        return User.childBuilder()
                .id(Long.valueOf(strings[0]))
                .username((strings[1]))
                .password(strings[2])
                .fullName(strings[3])
                .phoneNumber(strings[4])
                .role(UserRole.valueOf(strings[5]))
                .build();
    }

    // Updated functionality from the first commit:
    public void saveUserCsvFile(User user) {
        try (FileWriter fileWriter = new FileWriter(userFile, true)) {
            fileWriter.write(user.getId() + "," + user.getUsername() + "," +
                    user.getPassword() + "," + user.getFullName() + "," + user.getPhoneNumber() + "," + user.getRole() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(Long userId) {
        List<String> lines = null;
        Path path = Paths.get(userFile);
        try {
            lines = fileReader.readFile(userFile);
            List<String> modifiedLines = new ArrayList<>();
            List<String> strings = List.of("id,username,password,fullName,phoneNumber,role");
            modifiedLines.addAll(strings);
            modifiedLines.addAll(lines.stream().filter(line -> !line.startsWith(userId + ",")).toList());
            Files.write(path, modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
