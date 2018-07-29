package seme.vilson.david.com.sems.adapter;


import android.support.v7.widget.AppCompatTextView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;



public class EmployeeList extends RecyclerView.Adapter<EmployeeList.MyviewHolde>
{

    ArrayList<String> arrayList;
    private ItemClickListener mClickListener;


    public class MyviewHolde extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        AppCompatTextView labelName;
        AppCompatTextView labelDesignation;


        public MyviewHolde(View itemView)
        {
            super(itemView);
            labelName = itemView.findViewById(R.id.labelEmployeeName);
            labelDesignation = itemView.findViewById(R.id.labelEmployeeDesignation);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public EmployeeList(ArrayList<String> arrayList)
    {
        this.arrayList = arrayList;
    }

    @Override
    public MyviewHolde onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_employee_list, parent, false);
        return new MyviewHolde(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolde holder, int position)
    {
//        EmployeeDetail detail = arrayList.get(position);
        holder.labelName.setText("Name");
        holder.labelDesignation.setText("Designation");

    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener)
    {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener
    {
        void onItemClick(View view, int position);
    }
}
