package mama.aoc;

import java.util.ArrayList;
import java.util.List;

public class BitsDecoder {

    List<Integer> allVersions = new ArrayList<>();

    public int getVersionNumbers(String path) {
        List<String> strings = FileReader.readFile(path);
        if (strings.size() != 1) {
            System.out.println("check input");
        } else {
            return getSumOfVersionNumbers(strings.get(0));
        }
        return 0;
    }

    public long getLiteral(String path) {
        List<String> strings = FileReader.readFile(path);
        return getFinalLiteral(strings.get(0));
    }

    private int getSumOfVersionNumbers(String transmission) {
        StringBuilder binaryString = convertHexToBinary(transmission);
        getSinglePacket(binaryString.toString(), true);
        System.out.println("all version numbers: " + allVersions);

        int sumOfVersions = 0;
        for (int versionNumber : allVersions) {
            sumOfVersions += versionNumber;
        }

        System.out.println("Sum of all Versions: " + sumOfVersions);
        return sumOfVersions;
    }

    private long getFinalLiteral(String transmission) {
        StringBuilder binaryString = convertHexToBinary(transmission);
        BuoyancyPair pair = getSinglePacket(binaryString.toString(), true);
        System.out.println("Final literal: " + pair.getLiteral());
        return pair.getLiteral();
    }

    private StringBuilder convertHexToBinary(String transmission) {
        int pos = 0;
        System.out.println("Full Transmission: \t" + transmission);
        StringBuilder binary = new StringBuilder();
        while ((pos) < transmission.length()) {
            String partOfTransmission = transmission.substring(pos, pos + 1);
            int i = Integer.parseInt(partOfTransmission, 16);
            binary.append(appendStringToLength(Integer.toBinaryString(i), 4));
            pos++;
        }
        return binary;

    }

    public String appendStringToLength(String fullString, int length) {
        if (fullString.length() == length) {
            return fullString;
        }
        StringBuilder stringOfLength = new StringBuilder();
        stringOfLength.append(fullString);
        switch (fullString.length()) {
            case 4:
                break; // do nothing then
            case 3:
                stringOfLength.insert(0, '0');
                break;
            case 2:
                stringOfLength.insert(0, "00");
                break;
            case 1:
                stringOfLength.insert(0, "000");
                break;
            default:
                System.out.println("check input: " + fullString);
        }
        return stringOfLength.toString();
    }

