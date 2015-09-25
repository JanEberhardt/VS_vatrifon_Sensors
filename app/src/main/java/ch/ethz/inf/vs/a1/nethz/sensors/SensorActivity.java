package ch.ethz.inf.vs.a1.nethz.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor sensor;
    private SensorManager sensorManager;
    private ListView valuesList;
    private SensorViewData[] svd;
    private ArrayAdapter<SensorViewData> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        int sensorType = (int) getIntent().getSerializableExtra("sensorType");
        Log.d("###", "sensorType: "+sensorType);

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(sensorType);
        this.sensor = sensors.get(0);

        TextView title = (TextView) findViewById(R.id.sensorTitle);
        title.setText(sensor.getName());

        valuesList = (ListView) findViewById(R.id.sensorValues);

        SensorViewDataBuilder builder = new SensorViewDataBuilder();
        this.svd = builder.getSensorViewDatas(sensorType);

        adapter = new SensorAdapter(this, R.layout.sensor_detail_row, svd);

        valuesList.setAdapter(adapter);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        for (int i = 0; i < svd.length; i++) {
            svd[i].value = event.values[i];
        }
        adapter.notifyDataSetChanged();
        Log.d("###", "data has changed");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
