package day5;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class Day5 {
    static class Triplet {
        long f;
        long s;
        long t;

        Triplet(long f, long s, long t) {
            this.f = f;
            this.s = s;
            this.t = t;
        }
    }

    public static void populateArray(Scanner sc, ArrayList<Triplet> map) {
        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) break;
            String[] vals = line.split(" ");
            long src = Long.parseLong(vals[1]);
            long dest = Long.parseLong(vals[0]);
            long range = Long.parseLong(vals[2]);
            map.add(new Triplet(src, dest, range));
        }
    }

    public static void main2(String[] args) throws FileNotFoundException {

        try {
            URL url = ClassLoader.getSystemResource("day5_part1.txt");
            Scanner input = new Scanner(Path.of(url.toURI()).toFile());

            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(Path.of(ClassLoader.getSystemResource("day5_part1.txt").toURI()).toFile());
            ArrayList<Long> seeds = new ArrayList<>();
            String input = sc.nextLine();
            String[] seedVal = input.split("|")[1].split(" ");
            for (String seed : seedVal) {
                if (seed.equals("")) continue;
                seeds.add(Long.parseLong(seed));
            }
            sc.nextLine();

            ArrayList<Triplet> seedToSoil = new ArrayList<>();
            populateArray(sc, seedToSoil);

            ArrayList<Triplet> soilToFertilizer = new ArrayList<>();
            populateArray(sc, soilToFertilizer);

            ArrayList<Triplet> fertilizerToWater = new ArrayList<>();
            populateArray(sc, fertilizerToWater);

            ArrayList<Triplet> waterToLight = new ArrayList<>();
            populateArray(sc, waterToLight);

            ArrayList<Triplet> lightToTemperature = new ArrayList<>();
            populateArray(sc, lightToTemperature);

            ArrayList<Triplet> temperatureToHumidity = new ArrayList<>();
            populateArray(sc, temperatureToHumidity);

            ArrayList<Triplet> humidityToLocation = new ArrayList<>();
            populateArray(sc, humidityToLocation);

            long minLocation = Long.MAX_VALUE;

            //Code for Part 1
            // for(long curr: seeds){
            //     curr = findMapping(seedToSoil, curr);
            //     curr = findMapping(soilToFertilizer, curr);
            //     curr = findMapping(fertilizerToWater, curr);
            //     curr = findMapping(waterToLight, curr);
            //     curr = findMapping(lightToTemperature, curr);
            //     curr = findMapping(temperatureToHumidity, curr);
            //     curr = findMapping(humidityToLocation, curr);
            //     minLocation = Math.min(minLocation, curr);
            // }

            //Code for Part 2
            for (int i = 0; i < seeds.size(); i += 2) {
                for (long seed = seeds.get(i); seed < seeds.get(i) + seeds.get(i + 1); seed++) {
                    long curr = seed;
                    curr = findMapping(seedToSoil, curr);
                    curr = findMapping(soilToFertilizer, curr);
                    curr = findMapping(fertilizerToWater, curr);
                    curr = findMapping(waterToLight, curr);
                    curr = findMapping(lightToTemperature, curr);
                    curr = findMapping(temperatureToHumidity, curr);
                    curr = findMapping(humidityToLocation, curr);
                    // System.out.println(curr);
                    minLocation = Math.min(minLocation, curr);
                }
            }
            System.out.println(minLocation);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static long findMapping(ArrayList<Triplet> map, long curr) {
        for (Triplet t : map) {
            if (curr >= t.f && curr <= t.f + t.t) {
                curr = t.s + (curr - t.f);
                break;
            }
        }
        return curr;
    }
}
