package aoc2024.day1;

import java.util.ArrayList;

public class DistanceCalculation {

    public int calculateListDistance(InputLists inputLists) {

        var leftList = inputLists.leftList().stream().sorted().toList();
        var rightList = inputLists.rightList().stream().sorted().toList();

        var distances = new ArrayList<Integer>();

        for (int i = 0; i < leftList.size(); ++i) {
            var leftValue = leftList.get(i);
            var rightValue = rightList.get(i);
            var min = Math.min(leftValue, rightValue);
            var max = Math.max(leftValue, rightValue);
            var distance = max - min;
            distances.add(distance);
        }


        return distances.stream().reduce(Integer::sum).get();
    }
}
