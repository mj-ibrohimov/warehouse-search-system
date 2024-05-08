package warehouse.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CustomFileReader {
    public List<String> readFile(String path) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get(path));
        return br.lines().skip(1).toList();
    }

}
