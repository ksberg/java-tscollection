package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.mutable.DoublesArray;
import bitzguild.jcollection.mutable.DoublesUtil;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RepeatedMedianVelocity implements DoublesToDoubleFunction {

    public DoublesArray     _tmpOuter;
    public DoublesArray		_tmpInner;


	public RepeatedMedianVelocity() {
        _tmpOuter = new DoublesArray();
        _tmpInner = new DoublesArray();
	}
	
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
        if (index < 1) return domain.get(0);
        double alpha = 2.0 / (length + 1.0);
        double priorXma = range.get(index-1);
        return (alpha * (domain.get(index) - priorXma)) + priorXma;
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int index, int length) {

        int ijMin = 0;
        int ijMax = length;

        _tmpOuter.clear();
        for(int i=ijMin; i<=ijMax; i++) {
            _tmpInner.clear();
            for(int j=ijMin; j<ijMax; j++) {
                if (i != j) {
                    double v = (domain.get(index) - domain.get(index-i)) / (i - j);
                    _tmpInner.add(v);
                }
            }
            Double[] inner = _tmpInner.toArray(new Double[_tmpInner.size()]);
            Arrays.sort(inner);
            _tmpOuter.add(DoublesUtil.median(inner));
        }
        Double[] outer = _tmpOuter.toArray(new Double[_tmpOuter.size()]);
        Arrays.sort(outer);
        return DoublesUtil.median(outer);
    }

}
