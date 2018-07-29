package seme.vilson.david.com.sems.PreferenceHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Helper
{
    static final String PREF_USER_NAME = "username";

    static SharedPreferences getSharedPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserContext(Context context, String SchoolCode)
    {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_USER_NAME, SchoolCode);
        editor.commit();
    }

    public static String getUserContext(Context context)
    {
        return getSharedPreferences(context).getString(PREF_USER_NAME, "");
    }

    public static void clearUserContext(Context context)
    {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }
}
