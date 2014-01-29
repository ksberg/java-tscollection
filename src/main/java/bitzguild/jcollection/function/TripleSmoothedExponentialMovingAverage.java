package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;
import bitzguild.jcollection.DoublesFunctions;

/**
 * Triple Smoothed Exponential Moving Average (a.k.a. Tema). Member of
 * the so-called 'zero-lag' averages, which counter lag effect through
 * some mathematical adjustment.
 * <br>
 * <code>(3 * XAverage(Price, Len)) - (3 * XAverage(XAverage(Price, Len), Len)) + XAverage(XAverage(XAverage(Price, Len), Len), Len)</code>
 */
public class TripleSmoothedExponentialMovingAverage extends DoubleSmoothedExponentialMovingAverage {

	protected Doubles   _xmaXmaXma;
	
	public TripleSmoothedExponentialMovingAverage() {
		super();
	}
	
	protected void cache(Doubles domain, int length) {
		super.cache(domain, length);
		_xmaXmaXma  = DoublesFunctions.xma(_xma, length);
	}
	
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		return this.calculateEntry(domain, range, index, length);
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		cache(domain, length);
		return (3 * _xma.get(index)) - (3 * _xmaXma.get(index)) + _xmaXmaXma.get(index);
	}

}
