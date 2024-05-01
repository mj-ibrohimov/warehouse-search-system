package warehouse.dao;

import warehouse.config.CustomFileReader;
import warehouse.domains.Book;
import warehouse.domains.MessageHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements BaseDao<Book> {
    private final String bookFile = "src/main/resources/book.csv";

    private final CustomFileReader fileReader = new CustomFileReader();

    @Override
    public List<Book> findAll() throws IOException {
        return readBookFile();
    }

    public List<Book> readBookFile() throws IOException {
        List<Book> book = new ArrayList<>();
        List<String> strings = fileReader.readFile(bookFile);
        strings.forEach(s -> book.add(toBook(s)));
        return book;
    }


    private Book toBook(String line) {
        String[] strings = line.split(",");
        return Book.childBuilder()
                .id(Long.valueOf(strings[0]))
                .author_name((strings[1]))
                .price(Double.valueOf(strings[2]))
                .pageCount(Integer.valueOf(strings[3]))
                .color((strings[4]))
                .width(Double.valueOf(strings[5]))
                .height(Double.valueOf(strings[6]))
                .build();
    }

    public void saveData(Book book) {
        try {
            try (FileWriter fileWriter = new FileWriter(bookFile, true)) {
                fileWriter.write(book.getId() + "," + book.getAuthor_name() + "," +
                        book.getPrice() + "," + book.getPageCount() + "," + book.getColor() + "," + book.getWidth() + "," + book.getHeight() + "\n");
            }
            System.out.println(MessageHelper.BOOK_SAVED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeBook(Long id) {
        List<String> lines = null;
        Path path = Paths.get(bookFile);
        try {
            lines = fileReader.readFile(bookFile);
            List<String> modifiedLines = new ArrayList<>();
            List<String> strings = List.of("id,author_name,price,pageCount,color,width,height");
            modifiedLines.addAll(strings);
            modifiedLines.addAll(lines.stream().filter(line -> !line.startsWith(id + ",")).toList());
            Files.write(path, modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
