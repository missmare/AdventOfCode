package mama.aoc;

import java.util.*;

public class Cave {

    private Map<String, List<String>> cave = new HashMap<>();
    private List<List<String>> startedPaths = new ArrayList<>();
    private List<List<String>> possiblePaths = new ArrayList<>();


    public int countNumberOfPaths(String path) {
        List<String> strings = FileReader.readFile(path);
        for (String possiblePath : strings) {
            String[] split = possiblePath.split("-");
            if (!cave.containsKey(split[0])) {
                cave.put(split[0], new ArrayList<>());
            }
            if (!cave.containsKey(split[1])) {
                cave.put(split[1], new ArrayList<>());
            }
            cave.get(split[0]).add(split[1]);
            cave.get(split[1]).add(split[0]);
        }

        startPossiblePaths();
        return possiblePaths.size();
    }

    private void startPossiblePaths() {
        List<String> adjacentCaves = cave.get("start");
        for (String adjacentCave : adjacentCaves) {
            List<String> possiblePath = new ArrayList<>();
            possiblePath.add("start");
            possiblePath.add(adjacentCave);
            startedPaths.add(possiblePath);
        }
        calculatePaths();
    }

    private void calculatePaths() {
        List<List<String>> currentPaths = new ArrayList<>();
        for (List<String> path : startedPaths) {
            String lastElement = path.get(path.size() - 1);
            List<String> adjacentCaves = cave.get(lastElement);
            for (String adjacentCave : adjacentCaves) {
                //check rule
                if (adjacentCave.equals("end")) {
                    possiblePaths.add(path);
                } else if (isAllowed(path, adjacentCave)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(adjacentCave);
                    currentPaths.add(newPath);
                }
            }
        }
        startedPaths = currentPaths;
        if (!startedPaths.isEmpty()) {
            calculatePaths();
        }
    }

    private boolean isAllowed(List<String> path, String adjacentCave) {
        return (adjacentCave.equals(adjacentCave.toLowerCase()) && !path.contains(adjacentCave))
                || adjacentCave.equals(adjacentCave.toUpperCase());

    }


}
