package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.immutable.BoundedDoublesView;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class PercentRateOfChange implements DoublesToDoubleFunction {

	public PercentRateOfChange() {}
	
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
        return calculateEntry(new BoundedDoublesView(domain), range, index, length);
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
        int iBack = Math.max(0, index - length);
        double curr = domain.get(index);
        double base = domain.get(iBack);
        return (base != 0.0) ? (curr - base)/base : 0.0;
    }


}
