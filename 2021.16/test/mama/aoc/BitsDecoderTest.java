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
    public void testVersionNumbers1() {
        BitsDecoder submarine = new BitsDecoder();
        //submarine.getSumOfVersionNumbers("8A004A801A8002F478");

//        submarine.getSumOfVersionNumbers("D2FE28");
//        submarine.getSumOfVersionNumbers("EE00D40C823060");
//        submarine.getSumOfVersionNumbers("F210D40C823060");
        submarine.getVersionNumbers(smallPath4);
//        submarine.getVersionNumbers(smallPath2);

    }
    @Test
    public void testVersionNumbersBig() {
        BitsDecoder submarine = new BitsDecoder();
    }



}