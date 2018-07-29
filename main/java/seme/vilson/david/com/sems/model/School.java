package seme.vilson.david.com.sems.model;

import android.os.Parcel;
import android.os.Parcelable;


public class School implements Parcelable
{
    private String id;
    private String code;
    private String password;

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.id);
        dest.writeString(this.code);
        dest.writeString(this.password);
    }

    public School()
    {
    }

    protected School(Parcel in)
    {
        this.id = in.readString();
        this.code = in.readString();
        this.password = in.readString();
    }

    public static final Creator<School> CREATOR = new Creator<School>()
    {
        @Override
        public School createFromParcel(Parcel source)
        {
            return new School(source);
        }

        @Override
        public School[] newArray(int size)
        {
            return new School[size];
        }
    };
}
