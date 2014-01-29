package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.DoublesFunctions;
import bitzguild.jcollection.immutable.BoundedDoublesView;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;
import bitzguild.jcollection.transform.DoublesToDoublesFunctionCache;

public class StandardDeviation implements DoublesToDoubleFunction {

    public static final boolean DEBUG = true;

    protected Doubles                   _centerPoint;
    protected DoublesToDoubleFunction   _centeringFunction;


	public StandardDeviation() {
        _centeringFunction = new ExponentialMovingAverage();
    }

    public StandardDeviation(DoublesToDoubleFunction centerFunction) {
        _centeringFunction = centerFunction;
    }

    protected double sumSquares(Doubles domain, int j, int len) {
        int iMin = j - len;
        int iMax = j;
        double sumSqr = 0.0;
        for(int i=iMin; i<iMax; i++) {
            double diff = domain.get(i) - _centerPoint.get(i);
            sumSqr += diff*diff;
        }
        return sumSqr;
    }

    protected Doubles setupCenter(Doubles domain, int length) {
        return new DoublesToDoublesFunctionCache(_centeringFunction, domain, length);
    }

	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
        if (_centerPoint == null) _centerPoint = setupCenter(domain, length);
        return calculateEntry(new BoundedDoublesView(domain), range, index, length);
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {
        if (length == 0) return 0.0;
        double sumSqr = sumSquares(domain, index, length);
        return Math.sqrt(sumSqr/((double)length));
    }




}
