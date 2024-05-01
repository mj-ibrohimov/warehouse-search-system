package warehouse.dao;

import warehouse.config.CustomFileReader;
import warehouse.domains.Book;
import warehouse.domains.MessageHelper;
import warehouse.domains.Poster;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PosterDao implements BaseDao<Poster> {
    private final String posterFile = "src/main/resources/poster.csv";

    private final CustomFileReader fileReader = new CustomFileReader();

    @Override
    public List<Poster> findAll() throws IOException {
        return readPosterFile();
    }

    public List<Poster> readPosterFile() throws IOException {
        List<Poster> posters = new ArrayList<>();
        List<String> strings = fileReader.readFile(posterFile);
        strings.forEach(s -> posters.add(toPoster(s)));
        return posters;
    }

    private Poster toPoster(String line) {
        String[] strings = line.split(",");
        return Poster.childBuilder()
                .id(Long.valueOf(strings[0]))
                .author_name(String.valueOf(strings[1]))
                .price(Double.valueOf(strings[2]))
                .pageCount(Integer.valueOf(strings[3]))
                .title(String.valueOf(strings[4]))
                .length(Integer.valueOf(strings[5]))
                .build();
    }

    public void saveData(Poster poster) {
        try {
            try (FileWriter fileWriter = new FileWriter(posterFile, true)) {
                fileWriter.write(poster.getId() + "," + poster.getAuthor_name() + "," +
                        poster.getPrice() + "," + poster.getPageCount() + "," + poster.getTitle() + "," + poster.getLength() + "\n");
            }
            System.out.println(MessageHelper.POSTER_SAVED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removePoster(Long id) {
        List<String> lines = null;
        Path path = Paths.get(posterFile);
        try {
            lines = fileReader.readFile(posterFile);
            List<String> modifiedLines = new ArrayList<>();
            List<String> strings = List.of("id,author_name,price,pageCount,title,length");
            modifiedLines.addAll(strings);
            modifiedLines.addAll(lines.stream().filter(line -> !line.startsWith(id + ",")).toList());
            Files.write(path, modifiedLines);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
