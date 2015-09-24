package ch.ethz.inf.vs.a1.nethz.sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        int sensorType = (int) getIntent().getSerializableExtra("sensorType");

        SensorManager sm = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(sensorType);
        this.sensor = sensors.get(0);

        TextView title = (TextView) findViewById(R.id.sensorTitle);
        title.setText(sensor.getName());
        //TODO: print the rest of the data (again in a listView)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
