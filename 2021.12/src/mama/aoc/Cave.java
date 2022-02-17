package mama.aoc;

import java.util.*;

public class Cave {

    public static final String END = "end";
    public static final String START = "start";
    private final Map<String, List<String>> cave = new HashMap<>();
    private final List<List<String>> possiblePaths = new ArrayList<>();
    private List<List<String>> startedPaths = new ArrayList<>();


    public int countNumberOfPaths(String path) {
        List<String> strings = FileReader.readFile(path);
        extractCave(strings);
        startPossiblePaths(true);
        return possiblePaths.size();
    }

    public int countSecondPaths(String path) {
        List<String> strings = FileReader.readFile(path);
        extractCave(strings);
        startPossiblePaths(false);
        return possiblePaths.size();
    }

    private void extractCave(List<String> strings) {
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
    }

    private void startPossiblePaths(boolean isFirstPuzzle) {
        List<String> adjacentCaves = cave.get(START);
        for (String adjacentCave : adjacentCaves) {
            List<String> possiblePath = new ArrayList<>();
            possiblePath.add(START);
            possiblePath.add(adjacentCave);
            startedPaths.add(possiblePath);
        }
        calculatePaths(isFirstPuzzle);
    }

    private void calculatePaths(boolean isFirstPuzzle) {
        List<List<String>> currentPaths = new ArrayList<>();
        for (List<String> path : startedPaths) {
            String lastElement = path.get(path.size() - 1);
            List<String> adjacentCaves = cave.get(lastElement);
            for (String adjacentCave : adjacentCaves) {
                //check rule
                if (adjacentCave.equals(END)) {
                    List<String> endPath = new ArrayList<>(path);
                    endPath.add(adjacentCave);
                    possiblePaths.add(endPath);
                } else if (isAllowed(path, adjacentCave, isFirstPuzzle)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(adjacentCave);
                    currentPaths.add(newPath);
                }
            }
        }
        startedPaths = currentPaths;
        if (!startedPaths.isEmpty()) {
            calculatePaths(isFirstPuzzle);
        }
    }

    private boolean isAllowed(List<String> path, String adjacentCave, boolean isFirstPuzzle) {
        boolean allowFirstPuzzle = (isSmallCave(adjacentCave) && !path.contains(adjacentCave))
                || isBigCave(adjacentCave);

        Map<String, Integer> smallCavesVisited = new HashMap<>();
        cave.keySet().stream()
                .filter(this::isSmallCave)
                .forEach(possibleCave -> smallCavesVisited.putIfAbsent(possibleCave, 0));
        path.stream().filter(this::isSmallCave)
                .forEach(visitedCave -> smallCavesVisited.put(visitedCave,  smallCavesVisited.get(visitedCave)+1));
        boolean smallCavesVisitedTwice = smallCavesVisited.containsValue(2);

        boolean alreadySmallTwice = (isSmallCave(adjacentCave) && smallCavesVisitedTwice && !path.contains(adjacentCave));
        boolean noSmallTwice = (isSmallCave(adjacentCave) && !smallCavesVisitedTwice);
        boolean allowSecondPuzzle = isBigCave(adjacentCave) || alreadySmallTwice || noSmallTwice;

        return isFirstPuzzle ? allowFirstPuzzle : allowSecondPuzzle;
    }

    private boolean isSmallCave(String cave) {
        return !cave.equals(START) && cave.equals(cave.toLowerCase());
    }

    private boolean isBigCave(String cave) {
        return cave.equals(cave.toUpperCase());
    }

}
