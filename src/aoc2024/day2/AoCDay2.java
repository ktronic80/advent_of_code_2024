package aoc2024.day2;

import java.io.IOException;

public class AoCDay2 {

    public static void main(String[] args) {
        try {
           var reports = new ReportReader().readReports();
           var safeReportCount = new SafeReportCalculation().countSafeReports(reports);

            System.out.println("-----");
            System.out.println("Safe report count: " + safeReportCount);
            System.out.println("-----");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
