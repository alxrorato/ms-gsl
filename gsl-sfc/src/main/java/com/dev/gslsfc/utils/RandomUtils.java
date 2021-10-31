package com.dev.gslsfc.utils;

import java.util.Random;

public class RandomUtils {

    public static long getRandomNumber(long start, long end) {
    	
    	Random r = new Random();
    	long number = start + ((long)(r.nextDouble()*(end - start)));
    	return number;
    }
	
}
