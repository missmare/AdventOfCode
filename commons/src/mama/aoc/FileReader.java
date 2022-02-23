package mama.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

    /**
     * Read file, line by line.
     *
     * @param path Path to file
     * @return a list of Strings, an item per line
     */
    public static List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Failed to read file: " + path);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read a file, assume that the list is now separated by comma (,) on a single line.
     * Split the line by commas and return a list of the items.
     *
     * @param path path to file with a single line-list, separated by commas
     * @return a list of items
     */
    public static List<String> readListOnSingleLineFromFile(String path) {
        List<String> singleLine = FileReader.readFile(path);
        if (singleLine != null && singleLine.size() == 1) {
            String content = singleLine.get(0);
            return StringHelper.splitAndTrim(content);
        } else {
            return singleLine;
        }
    }

    public static String readSingleLine(String path) {
        List<String> singleLine = FileReader.readFile(path);
        if (singleLine != null && singleLine.size() == 1) {
            return singleLine.get(0);
        } else {
            return null;
        }
    }

}
