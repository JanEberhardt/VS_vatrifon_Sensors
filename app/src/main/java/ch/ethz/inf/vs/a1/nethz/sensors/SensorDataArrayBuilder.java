package ch.ethz.inf.vs.a1.nethz.sensors;

import android.hardware.Sensor;

/**
 * Created by jan on 25.09.15.
 * <p/>
 * Builder that returns an array of SensorData objects.
 * Note that all values are currently set to 0. They later get updated
 * when the sensors value changes.
 */

public class SensorDataArrayBuilder {
    // todo: do this in a nice way using string.xml

    public SensorData[] getSensorData(int sensorType) {
        SensorData[] res;
        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                res = new SensorData[1];
                res[0] = new SensorData("Ambient Light", 0, "lux");
                break;
            case Sensor.TYPE_PRESSURE:
                res = new SensorData[1];
                res[0] = new SensorData("Atmospheric pressure", 0, "hPa");
                break;
            case Sensor.TYPE_PROXIMITY:
                res = new SensorData[1];
                res[0] = new SensorData("Distance", 0, "cm");
                break;
            case Sensor.TYPE_ACCELEROMETER:
            case Sensor.TYPE_LINEAR_ACCELERATION:
            case Sensor.TYPE_GRAVITY:
                res = new SensorData[3];
                res[0] = new SensorData("X", 0, "m/s^2");
                res[1] = new SensorData("Y", 0, "m/s^2");
                res[2] = new SensorData("Z", 0, "m/s^2");
                break;
            //todo: add other sensors...
            default:
                res = new SensorData[1];
                res[0] = new SensorData("undefined sensor type: " + sensorType + "  ", 0, "");
        }
        return res;
    }
}
