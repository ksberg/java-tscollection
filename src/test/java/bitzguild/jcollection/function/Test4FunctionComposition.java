package bitzguild.jcollection.function;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;


import bitzguild.jcollection.mutable.DoublesArray;
import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.DoublesFunctions;


public class Test4FunctionComposition {

	public static final boolean VERBOSE = true;
	
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void testForComposition() {
      	DoublesArray arr = new DoublesArray();
    	int avglen = 10;
      	int samples = 15;
    	for(int i=0; i<samples; i++) {
    		arr.add((double)i*2);
    	}
      	Doubles sma = DoublesFunctions.sma(arr, avglen);
      	Doubles smaSma = DoublesFunctions.sma(sma, avglen);

    	for(int i=avglen; i<samples; i++) {
    		double sum = 0.0;
    		for(int j=0; j<avglen; j++) {
    			sum += arr.get(i-j);
    		}
    		double avg = sum / (double)avglen;
    		assertEquals("entry i" + i, sma.get(i), new Double(avg));
    		assertTrue("sma(sma) vs. sma", sma.get(i) > smaSma.get(i));
    		if (VERBOSE) System.out.println(i + ": " + arr.get(i) + " --> " + avg + " --> " + sma.get(i) + " --> " + smaSma.get(i));
    	}
      	
    }
}
