package aoc2024.day2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportReader {

    public List<List<Integer>> readReports() throws IOException {
        var fileUri = ReportReader.class.getResource("input");
        try (BufferedReader listFileReader = new BufferedReader(new FileReader(fileUri.getFile()))) {
            var line = listFileReader.readLine();
            List<List<Integer>> reports = new ArrayList<>();
            while (line != null) {
                var splitLine = line.split("\\s");
                reports.add(Arrays.stream(splitLine).map(Integer::parseInt).toList());
                line = listFileReader.readLine();
            }
            return reports;

        }
    }
}
