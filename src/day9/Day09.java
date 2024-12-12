package day9;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day09 {

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        Scanner sc = new Scanner(Path.of(ClassLoader.getSystemResource("day9_part1.txt").toURI()).toFile());
        String output1 = new Day09().solve(true, sc);
        System.out.println(output1);

        sc = new Scanner(Path.of(ClassLoader.getSystemResource("day9_part1.txt").toURI()).toFile());
        String output2 = new Day09().solve(false, sc);
        System.out.println(output2);
    }
    public String solve(boolean part1, Scanner in) {
        long answer;
        String line = in.nextLine();
        int[] parts = new int[line.length()];
        int length = 0;
        for(int i = 0; i < parts.length; i++){
            parts[i] = Integer.parseInt(line.substring(i, i+1));
            length += parts[i];
        }
        if(part1){
            answer = part1(length, parts);
        }
        else{
            answer = part2(parts);
        }
        return answer + "";
    }

    private long part1(int length, int[] parts){
        long answer = 0;
        int[] filesystem = new int[length];
        int[] condensed = new int[length];
        int index = 0;
        for(int i = 0; i < parts.length; i++){
            int part = parts[i];
            for(int j = 0; j < part; j++){
                filesystem[index + j] = (i % 2 == 1)?-1:i/2;
            }
            index += part;
        }
        int backindex = length - 1;
        for(int i = 0; i < length; i++){
            if(filesystem[i] != -1){
                condensed[i] = filesystem[i];
            }
            else{
                while(backindex >= i && filesystem[backindex] == -1){
                    backindex--;
                }
                if(backindex > i){
                    condensed[i] = filesystem[backindex];
                    filesystem[backindex] = -1;
                }
            }
        }
        for(int i = 0; i < condensed.length; i++){
            if(condensed[i] != -1){
                answer+= (long) i * condensed[i];
            }
        }
        return answer;
    }

    private long part2(int[] parts){
        long answer = 0;
        Map<Integer, int[]> starting = new HashMap<>();
        Map<Integer, int[]> gaps = new HashMap<>();
        int index = 0;
        for(int i = 0; i < parts.length; i++) {
            if(i %2 == 0){
                starting.put(i / 2, new int[]{index, parts[i]});
            }
            if(i % 2 == 1){
                gaps.put(i / 2, new int[]{index, parts[i]});
            }
            index += parts[i];
        }
        for(int i = parts.length/2 ; i >= 0; i--){
            int size = starting.get(i)[1];
            for(int j = 0; j < i; j++){
                int gap = gaps.get(j)[1];
                if(gap >= size){
                    starting.put(i, new int[]{gaps.get(j)[0], size});
                    gaps.put(j, new int[]{gaps.get(j)[0] + size, gaps.get(j)[1] - size});
                    break;
                }
            }
        }

        for (Map.Entry<Integer, int[]> entry : starting.entrySet()) {
            int[] value = entry.getValue();
            for (int i = value[0]; i < value[0] + value[1]; i++) {
                answer += (long) i * entry.getKey();
            }
        }
        return answer;
    }
}
