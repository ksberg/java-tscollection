package bitzguild.jcollection;

import bitzguild.jcollection.Doubles;
import bitzguild.jcollection.function.*;
import bitzguild.jcollection.transform.DoublesToDoublesFunctionCache;

public class DoublesFunctions {

	/**
	 * Create a simple moving average from the input domain.
	 * Average values are computed once and cached.
     *
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles sma(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new SimpleMovingAverage(), domain, len);
	}
	
	/**
	 * Create an exponential moving average from the input domain.
	 * Average values are computed once and cached.
     *
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles xma(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new ExponentialMovingAverage(len), domain, len);
	}
	
	/**
	 * Create an double smoothed exponential moving average from the input domain.
	 * Average values are computed once and cached.
     *
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles dema(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new DoubleSmoothedExponentialMovingAverage(), domain, len);
	}
	
	/**
	 * Create an double smoothed exponential moving average from the input domain.
	 * Average values are computed once and cached.
     *
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output range of moving averages
	 */
	public static Doubles tema(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new TripleSmoothedExponentialMovingAverage(), domain, len);
	}
	
	/**
	 * Create a 5-pole finite impulse filter from the input domain.
	 * Average values are computed once and cached.
     *
	 * @param domain Doubles input domain
	 * @return Doubles output range of moving averages
	 */
	public static Doubles fif5(Doubles domain) {
		return new DoublesToDoublesFunctionCache(new FiniteImpulseFilter5Pole(), domain, 5);
	}
	
	/**
	 * Create a 6-pole finite impulse filter from the input domain.
	 * Average values are computed once and cached.
     *
	 * @param domain Doubles input domain
	 * @return Doubles output range of moving averages
	 */
	public static Doubles fif6(Doubles domain) {
		return new DoublesToDoublesFunctionCache(new FiniteImpulseFilter6Pole(), domain, 6);
	}


    /**
     * Create a 3-pole infinite impulse filter from the input domain.
     *
     * @param domain Doubles input domain
     * @return Doubles output range of moving averages
     */
    public static Doubles iif3(Doubles domain) {
        return new DoublesToDoublesFunctionCache(new InfiniteImpulseFilter3Pole(), domain, 3);
    }

    /**
     * Create a N-pole infinite impulse filter from the input domain.
     *
     * @param domain Doubles input domain
     * @param len int length of look back period
     * @return Doubles output range of moving averages
     */
    public static Doubles iifN(Doubles domain, int len) {
        return new DoublesToDoublesFunctionCache(new InfiniteImpulseFilterNPole(), domain, len);
    }

    /**
     * Create Kaufman adaptive moving average with given minimum, maximum and adaptive periods.
     *
     * @param domain input
     * @param lenMin fast moving average period
     * @param lenMax slow moving average period
     * @param adaptPeriod period to evaluate adaptation
     * @return Doubles output
     */
	public static Doubles kaufmanAMA(Doubles domain, double lenMin, double lenMax, int adaptPeriod) {
		return new DoublesToDoublesFunctionCache(new KaufmanAdaptiveMovingAverage(lenMin,lenMax), domain, adaptPeriod);
	}


    /**
     * Create a Repeated Mean Velocity series from the input domain.
     *
     * @param domain input
     * @param len int length of look back period
     * @return Doubles output range of moving averages
     */
    public static Doubles rmv(Doubles domain, int len) {
        return new DoublesToDoublesFunctionCache(new RepeatedMedianVelocity(), domain, len);
    }

    /**
     * Create a Momentum series from the input domain.
     *
     * @param domain input
     * @param len int length of look back period
     * @return Doubles output range of moving averages
     */
    public static Doubles momentum(Doubles domain, int len) {
        return new DoublesToDoublesFunctionCache(new Momentum(), domain, len);
    }


    /**
	 * Create a series which tracks highest value in given look back period.
	 * Values are cached for subsequent lookup.
     *
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output
	 */
	public static Doubles highest(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new Highest(), domain, len);
	}
	
	/**
	 * Create a series which tracks highest value in given look back period.
	 * Values are cached for subsequent lookup.
     *
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output
	 */
	public static Doubles lowest(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new Lowest(), domain, len);
	}
	
	/**
	 * Create series for efficiency ratio, measure of signal-to-noise in
	 * any directional movement.
     *
	 * @param domain Doubles input domain
	 * @param len length of moving average
	 * @return Doubles output
	 */
	public static Doubles efficiency(Doubles domain, int len) {
		return new DoublesToDoublesFunctionCache(new EfficiencyRatio(), domain, len);
	}
	


	
}
