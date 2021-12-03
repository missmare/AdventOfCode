package mama.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DriveSubmarine {

    private final int[] position = new int[2];
    private int aim = 0;
    private ArrayList<Drive> driveInstructions = new ArrayList<>();

    private void changePosition(Drive drive) {
        switch (drive.getDirection()) {
            case UP:
                position[0] = position[0] - drive.getHowFar();
                break;
            case DOWN:
                position[0] = position[0] + drive.getHowFar();
                break;
            case FORWARD:
                position[1] = position[1] + drive.getHowFar();
                break;
            default:
                throw new UnsupportedOperationException("Unsupported direction");
        }
    }

    private void changePositionOfAim(Drive drive) {
        switch (drive.direction) {
            case UP:
                aim = aim - drive.getHowFar();
                break;
            case DOWN:
                aim = aim + drive.getHowFar();
                break;
            case FORWARD:
                position[1] = position[1] + drive.getHowFar();
                position[0] = position[0] + drive.getHowFar() * aim;
                break;
            default:
                throw new UnsupportedOperationException("Unsupported direction");
        }
    }

    private void readDirections(String directionPath) {
        List<String> lines;
        int howFar;
        Direction direction;
        for (String positionAndUnit : FileReader.readFile(directionPath)) {
            String[] drive = positionAndUnit.split(" ");
            howFar = Integer.parseInt(drive[1]);
            direction = Direction.valueOf(drive[0].toUpperCase());
            driveInstructions.add(new Drive(direction, howFar));
        }
        System.out.println("Drive Instructions: " + driveInstructions.size());
        //System.out.println(driveInstructions.stream().collect(Collectors.toList()));
    }

    public int[] drive(String pathToDirections) {
        readDirections(pathToDirections);

        try {
            for (Drive direction : driveInstructions) {
                changePosition(direction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }

    public int[] driveAim(String pathToDirections) {
        readDirections(pathToDirections);
        try {
            for (Drive direction : driveInstructions) {
                changePositionOfAim(direction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }

    public void printCurrentPosition() {
        System.out.println("Depth: " + position[0] + ", Horizontal: " + position[1]);
    }

    public int multiplyCurrentPosition() {
        System.out.println("Multiply Depth and horizontal position: " + (position[0] * position[1]));
        return position[0] * position[1];
    }

    private enum Direction {
        FORWARD("+"),
        UP("-"),
        DOWN("1");
        private String operation;

        Direction(String operation) {
            this.operation = operation;
        }
    }

    private class Drive {
        private Direction direction;
        private int howFar;

        Drive(Direction direction, int unit) {
            this.direction = direction;
            this.howFar = unit;
        }

        public int getHowFar() {
            return howFar;
        }

        public Direction getDirection() {
            return direction;
        }

        public String toString() {
            return direction.toString() + ": " + howFar;
        }
    }
}
