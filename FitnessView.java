

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.fitness.data.DataType;

import java.util.ArrayList;

/**
 * Created by David.Bickford on 6/9/2016.
 */
public class FitnessView extends CardView
{
    DataType dataz;
    public FitnessView(Context context)
    {
        super(context);
        init(context);
    }

    public FitnessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FitnessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context)
    {
        RecyclerView recyclerView = new RecyclerView(context);
        //this will try to update the key below the calendar when the user presses a day
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        dataz =
        MyAdapter mAdapter = new MyAdapter(dataz);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setBackgroundColor(Color.WHITE);
    }

}
