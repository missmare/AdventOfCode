package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriveSubmarineTest {

    @Test
    public void driveSubmarine() {
        System.out.println("\nDrive Submarine TEST");
        DriveSubmarine submarine = new DriveSubmarine();
        String path = "/home/manuela/projects/AdventOfCode/2021.02/test/mama/aoc/SmallDirections.txt";
        submarine.drive(path);
        submarine.printCurrentPosition();
        int i = submarine.multiplyCurrentPosition();
        assertTrue(i>0, "submarine is somewhere in the water");
    }

    @Test
    public void driveSubmarineAim() {
        System.out.println("\nDrive with an aim a submarine TEST");
        DriveSubmarine submarine = new DriveSubmarine();
        String path = "/home/manuela/projects/AdventOfCode/2021.02/test/mama/aoc/SmallDirections.txt";
        submarine.driveAim(path);
        submarine.printCurrentPosition();
        int i = submarine.multiplyCurrentPosition();
        assertTrue(i>0, "submarine is somewhere in the water");
    }

    @Test
    public void driveBigSubmarine() {
        System.out.println("\nBIG PUZZLE Drive Submarine");
        DriveSubmarine submarine = new DriveSubmarine();
        String path = "/home/manuela/projects/AdventOfCode/2021.02/test/mama/aoc/BigDirections.txt";
        submarine.drive(path);
        submarine.printCurrentPosition();
        int i = submarine.multiplyCurrentPosition();
        assertTrue(i>0, "depth*horizontal position should be bigger than 0");
    }

    @Test
    public void driveBigSubmarineAim() {
        System.out.println("\nBIG PUZZLE Drive with an aim a submarine");
        DriveSubmarine submarine = new DriveSubmarine();
        String path = "/home/manuela/projects/AdventOfCode/2021.02/test/mama/aoc/BigDirections.txt";
        submarine.driveAim(path);
        submarine.printCurrentPosition();
        int i = submarine.multiplyCurrentPosition();
        assertTrue(i>0, "submarine is somewhere in the water");
    }

}