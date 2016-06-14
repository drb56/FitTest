package grapecity.fittest;

import android.graphics.Color;
import android.graphics.Typeface;
import android.renderscript.Element;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.fitness.data.DataType;

import java.util.ArrayList;

/**
 * Created by David.Bickford on 6/9/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private int location;
    DataType dataz;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public View colorView;
        public View padding;
        public TextView title;
        public ViewHolder(View v)
        {
            super(v);
//            colorView = v.findViewById(R.id.colorview);
//            padding = v.findViewById(R.id.paddingview);
//            title = (TextView)v.findViewById(R.id.textview);
//            title.setPadding(0, DimensionUtil.getDimensionSize(5), 0, 0);
            title.setTypeface(null, Typeface.BOLD);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(DataType dataz)
    {
        this.dataz = dataz;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
//        v.setPadding(DimensionUtil.getDimensionSize(10), DimensionUtil.getDimensionSize(10),
//                DimensionUtil.getDimensionSize(10), DimensionUtil.getDimensionSize(10));

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        if(activities != null)
//        {
//            holder.colorView.setBackgroundColor(colorFactory.getNewColor(activities.get(position).getActivity()).getColor());
//            holder.title.setText(activities.get(position).getActivity() + " (" + activities.get(position).getCalorie() + ")");
//            Log.v("MyApp", "Top Loop");
//        }
//        else
//        {
//            Log.v("MyApp", "Bottom Loop");
//            holder.colorView.setBackgroundColor(Color.TRANSPARENT);
//            holder.title.setText("");
//        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return 0;
    }

//    public void setDayActivities(ArrayList<CalorieActivity> activityList, RandomColorFactory colorList)
//    {
//        activities = activityList;
//        colorFactory = colorList;
//        super.notifyDataSetChanged();
//    }
}
