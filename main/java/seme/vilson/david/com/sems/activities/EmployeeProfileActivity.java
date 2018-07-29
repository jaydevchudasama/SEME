package seme.vilson.david.com.sems.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EmployeeProfileActivity extends Activity
{
    AppCompatImageView imageView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        toolbar.getBackground().setAlpha(0);
    }

    @Override
    protected void initializeComponents()
    {
        super.initializeComponents();
        imageView = findViewById(R.id.profile_image);
        fab = findViewById(R.id.actionFab);
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
                    case R.id.actionFab:

                        Helper.clearUserContext(context);
          /*              SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                        SharedPreferences.Editor e=sp.edit();
                        e.clear();
                        e.commit();

                        startActivity(new Intent(EmployeeProfileActivity.this,LoginActivity.class));
                        finish();   //finish current activity
                        */
                        Toast.makeText(EmployeeProfileActivity.this, "Save", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.profile_image:
                        selectImage();
                        break;

                }
            }
        };
        fab.setOnClickListener(clickListener);
        imageView.setOnClickListener(clickListener);
    }

    private void selectImage()
    {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int item)
            {

                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {

            if (requestCode == 1)
            {

                File f = new File(Environment.getExternalStorageDirectory().toString());

                for (File temp : f.listFiles())
                {

                    if (temp.getName().equals("temp.jpg"))
                    {

                        f = temp;
                        break;
                    }
                }
                try
                {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);

                    imageView.setImageBitmap(bitmap);
                    String path = Environment

                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";

                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    try
                    {

                        outFile = new FileOutputStream(file);

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);

                        outFile.flush();

                        outFile.close();

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
            else if (requestCode == 2)
            {

                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                Log.e("path", picturePath + "");

                imageView.setImageBitmap(thumbnail);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
