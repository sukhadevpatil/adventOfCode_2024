import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1Part2 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        part1("day1_part2.txt");
    }

    private static void part1(String fileName) throws URISyntaxException, IOException {
        List<String> lines = Utils.getFileLines(fileName);
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        lines.forEach(val -> {
            String [] arr = val.trim().split("   ");
            leftList.add(Integer.valueOf(arr[0]));
            rightList.add(Integer.valueOf(arr[1]));
        });

        System.out.println(leftList);
        System.out.println(rightList);

        AtomicInteger sum = new AtomicInteger(0);
        AtomicInteger counter = new AtomicInteger(0);
        leftList.forEach(num -> {
            int elem = leftList.get(counter.get());
            int freq = Collections.frequency(rightList, elem);
            sum.getAndAdd(elem * freq);
            counter.getAndIncrement();
        });

        System.out.println("Total Sum :: " + sum);
    }
}
