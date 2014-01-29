package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.immutable.BoundedDoublesView;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

public class SecondOrderMovingAverage implements DoublesToDoubleFunction {

    public static final boolean DEBUG = true;

    protected SimpleMovingAverage   _sma;

	public SecondOrderMovingAverage() {
        _sma = new SimpleMovingAverage();
	}


	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
        return calculateEntry(new BoundedDoublesView(domain), new BoundedDoublesView(range), index, length);
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {


        int i;
        int iMax = index+1;
        int iMin = iMax - length;
        int ii = 0;
        double slope = 0.0;
        double factor = 1.0;
        for(i=iMin; i<iMax; i++) {
            ii = iMin + iMax - i -1;
            factor = 1.0 + (2.0 * (i-iMin)); // i-1
            slope += domain.get(ii)*((length - factor)/2.0);

            if (DEBUG) {
                System.err.print("i = " + i);
                System.err.print(" ii = " + ii);
                System.err.print(" i-iMin = " + (i-iMin));
                System.err.print(" factor = " + factor);
                System.err.print(" domain = " + domain.get(ii));
                System.err.print(" slope = " + slope);
                System.err.println();
            }
        }

        if (DEBUG) System.err.println(((6.0 * slope)/((length + 1)*length)));

        return _sma.calculateEntry(domain, range, index, length)
                + (6.0 * slope)/((length + 1)*length);
    }




}
