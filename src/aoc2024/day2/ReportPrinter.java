package aoc2024.day2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ReportPrinter {

    public static void printReport(List<Integer> report) {
        System.out.println("#-----");
        System.out.println(report.stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println("#-----");
    }


    public static void printReportToFile(List<Integer> report) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("safe_reports", true))) {

            var reportAsString = reportString(report);

                try {
                    writer.write(reportAsString);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String reportString(List<Integer> report) {
        return report.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
