package bitzguild.jcollection.function;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.DoublesFunctions;
import bitzguild.jcollection.transform.DoublesToDoubleFunction;

/**
 * Kaufman Adaptive Moving Average, an exponential
 * moving average with modulated smoothing. The
 * smoothing is exponential and based on notion
 * of price efficiency, or so-called signal-to-noise.
 * This measures the movement of price in one direction
 * vs. counter directions. When the efficiency ratio is
 * high, most price movement is in a single direction.
 * With high efficiency, the Kaufman AMA shortens the
 * effective average length and quickly adapts to price.
 * When efficiency is low the Kaufman AMA lengthens
 * the effective average length and price changes
 * have less impact. During these periods the average
 * will nearly flatten out.
 */
public class KaufmanAdaptiveMovingAverage implements DoublesToDoubleFunction {

	protected Doubles				_effRatio = null;

	protected int					_effPeriod;	// this is set by calc length
	protected double				_lenFast;	// fast fractional length
	protected double				_lenSlow;	// slow fractional length
	
	protected double				_alphaFast;	// fast smoothing constant
	protected double				_alphaSlow;	// slow smoothing constant
	
	public KaufmanAdaptiveMovingAverage() {
		setMinMax(2.0, 30.0);
	}
	
	public KaufmanAdaptiveMovingAverage(double fastLength, double slowLength) {
		setMinMax(fastLength, slowLength);
	}

	// -------------------------------------------------
	// Accessors
	// -------------------------------------------------

	public int getEfficiencyPeriod()	{ return _effPeriod; }
	
	public void setMinMax(double fastLen, double slowLen) {
		_lenFast = fastLen;
		_lenSlow = slowLen;
		
		_alphaFast = 2.0 / (_lenFast + 1);
		_alphaSlow = 2.0 / (_lenSlow + 1);
	}

	public double getFastLength() 	{ return _lenFast; }
	public double getSlowLength() 	{ return _lenSlow; }
	
	// -------------------------------------------------
	// Inner Utilities
	// -------------------------------------------------

	private void set(Doubles domain, int len) {
		_effPeriod = len;
		_effRatio = DoublesFunctions.efficiency(domain, len);
	}

	// -------------------------------------------------
	// DoublesToDoubleFunction interface
	// -------------------------------------------------
	
	/**
	 * Approximate values with an exponential moving average
	 */
	@Override
	public double calculateFirst(Doubles domain, Doubles range, int index, int length) {
		if (index < 1) {
            set(domain, length);
            return domain.get(0);
        }
		double alpha = 2.0 / (length + 1.0);
		double priorXma = range.get(index-1);
		return (alpha * (domain.get(index) - priorXma)) + priorXma;
	}

	@Override
	public double calculateEntry(Doubles domain, Doubles range, int i, int length) {
		double er = _effRatio.get(i);
		double smooth = Math.pow(er*(_alphaFast - _alphaSlow) + _alphaSlow,2.0);
		double amaPrior = range.get(i-1);
		return amaPrior + smooth*(domain.get(i) - amaPrior);
	}

}
