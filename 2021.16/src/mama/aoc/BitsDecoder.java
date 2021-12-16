package mama.aoc;

import java.util.ArrayList;
import java.util.List;

public class BitsDecoder {

    public int getVersionNumbers(String path) {
        List<String> strings = FileReader.readFile(path);
        if (strings.size() != 1) {
            System.out.println("check input");
        } else {
            return getSumOfVersionNumbers(strings.get(0));
        }
        return 0;
    }

    private int getSumOfVersionNumbers(String transmission) {
        StringBuilder binaryString = decodeVersionNumbers(transmission);
        List<Integer> allVersions = getSinglePacket(binaryString.toString(), new ArrayList<>(), true);
        System.out.println(allVersions);

        int sumOfVersions = 0;
        for (int versionNumber : allVersions) {
            sumOfVersions += versionNumber;
        }

        System.out.println("Sum of all Versions: " + sumOfVersions);
        return sumOfVersions;
    }

    private StringBuilder decodeVersionNumbers(String transmission) {
        int pos = 0;
        System.out.println("Full Transmission: \t" + transmission);
        StringBuilder binary = new StringBuilder();
        while ((pos) < transmission.length()) {
            String partOfTransmission = transmission.substring(pos, pos + 1);
            int i = Integer.parseInt(partOfTransmission, 16);
            binary.append(appendStringToLength(Integer.toBinaryString(i), 4));
            pos += 1;
        }
        System.out.println("Full Binary: \t\t" + binary.toString());
        return binary;

    }

    public String appendStringToLength(String fullString, int lenght) {
        if (fullString.length() == lenght) {
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

    private List<Integer> getSinglePacket(String binaryString, List<Integer> allVersions, boolean isOuterPackage) {

        int originalLength = binaryString.length();
        String version = binaryString.substring(0, 3);
        allVersions.add(Integer.parseInt(version, 2));

        System.out.println("Parsing string:\t\t" + binaryString);
        System.out.println("Packet has version: \t" + version + "=" + Integer.parseInt(version, 2));
        String type = binaryString.substring(3, 6);
        System.out.println("Type of packet is: \t\t" + type + "=" + Integer.parseInt(type, 2));

        binaryString = binaryString.substring(6);
        //get next packet
        if (Integer.parseInt(type, 2) == 4) {//literal
            int subPackageStart;
            int sizeOfPackages = 0;
            StringBuilder literal = new StringBuilder();
            do {
                String subPackage = binaryString.substring(0, 5);
                subPackageStart = Integer.parseInt(subPackage.substring(0, 1));
                literal.append(subPackage.substring(1));
                sizeOfPackages += 5;
                binaryString = binaryString.substring(5);
            }
            while (subPackageStart == 1);
            System.out.println("Literal: " + literal.toString() + " as dec. number " + Integer.parseInt(literal.toString(), 2));

            if (isOuterPackage) {
                int remaining = (originalLength - sizeOfPackages - 6) % 4;
                binaryString = binaryString.substring(remaining);
                System.out.println("remaining number of digits: " + remaining + " results in: " + binaryString);
                //recursive call if string is not empty
                if (!binaryString.isEmpty()) {
                    return getSinglePacket(binaryString, allVersions, false);
                }
            } else {
                List<Integer> result = new ArrayList<>(1);
                result.add(sizeOfPackages + 6);
                return result; // by contract: this represents the length of the package
            }

        } else { //operator
            System.out.println("Operator");
            int lengthType = Integer.parseInt(binaryString.substring(0, 1));
            if (lengthType == 0) {
                //next 15 bits tell how many bits are subpackages
                int totalLenghtOfSubpackages = Integer.parseInt(binaryString.substring(1, 16), 2);
                System.out.println("case 0, read 15 bits results in lenght: " + totalLenghtOfSubpackages);
                binaryString = binaryString.substring(16, 16 + totalLenghtOfSubpackages);
                System.out.println("remaining String: " + binaryString + " of length " + binaryString.length());
                int totalLength = 0;
                while (totalLength < totalLenghtOfSubpackages) {
                    List<Integer> lengthOfPackage = getSinglePacket(binaryString.substring(totalLength), allVersions, false);
                    totalLength += lengthOfPackage.get(0); // by contract, the lenght of the package above
                }
                return allVersions;
                //return getSinglePacket(binaryString, allVersions, false);
            } else { //length type is 1
                int numberOfSubpackages = Integer.parseInt(binaryString.substring(1, 12), 2);
                System.out.println("case 1. the number " + binaryString.substring(1, 12) + " defines the number of subpackages: " + numberOfSubpackages);
                binaryString = binaryString.substring(12);
                System.out.println("remaining string is " + binaryString);
                for (int i = 0; i < numberOfSubpackages; i++) {
                    List<Integer> singlePacket = getSinglePacket(binaryString, allVersions, false);
                    binaryString = binaryString.substring(singlePacket.get(0)); // by contract, this returns the length of the subpackage
                }
                return allVersions;
            }
        }
        System.out.println(allVersions);
        return allVersions;
    }

}
