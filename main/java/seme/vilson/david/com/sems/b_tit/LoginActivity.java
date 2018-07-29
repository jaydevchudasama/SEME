package seme.vilson.david.com.sems.b_tit;



//

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;


public class LoginActivity extends Activity {

    private LinearLayout linearLayout;
    private Spinner spinnerCountry;
    private EditText textNumber;
    private Button buttonOTP;
    private String[] listValue;
    private String[] Name;
    SharedPreferences sp;
    ArrayList<Country> arrayList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
    }

    @Override
    protected void initializeComponents()
    {
        super.initializeComponents();
        linearLayout = (LinearLayout) findViewById(R.id.LinearLayout1);
        arrayList = new ArrayList<>();
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        textNumber = (EditText) findViewById(R.id.textNumber);
        buttonOTP = (Button) findViewById(R.id.buttonOTP);
    }

    @Override
    protected void bindEvents()
    {
        super.bindEvents();
        networkinfo();


        listValue = getResources().getStringArray(R.array.countryCodes);
        Name = getResources().getStringArray(R.array.countryName);
        for (int i = 0; i < listValue.length; i++)
        {
            Country country1 = new Country(listValue[i], Name[i]);
            arrayList.add(country1);
        }

        sp=getSharedPreferences("status",MODE_PRIVATE);
        if (sp.getBoolean("isLogin",false))
        {
            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
            finish();
        }

        final CountryAdapter countryAdapter = new CountryAdapter(LoginActivity.this, arrayList);
        spinnerCountry.setAdapter(countryAdapter);



        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                networkinfo();
                switch (v.getId())
                {
                    case R.id.buttonOTP:

                        Country country = (Country) spinnerCountry.getSelectedItem();
                        String phoneNumber = textNumber.getText().toString().trim();


                        if (spinnerCountry.getSelectedItemPosition() == 0)
                        {
                            Snackbar.make(linearLayout, "Select country", Snackbar.LENGTH_LONG).show();
                            return;
                        }
//                        Country country = (Country) spinnerCountry.getSelectedItem();
//                        String phoneNumber = textNumber.getText().toString().trim();

                        if (TextUtils.isEmpty(phoneNumber) )
                        {
                            Snackbar.make(linearLayout, "Enter phone number", Snackbar.LENGTH_LONG).show();
                            return;
                        }

                        int countryCode = Integer.parseInt(country.code);
                        long phone = Long.parseLong(phoneNumber);

                        if (!isValidPhoneNumber(countryCode, phone))
                        {
                            Snackbar.make(linearLayout, "Enter valid phone", Snackbar.LENGTH_LONG).show();
                            return;
                        }

                        progressDialog = new ProgressDialog(context,R.style.AppCompatAlertDialogStyle);//,
                        progressDialog.setMessage("Please wait data is Loading...");
                        progressDialog.show();

                        final UserApi apiService =
                                RegisterUserApi.getClient().create(UserApi.class);

                        Call<String> call = apiService.createUser(String.valueOf(country), phoneNumber);
                        call.enqueue(new Callback<String>()
                        {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response)
                            {
                                if (response.isSuccessful())
                                {

                                    Toast.makeText(LoginActivity.this, response.body() + response.code(), Toast.LENGTH_SHORT).show();
                                    Log.e("result", response.body() + "" + response.code());
                                    Log.e("result", response.code() + "");

                                    switch (response.code())
                                    {

                                        case 400:
                                            Toast.makeText(LoginActivity.this, "Argument is invalid ", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 401: //
                                            Toast.makeText(LoginActivity.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 403: //
                                            Toast.makeText(LoginActivity.this, "AccessDenied", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 404: //
                                            Toast.makeText(LoginActivity.this, "Resource doesn’t exist", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 409:
                                            Toast.makeText(LoginActivity.this, "Account is alerady exists", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 500:
                                            Toast.makeText(LoginActivity.this, "Internal Server Error Problems", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 200:

                                            startActivity(new Intent(LoginActivity.this, OTPVerifyActivity.class));
                                            finish();
                                            break;

                                        default:
                                            Toast.makeText(LoginActivity.this, "Out of Range", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Log.e("result", response.body() + "" + response.code());
                                    Log.e("result", response.code() + "");

                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        break;
                }
            }
        };
        buttonOTP.setOnClickListener(clickListener);
    }

    private boolean isValidPhoneNumber(int countryCode, long phone)
    {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber().setCountryCode(countryCode).setNationalNumber(phone);
        return phoneNumberUtil.isValidNumber(phoneNumber);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (progressDialog != null)
        {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void networkinfo()
    {
        ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true )
        {

        }
        else
        {
            Snackbar.make(linearLayout, "Network Not Available", Snackbar.LENGTH_LONG).show();
        }
    }

}



/*
        if (!isNetworkAvailable()) {
        // Create an Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the Alert Dialog Message
        builder.setMessage("Internet Connection Required")
                .setCancelable(false)
                .setPositiveButton("Retry",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // Restart the Activity
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private boolean isNetworkAvailable() {
        // Using ConnectivityManager to check for Network Connection
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    */