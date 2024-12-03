package aoc2024.day3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AocDay3 {

    private static final String DONT_TOKEN = "don't\\(\\)";
    private static final String DO_TOKEN = "do()";

    public static void main(String[] arg) {
        try {
            var memory = readCorruptedMemory();

            List<String> validMult = new ArrayList<>();
            var splitByDont = memory.split(DONT_TOKEN);
            validMult.add(splitByDont[0]);

            for (int i = 1; i < splitByDont.length; i++) {
                var currentToken = splitByDont[i];
                var doIndex = currentToken.indexOf(DO_TOKEN);

                if (doIndex > -1) {
                    if (doIndex > 0) {
                        // left side of do was always after don't().
                        currentToken = currentToken.substring(doIndex);
                    }
                    validMult.add(currentToken);
                }
            }
            // Add space for debugging
            var allowedMulString = String.join(" ", validMult);

            mulAll(allowedMulString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void mulAll(String memory) {
        var mulPattern = Pattern.compile(".*?(mul\\((\\d{1,3},\\d{1,3})\\)).*?");
        var matcher = mulPattern.matcher(memory);

        List<Integer> summands = new ArrayList<>();
        while (matcher.find()) {
            var multipliers = matcher.group(2).split(",");
            summands.add(Integer.parseInt(multipliers[0]) * Integer.parseInt(multipliers[1]));
            //toFile(matcher.group(1), "all");
        }

        var result = summands.stream().reduce(Integer::sum);

        result.ifPresentOrElse(value -> System.out.println("Result: " + value), () -> System.out.println("No Result found."));
    }

    static void toFile(String string, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            try {
                writer.write(string);
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String readCorruptedMemory() throws IOException {
        var fileUri = AocDay3.class.getResource("input");
        try (BufferedReader listFileReader = new BufferedReader(new FileReader(fileUri.getFile()))) {
            var line = listFileReader.readLine();
            List<String> memory = new ArrayList<>();
            while (line != null) {
                memory.add(line);
                line = listFileReader.readLine();
            }
            return String.join("", memory);
        }
    }
}
