package warehouse.dao;

import warehouse.config.CustomFileReader;
import warehouse.domains.Book;
import warehouse.domains.Magazine;
import warehouse.domains.MessageHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MagazineDao implements BaseDao<Magazine> {

    private final String magazineFile = "src/main/resources/magazine.csv";

    private final CustomFileReader fileReader = new CustomFileReader();

    @Override
    public List<Magazine> findAll() throws IOException {
        return readMagazineFile();
    }

    public List<Magazine> readMagazineFile() throws IOException {
        List<Magazine> magazines = new ArrayList<>();
        List<String> strings = fileReader.readFile(magazineFile);
        strings.forEach(s -> magazines.add(toMagazine(s)));
        return magazines;
    }

    private Magazine toMagazine(String line) {
        String[] strings = line.split(",");
        return Magazine.childBuilder()
                .id(Long.valueOf(strings[0]))
                .author_name(String.valueOf(strings[1]))
                .price(Double.valueOf(strings[2]))
                .pageCount(Integer.valueOf(strings[3]))
                .front_of_book_content(strings[4])
                .type(String.valueOf(strings[5]))
                .build();
    }

    public void saveData(Magazine magazine) {
        try {
            try (FileWriter fileWriter = new FileWriter(magazineFile, true)) {
                fileWriter.write(magazine.getId() + "," + magazine.getAuthor_name() + "," +
                        magazine.getPrice() + "," + magazine.getPageCount() + "," + magazine.getFront_of_book_content() + "," + magazine.getType() + "\n");
            }
            System.out.println(MessageHelper.MAGAZINE_SAVED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeMagazine(Long id) {
        List<String> lines = null;
        Path path = Paths.get(magazineFile);
        try {
            lines = fileReader.readFile(magazineFile);
            List<String> modifiedLines = new ArrayList<>();
            List<String> strings = List.of("id,author_name,price,pageCount,front_of_book_content,type");
            modifiedLines.addAll(strings);
            modifiedLines.addAll(lines.stream().filter(line -> !line.startsWith(id + ",")).toList());
            Files.write(path, modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
