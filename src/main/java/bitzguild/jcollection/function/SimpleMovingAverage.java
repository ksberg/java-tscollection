package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class SimpleMovingAverage implements DoublesToDoubleFunction {

	protected double    _rollingSum   = 0;

	public SimpleMovingAverage() {
		_rollingSum = 0;
	}
	
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		_rollingSum += domain.get(index);
		double len = index+1;
		return _rollingSum/len;
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		_rollingSum = _rollingSum + domain.get(index) - domain.get(index - length);
		return _rollingSum/(double)length;
	}

}
