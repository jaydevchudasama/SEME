package seme.vilson.david.com.sems.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;


public class EmployeeListAdapter extends BaseAdapter
{

    Context context;
    ArrayList<String> arrayList;

    public EmployeeListAdapter(Context context, ArrayList<String> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.partial_employee_list, null);

        AppCompatTextView labelName;
        AppCompatTextView labelDesignation;

        labelName = view.findViewById(R.id.labelEmployeeName);
        labelDesignation = view.findViewById(R.id.labelEmployeeDesignation);


        return view;
    }
}
