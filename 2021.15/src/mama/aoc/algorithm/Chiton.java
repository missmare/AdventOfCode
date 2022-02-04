package mama.aoc.algorithm;

import mama.aoc.FileReader;
import mama.aoc.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class Chiton {

    public long calculateLowestRiskPath(String path) {
        List<String> strings = FileReader.readFile(path);
        List<List<Integer>> chitonList = StringHelper.convertToNumberListOfLists(strings);

        //destination
        int maxX = chitonList.size() - 1;
        int maxY = chitonList.get(maxX).size() - 1;

        int step = 0;

        List<RiskPath> possiblePaths = new ArrayList<>();
        RiskPath currentPath = new RiskPath();
        possiblePaths.add(currentPath);
        while (!currentPath.isAtPosition(maxX, maxY) && !possiblePaths.isEmpty()) {
            currentPath = possiblePaths.remove(0);
//            System.out.println("Step " + step++ + " choose first of "+ possiblePaths.size() +  " elements. From risk " + currentPath.getTotalRisk() + " at Position " + currentPath.getCurrentPosition());

            if (currentPath.isAtPosition(maxX, maxY)) {
                return currentPath.getTotalRisk();
            }

            //check all positions
            int currentX = currentPath.getCurrentPosition().getFirst();
            int currentY = currentPath.getCurrentPosition().getSecond();
/*            //up
            if (currentY - 1 >= 0 && !currentPath.cameFrom(currentX, currentY - 1)) {
                RiskPath newRisk = currentPath.moveTo(currentX, currentY - 1, chitonList);
                System.out.println("\tmove up to " + newRisk.getCurrentPosition() + " at risk " + newRisk.getTotalRisk());
                possiblePaths.add(newRisk);
            }
            //left
            if (currentX - 1 >= 0 && !currentPath.cameFrom(currentX - 1, currentY)) {
                RiskPath newRisk = currentPath.moveTo(currentX - 1, currentY, chitonList);
                System.out.println("\tmove left to " + newRisk.getCurrentPosition() + " at risk " + newRisk.getTotalRisk());
                possiblePaths.add(newRisk);
            }*/
            //down
            if (currentY + 1 <= maxY && !currentPath.cameFrom(currentX, currentY + 1)) {
                RiskPath newRisk = currentPath.moveTo(currentX, currentY + 1, chitonList);
//                System.out.println("\tmove down to " + newRisk.getCurrentPosition() + " at risk " + newRisk.getTotalRisk());
                possiblePaths.add(newRisk);
            }
            //right
            if (currentX + 1 <= maxX && !currentPath.cameFrom(currentX + 1, currentY)) {
                RiskPath newRisk = currentPath.moveTo(currentX + 1, currentY, chitonList);
//                System.out.println("\tmove right to " + newRisk.getCurrentPosition() + " at risk " + newRisk.getTotalRisk());
                possiblePaths.add(newRisk);
            }

            possiblePaths.sort((p1, p2) -> {
                if (p1.getTotalRisk() == p2.getTotalRisk()) {
                    return Long.compare(p1.getIterations(), p2.getIterations());
                } else {
                    return Long.compare(p1.getTotalRisk(), p2.getTotalRisk());
                }
            });
        }
        return 0L;
    }

}