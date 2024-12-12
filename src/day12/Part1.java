package day12;

import common.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1 {


    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> programLines = Utils.getFileLines("day12_part1.txt");
        part1Solution(programLines);
    }

    private static void part1Solution(List<String> programLines) {
        char[][] grid = prepareGrid(programLines);
        char[] distinctChars = getDistinctChars(grid);
        int totalCost = 0;

        for (char c : distinctChars) {
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == c && !visited[i][j]) {
                        Map<String, Integer> map = new HashMap<>();
                        map.put("AREA", 0);
                        map.put("PERIMETER", 0);
                        dfs(grid, visited, i, j, c, map);
                        totalCost += map.get("AREA") * map.get("PERIMETER");
                    }
                }
            }
        }

        System.out.println(totalCost);
    }

    private static char[] getDistinctChars(char[][] grid) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(!stringBuilder.toString().contains(grid[i][j]+"")) {
                    stringBuilder.append(grid[i][j]);
                }
            }
        }

       return stringBuilder.toString().toCharArray();
    }

    private static void dfs(char[][] grid, boolean[][] visited, int i, int j, char c, Map<String, Integer> map) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] != c) {
            return;
        }
        int area = map.get("AREA");
        int perimeter = map.get("PERIMETER");

        visited[i][j] = true;
        area++;

        if (i == 0 || grid[i - 1][j] != c) {
            perimeter++;
        }
        if (i == grid.length - 1 || grid[i + 1][j] != c) {
            perimeter++;
        }
        if (j == 0 || grid[i][j - 1] != c) {
            perimeter++;
        }
        if (j == grid[0].length - 1 || grid[i][j + 1] != c) {
            perimeter++;
        }

        map.put("AREA", area);
        map.put("PERIMETER", perimeter);

        dfs(grid, visited, i - 1, j, c, map);
        dfs(grid, visited, i + 1, j, c, map);
        dfs(grid, visited, i, j - 1, c, map);
        dfs(grid, visited, i, j + 1, c, map);
    }

    private static char[][] prepareGrid(List<String> programLines) {
        char[][] grid = new char[programLines.getFirst().length()][programLines.size()];
        AtomicInteger counter = new AtomicInteger(0);
        programLines.forEach(line -> System.arraycopy(line.toCharArray(), 0, grid[counter.get()], 0, grid[counter.getAndIncrement()].length));
        return grid;
    }
}
