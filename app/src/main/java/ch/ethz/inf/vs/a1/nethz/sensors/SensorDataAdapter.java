package ch.ethz.inf.vs.a1.nethz.sensors;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by jan on 25.09.15.
 * <p/>
 * Custom ArrayAdapter for our SensorData array.
 * Simply adds all the important things (name, value, until) per
 * measured value by the sensor to the listView (row-wise)
 * <p/>
 * Could easily be extended to also display other stuff, e.g: a description of
 * what that sensors value means.
 */
public class SensorDataAdapter extends ArrayAdapter<SensorData> {

    private int layoutResourceId;
    private Context context;
    private SensorData[] data;

    public SensorDataAdapter(Context context, int resource, SensorData[] data) {
        super(context, resource, data);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SensorDataHolder holder;

        // if not yet defined get the layout of the row
        if (row == null) {
            // get the layout of a single listView item (one sensor value)
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            // initialize holder object (static class) for reuse later
            holder = new SensorDataHolder();
            holder.name = (TextView) row.findViewById(R.id.sensorViewName);
            holder.value = (TextView) row.findViewById(R.id.sensorViewValue);
            holder.unit = (TextView) row.findViewById(R.id.sensorViewUnit);

            // add the holder object as a tag to the view (listView item)
            row.setTag(holder);
        } else {
            // just get the value which was saved in the view
            holder = (SensorDataHolder) row.getTag();
        }

        // set the actual texts
        holder.name.setText(data[position].name+":");
        holder.value.setText("" + data[position].value);
        holder.unit.setText(data[position].unit);

        return row;
    }

    static class SensorDataHolder {
        TextView name;
        TextView value;
        TextView unit;
    }
}

