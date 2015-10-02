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
    public SensorData[] getSensorData(int sensorType) {
        SensorData[] res;
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
            case Sensor.TYPE_LINEAR_ACCELERATION:
            case Sensor.TYPE_GRAVITY:
                res = new SensorData[3];
                res[0] = new SensorData(R.string.sensor_x, 0, R.string.sensor_mpss);
                res[1] = new SensorData(R.string.sensor_y, 0, R.string.sensor_mpss);
                res[2] = new SensorData(R.string.sensor_z, 0, R.string.sensor_mpss);
                break;
            case Sensor.TYPE_GYROSCOPE:
                res = new SensorData[3];
                res[0] = new SensorData(R.string.sensor_x, 0, R.string.sensor_radps);
                res[1] = new SensorData(R.string.sensor_y, 0, R.string.sensor_radps);
                res[2] = new SensorData(R.string.sensor_z, 0, R.string.sensor_radps);
                break;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                res = new SensorData[6];
                res[0] = new SensorData(R.string.sensor_x, 0, R.string.sensor_radps);
                res[1] = new SensorData(R.string.sensor_y, 0, R.string.sensor_radps);
                res[2] = new SensorData(R.string.sensor_z, 0, R.string.sensor_radps);
                res[3] = new SensorData(R.string.sensor_drift_x, 0, R.string.sensor_radps);
                res[4] = new SensorData(R.string.sensor_drift_y, 0, R.string.sensor_radps);
                res[5] = new SensorData(R.string.sensor_drift_z, 0, R.string.sensor_radps);
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                res = new SensorData[4];
                res[0] = new SensorData(R.string.sensor_x, 0, R.string.sensor_unitless);
                res[1] = new SensorData(R.string.sensor_y, 0, R.string.sensor_unitless);
                res[2] = new SensorData(R.string.sensor_z, 0, R.string.sensor_unitless);
                res[3] = new SensorData(R.string.sensor_scalar_component, 0, R.string.sensor_unitless);
                break;
            case Sensor.TYPE_STEP_COUNTER:
                res = new SensorData[1];
                res[0] = new SensorData(R.string.sensor_steps, 0, R.string.sensor_no_unit);
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                res = new SensorData[3];
                res[0] = new SensorData(R.string.sensor_x, 0, R.string.sensor_unitless);
                res[1] = new SensorData(R.string.sensor_y, 0, R.string.sensor_unitless);
                res[2] = new SensorData(R.string.sensor_z, 0, R.string.sensor_unitless);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                res = new SensorData[3];
                res[0] = new SensorData(R.string.sensor_x, 0, R.string.sensor_mutesla);
                res[1] = new SensorData(R.string.sensor_y, 0, R.string.sensor_mutesla);
                res[2] = new SensorData(R.string.sensor_z, 0, R.string.sensor_mutesla);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                res = new SensorData[6];
                res[0] = new SensorData(R.string.sensor_x, 0, R.string.sensor_mutesla);
                res[1] = new SensorData(R.string.sensor_y, 0, R.string.sensor_mutesla);
                res[2] = new SensorData(R.string.sensor_z, 0, R.string.sensor_mutesla);
                res[3] = new SensorData(R.string.sensor_iron_bias_x, 0, R.string.sensor_mutesla);
                res[4] = new SensorData(R.string.sensor_iron_bias_y, 0, R.string.sensor_mutesla);
                res[5] = new SensorData(R.string.sensor_iron_bias_z, 0, R.string.sensor_mutesla);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
            case Sensor.TYPE_TEMPERATURE:
                res = new SensorData[1];
                res[0] = new SensorData(R.string.sensor_temp, 0, R.string.sensor_degreecel);
                break;
            case Sensor.TYPE_LIGHT:
                res = new SensorData[1];
                res[0] = new SensorData(R.string.sensor_light, 0, R.string.sensor_lux);
                break;
            case Sensor.TYPE_PRESSURE:
                res = new SensorData[1];
                res[0] = new SensorData(R.string.sensor_pressure, 0, R.string.sensor_hpa);
                break;
            case Sensor.TYPE_PROXIMITY:
                res = new SensorData[1];
                res[0] = new SensorData(R.string.sensor_distance, 0, R.string.sensor_cm);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                res = new SensorData[1];
                res[0] = new SensorData(R.string.sensor_humidity, 0, R.string.sensor_relpercent);
                break;
            // default case, just print the sensor readings...
            default:
                res = new SensorData[1];
                res[0] = new SensorData(R.string.sensor_undefined, 0, R.string.sensor_no_unit);
                res[1] = new SensorData(R.string.sensor_undefined, 0, R.string.sensor_no_unit);
                res[2] = new SensorData(R.string.sensor_undefined, 0, R.string.sensor_no_unit);
        }
        return res;
    }
}
