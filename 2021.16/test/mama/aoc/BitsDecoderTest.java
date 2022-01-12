package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitsDecoderTest {

    String smallPath1a = "/home/manuela/projects/AdventOfCode/2021.16/test/mama/aoc/SmallTransmission1a.txt";
    String smallPath1b = "/home/manuela/projects/AdventOfCode/2021.16/test/mama/aoc/SmallTransmission1b.txt";
    String smallPath1c = "/home/manuela/projects/AdventOfCode/2021.16/test/mama/aoc/SmallTransmission1c.txt";
    String smallPath2 = "/home/manuela/projects/AdventOfCode/2021.16/test/mama/aoc/SmallTransmission2.txt";
    String smallPath3 = "/home/manuela/projects/AdventOfCode/2021.16/test/mama/aoc/SmallTransmission3.txt";
    String smallPath4 = "/home/manuela/projects/AdventOfCode/2021.16/test/mama/aoc/SmallTransmission4.txt";

    @Test
    public void testVersionNumbers1a() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath1a);
        assertEquals(6, totalVersionNumber);
    }

    @Test
    public void testVersionNumbers1b() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath1b);
        assertEquals(9, totalVersionNumber);
    }

    @Test
    public void testVersionNumbers1c() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath1c);
        assertEquals(14, totalVersionNumber);
    }

    @Test
    public void testVersionNumbers2() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath2);
        assertEquals(16, totalVersionNumber);
    }

    @Test
    public void testVersionNumbers3() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath3);
        assertEquals(16, totalVersionNumber);
    }

    @Test
    public void testVersionNumbers4() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath4);
        assertEquals(16, totalVersionNumber);
    }

    @Test
    public void testVersionNumbersBig() {
        BitsDecoder submarine = new BitsDecoder();
    }


}