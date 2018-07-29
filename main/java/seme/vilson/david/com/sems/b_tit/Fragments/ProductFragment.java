package seme.vilson.david.com.sems.b_tit.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.eccelor.bartit.AddProductActivity;
import com.eccelor.bartit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends com.eccelor.android.library.Fragment{
    private Uri fileUri;

    FloatingActionButton floatingActionButton;
    public ProductFragment() {
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product, container, false);

        floatingActionButton=(FloatingActionButton)view.findViewById(R.id.productFab);
        floatingActionButton.setColorFilter(Color.WHITE);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 5001);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==5001)
        {
            if (resultCode == getActivity().RESULT_OK)
            {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                Bundle extras = new Bundle();
                extras.putParcelable("image", image);
                Intent intent = new Intent(getContext().getApplicationContext(), AddProductActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
            else if (resultCode == getActivity().RESULT_CANCELED)
            {
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(getContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
