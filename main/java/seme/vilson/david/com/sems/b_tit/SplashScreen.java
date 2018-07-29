package seme.vilson.david.com.sems.b_tit;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;



public class SplashScreen extends Activity {

    private boolean flag; //mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 3000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run() {

                finish();

                if (!flag)  //mIsBackButtonPressed
                {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    SplashScreen.this.startActivity(intent);
                }
            }
        }, SPLASH_DURATION);
    }

    @Override
    protected void bindEvents()
    {
        super.bindEvents();
    }

    @Override
    public void onBackPressed() {
        flag = true;
        // mIsBackButtonPressed = true;
        super.onBackPressed();
    }
}

