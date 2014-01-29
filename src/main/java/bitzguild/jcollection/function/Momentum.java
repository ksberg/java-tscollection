package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class Momentum implements DoublesToDoubleFunction {

	public Momentum() {}

    /**
     * Initialize momentum by incrementing period from zero up to look back.
     *
     * @param domain input
     * @param range output
     * @param index current index
     * @param length lookback
     * @return double
     */
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
        return domain.get(index) - domain.get(0);
	}

    /**
     *
     * @param domain input
     * @param range output
     * @param index current index
     * @param length lookback
     * @return double
     */
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
        return domain.get(index) - domain.get(index-length+1);
	}


}
