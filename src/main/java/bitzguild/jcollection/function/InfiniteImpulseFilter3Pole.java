package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

/**
 * Infinite Impulse Filter with compensated lag. This function
 * uses a fixed-length look back of three events. Reference is
 * July 2002 article in Technical Analysis of Stocks & Commodities,
 * by John F. Ehlers. The aim of so-called 'zero-lag' functions
 * is to counter effect of filtering lag through mathematical
 * adjustment.
 */
public class InfiniteImpulseFilter3Pole implements DoublesToDoubleFunction {

	public InfiniteImpulseFilter3Pole() {
		
	}
	
	/**
	 * Approximate initial values with exponential moving average
	 */
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		if (index < 1) return domain.get(0);
		double alpha = 2.0 / (length + 1.0);
		double priorXma = range.get(index-1);
		return (alpha * (domain.get(index) - priorXma)) + priorXma;
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int i, int length) {
        return 0.2*(2.0*domain.get(i) - domain.get(i-3)) + 0.8*range.get(i-1);
	}

}
