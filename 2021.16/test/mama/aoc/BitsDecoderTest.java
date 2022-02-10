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
    String smallPath5 = "/home/manuela/projects/AdventOfCode/2021.16/test/mama/aoc/SmallTransmission5.txt";
    String bigPath = "/home/manuela/projects/AdventOfCode/2021.16/test/mama/aoc/BigTransmission.txt";

    /**
     * Input:   D2FE28
     * Binary:  110100101111111000101000
     * Version: 6
     * Type:    4
     * Literal: 10111 11110 00101 000 --> 011111100101 = 2021
     */
    @Test
    public void testVersionNumbers1a() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath1a);
        assertEquals(6, totalVersionNumber);
    }

    /**
     * Input:   38006F45291200
     * Binary:  00111000000000000110111101000101001010010001001000000000
     * Version: 1
     * Type:    6
     * Read Operator 0 (length of result is written in 15  bits)
     *   length:    27
     *   binary:    A: 11010001010 B: 0101001000100100
     *              A: v6, t4, l10; B: v2, t4, l20
     */
    @Test
    public void testVersionNumbers1b() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath1b);
        assertEquals(9, totalVersionNumber);
    }

    /**
     * Input:   EE00D40C823060
     * Binary:  11101110000000001101010000001100100000100011000001100000
     * Version: 7
     * type:    3
     * Read operator 1 (number of packets is written in 11 bits)
     *   packets:   3
     *   binary:    A: 01010000001 B: 10010000010 C: 00110000011 00000
     *              A: v2, t4, l1  B: v4, t4, l2  C: v1, t4, l3
     */
    @Test
    public void testVersionNumbers1c() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath1c);
        assertEquals(14, totalVersionNumber);
    }

    /**
     * input:   8A004A801A8002F478
     * binary:  100010100000000001001010100000000001101010000000000000101111010001111000
     * version: 4
     * type:    2
     * Read operator 1 (1 subpackage)
     *   binary:    A: 001 010 1 00000000001 101010000000000000101111010001111000
     *   Read operator 1 (1 subpackage)
     *     binary:      B: 101 010 0 000000000001011 11010001111000
     *     Read operator 0 (read 15 bits for length: subpackages are 11 bits long)
     *       binary:    11010001111
     *       version:   6
     *       type:      4 (literal)
     *       literal:   01111 --> 1111 = 15
     *
     */
    @Test
    public void testVersionNumbers2() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath2);
        assertEquals(16, totalVersionNumber);
    }

    /**
     * Input:   620080001611562C8802118E34
     * binary:  011 000 1 00000000010 00000000000000000101100001000101010110001011001000100000000010000100011000111000110100
     * Version: 3
     * Type:    0
     * Read Operator 1 (# of subpackages in 11 bits: 00000000010 = 2)
     *   binary:    000 000 0 000000000010110 0001000101010110001011001000100000000010000100011000111000110100
     *   version:   0
     *   type:      0
     *   read operator 0 (length of subpackages in 15 bits: 000000000010110 = 22)
     *     binary: 000 100 01010 101 100 01011
     *     version: 0
     *     type:    4
     *     literal: 10
     *     ---
     *     version: 5
     *     type:    4
     *     literal: 11
     *   binary: 001 000 1 00000000010 000100011000111000110100
     *   version:   1
     *   type:      0
     *   read operator 1(# of subpackages in 11 bits:  00000000010 = 2)
     *     binary: 000 100 01100 011 100 01101 00
     *     version: 0
     *     type:    4
     *     literal: 12
     *     ---
     *     version: 3
     *     type:    4
     *     literal: 13
     * remaining: 2 bit
     */
    @Test
    public void testVersionNumbers3() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath3);
        assertEquals(12, totalVersionNumber);
    }

    /**
     * Input:   C0015000016115A2E0802F182340
     * binary:  1100000000000001010100000000000000000001011000010001010110100010111000001000000000101111000110000010001101000000
     * version: 6
     * type:    0
     * Read operator 0 (length of subpackages in 15 bit: 000000001010100 = 84)
     *   binary: 000 000 0 000000000010110 0001000101011010001011 1000001000000000101111000110000010001101
     *   version:   0
     *   type:      0
     *   Read Operator 0 (length of subpackages in 15 bit: 000000000010110 = 22)
     *     binary: 000 100 01010 110 100 01011
     *     version: 0
     *     type:    4
     *     literal: 10
     *     ---
     *     version: 6
     *     type:    4
     *     literal: 11
     *   binary: 100 000 1 00000000010 1111000110000010001101
     *   version: 4
     *   type: 0
     *   read operator 1 (# of subpackages in 11 bit: 00000000010 = 2)
     *     binary:  111 100 01100 000 100 01101
     *     version: 7
     *     type:    4
     *     literal: 12
     *     ---
     *     version: 0
     *     type:    4
     *     literal: 13
     */
    @Test
    public void testVersionNumbers4() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath4);
        assertEquals(23, totalVersionNumber);
    }

    /**
     * input:   A0016C880162017C3686B18A3D4780
     * binary:  101 000 0 000000001011011 00100010000000000101100010000000010111110000110110100001101011000110001010001111010100011110000000
     * version: 5
     * type:    0
     * read operator 0 (length of subpackages in 15 bit: 000000001011011 = 91)
     *   binary: 001 000 1 00000000001 0110001000000001011111000011011010000110101100011000101000111101010001111
     *   version:   1
     *   type:      0
     *   read operator 1 (# of subpackages in 11 bit: 00000000001 = 1)
     *     binary: 011 000 1 00000000101 1111000011011010000110101100011000101000111101010001111
     *     version: 3
     *     type:    0
     *     read operator 1 (# of subpackages in 11 bit: 00000000101 = 5)
     *       binary: sp: 111 100 00110 sp: 110 100 00110 sp: 101 100 01100 sp: 010 100 01111 sp: 010 100 01111
     *       version:   7
     *       type:      4
     *       literal:   6
     *       ---
     *       version:   6
     *       type:      4
     *       literal:   6
     *       ---
     *       version:   5
     *       type:      4
     *       literal:   12
     *       ---
     *       version:   2
     *       type:      4
     *       literal:   15
     *       ---
     *       version:   2
     *       type:      4
     *       literal:   15
     * */
    @Test
    public void testVersionNumbers5() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(smallPath5);
        assertEquals(31, totalVersionNumber);
    }


    @Test
    public void testVersionNumbersBig() {
        BitsDecoder submarine = new BitsDecoder();
        int totalVersionNumber = submarine.getVersionNumbers(bigPath);
        assertEquals(960, totalVersionNumber);
    }


    @Test
    public void testNumberConversion() {
        long i = Long.parseLong("010010110100101101100110101100111111", 2);
        assertEquals(20211723071L, i);
    }

}