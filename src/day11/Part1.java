package day11;

import common.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {
    static long CONST_NUM = 2024;
    HashMap[] map;
    int num_blinks;

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> programLines = Utils.getFileLines("day11_part1.txt");

        List<Long> input = Arrays.stream(programLines.getFirst().split(" ")).map(Long::valueOf).toList();

        new Part1().part1(input, 25);
        new Part1().part1(input, 75);
    }

    void part1(List<Long> stones, int blinkIterations) {
        this.map = new HashMap[blinkIterations + 1];
        this.num_blinks = blinkIterations;

        for(int i = 0; i <= num_blinks; i++){
            this.map[i] = new HashMap<>();
        }

        long ans = 0;

        for(long stone : stones){
            pebbleChange(stone, 0);
            ans += Long.parseLong(map[0].get(stone).toString());
        }

        System.out.println("Final Stones :: " + ans);
    }

    public long pebbleChange(long stone, int depth){

        if(depth == num_blinks){
            return 1;
        }

        if(map[depth].containsKey(stone))
            return Long.parseLong(map[depth].get(stone).toString());

        long ans = 0;

        if(stone == 0){
            ans = pebbleChange(1, depth + 1);
            map[depth].put(stone, ans);
            return ans;
        }

        String str = String.valueOf(stone);

        if(str.length() % 2 == 0){
            int mid = str.length() / 2;

            long left = pebbleChange(Long.parseLong(str.substring(0, mid)), depth + 1);
            long right = pebbleChange(Long.parseLong(str.substring(mid)), depth + 1);

            map[depth].put(stone, left + right);
            return Long.parseLong(map[depth].get(stone).toString());
        }

        ans = pebbleChange(stone * 2024, depth + 1);
        map[depth].put(stone, ans);
        return ans;
    }
}
