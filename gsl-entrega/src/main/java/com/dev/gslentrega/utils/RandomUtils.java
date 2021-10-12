package com.dev.gslentrega.utils;

import java.util.Random;

public class RandomUtils {

    public static long getRandomNumber(long start, long end) {
    	
    	Random r = new Random();
    	long number = start + ((long)(r.nextDouble()*(end - start)));
    	return number;
    }

    public static int getRandomNumber(int limit) {
    	
    	Random r = new Random();
    	int number = r.nextInt(limit); 
    	return number;
    }
    
}
