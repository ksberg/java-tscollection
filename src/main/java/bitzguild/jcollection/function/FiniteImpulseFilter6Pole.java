package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

/**
 * Finite Impulse Filter with compensated lag. This function
 * uses a fixed-length look back of six events. Reference is
 * July 2002 article in Technical Analysis of Stocks & Commodities,
 * by John F. Ehlers. The aim of so-called 'zero-lag' functions
 * is to counter effect of filtering lag through mathematical
 * adjustment.
 */
public class FiniteImpulseFilter6Pole implements DoublesToDoubleFunction {

	public FiniteImpulseFilter6Pole() {}
	
	/**
	 * Approximate values with 5-period exponential moving average
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
		return (domain.get(i) + 3.5*domain.get(i-1) + 4.5*domain.get(i-2) + 3*domain.get(i-3)
				+ 0.5*domain.get(i-4) - 0.5*domain.get(i-5) - 1.5*domain.get(i-6))/10.5;
	}

}
