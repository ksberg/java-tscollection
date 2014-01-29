package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class ExponentialMovingAverage implements DoublesToDoubleFunction {

	protected double _alpha;
	protected double _length;
	protected double _priorRange;
	
	public ExponentialMovingAverage() {
		_length = 10;
		_alpha = 2.0 / (_length + 1.0);
	}
	
	public ExponentialMovingAverage(int length) {
		_length = Math.min(1, length);
		_alpha = 2.0 / (_length + 1.0);
	}
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		if (index < 1) return domain.get(0);
		return this.calculateEntry(domain, range, index, length);
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		double priorXma = range.get(index-1);
		return (_alpha * (domain.get(index) - priorXma)) + priorXma;
	}

}
