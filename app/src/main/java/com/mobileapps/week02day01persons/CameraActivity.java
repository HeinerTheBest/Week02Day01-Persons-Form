package com.mobileapps.week02day01persons;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity
{
    private static final int picture_id = 1111;
    ImageView imgvPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imgvPicture = findViewById(R.id.imgvPhoto);
    }

    public void openCamera(View view)
    {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, picture_id);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        imgvPicture.setImageBitmap(photo);
    }
}
