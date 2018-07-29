package seme.vilson.david.com.sems.b_tit.Fragments;



import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.eccelor.bartit.Helper.SqliteHelper;
import com.eccelor.bartit.R;
import com.eccelor.bartit.adapter.ContactAdapter;
import com.eccelor.bartit.model.Mycontact;

import java.util.ArrayList;



import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {


    ListView listViewContact;
    public static ArrayList<String> StoreContacts;
    ArrayList<Mycontact> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Cursor cursor;
    String name, phonenumber;
    Mycontact mycontact;
    SharedPreferences sp;
    Button button;


    public ContactsFragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        listViewContact = (ListView) view.findViewById(R.id.listviewContact);


        GetContactsIntoArrayList();

        SqliteHelper sqliteHelper = new SqliteHelper(getContext());
        arrayList = sqliteHelper.display_contct();

        ContactAdapter contactAdapter = new ContactAdapter(getContext(), arrayList);
        listViewContact.setAdapter(contactAdapter);

        return view;
    }

    public void GetContactsIntoArrayList() {
        sp = getActivity().getSharedPreferences("contact", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", true);
        editor.commit();

        cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext())
        {
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


            mycontact = new Mycontact();
            mycontact.setId(id);
            mycontact.setName(name);
            mycontact.setContact(phonenumber);

            SqliteHelper sqliteHelper = new SqliteHelper(getContext());
            sqliteHelper.insert_contact(mycontact);
            sqliteHelper.delete(mycontact);
            sqliteHelper.update(mycontact);


        }
        cursor.close();
    }
}
