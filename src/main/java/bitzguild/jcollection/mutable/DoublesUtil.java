package bitzguild.jcollection.mutable;

import bitzguild.jcollection.Doubles;

/**
 * Created by ksvenberg on 1/29/14.
 */
public class DoublesUtil {


    public static double median(Double[] sortedData) {
        return quantile(sortedData, 0.5);
    }


    /**
     * <p>
     * Answer the actual or interpolated quantile. Input data
     * must be sorted.
     * </p>
     *
     * @param sortedData array of sorted Double
     * @param phi partition factor
     * @return double
     */
    public static double quantile(Double[] sortedData, double phi) {
        int n = sortedData.length;
        double index = phi * (n - 1) ;
        int lhs = (int)index ;
        double delta = index - lhs ;
        double result;

        if (n == 0) return 0.0 ;

        if (lhs == n - 1) {
            result = sortedData[lhs] ;
        } else {
            result = (1 - delta) * sortedData[lhs] + delta * sortedData[lhs + 1] ;
        }
        return result ;
    }



}
