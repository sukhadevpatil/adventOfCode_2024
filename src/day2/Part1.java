package day2;

import common.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        part1("day2_part1.txt");
    }

    private static void part1(String fileName) throws URISyntaxException, IOException {
        List<String> reports = Utils.getFileLines(fileName);

        System.out.println(reports.stream().filter(Part1::isSafeReport).count());
//        reports.forEach(report -> {
//
//            boolean isSafe = isSafeReport(report);
//
//        });

    }

    private static boolean isSafeReport(String report) {
        String []arr = report.split(" ");

        for (int i = 1; i < arr.length; i++) {
            int diff = Integer.parseInt(arr[i]) - Integer.parseInt(arr[i-1]);
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3 || (diff > 0 && i > 1 && Integer.parseInt(arr[i - 1]) < Integer.parseInt(arr[i - 2])) || (diff < 0 && i > 1 && Integer.parseInt(arr[i - 1]) > Integer.parseInt(arr[i - 2]))) {
                return false;
            }
        }

        return true;
    }
}
