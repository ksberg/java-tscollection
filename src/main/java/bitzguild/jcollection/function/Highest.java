package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class Highest implements DoublesToDoubleFunction {

	public Highest() {
	}
	
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		return domain.get(highestInRange(domain, 0, length-1));
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		return domain.get(highestInRange(domain, index-length+1, index));
	}

	public int highestInRange(Doubles dbls, int from, int upto) {
		int iMin = Math.max(0, from);
		int iMax = Math.min(upto+1, dbls.size());
		int iHigh = iMin;
		double high = dbls.get(iHigh);
		for(int i=iMin; i<iMax; i++) {
			double val = dbls.get(i);
			if (val > high) {
				iHigh = i;
				high = val;
			}
		}
		return iHigh;
	}
	
}
