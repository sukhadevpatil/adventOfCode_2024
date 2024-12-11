package day11;

import common.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1_BKP {
    static long CONST_NUM = 2024;
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> programLines = Utils.getFileLines("day11_part1.txt");

        List<Long> input = Arrays.stream(programLines.getFirst().split(" ")).map(Long::valueOf).toList();

        part1(input, 5);
    }

    static void part1(List<Long> input, int blinkIterations) {
        List<Long> res = new ArrayList<>();
        for (Long stoneVal : input) {
            res.addAll(iterativePebbleSplit(stoneVal));
        }
        blinkIterations = blinkIterations -1;
        System.out.println("Iteration :: " + blinkIterations + " :: " + res + " :: total stones :: " + res.size());
        while (blinkIterations>0) {
            blinkIterations = blinkIterations-1;
            part1(res, blinkIterations);
        }

        System.out.println("Final Stones :: " + res.size() + " :: " + res);
    }

    private static List<Long> iterativePebbleSplit(long numberedStone) {

        List<List<Long>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        blinkStone(numberedStone, result.getFirst());

//        for (int i = 1; result.size() < i; i++) {
//            result.add(new ArrayList<>());
//            blinkStone(result.get(i-1).get(i-1), result.get(i));
//        }

        return result.stream().flatMap(List::stream).collect(Collectors.toList());

    }

    private static void blinkStone(long numberedStone, List<Long> result) {
        if (numberedStone == 0) {
            result.add(1l);
        } else if (numberedStone == 1) {
            result.add(CONST_NUM);
        } else if (String.valueOf(numberedStone).length() % 2 == 0) {
            long divisionNum = getDivisor(String.valueOf(numberedStone).length() / 2);
            result.add(numberedStone / divisionNum);
            result.add(numberedStone % divisionNum);
        } else {
            result.add(numberedStone * CONST_NUM);
        }
    }

    private static long getDivisor(int i) {
        int divisor = 1;
        for(int k = 1; k <= i; k++){divisor *= 10;}
        return divisor;
    }
}
