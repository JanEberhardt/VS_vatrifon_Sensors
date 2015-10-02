package ch.ethz.inf.vs.a1.nethz.sensors;

/**
 * Created by jan on 25.09.15.
 * <p/>
 * Stores one detail about the sensor.
 */
public class SensorDetail {
    public int name;
    public String detail;

    public SensorDetail(int name, String detail) {
        this.name = name;
        this.detail = detail;
    }
}
