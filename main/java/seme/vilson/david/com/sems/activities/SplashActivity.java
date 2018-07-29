package seme.vilson.david.com.sems.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;




public class SplashActivity extends Activity
{
    public static final int sec = 2000;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initializeComponents()
    {
        super.initializeComponents();
    }

    @Override
    protected void bindEvents()
    {
        super.bindEvents();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                finish();
                if (!flag)
                {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        }, sec);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        flag = true;
    }

}