    private BuoyancyPair getSinglePacket(String binaryString, boolean isOuterPackage) {
        int originalLength = binaryString.length();
        String version = binaryString.substring(0, 3);
        allVersions.add(Integer.parseInt(version, 2));

        System.out.println("Parsing string:\t\t" + binaryString);
        System.out.println("  Packet has version: \t" + version + "=" + Integer.parseInt(version, 2));
        String type = binaryString.substring(3, 6);
        System.out.println("  Type of packet is: \t" + type + "=" + Integer.parseInt(type, 2));

        binaryString = binaryString.substring(6);
        //get next packet
        if (Integer.parseInt(type, 2) == 4) {//literal
            int subPackageStart;
            int sizeOfPackages = 0;
            StringBuilder literalBinary = new StringBuilder();
            do {
                String subPackage = binaryString.substring(0, 5);
                subPackageStart = Integer.parseInt(subPackage.substring(0, 1));
                literalBinary.append(subPackage.substring(1));
                sizeOfPackages += 5;
                binaryString = binaryString.substring(5);
            }
            while (subPackageStart == 1);

            long literalLong = Long.parseLong(literalBinary.toString(), 2);
            System.out.println("\tLiteral: " + literalBinary + " as dec. number " + literalLong);

            if (isOuterPackage) {
                int remaining = (originalLength - sizeOfPackages - 6) % 4;
                binaryString = binaryString.substring(remaining); //TODO: das ist ziemlich sicher falsch
                System.out.println("  remaining number of digits: " + remaining + " \nresults in: " + binaryString);
                //recursive call if string is not empty
                if (!binaryString.isEmpty()) {
                    BuoyancyPair nextPair = getSinglePacket(binaryString, false);
                    int length = originalLength - remaining + nextPair.getLength();
                    BuoyancyPair thisPair = new BuoyancyPair(length, nextPair.getLiteral());
                    System.out.println(thisPair);
                    return thisPair;
                }
            } else {
                return new BuoyancyPair(sizeOfPackages + 6, literalLong); // by contract: this represents the length of the package
            }

        } else { //operator
            int lengthType = Integer.parseInt(binaryString.substring(0, 1));
            System.out.println("\tOperator of type " + lengthType);
            List<Long> literalList = new ArrayList<>();
            int totalLengthOfSubpackages = 0;

            if (lengthType == 0) {
                //next 15 bits tell how many bits are subpackages
                int transmittedLength = Integer.parseInt(binaryString.substring(1, 16), 2);
                System.out.println("\tC0, read 15 bits results in length: " + binaryString.substring(1, 16) + " " + transmittedLength);
                binaryString = binaryString.substring(16, 16 + transmittedLength);
                System.out.println("\tC0, remaining String: " + binaryString + " of length " + binaryString.length());
                while (totalLengthOfSubpackages < transmittedLength) {
                    BuoyancyPair pair = getSinglePacket(binaryString.substring(totalLengthOfSubpackages), false);
                    literalList.add(pair.getLiteral());
                    totalLengthOfSubpackages += pair.getLength(); // by contract, the length of the package above
                }
                totalLengthOfSubpackages += 7 + 15;
                System.out.println("\tTotal length of C0 is " + totalLengthOfSubpackages + ". This should be the same as read: " + transmittedLength + " plus 22 (version, type, operator type 0) = " + (transmittedLength + 22));
            } else { //length type is 1
                int numberOfSubpackages = Integer.parseInt(binaryString.substring(1, 12), 2);
                System.out.println("\tC1, the 11 bits " + binaryString.substring(1, 12) + " define the number of subpackages: " + numberOfSubpackages);
                binaryString = binaryString.substring(12);
                System.out.println("\tC1, remaining string is " + binaryString);
                for (int i = 0; i < numberOfSubpackages; i++) {
                    System.out.println("#subpackage=" + (i + 1));
                    BuoyancyPair pair = getSinglePacket(binaryString, false);
                    totalLengthOfSubpackages += pair.getLength();
                    literalList.add(pair.getLiteral());
                    binaryString = binaryString.substring(pair.getLength()); // by contract, this returns the length of the subpackage
                }
                totalLengthOfSubpackages += 7 + 11;
            }
            long resultingLiteral;
            switch (Integer.parseInt(type, 2)) {
                case 0:
                    resultingLiteral = literalList.stream().reduce(0L, Long::sum);
                    System.out.println("Calculate 0: sum of " + literalList + " = " + resultingLiteral);
                    break;
                case 1:
                    resultingLiteral = literalList.stream().reduce(1L, (a, b) -> a * b);
                    System.out.println("Calculate 1: product of " + literalList + " = " + resultingLiteral);
                    break;
                case 2:
                    resultingLiteral = literalList.stream().min(Long::compare).get();
                    System.out.println("Calculate 2: minimum of " + literalList + " = " + resultingLiteral);
                    break;
                case 3:
                    resultingLiteral = literalList.stream().max(Long::compare).get();
                    System.out.println("Calculate 3: maximum of " + literalList + " = " + resultingLiteral);
                    break;
                case 5:
                    checkSizeTwo(literalList);
                    resultingLiteral = literalList.get(0) > literalList.get(1) ? 1 : 0;
                    System.out.println("Calculate 5: greater than " + literalList + " = " + resultingLiteral);
                    break;
                case 6:
                    checkSizeTwo(literalList);
                    resultingLiteral = literalList.get(0) < literalList.get(1) ? 1 : 0;
                    System.out.println("Calculate 6: less than " + literalList + " = " + resultingLiteral);
                    break;
                case 7:
                    checkSizeTwo(literalList);
                    resultingLiteral = literalList.get(0).compareTo(literalList.get(1)) == 0 ? 1 : 0;
                    System.out.println("Calculate 7: equals of " + literalList + " = " + resultingLiteral);
                    break;
                default:
                    System.out.println("Calculate not clear. check.");
                    throw new UnsupportedOperationException("check type");
            }
            return new BuoyancyPair(totalLengthOfSubpackages, resultingLiteral);
        }
        System.out.println("shouldn't get here");
        return new BuoyancyPair(0, 0L);
    }

    private void checkSizeTwo(List<?> list) {
        if (!(list.size() == 2)) {
            throw new UnsupportedOperationException("not enough or too many items. list should have exactly two elements: " + list);
        }
    }

}
