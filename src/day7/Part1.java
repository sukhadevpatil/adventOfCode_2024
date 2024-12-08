package day7;

import common.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> programLines = Utils.getFileLines("day7_part1.txt");

        solution(programLines);

        //programLines.forEach(System.out::println);
    }

    static void solution(List<String> lines) {
        long sum1 = 0;
        long sum2 = 0;
        for (String line : lines) {
            String[] parts = line.split(": ");
            long sum = Long.parseLong(parts[0]);
            List<Long> numbers = Arrays.stream(parts[1].split(" ")).map(Long::parseLong).toList();

            if (possible(numbers, 1, numbers.get(0), sum, false)) {
                sum1 += sum;
            }
            if (possible(numbers, 1, numbers.get(0), sum, true)) {
                sum2 += sum;
            }
        }

        System.out.println("Part 1: " + sum1); // 1298103531759
        System.out.println("Part 2: " + sum2); // 140575048428831
    }

    private static boolean possible(List<Long> numbers, int index, long totalUntilNow, long requiredTotal,
                                    boolean considerOr) {
        if (index == numbers.size()) {
            return totalUntilNow == requiredTotal;
        }

        if (totalUntilNow > requiredTotal) {
            return false;
        }

        long num = numbers.get(index);
        return possible(numbers, index + 1, totalUntilNow + num, requiredTotal, considerOr)
                || possible(numbers, index + 1, totalUntilNow * num, requiredTotal, considerOr)
                || (considerOr
                && possible(numbers, index + 1, append(totalUntilNow, num), requiredTotal, considerOr));

    }

    private static long append(long n1, long n2) {
        long second = n2;
        while(second > 0) {
            second /= 10;
            n1 *= 10;
        }
        return n1 + n2;
    }
}
