package bitzguild.jcollection;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.transform.DoublesToDoublesFunctionCache;

import bitzguild.jcollection.function.Highest;
import bitzguild.jcollection.function.Lowest;
import bitzguild.jcollection.function.SimpleMovingAverage;
import bitzguild.jcollection.function.ExponentialMovingAverage;
import bitzguild.jcollection.function.DoubleSmoothedExponentialMovingAverage;
import bitzguild.jcollection.function.TripleSmoothedExponentialMovingAverage;
import bitzguild.jcollection.function.FiniteImpulseFilter5Pole;
import bitzguild.jcollection.function.FiniteImpulseFilter6Pole;
import bitzguild.jcollection.function.KaufmanAdaptiveMovingAverage;
import bitzguild.jcollection.function.EfficiencyRatio;

public class DoublesFunctions {

	/**
	 * <p>
	 * Create a simple moving average from the input domain.
	 * Average values are computed once and cached.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles sma(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new SimpleMovingAverage(), domain, len);
	}
	
	/**
	 * <p>
	 * Create an exponential moving average from the input domain.
	 * Average values are computed once and cached.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles xma(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new ExponentialMovingAverage(len), domain, len);
	}
	
	/**
	 * <p>
	 * Create an double smoothed exponential moving average from the input domain.
	 * Average values are computed once and cached.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles dema(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new DoubleSmoothedExponentialMovingAverage(), domain, len);
	}
	
	/**
	 * <p>
	 * Create an double smoothed exponential moving average from the input domain.
	 * Average values are computed once and cached.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles tema(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new TripleSmoothedExponentialMovingAverage(), domain, len);
	}
	
	/**
	 * <p>
	 * Create a 5-pole finite impulse filter from the input domain.
	 * Average values are computed once and cached.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles fif5(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new FiniteImpulseFilter5Pole(), domain, len);
	}
	
	/**
	 * <p>
	 * Create a 5-pole finite impulse filter from the input domain.
	 * Average values are computed once and cached.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles fif6(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new FiniteImpulseFilter6Pole(), domain, len);
	}
	
	/**
	 * <p>
	 * Create a 5-pole finite impulse filter from the input domain.
	 * Average values are computed once and cached.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles kaufmanAMA(Doubles domain, double lenMin, double lenMax, int adaptPeriod) {
		return new DoublesToDoublesFunctionCache(new KaufmanAdaptiveMovingAverage(lenMin,lenMax), domain, adaptPeriod);
	}
	
	/**
	 * <p>
	 * Create a series which tracks highest value in given look back period.
	 * Values are cached for subsequent lookup.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output
	 */
	public static Doubles highest(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new Highest(), domain, len);
	}
	
	/**
	 * <p>
	 * Create a series which tracks highest value in given look back period.
	 * Values are cached for subsequent lookup.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output
	 */
	public static Doubles lowest(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new Lowest(), domain, len);
	}
	
	/**
	 * <p>
	 * Create series for efficiency ratio, measure of signal-to-noise in
	 * any directional movement.
	 * </p>
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output
	 */
	public static Doubles efficiency(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new EfficiencyRatio(), domain, len);
	}
	
	
	
}
