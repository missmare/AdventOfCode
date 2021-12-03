package mama.aoc;

import java.util.ArrayList;
import java.util.List;

public class Diagnosis {

    private ArrayList<Integer> numberOfZeroes = new ArrayList<>();
    private ArrayList<Integer> numberOfOnes = new ArrayList<>();
    private StringBuilder gamma = new StringBuilder();
    private StringBuilder epsilon = new StringBuilder();

    public int diagnosis(String pathToDiagnosis) {
        List<String> fullDiagnosis = FileReader.readFile(pathToDiagnosis);

        //initialize arrays to count with 0
        int lengthOfDiagnosis = fullDiagnosis.stream().findAny().get().length();
        for (int i = 0; i < lengthOfDiagnosis; i++) {
            numberOfZeroes.add(0);
            numberOfOnes.add(0);
        }

        for (String diagnosis : fullDiagnosis) {
//            System.out.println("Binary diagnosis: " + diagnosis);
            countZeroesAndOnes(diagnosis);
        }

        calculateGammaAndEpsilon(lengthOfDiagnosis);
        int gammaDec = Integer.parseInt(gamma.toString(), 2);
        int epsilonDec = Integer.parseInt(epsilon.toString(), 2);

        return gammaDec * epsilonDec;
    }

    private void countZeroesAndOnes(String diagnosis) {
        for (int i = 0; i < diagnosis.length(); i++) {
            switch (diagnosis.charAt(i)) {
                case '0':
                    int count = numberOfZeroes.get(i);
                    numberOfZeroes.set(i, ++count);
                    break;
                case '1':
                    Integer integer = numberOfOnes.get(i);
                    integer++;
                    numberOfOnes.set(i, integer);
                    break;
                default:
                    System.out.println(diagnosis.charAt(i) + " results in default case");
            }
        }
//        System.out.println("Zeroes: " + numberOfZeroes);
//        System.out.println("Ones: " + numberOfOnes);
    }

    private int calculateGammaAndEpsilon(int lengthOfInput) {
        for (int i = 0; i < lengthOfInput; i++) {
            if (this.numberOfZeroes.get(i) < this.numberOfOnes.get(i)) { //there are more 0es at posiiton i
                this.gamma.append(0);
                this.epsilon.append(1);
            } else { // there are more 1es at position i
                this.gamma.append(1);
                this.epsilon.append(0);
            }
//            System.out.println("Gamma: " + gamma.toString());
//            System.out.println("Epsilon: "+ epsilon.toString());
        }
        return 0;
    }
}
