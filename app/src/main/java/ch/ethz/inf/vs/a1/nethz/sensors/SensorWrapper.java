package ch.ethz.inf.vs.a1.nethz.sensors;

import android.hardware.Sensor;

import java.io.Serializable;

/**
 * Created by jan on 24.09.15.
 *
 * kind of dumb, but makes life easier...
 *
 */
public class SensorWrapper implements Serializable{
    public Sensor s;

    public SensorWrapper(Sensor sensor){
        this.s = sensor;
    }

    public String toString(){
        return s.getName();
    }


}
