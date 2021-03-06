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
import java.util.ListIterator;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorData[] sensorData;
    private ArrayAdapter<SensorData> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        int sensorType = (int) getIntent().getSerializableExtra("sensorType");
        int hashCode = (int) getIntent().getSerializableExtra("hashCode");

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        // needs to be done like this, because there can be multiple same typed sensors in a device
        List<Sensor> allSensors = sensorManager.getSensorList(sensorType);
        ListIterator<Sensor> iterator = allSensors.listIterator();
        sensor = iterator.next();
        while(sensor.hashCode() != hashCode) {
            if (iterator.hasNext())
                sensor = iterator.next();
            else
                throw new RuntimeException("sensor doesn't exist?");
        }

        // set the title
        TextView title = (TextView) findViewById(R.id.sensorTitle);
        title.setText(sensor.getName());

        // set the static values: name, vendor and stuff...
        ListView detailList = (ListView) findViewById(R.id.sensorDetails);
        SensorDetail[] sd = getDetails();
        ArrayAdapter<SensorDetail> detailAdapter = new SensorDetailAdapter(this, R.layout.sensor_detail_row, sd);
        detailList.setAdapter(detailAdapter);

        // set the dynamic values: actual sensor readings that later get updated...
        ListView dataList = (ListView) findViewById(R.id.sensorValues);
        SensorDataArrayBuilder builder = new SensorDataArrayBuilder();
        this.sensorData = builder.getSensorData(sensorType);
        dataAdapter = new SensorDataAdapter(this, R.layout.sensor_data_row, sensorData);
        dataList.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        try {
            for (int i = 0; i < event.values.length; i++) {
                sensorData[i].value = event.values[i];
            }
            dataAdapter.notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("###", e.toString());
        }
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

    private SensorDetail[] getDetails(){
        SensorDetail[] res = new SensorDetail[6];
        res[0] = new SensorDetail(R.string.sensor_name, sensor.getName());
        res[1] = new SensorDetail(R.string.sensor_vendor, sensor.getVendor());
        res[3] = new SensorDetail(R.string.sensor_version, sensor.getVersion()+"");
        res[2] = new SensorDetail(R.string.sensor_power, sensor.getPower()+" mA");
        res[3] = new SensorDetail(R.string.sensor_min_delay, sensor.getMinDelay()+" ms");
        res[4] = new SensorDetail(R.string.sensor_max_range, sensor.getMaximumRange()+"");
        res[5] = new SensorDetail(R.string.sensor_resolution, sensor.getResolution()+"");
        return res;
    }
}
