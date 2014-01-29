package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class EfficiencyRatio implements DoublesToDoubleFunction {

	public EfficiencyRatio() {}

	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		return calculateEntry(domain, range, index, index+1);
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		int iMax = index + 1;
		int iMin = iMax - length + 1;

		double signal = Math.abs(domain.get(index) - domain.get(iMin));
		double noise = 0.0;
		double a, b;
		for(int j=iMin; j<iMax; j++) {
			a = domain.get(j);
			b = domain.get(j-1);
			noise += Math.abs(a-b);
		}
		return (noise == 0.0) ? 1 : Math.abs(signal/noise);
	}

}
