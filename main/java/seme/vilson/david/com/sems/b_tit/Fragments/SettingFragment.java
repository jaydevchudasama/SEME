package seme.vilson.david.com.sems.b_tit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eccelor.bartit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the partial_contact for this fragment

        View view=inflater.inflate(R.layout.fragment_setting, container, false);

        /*
        listView=(ListView) view.findViewById(R.id.listviewsetting);
        arrayList=new ArrayList<>();

        SqliteHelper sqliteHelper=new SqliteHelper(getContext());
        arrayList=sqliteHelper.display_contct();

        Log.e("contact",arrayList+"");
        ContactAdapter contactAdap=new ContactAdapter(getContext(),arrayList);
        listView.setAdapter(contactAdap);
*/

        return view;
    }

}
