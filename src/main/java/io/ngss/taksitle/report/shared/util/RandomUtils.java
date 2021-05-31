package io.ngss.taksitle.report.shared.util;

import java.util.Random;

public class RandomUtils {

    public static Double randomBetween(Double min, Double max, Random r){
        return min + (max - min) * r.nextDouble();
    }

    public static Long randomBetween(Long min, Long max, Random r){
        return min + (max - min) * r.nextLong();
    }

    public static Integer randomBetween(Integer min, Integer max, Random r){
        return min + (max - min) * r.nextInt();
    }

    private RandomUtils(){}
}
