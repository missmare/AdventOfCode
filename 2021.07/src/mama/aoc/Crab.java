package mama.aoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crab {
    public int getMinimumFuelForPositions(String positions) {
        List<Integer> horizontalNumbers = HelperToManipulateStrings.convertToIntegerList(FileReader.readListOnSingleLineFromFile(positions));

        List<Integer> fuelToPosition = new ArrayList<>();
        int maxPosition = Collections.max(horizontalNumbers);
        for (int i = 0; i < maxPosition; i++) {
            fuelToPosition.add(calculateFuelForPosition(horizontalNumbers, i));
        }
        int minFuel = Collections.min(fuelToPosition);
        int minPosition = searchMinimumPosition(fuelToPosition, minFuel);

        System.out.println(fuelToPosition);
        System.out.println("Minimum fuel needed: " + minFuel + ". this is reached at position: " + minPosition);

        return minFuel;
    }

    public int getMinimalFuelForCrabEngines(String positions) {
        List<Integer> horizontalNumbers = HelperToManipulateStrings.convertToIntegerList(FileReader.readListOnSingleLineFromFile(positions));

        List<Integer> fuelToPosition = new ArrayList<>();
        int maxPosition = Collections.max(horizontalNumbers);
        for (int i = 0; i < maxPosition; i++) {
            fuelToPosition.add(calculateFuelForCrabEngineToPosition(horizontalNumbers, i));
        }
        int minFuel = Collections.min(fuelToPosition);
        int minPosition = searchMinimumPosition(fuelToPosition, minFuel);

        System.out.println(fuelToPosition);
        System.out.println("Minimum fuel needed: " + minFuel + ". this is reached at position: " + minPosition);

        return minFuel;
    }

    public void testSmallEngine() {
        System.out.println("position 16 to 5");
        int i = calculateFuelForCrabEngineFromTo(16, 5);
        System.out.println(i);
    }

    private int calculateFuelForCrabEngineToPosition(List<Integer> horizontalPositions, int holePosition) {
        int sumOfFuelForPosition = 0;
        for (int crab : horizontalPositions) {
            sumOfFuelForPosition += calculateFuelForCrabEngineFromTo(crab, holePosition);
        }
        return sumOfFuelForPosition;
    }

    private int calculateFuelForCrabEngineFromTo(int currentPosition, int holePosition) {
        int difference = Math.abs(currentPosition - holePosition);
        int fuel = 0;
        for (int i = 1; i < difference+1; i++) {
            fuel += i;
        }
//        System.out.println("Use " + fuel + " fuel to get from position " + currentPosition + " to position " + holePosition + ".");
        return fuel;
    }

    private int calculateFuelForPosition(List<Integer> horizontalPositions, int holePosition) {
        int fuel = 0;
        for (int crabPosition : horizontalPositions) {
            fuel += Math.abs(crabPosition - holePosition);
//            System.out.println("Fuel: to get from " + crabPosition + " to position " + holePosition + ", " + fuel);
        }
//        System.out.println("Fuel to position " + holePosition + ": " + fuel);
        return fuel;
    }

    private int searchMinimumPosition(List<Integer> fuelNeeded, int minimumFuel) {
        for (int i = 0; i < fuelNeeded.size(); i++) {
            if (fuelNeeded.get(i) == minimumFuel) {
                return i;
            }
        }
        return 0;
    }
}
