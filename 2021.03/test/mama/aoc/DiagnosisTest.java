package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiagnosisTest {

    @Test
    public void checkDiagnosisSmall() {
        Diagnosis submarine = new Diagnosis();
        int i = submarine.diagnosis("/home/manuela/projects/AdventOfCode/2021.03/test/mama/aoc/DiagnosisSmall.txt");
        assertEquals(198, i, "Multiplication of gamma and epsilon diagnosis");
    }

    @Test
    public void checkDiagnosisBig() {
        Diagnosis submarine = new Diagnosis();
        int i = submarine.diagnosis("/home/manuela/projects/AdventOfCode/2021.03/test/mama/aoc/DiagnosisBig.txt");
        System.out.println("Big diagnosis: " + i);
        assertTrue(i > 0, "Multiplication of gamma and epsilon diagnosis for big diagnosis");
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