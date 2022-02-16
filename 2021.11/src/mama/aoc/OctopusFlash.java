package mama.aoc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OctopusFlash {

    int maxX;
    int maxY;

    public int countFlashes(String path) {
        List<String> strings = FileReader.readFile(path);
        int[][] energyLevel = StringHelper.convertToIntegerArray(strings);

        maxX = energyLevel.length;
        maxY = energyLevel[0].length;

        int countFlashes = 0;
        for (int i = 0; i < 100; i++) {
            countFlashes += executeStep(energyLevel, true);
        }
        return countFlashes;
    }

    public int getNumberOfStepsForSimultaneousFlash(String path) {
        List<String> strings = FileReader.readFile(path);
        int[][] energyLevel = StringHelper.convertToIntegerArray(strings);

        maxX = energyLevel.length;
        maxY = energyLevel[0].length;

        int simultaneousFlash = maxX * maxY;
        int numberOfFlashes =0;
        int step = 0;
        while (numberOfFlashes != simultaneousFlash) {
            numberOfFlashes = executeStep(energyLevel, true);
            step++;
        }
        return step;
    }

    private int executeStep(int[][] energyLevel, boolean outerStep) {
        int numberOfFlashes = 0;

        Set<AdventPair<Integer, Integer>> flashedOctopusses = new HashSet<>();
        for (int i = 0; i < energyLevel.length; i++) {
            for (int j = 0; j < energyLevel[i].length; j++) {
                if (outerStep) {
                    energyLevel[i][j]++;
                }
                if (energyLevel[i][j] > 9) {
                    numberOfFlashes++;
                    flashedOctopusses.add(new AdventPair<>(i, j));
                    energyLevel[i][j] = 0;
                }
            }
        }

        if (!flashedOctopusses.isEmpty()) {
            increaseAdjacentEnergy(energyLevel, flashedOctopusses);
            numberOfFlashes += executeStep(energyLevel, false);
        }
        return numberOfFlashes;
    }

    private void increaseAdjacentEnergy(int[][] energyLevel, Set<AdventPair<Integer, Integer>> flashedOctopusses) {
        for (AdventPair<Integer, Integer> flash : flashedOctopusses) {
            int flashX = flash.getFirst();
            int flashY = flash.getSecond();
            //increase adjacents
            flashOctopus(energyLevel, flashX - 1, flashY);
            flashOctopus(energyLevel, flashX - 1, flashY - 1);
            flashOctopus(energyLevel, flashX - 1, flashY + 1);
            flashOctopus(energyLevel, flashX, flashY - 1);
            flashOctopus(energyLevel, flashX, flashY + 1);
            flashOctopus(energyLevel, flashX + 1, flashY);
            flashOctopus(energyLevel, flashX + 1, flashY - 1);
            flashOctopus(energyLevel, flashX + 1, flashY + 1);
        }
    }

    private void flashOctopus(int[][] energyLevel, int x, int y) {
        if (x >= 0 && x < maxX && y >= 0 && y < maxY && energyLevel[x][y] > 0) {
            energyLevel[x][y]++;
        }
    }
}
