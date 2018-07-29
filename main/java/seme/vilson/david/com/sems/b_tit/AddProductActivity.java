package seme.vilson.david.com.sems.b_tit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddProductActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    EditText textName;
    EditText textAddress;
    ImageView imageView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        textName = (EditText) findViewById(R.id.textName);
        textAddress = (EditText) findViewById(R.id.textAddress);
        imageView = (ImageView) findViewById(R.id.imageView);

        toolbar = (Toolbar) findViewById(R.id.addproductToolbar);
        toolbar.setTitle("AddProduct");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("image");
        imageView.setImageBitmap(bmp);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.productFabCheck);
        floatingActionButton.setColorFilter(Color.WHITE);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Successfully....", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
