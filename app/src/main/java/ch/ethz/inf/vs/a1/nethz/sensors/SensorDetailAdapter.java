package ch.ethz.inf.vs.a1.nethz.sensors;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by jan on 25.09.15.
 * <p/>
 * Custom array adaptor for the details of a sensor.
 */
public class SensorDetailAdapter extends ArrayAdapter<SensorDetail> {

    private int layoutResourceId;
    private Context context;
    private SensorDetail[] data;

    public SensorDetailAdapter(Context context, int resource, SensorDetail[] data) {
        super(context, resource, data);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SensorDetailHolder holder;

        // if not yet defined get the layout of the row
        if (row == null) {
            // get the layout of a single listView item (one sensor value)
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            // initialize holder object (static class) for reuse later
            holder = new SensorDetailHolder();
            holder.name = (TextView) row.findViewById(R.id.sensorViewName);
            holder.detail = (TextView) row.findViewById(R.id.sensorViewDetail);

            // add the holder object as a tag to the view (listView item)
            row.setTag(holder);
        } else {
            // just get the value which was saved in the view
            holder = (SensorDetailHolder) row.getTag();
        }

        // set the actual texts
        holder.name.setText(data[position].name);
        holder.detail.setText(data[position].detail);

        return row;
    }

    static class SensorDetailHolder {
        TextView name;
        TextView detail;
    }
}
