package day4;

import common.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> programLines = Utils.getFileLines("day4_part1.txt");
        String searchStr = "XMAS";
        int count;

        char[][]arr = programLines.stream().map(String::toCharArray).toArray(char[][]::new);

        Arrays.asList(arr).forEach(System.out::println);

        count = countWordInText(arr, searchStr);

        System.out.println("Total :: " + count);
    }

    private static int countWordInText(char[][] arr, String searchStr) {
        int count = 0;
        int rows = arr.length;
        int cols = arr[0].length;
        int searchWordLength = searchStr.length();
        int[][] directions = {
                { 0, 1 }, // Horizontal (right)
                { 0, -1 }, // Horizontal (left)

                { 1, 0 }, // Vertical (down)
                { -1, 0 }, // Vertical (up)

                { 1, 1 }, // Diagonal (down-right)
                { 1, -1 }, // Diagonal (down-left)

                { -1, 1 }, // Diagonal (up-right)
                { -1, -1 }, // Diagonal (up-left)
        };

        for (int rIndex = 0; rIndex < rows; rIndex++) {
            for (int cIndex = 0; cIndex < cols; cIndex++) {
                for (int[] direction : directions) {
                    int tempRowIndex = rIndex;
                    int tempColIndex = cIndex;
                    int wordIndex = 0;

                    while (wordIndex < searchWordLength) {

                        if ((tempRowIndex < 0 || tempRowIndex >= rows
                                || tempColIndex < 0 || tempColIndex >= cols)
                                || arr[tempRowIndex][tempColIndex] != searchStr.charAt(wordIndex)) {
                            break;
                        }
                        tempRowIndex += direction[0];
                        tempColIndex += direction[1];
                        wordIndex++;
                    }
                    if (wordIndex == searchWordLength) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
