package bitzguild.jcollection.transform;

import bitzguild.jcollection.Doubles;

public interface DoublesToDoubleFunction {
	public double calculateFirst(Doubles domain, Doubles range, int index, int length);
	public double calculateEntry(Doubles domain, Doubles range, int index, int length);
}
