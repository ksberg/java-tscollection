package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class SumOverPeriod implements DoublesToDoubleFunction {

	protected double    _rollingSum   = 0;

	public SumOverPeriod() {
		_rollingSum = 0.0;
	}
	
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		_rollingSum += domain.get(index);
		return _rollingSum;
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		_rollingSum = _rollingSum + domain.get(index) - domain.get(index - length);
		return _rollingSum;
	}

}
