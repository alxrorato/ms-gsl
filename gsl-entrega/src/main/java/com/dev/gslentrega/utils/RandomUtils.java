package com.dev.gslentrega.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    
    public static BigDecimal getRandomNumber(BigDecimal min, BigDecimal max, int scale) {
    	
    	   return min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)))
    			   .setScale(scale, RoundingMode.HALF_UP)
    			   /*.round(new MathContext(2, RoundingMode.HALF_UP))*/;
     }
/*
    public static BigDecimal getRandomNumber(BigDecimal min, BigDecimal max) {
    	
   	   return min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)))
   			   .round(new MathContext(2, RoundingMode.HALF_UP));
    }
*/
	public static int getNonZeroRandomNumber(int limit) {
		int number = 0;
		do {
			number = RandomUtils.getRandomNumber(limit);
		} while (number == 0);
		return number;
	}
    
}
