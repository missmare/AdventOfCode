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

        initializeArrays(fullDiagnosis);

        //count 0 and 1 at each position
        for (String diagnosis : fullDiagnosis) {
//            System.out.println("Binary diagnosis: " + diagnosis);
            for (int i = 0; i < diagnosis.length(); i++) {
                countZeroesAndOnes(diagnosis, i);
            }
        }

        calculateGammaAndEpsilon(numberOfOnes.size());
        int gammaDec = Integer.parseInt(gamma.toString(), 2);
        int epsilonDec = Integer.parseInt(epsilon.toString(), 2);

        return gammaDec * epsilonDec;
    }

    public int lifeSupportRating(String pathToDiagnosis) {
        List<String> fullDiagnosis = FileReader.readFile(pathToDiagnosis);
        initializeArrays(fullDiagnosis);

        //Oxygen rating
        String finalRating = oxygenRating(fullDiagnosis, 0);
        System.out.println("Oxygen: Binaer: " + finalRating);
        int oxygen = Integer.parseInt(finalRating, 2);
        System.out.println("Oxygen Dezimal: " + oxygen);
        System.out.println("");

        initializeArrays(fullDiagnosis);
        String finalCo2Rating = co2Rating(fullDiagnosis, 0);
        System.out.println("Co2: Binaer: " + finalCo2Rating);
        int co2 = Integer.parseInt(finalCo2Rating, 2);
        System.out.println("Co2 Dezimal: " + co2);

        System.out.println("\nLife Support Rating: " + oxygen * co2);
        return oxygen * co2;
    }

    private void initializeArrays(List<String> fullDiagnosis) {
        //initialize arrays to count with 0
        int lengthOfDiagnosis = fullDiagnosis.stream().findAny().get().length();
        numberOfZeroes = new ArrayList<>(lengthOfDiagnosis);
        numberOfOnes = new ArrayList<>(lengthOfDiagnosis);
        for (int i = 0; i < lengthOfDiagnosis; i++) {
            numberOfZeroes.add(0);
            numberOfOnes.add(0);
        }
    }

    private void countZeroesAndOnes(String diagnosis, int position) {
        int count;
        switch (diagnosis.charAt(position)) {
            case '0':
                count = numberOfZeroes.get(position);
                numberOfZeroes.set(position, ++count);
                break;
            case '1':
                count = numberOfOnes.get(position);
                numberOfOnes.set(position, ++count);
                break;
            default:
                System.out.println(diagnosis.charAt(position) + " results in default case");
        }
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
        }
        return 0;
    }

    private String oxygenRating(List<String> fullDiagnosis, int position) {
        for (String diagnosis :
                fullDiagnosis) {
            countZeroesAndOnes(diagnosis, position);
        }
//        System.out.println("0: " + numberOfZeroes);
//        System.out.println("1: " + numberOfOnes);

        List<String> reducedList;
        if (numberOfZeroes.get(position) > numberOfOnes.get(position)) { // there are more zeroes, keep zeroes
            reducedList = keepNumbersWithBitAtPosition(fullDiagnosis, position, '0');
        } else {
            reducedList = keepNumbersWithBitAtPosition(fullDiagnosis, position, '1');
        }

        if (reducedList.size() > 1) {
            return oxygenRating(reducedList, ++position);
        } else {
            return reducedList.get(0);
        }
    }

    private String co2Rating(List<String> fullDiagnosis, int position) {
        for (String diagnosis :
                fullDiagnosis) {
            countZeroesAndOnes(diagnosis, position);
        }
//        System.out.println("0: " + numberOfZeroes);
//        System.out.println("1: " + numberOfOnes);

        List<String> reducedList;
        if (numberOfZeroes.get(position) > numberOfOnes.get(position)) { // there are less ones, keep ones
            reducedList = keepNumbersWithBitAtPosition(fullDiagnosis, position, '1');
        } else {
            reducedList = keepNumbersWithBitAtPosition(fullDiagnosis, position, '0');
        }

        if (reducedList.size() > 1) {
            return co2Rating(reducedList, ++position);
        } else {
            return reducedList.get(0);
        }
    }

    private List<String> keepNumbersWithBitAtPosition(List<String> fullDiagnosis, int position, char keepBit) {
        List<String> reducedDiagnosis = new ArrayList<>();
        for (String diagnosis : fullDiagnosis) {
            if (diagnosis.charAt(position) == keepBit) {
                reducedDiagnosis.add(diagnosis);
            }
        }
//        System.out.println("Liste für Position " + position + " hat " + reducedDiagnosis.size() + " Elemente. Behalte: " + keepBit);
        return reducedDiagnosis;
    }


}
