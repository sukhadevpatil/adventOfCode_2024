package day4;

import common.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> programLines = Utils.getFileLines("day4_part2.txt");
        String searchStr = "XMAS";
        int count = 0;

        char[][]arr = programLines.stream().map(String::toCharArray).toArray(char[][]::new);

        Arrays.asList(arr).forEach(System.out::println);

        count += countWordInText(arr, searchStr);

        System.out.println("Total :: " + count);
    }

    private static int countWordInText(char[][] arr, String searchStr) {
        int count = 0;
        int rows = arr.length;
        int cols = arr[0].length;
        List<Character[][]> patterns = List.of(
                new Character[][] {
                        { 'M', '.', 'S' },
                        { '.', 'A', '.' },
                        { 'M', '.', 'S' },
                },
                new Character[][] {
                        { 'S', '.', 'M' },
                        { '.', 'A', '.' },
                        { 'S', '.', 'M' },
                },
                new Character[][] {
                        { 'S', '.', 'S' },
                        { '.', 'A', '.' },
                        { 'M', '.', 'M' },
                },
                new Character[][] {
                        { 'S', '.', 'S' },
                        { '.', 'A', '.' },
                        { 'M', '.', 'M' },
                },
                new Character[][] {
                        { 'M', '.', 'M' },
                        { '.', 'A', '.' },
                        { 'S', '.', 'S' },
                });

        for (int rIndex = 0; rIndex < rows - 2; rIndex++) {
            for (int cIndex = 0; cIndex < cols - 2; cIndex++) {
                Character[][] xmasGrid = {
                        { arr[rIndex][cIndex], '.', arr[rIndex][cIndex + 2] },
                        { '.', arr[rIndex + 1][cIndex + 1], '.' },
                        { arr[rIndex + 2][cIndex], '.', arr[rIndex + 2][cIndex + 2] }
                };
                for (Character[][] pattern : patterns) {
                    boolean isMatch = true;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (pattern[i][j] != '.'
                                    && pattern[i][j] != xmasGrid[i][j]) {
                                isMatch = false;
                                break;
                            }
                        }
                    }
                    if (isMatch) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
}
