package seme.vilson.david.com.sems.b_tit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;




public class OTPVerifyActivity extends Activity {

    private Button buttonVERIFY;
    private EditText textOTPNUMBER;
    private LinearLayout OTPLayout;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otpverifyactivity);
    }

    @Override
    protected void initializeComponents()
    {
        super.initializeComponents();

        OTPLayout=(LinearLayout) findViewById(R.id.OTPLayout);
        buttonVERIFY=(Button) findViewById(R.id.buttonVERIFY);
        textOTPNUMBER=(EditText) findViewById(R.id.textOTPNumber);

    }

    @Override
    protected void bindEvents()
    {
        super.bindEvents();
        View.OnClickListener clickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.buttonVERIFY:

                        sp=getSharedPreferences("status",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        textOTPNUMBER.getText().toString().equals("12345");
//                            {
                                editor.putBoolean("isLogin",true);
                                editor.commit();
                                startActivity(new Intent(OTPVerifyActivity.this, DashboardActivity.class));
                                finish();
                                Toast.makeText(OTPVerifyActivity.this, "Login Success...", Toast.LENGTH_SHORT).show();
//                            }
//                        else {
//                            Snackbar.make(OTPLayout,"OTP Number is Wrong",Snackbar.LENGTH_LONG).show();
//                        }
                        break;
                }
            }
        };
        buttonVERIFY.setOnClickListener(clickListener);
    }

    @Override
    public void onBackPressed() {

    }
}
