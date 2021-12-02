package mama.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Submarine {

    public int measureIncrease(int[] sonar) {
        int increases = 0;
        for (int i = 1; i < sonar.length; i++) {
            if (sonar[i] > sonar[i - 1]) {
                increases++;
            }
        }
        return increases;
    }

    public int measureWindowIncrease(int[] sonar) {
        int[] windows = createWindows(sonar);
        return measureIncrease(windows);
    }

    private int[] createWindows(int[] sonar) {
        int[] windows = new int[sonar.length - 2];
        for (int i = 0; i < sonar.length - 2; i++) {
            windows[i] = sonar[i] + sonar[i + 1] + sonar[i + 2];
        }
        return windows;
    }

    public int[] readSonar(String pathToFile) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] sonar = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            sonar[i] = Integer.parseInt(line);
        }
        return sonar;
    }
}
