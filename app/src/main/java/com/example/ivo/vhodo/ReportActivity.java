package com.example.ivo.vhodo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportActivity extends AppCompatActivity {
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "VHODO Images";
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.take_picture_button:
                captureImage();
                break;
            case R.id.send_problem_button:
                sendProblem();
                break;
        }
    }

    private void sendProblem() {
        //TODO: implement the send problem function
        EditText editText = (EditText) findViewById(R.id.problem_text);
        int size = editText.getText().length();
        boolean isSuccess =false;
        String toastText = "No text was entered, please specify the problem";
        if (size == 0){
            //default toastText
        }else if (size > 0 && size < 5){
            toastText = "Text is too short, 5 characters minimum";
        }else{
            toastText = "Successfully sent the report to the message board.";
            isSuccess = true;
            GlobalData.addMessage(GlobalData.getCurrentUser().getUsername(),editText.getText().toString(), DateFormat.getDateTimeInstance().format(new Date()),2);
        }
        Toast.makeText(getApplicationContext(),toastText,Toast.LENGTH_LONG).show();
        if (isSuccess) finish();
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = Uri.fromFile(getOutputMediaFile(MEDIA_TYPE_IMAGE));

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);


    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        String resultText = "Sorry! Failed to capture image";
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //image was taken
                resultText = "Image captured successfully";
                ImageView iv = (ImageView) findViewById(R.id.imageView);
                iv.setImageBitmap(BitmapFactory.decodeFile(getOutputMediaFile(MEDIA_TYPE_IMAGE).getAbsolutePath()));
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                resultText = "You have cancelled image capture";
            } else {
                // failed to capture image
            }
        }
        Toast.makeText(getApplicationContext(),
                resultText, Toast.LENGTH_SHORT)
                .show();

    }

}
