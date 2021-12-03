package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiagnosisTest {

    private String smallDiagnosisPath = "/home/manuela/projects/AdventOfCode/2021.03/test/mama/aoc/DiagnosisSmall.txt";
    private String bigDiagnosisPath = "/home/manuela/projects/AdventOfCode/2021.03/test/mama/aoc/DiagnosisBig.txt";

    @Test
    public void checkDiagnosisSmall() {
        Diagnosis submarine = new Diagnosis();
        int i = submarine.diagnosis(smallDiagnosisPath);
        System.out.println("Small diagnosis: " + i);
        assertEquals(198, i, "Multiplication of gamma and epsilon diagnosis");
    }

    @Test
    public void checkDiagnosisBig() {
        Diagnosis submarine = new Diagnosis();
        int i = submarine.diagnosis(bigDiagnosisPath);
        System.out.println("Big diagnosis: " + i);
        assertTrue(i > 0, "Multiplication of gamma and epsilon diagnosis for big diagnosis");
    }

    @Test
    public void checkLifeSupportRatingSmall() {
        Diagnosis submarine = new Diagnosis();
        int i = submarine.lifeSupportRating(smallDiagnosisPath);
        assertEquals(230, i);
    }

    @Test
    public void checkLifeSupportRatingBig() {
        Diagnosis submarine = new Diagnosis();
        int i = submarine.lifeSupportRating(bigDiagnosisPath);
        assertTrue(i > 0, "Multiplication of oxygen and co2 rating for big diagnosis");
    }

    @Test
    public void checkBinaryNumbers() {
        String binaryNumber = "10110";
        int decimalNumber = 22;
        int i = Integer.parseInt(binaryNumber, 2);
        assertEquals(decimalNumber, i, "The binary number '" + binaryNumber + "' corresponds to '"
                + decimalNumber + "' in the decimal system");
    }

}