package aoc2024.day1;

import java.util.HashMap;
import java.util.Map;

public class CalculateSimilarity {

    public long calculateSimilarity(InputLists inputLists) {
        var leftList = inputLists.leftList();
        var rightList = inputLists.rightList();

        Map<Integer, Long> similarities = new HashMap<>();

       /* for (Integer location : leftList) {
            int counter = 0;

            for (Integer locationRight: rightList) {
                if (location.compareTo(locationRight) == 0) {
                    counter++;
                }
            }

            if (counter > 0) {
                var simScore = similarities.get(location);

                if (simScore == null) {
                    similarities.put(location, ((long) counter * location));
                } else {
                    similarities.put(location, simScore + simScore);
                }
            }
        }

        var totalSimScore = 0;
        for (Long simScore: similarities.values()) {
            totalSimScore += simScore;
        }
        return totalSimScore;*/
        leftList.forEach(location -> {
            similarities.compute(location, (leftLocation,similarityScore) -> {
                if (similarityScore == null) {
                    var count = rightList.stream().filter(rightLocation -> rightLocation.equals(leftLocation)).count();
                    //System.out.println("Found " + leftLocation + " " + count + " times.");
                    return leftLocation * count;
                } else {
                    //System.out.println("Current similarity score for " + leftLocation + " " + similarityScore);
                    //System.out.println("New similarity score for " + leftLocation + " " + newDistance);
                    return similarityScore + similarityScore;
                }
            });
        });

        return similarities.values().stream().reduce(Long::sum).get();
    }
}
