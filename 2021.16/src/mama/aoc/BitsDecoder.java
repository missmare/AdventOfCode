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

    private int getSumOfVersionNumbers(String transmission) {
        StringBuilder binaryString = decodeVersionNumbers(transmission);
        getSinglePacket(binaryString.toString(), true);
        System.out.println("all version numbers: " + allVersions);

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
            pos++;
        }
        System.out.println("Full Binary: \t\t" + binary);
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

    private int getSinglePacket(String binaryString, boolean isOuterPackage) {
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
            StringBuilder literal = new StringBuilder();
            do {
                String subPackage = binaryString.substring(0, 5);
                subPackageStart = Integer.parseInt(subPackage.substring(0, 1));
                literal.append(subPackage.substring(1));
                sizeOfPackages += 5;
                binaryString = binaryString.substring(5);
            }
            while (subPackageStart == 1);
            System.out.println("  Literal: " + literal + " as dec. number " + Long.parseLong(literal.toString(), 2));

            if (isOuterPackage) {
                int remaining = (originalLength - sizeOfPackages - 6) % 4;
                binaryString = binaryString.substring(remaining); //TODO: das ist ziemlich sicher falsch
                System.out.println("  remaining number of digits: " + remaining + " \nresults in: " + binaryString);
                //recursive call if string is not empty
                if (!binaryString.isEmpty()) {
                    return originalLength - remaining + getSinglePacket(binaryString, false);
                }
            } else {
                return sizeOfPackages + 6; // by contract: this represents the length of the package
            }

        } else { //operator
            int lengthType = Integer.parseInt(binaryString.substring(0, 1));
            System.out.println("  Operator of type "+ lengthType);
            if (lengthType == 0) {
                //next 15 bits tell how many bits are subpackages
                int totalLengthOfSubpackages = Integer.parseInt(binaryString.substring(1, 16), 2);
                System.out.println("    C0, read 15 bits results in length: " + binaryString.substring(1, 16) + " " + totalLengthOfSubpackages);
                binaryString = binaryString.substring(16, 16 + totalLengthOfSubpackages);
                System.out.println("    C0, remaining String: " + binaryString + " of length " + binaryString.length());
                int totalLength = 0;
                while (totalLength < totalLengthOfSubpackages) {
                    int lengthOfPackage = getSinglePacket(binaryString.substring(totalLength), false);
                    totalLength += lengthOfPackage; // by contract, the length of the package above
                }
                System.out.println("Total length of C0 is " + (totalLength + 7 + 15));
                return totalLengthOfSubpackages + 7 + 15;
            } else { //length type is 1
                int numberOfSubpackages = Integer.parseInt(binaryString.substring(1, 12), 2);
                System.out.println("    C1, the 11 bits " + binaryString.substring(1, 12) + " define the number of subpackages: " + numberOfSubpackages);
                binaryString = binaryString.substring(12);
                int totalLengthOfSubpackages = 0;
                System.out.println("    C1, remaining string is " + binaryString);
                for (int i = 0; i < numberOfSubpackages; i++) {
                    System.out.println("#subpackage="+(i+1));
                    int lengthOfPackage = getSinglePacket(binaryString, false);
                    totalLengthOfSubpackages += lengthOfPackage;
                    System.out.println("    C1, reduce string by " + lengthOfPackage + " digits. ");
                    binaryString = binaryString.substring(lengthOfPackage); // by contract, this returns the length of the subpackage
                }
                return totalLengthOfSubpackages + 7 + 11;
            }
        }
        System.out.println(allVersions);
        System.out.println("shouldn't get here");
        return 0;
    }

}
