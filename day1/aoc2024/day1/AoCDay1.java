package aoc2024.day1;

import java.io.IOException;
import java.util.ArrayList;

public class AoCDay1 {

    public static void main(String[] args) {
        try {
            var inputLists = new ListReader().readLists();
            var listDistance = new DistanceCalculation().calculateListDistance(inputLists);
            System.out.println("1. ------");
            System.out.println("Total distance of list: " + listDistance);
            System.out.println("---------");

            var similarityScore = new CalculateSimilarity().calculateSimilarity(inputLists);
            System.out.println("2. ####");
            System.out.println("Total similarity score: " + similarityScore);
            System.out.println("#######");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
