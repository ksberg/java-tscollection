package bitzguild.jcollection.function;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;


import bitzguild.jcollection.mutable.DoublesArray;
import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.DoublesFunctions;



public class Test4SimpleMovingAverage {

	public static final boolean VERBOSE = false;
	
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void testConstantAveragePriorToLookback() {
    	int avglen = 10;
    	int samples = 9;
    	Double onlyVal = 10.0;
    	DoublesArray arr = new DoublesArray();
    	for(int i=0; i<samples; i++) arr.add(onlyVal);
    	
    	Doubles sma = DoublesFunctions.sma(arr, avglen);

    	for(int i=0; i<samples-1; i++) {
    		assertEquals("entry i" + i, sma.get(i), onlyVal);
    	}
    	assertEquals("cache.size == origin.size", sma.size(), arr.size());
    }
    
    @Test
    public void testConstantAverageAtLookback() {
    	int avglen = 10;
    	int samples = avglen;
    	Double onlyVal = 10.0;
    	DoublesArray arr = new DoublesArray();
    	for(int i=0; i<samples; i++) arr.add(onlyVal);
    	
    	Doubles sma = DoublesFunctions.sma(arr, avglen);

    	for(int i=0; i<samples; i++) {
    		assertEquals("entry i" + i, sma.get(i), onlyVal);
    	}
    	assertEquals("cache.size == origin.size", sma.size(), arr.size());
    }
    
    @Test
    public void testConstantAverage() {
    	int samples = 11;
    	int avglen = 10;
    	Double onlyVal = 10.0;
    	DoublesArray arr = new DoublesArray();
    	for(int i=0; i<samples; i++) arr.add(onlyVal);
    	
    	Doubles sma = DoublesFunctions.sma(arr, avglen);

    	for(int i=0; i<samples; i++) 
    		assertEquals("entry i" + i, sma.get(i), onlyVal);
    	assertEquals("cache.size == origin.size", sma.size(), arr.size());
    }
    
    
    
    @Test
    public void testRangeGreaterThanLookback() {
    	int samples = 13;
    	int avglen = 10;
    	Double onlyVal = 10.0;
    	DoublesArray arr = new DoublesArray();
    	for(int i=0; i<samples; i++) arr.add(onlyVal);
    	
    	Doubles sma = DoublesFunctions.sma(arr, avglen);

    	for(int i=0; i<samples; i++) 
    		assertEquals("entry i" + i, sma.get(i), onlyVal);
    	assertEquals("cache.size == origin.size", sma.size(), arr.size());
    }

    @Test
    public void testCalcGreaterThanLookback() {
      	DoublesArray arr = new DoublesArray();
    	int avglen = 10;
      	int samples = 15;
    	for(int i=0; i<samples; i++) {
    		arr.add((double)i*2);
    	}
      	Doubles sma = DoublesFunctions.sma(arr, avglen);

    	for(int i=avglen; i<samples; i++) {
    		double sum = 0.0;
    		for(int j=0; j<avglen; j++) {
    			sum += arr.get(i-j);
    		}
    		double avg = sum / (double)avglen;
    		assertEquals("entry i" + i, sma.get(i), new Double(avg));
    		if (VERBOSE) System.out.println(i + ": " + arr.get(i) + " --> " + avg + " --> " + sma.get(i));
    	}
      	
    }
    
  
    @Test
    public void testCalcLessThanLookback() {
      	DoublesArray arr = new DoublesArray();
      	arr.add(1.0);
      	arr.add(3.0);
      	arr.add(2.0);
      	
      	Doubles sma = DoublesFunctions.sma(arr, 10);
      	
      	assertEquals("1st Entry", sma.get(0), arr.get(0));
	    assertEquals("2nd Entry", sma.get(1), new Double(2.0));
	    assertEquals("3rd Entry", sma.get(2), new Double(2.0));
	    assertEquals("cache.size == origin.size", sma.size(), arr.size());
    }
    
    @Test
    public void testCalcIncrementCalc() {
      	DoublesArray arr = new DoublesArray();
    	int avglen = 10;
      	int samples = 12;
    	for(int i=0; i<samples; i++) {
    		arr.add((double)i*2);
    	}
      	Doubles sma = DoublesFunctions.sma(arr, avglen);

	    assertEquals("cache.size == 0 on init", sma.size(), 0);
      	
    	for(int i=avglen; i<samples; i++) {
    		double sum = 0.0;
    		for(int j=0; j<avglen; j++) {
    			sum += arr.get(i-j);
    		}
    		double avg = sum / (double)avglen;
    		assertEquals("entry i" + i, sma.get(i), new Double(avg));
    		if (VERBOSE) System.out.println(i + ": " + arr.get(i) + " --> " + avg + " --> " + sma.get(i));
    	}

    	
    	int newsamples = 20;
    	for(int i=samples; i<newsamples; i++) {
    		arr.add((double)i*2);
    	}
    	if (VERBOSE) System.out.println();
    	
    	for(int i=avglen; i<newsamples; i++) {
    		double sum = 0.0;
    		for(int j=0; j<avglen; j++) {
    			sum += arr.get(i-j);
    		}
    		double avg = sum / (double)avglen;
    		assertEquals("entry i" + i, sma.get(i), new Double(avg));
    		if (VERBOSE) System.out.println(i + ": " + arr.get(i) + " --> " + avg + " --> " + sma.get(i));
    	}

    }
    
    
}
