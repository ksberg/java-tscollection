package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class ExponentialMovingAverage implements DoublesToDoubleFunction {

	protected double _alpha;
	protected double _length;

	public ExponentialMovingAverage() {
	}
	

	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		if (index < 1) {
            _length = Math.min(1, length);
            _alpha = 2.0 / (_length + 1.0);
            return domain.get(0);
        }
		return this.calculateEntry(domain, range, index, length);
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		double priorXma = range.get(index-1);
		return (_alpha * (domain.get(index) - priorXma)) + priorXma;
	}

}
