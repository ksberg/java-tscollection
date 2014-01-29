package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class Lowest implements DoublesToDoubleFunction {

	public Lowest() {
	}
	
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		return domain.get(lowestInRange(domain, 0, length-1));
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
		return domain.get(lowestInRange(domain, index-length+1, index));
	}

	public int lowestInRange(Doubles dbls, int from, int upto) {
		int iMin = Math.max(0, from);
		int iMax = Math.min(upto+1, dbls.size());
		int iLow = iMin;
		double low = dbls.get(iLow);
		for(int i=iMin; i<iMax; i++) {
			double val = dbls.get(i);
			if (val < low) {
				iLow = i;
				low = val;
			}
		}
		return iLow;
	}
	
}
