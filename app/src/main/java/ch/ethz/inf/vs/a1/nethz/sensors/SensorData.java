package ch.ethz.inf.vs.a1.nethz.sensors;

/**
 * Created by jan on 25.09.15.
 * <p/>
 * Object of this type holds one measured value including the name and unit.
 */

public class SensorData {
    public String name;
    public float value;
    public String unit;

    public SensorData(String n, float v, String u) {
        this.name = n;
        this.value = v;
        this.unit = u;
    }
}
