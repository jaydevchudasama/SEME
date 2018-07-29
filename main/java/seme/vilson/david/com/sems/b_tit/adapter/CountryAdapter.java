package seme.vilson.david.com.sems.b_tit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.eccelor.bartit.R;
import com.eccelor.bartit.model.Country;
import java.util.ArrayList;

/**
 * Created by ECLR-01 on 17-05-2017
 */

public class CountryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Country> countries;
    private LayoutInflater inflater;

    public CountryAdapter(Context context, ArrayList<Country> countries)
    {
        this.context = context;
        this.countries = countries;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return countries.size();
    }
    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if (convertView == null)

            convertView = inflater.inflate(R.layout.partial_country_item, parent, false);

        CountryHolder holder = (CountryHolder) convertView.getTag();

        if (holder == null)
        {
            holder = new CountryHolder();
            holder.labelName = (TextView) convertView.findViewById(R.id.labelName);
            convertView.setTag(holder);
        }
        Country country = (Country) getItem(position);
        holder.labelName.setText("+" + country.code + " - " + country.name);
        return convertView;
    }

    public class CountryHolder
    {
        TextView labelName;
    }
}
