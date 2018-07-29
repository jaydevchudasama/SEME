package seme.vilson.david.com.sems.b_tit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eccelor.bartit.R;
import com.eccelor.bartit.model.Mycontact;

import java.util.ArrayList;

/**
 * Created by ECLR-01 on 08-06-2017.
 */

public class ContactAdapter extends BaseAdapter {


    ArrayList<Mycontact> arrayList;
    Context context;

    public ContactAdapter(Context context, ArrayList<Mycontact> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.partial_contact, null);

        TextView id = (TextView) convertView.findViewById(R.id.labelid);
        TextView name = (TextView) convertView.findViewById(R.id.labelname);
        TextView contact = (TextView) convertView.findViewById(R.id.labelcontact);

//        id.setText(arrayList.get(position).getId());
        name.setText(arrayList.get(position).getName());
        contact.setText(arrayList.get(position).getContact());
        return convertView;
    }
}

