package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;
import bitzguild.jcollection.DoublesFunctions;

/**
 * Double Smoothed Exponential Moving Average (a.k.a. Dema). Member of
 * the so-called 'zero-lag' averages, which counter lag effect through
 * some mathematical adjustment.
 * <br>
 * <code>(2 * XAverage(Price, Len)) - (XAverage(XAverage(Price, Len), Len))</code>
 */
public class DoubleSmoothedExponentialMovingAverage implements DoublesToDoubleFunction {

	protected Doubles	_xma;
	protected Doubles	_xmaXma;
	
	public DoubleSmoothedExponentialMovingAverage() {
		_xma = null;
	}
	
	
	protected void cache(Doubles domain, int length) {
		_xma = DoublesFunctions.xma(domain, length);
		_xmaXma = DoublesFunctions.xma(_xma, length);
	}
	
	protected void cacheIff(Doubles domain, int length) {
		if (_xma == null) cache(domain, length);
	}
	
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		return this.calculateEntry(domain, range, index, length);
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		cacheIff(domain, length);
		return (2 * _xma.get(index)) - _xmaXma.get(index);
		
	}

}
