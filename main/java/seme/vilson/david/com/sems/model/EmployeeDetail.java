package seme.vilson.david.com.sems.model;

import android.os.Parcel;
import android.os.Parcelable;


public class EmployeeDetail implements Parcelable
{
    private String id;
    private String Name;
    private String Designation;
    private String JoiningDate;


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.id);
        dest.writeString(this.Name);
        dest.writeString(this.Designation);
        dest.writeString(this.JoiningDate);
    }

    public EmployeeDetail()
    {
    }

    protected EmployeeDetail(Parcel in)
    {
        this.id = in.readString();
        this.Name = in.readString();
        this.Designation = in.readString();
        this.JoiningDate = in.readString();
    }

    public static final Creator<EmployeeDetail> CREATOR = new Creator<EmployeeDetail>()
    {
        @Override
        public EmployeeDetail createFromParcel(Parcel source)
        {
            return new EmployeeDetail(source);
        }

        @Override
        public EmployeeDetail[] newArray(int size)
        {
            return new EmployeeDetail[size];
        }
    };
}
