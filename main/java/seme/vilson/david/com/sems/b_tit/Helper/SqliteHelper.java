package seme.vilson.david.com.sems.b_tit.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class SqliteHelper extends SQLiteOpenHelper {
    public String tbname = "contact", id = "id", name = "name", contact = "contact";

    public SqliteHelper(Context context) {
        super(context, "Mycontact", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " create table " + tbname + " ("
                + id + " integer primary key autoincrement ,"
                + name + " text, "
                + contact + " text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert_contact(Mycontact arrayList) {
        SQLiteDatabase db = getReadableDatabase();
//        String sql = " select * from " + tbname + " where "+name+ "='"+arrayList.getName()+"'";
        String sql = " select * from " + tbname + " where " + contact + "='" + arrayList.getContact() + "'";
//        String sql = " select  * from " + tbname + " where "+name+ "='"+arrayList.getName()+"' OR "+contact+"='"+arrayList.getContact()+"'";
//        String sql = " select  * from " + tbname + " where "+name+ "='"+arrayList.getName()+"' AND "+contact+"='"+arrayList.getContact()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        boolean isAvalable = false;
        while (cursor.moveToNext()) {
            isAvalable = true;
        }
        if (!isAvalable) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(name, String.valueOf(arrayList.getName()));
            contentValues.put(contact, String.valueOf(arrayList.getContact()));
            db.insert(tbname, null, contentValues);
        }
        db.close();
    }


    public ArrayList<Mycontact> display_contct() {
        ArrayList<Mycontact> arrayList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = " select * from " + tbname;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Mycontact mycontact = new Mycontact();
                mycontact.setId(cursor.getInt(cursor.getColumnIndex("id")) + "");
                mycontact.setName(cursor.getString(cursor.getColumnIndex("name")));
                mycontact.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                arrayList.add(mycontact);
            } while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }

    public void update(Mycontact arrayList) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name, arrayList.getName());
        contentValues.put(contact, arrayList.getContact());
        db.update(tbname, contentValues, id + "=" + arrayList.getId(), null);
        db.close();

    }

    public void delete(Mycontact arrayList) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tbname, this.id + "=" + arrayList.getId(), null);
        db.close();
    }
}


