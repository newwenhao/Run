package www.wtu.com.run.interf;

/**
 * Created by Administrator on 2017/4/21.
 */

public interface DistanceComputeInterface {

    /**
     * 返回米
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    double getDistance(double lat1, double lng1, double lat2, double lng2);

    double getShortDistance(double lat1, double lon1, double lat2, double lon2);

    double getLongDistance(double lat1, double lon1, double lat2, double lon2);

    double getDistanceBySpeed(double speed, double timeSpace);

    double getAccurancyDistance(double lat_a, double lng_a, double lat_b, double lng_b);

}

