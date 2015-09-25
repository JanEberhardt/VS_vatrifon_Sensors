package ch.ethz.inf.vs.a1.nethz.sensors;

import android.hardware.Sensor;

/**
 * Created by jan on 25.09.15.
 * <p/>
 * Builder that returns an array of SensorViewData objects.
 * Note that all values are currently set to 0. They later get updated
 * when the sensors value changes.
 */

public class SensorViewDataBuilder {

    public SensorViewData[] getSensorViewDatas(int sensorType) {
        SensorViewData[] res;
        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                res = new SensorViewData[1];
                res[0] = new SensorViewData("Ambient Light", 0, "lux");
                break;
            case Sensor.TYPE_PRESSURE:
                res = new SensorViewData[1];
                res[0] = new SensorViewData("Atmospheric pressure", 0, "hPa");
                break;
            case Sensor.TYPE_PROXIMITY:
                res = new SensorViewData[1];
                res[0] = new SensorViewData("Distance", 0, "cm");
                break;
            //todo: extend for other sensors...
            default:
                res = new SensorViewData[1];
                res[0] = new SensorViewData("undefined sensor type: " + sensorType, 0, "");
        }
        return res;
    }
}
