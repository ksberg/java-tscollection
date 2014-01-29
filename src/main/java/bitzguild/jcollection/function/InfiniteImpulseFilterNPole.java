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
public class InfiniteImpulseFilterNPole implements DoublesToDoubleFunction {

    protected double    _alpha;
    protected int       _lag;
    protected int       _len;

	public InfiniteImpulseFilterNPole() {
		
	}
	
	/**
	 * Approximate initial values with exponential moving average
	 */
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
        _len = length;
        _alpha = 2.0 / ((double)(length + 1.0));
        _lag = ((int)(1.0/_alpha)) - 1;

		if (index < 1) return domain.get(0);
		double priorXma = range.get(index-1);
		return (_alpha * (domain.get(index) - priorXma)) + priorXma;
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int i, int length) {
        return _alpha*(2.0*domain.get(i) - domain.get(i-_lag)) + (1.0 - _alpha)*range.get(i-1);
	}

}
