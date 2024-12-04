package day3;

import common.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Part2 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> programLines = Utils.getFileLines("day3_part2.txt");

        AtomicBoolean ignoreConditions = new AtomicBoolean(false);
        AtomicLong sum = new AtomicLong(0);
        programLines.forEach(codeLine -> {
            Pattern pattern = Pattern.compile("(mul\\((\\d+),(\\d+)\\))|(do\\(\\))|(don't\\(\\))");
            Matcher matcher = pattern.matcher(codeLine);

            AtomicBoolean flag = new AtomicBoolean(true);
            matcher.results().forEach(val -> {
                if(val.group().equals("do()")) {
                    flag.set(true);
                } else if (val.group().equals("don't()")) {
                    flag.set(false);
                }

                if(flag.get() && val.group(1) != null) {
                    long sumVal = (long) parseInt(val.group(2)) * parseInt(val.group(3));
                    sum.getAndAdd(sumVal);
                }
            });

            System.out.println("Result :: " + sum.get());


            //AtomicBoolean isEnabled = new AtomicBoolean(true);

//            int total = matcher.results().mapToInt(result -> {
//                if (result.group(1) != null && (ignoreConditions.get() || isEnabled.get())) {
//                    return parseInt(result.group(2)) * parseInt(result.group(3));
//                } else if (!ignoreConditions.get()) {
//                    if (result.group(4) != null) {
//                        isEnabled.set(true);
//                    } else if (result.group(5) != null) {
//                        isEnabled.set(false);
//                    }
//                }
//                return 0;
//            }).sum();
//
//            sum.getAndAdd(total);
       });

        System.out.println("Total :: " + sum);
    }
}
