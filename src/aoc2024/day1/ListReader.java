package aoc2024.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListReader {

    private final boolean debug = false;

    public InputLists readLists() throws IOException {
        var fileUri = ListReader.class.getResource("input");
        try (BufferedReader listFileReader = new BufferedReader(new FileReader(fileUri.getFile()))) {
            var line = listFileReader.readLine();
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            while (line != null) {
                if (!line.equals(" ")) {
                    var normalizedLine = line.replaceAll("\\s+", " ");

                    var values = normalizedLine.split(" ");
                    if (values.length == 2) {
                        left.add(Integer.parseInt(values[0]));
                        right.add(Integer.parseInt(values[1]));
                    } else {
                        System.out.println("No valid line for input " + line);
                    }
                    line = listFileReader.readLine();
                }
            }

            if (debug) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("input_right", false))) {
                    right.forEach(integer -> {
                        try {
                            writer.write(String.valueOf(integer));
                            writer.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("input_left", false))) {
                    left.forEach(integer -> {
                        try {
                            writer.write(String.valueOf(integer));
                            writer.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                }
            }
            return new InputLists(left, right);
        }
    }
}
