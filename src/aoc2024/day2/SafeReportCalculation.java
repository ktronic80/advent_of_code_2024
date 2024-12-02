package aoc2024.day2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static aoc2024.day2.SafeReportCalculation.Direction.SAME;

public class SafeReportCalculation {

    public long countSafeReports(List<List<Integer>> reports) {
        if (reports == null || reports.isEmpty()) {
            return 0;
        }
        return reports.stream().filter(this::bruteForce).count();
    }

    private boolean isReportSafe(List<Integer> report) {
        if (report == null || report.isEmpty()) {
            return false;
        }

        Direction direction = null;
        for (int i = 0; i < report.size() - 1; ++i) {
            var currentReport = report.get(i);
            var nextReport = report.get(i + 1);

            var currentDirection = Direction.getDirection(currentReport, nextReport);

            if (direction == null && currentDirection != SAME) {
                direction = currentDirection;
            } else if (currentDirection == SAME) {
                return false;
            } else if (direction != currentDirection) {
                System.out.println("Report level changed from " + direction + " to " + currentDirection + ". For report " + currentReport + " to " + nextReport + ".");
                ReportPrinter.printReport(report);
                return false;
            }

            var diff = Math.max(currentReport, nextReport) - Math.min(currentReport, nextReport);

            if (!(diff >= 1 && diff <= 3)) {

                System.out.println("Report level differ to much " + diff + ". For report " + currentReport + " to " + nextReport + ".");
                ReportPrinter.printReport(report);
                return false;
            }
        }
        return true;
    }

    /**
     * Solution for second problem.
     * Just check if removing every level one by one from the report makes it valid.
     * Not elegant but works.
     */
    private boolean bruteForce(List<Integer> report) {
        var isSafe = isReportSafe(report);

        if (isSafe) {
            return true;
        } else {
            boolean oneIsSafe = false;
            for(int i = 0; i < report.size(); ++i) {
                List<Integer> copy = new ArrayList<>(report);
                copy.remove(i);
                var check = isReportSafe(copy);

                if (!oneIsSafe && check) {
                    oneIsSafe = true;
                }
            }
            return oneIsSafe;
        }
    }

    /**
     * didn't work was of by count of 3
     */
    private boolean isReportSafeWithDampener(List<Integer> report, boolean isDampened) {
        if (report == null || report.isEmpty()) {
            return false;
        }

        Direction direction = null;
        for (int i = 0; i < report.size() - 1; ++i) {
            List<Integer> reportCopy = new LinkedList<>(report);
            var currentReport = reportCopy.get(i);
            var nextReport = reportCopy.get(i + 1);
            boolean reportDampened = isDampened;

            var currentDirection = Direction.getDirection(currentReport, nextReport);

            if (direction == null && currentDirection != SAME) {
                direction = currentDirection;
            } else if (!isDirectionValid(direction, currentDirection)) {
                if (!reportDampened) {
                    var isSafeDampened = checkDampenReport(reportCopy, i, i+1);
                    reportDampened = true;
                    return isSafeDampened;
                } else {
                    System.out.println("Report level changed from " + direction + " to " + currentDirection + ". For report " + currentReport + " to " + nextReport + ".");
                    return false;
                }
            }

            ;
            var diff = Math.max(currentReport, nextReport) - Math.min(currentReport, nextReport);

            if (!(diff >= 1 && diff <= 3)) {
                if (!reportDampened) {
                    return checkDampenReport(reportCopy, i, i + 1);
                } else {
                    System.out.println("Report level differ to much " + diff + ". For report " + currentReport + " to " + nextReport + ".");
                    ReportPrinter.printReport(report);


                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkDampenReport(List<Integer> report, int currentLevel, int nextLevel) {
        List<Integer> dampenedReportCurrentLevel = new LinkedList<>(report);
        List<Integer> dampenedReportNextLevel = new LinkedList<>(report);
        dampenedReportCurrentLevel.remove(currentLevel);
        dampenedReportNextLevel.remove(nextLevel);
        return isReportSafeWithDampener(dampenedReportCurrentLevel, true) || isReportSafeWithDampener(dampenedReportNextLevel, true);
    }

    private boolean isDirectionValid(Direction direction, Direction currentDirection) {
        if (currentDirection == SAME) {
            return false;
        } else return direction == currentDirection;
    }

    enum Direction {
        INCREASE,
        DECREASE,
        SAME;

        public static Direction getDirection(Integer left, Integer right) {
            var compare = Integer.compare(left, right);
            if (compare == 0) {
                return SAME;
            } else if (compare < 0) {
                return INCREASE;
            } else {
                return DECREASE;
            }
        }
    }
}
