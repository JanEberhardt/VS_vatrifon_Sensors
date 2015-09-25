package ch.ethz.inf.vs.a1.nethz.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        for (int i = 0; i < sensorData.length; i++) {
            sensorData[i].value = event.values[i];
        }
        dataAdapter.notifyDataSetChanged();

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
        SensorDetail[] res = new SensorDetail[3];
        res[0] = new SensorDetail("Name", sensor.getName());
        res[1] = new SensorDetail("Vendor", sensor.getVendor());
        res[2] = new SensorDetail("Power consumption", sensor.getPower()+" mA");
        // todo: add other stuff...

        return res;
    }
}
