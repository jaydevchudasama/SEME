package seme.vilson.david.com.sems.activities;

import android.support.v7.widget.AppCompatEditText;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


public class LoginActivity extends Activity
{

    AppCompatEditText textSchoolCode;
    AppCompatEditText textSchoolPassword;
    AppCompatButton buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Helper.getUserContext(context).length() != 0)
        {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void initializeComponents()
    {
        super.initializeComponents();

        textSchoolCode = findViewById(R.id.textSchoolCode);
        textSchoolPassword = findViewById(R.id.textSchoolPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    @Override
    protected void bindEvents()
    {
        super.bindEvents();
        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.buttonLogin:

                        String SchoolCode = textSchoolCode.getText().toString().trim();
                        String SchoolPassword = textSchoolPassword.getText().toString().trim();

                        if (TextUtils.isEmpty(SchoolCode))
                        {
                            textSchoolCode.setError("Enter School Code");
                            textSchoolCode.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(SchoolPassword) || textSchoolPassword.getText().toString().trim().length() <= 5)
                        {
                            textSchoolPassword.setError("Enter School Password");
                            textSchoolPassword.requestFocus();
                            return;
                        }
                        else
                        {
                            Helper.setUserContext(context, textSchoolCode.getText().toString());
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };
        buttonLogin.setOnClickListener(clickListener);
    }
}