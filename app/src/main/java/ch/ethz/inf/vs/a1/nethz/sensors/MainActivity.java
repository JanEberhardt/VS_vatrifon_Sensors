package ch.ethz.inf.vs.a1.nethz.sensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Sensor> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get sensor manager
        SensorManager sm = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        sensors = sm.getSensorList(Sensor.TYPE_ALL);
        Iterator iterator = sensors.iterator();
        List<SensorWrapper> sensorWrappers = new LinkedList<>();


        // create the wrapper objects
        while(iterator.hasNext()){
            SensorWrapper temp = new SensorWrapper((Sensor) iterator.next());
            sensorWrappers.add(temp);
        }

        // give the array of wrapper objects to the adapter
        ArrayAdapter<SensorWrapper> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sensorWrappers);

        // get the listView and add the array
        ListView sensorList = (ListView) findViewById(R.id.sensorsList);
        sensorList.setAdapter(adapter);

        // define onClick listener for events
        sensorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3){
                Intent i = new Intent(getApplicationContext(), SensorActivity.class);
                SensorWrapper temp = (SensorWrapper) adapter.getItemAtPosition(position);
                i.putExtra("sensorType", temp.s.getType());
                i.putExtra("hashCode", temp.s.hashCode());
                startActivity(i);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
