package at.tugraz.ist.swe.photogallery;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import at.tugraz.ist.swe.photogallery.adapter.ImageAdapter;
import at.tugraz.ist.swe.photogallery.adapter.ImageAdapterFactory;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> images;

    //Permision code that will be checked in the method onRequestPermissionsResult
    private int STORAGE_PERMISSION_CODE = 23;
    final int PIC_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Check Permission for Media
        if (isReadStorageAllowed()) {
            loadImages();
        } else {
            requestStoragePermission();
        }
    }

    private void loadImages() {
        setContentView(R.layout.activity_main);
        final Spinner spinner = findViewById(R.id.spinner_toolBar);
        final ImageAdapter ia = ImageAdapterFactory.generateImageAdapter(this);
        final Button camera = findViewById(R.id.camera);
        String spinnerValue = spinner.getSelectedItem().toString();
        ia.sortImages(spinnerValue);

        final GridView gallery = (GridView) findViewById(R.id.gallery);
        gallery.setAdapter(ia);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowPictureActivity.class);
                intent.setData(ia.getItemUri(position));
                Log.i("iURI", ia.getItemUri(position).toString());
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = spinner.getSelectedItem().toString();
                ia.sortImages(spinnerValue);
                gallery.setAdapter(ia);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Error, nothing selected in Sort Dropdown Menu!", Toast.LENGTH_SHORT).show();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                try{
                    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                        Uri file = getUri();
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                        startActivityForResult(cameraIntent, PIC_ID);
                        galleryAddPic();
                    }

                }catch (Exception e)
                {e.printStackTrace();}
            }

        });
    }
    private Uri getUri(){
            Uri file = FileProvider.getUriForFile(this, "com.example.android.fileprovider", getFile());
            return file;
    }

    String mCurrentPhotoPath;
    private File getFile() {

            File folder = new File(getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM), "Camera");// the file path

            //if it doesn't exist the folder will be created
            if (!folder.exists()) {
                folder.mkdir();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File imageFile = null;

            try {
                imageFile = File.createTempFile(imageFileName, ".jpg", folder);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mCurrentPhotoPath = imageFile.getAbsolutePath();
            return imageFile;
    }


    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
        loadImages();
    }




    @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

            //We are calling this method to check the permission status
            private boolean isReadStorageAllowed() {
                //Getting the permission status
                int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

                //If permission is granted returning true
                if (result == PackageManager.PERMISSION_GRANTED)
                    return true;

                //If permission is not granted returning false
                return false;
            }

            //Requesting permission
            private void requestStoragePermission() {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    //If the user has denied the permission previously your code will come to this block
                    //Here you can explain why you need this permission
                    //Explain here why you need this permission
                    //requestStoragePermission();

                }

                //And finally ask for the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
            }


            //This method will be called when the user will tap on allow or deny
            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

                //Checking the request code of our request
                if (requestCode == STORAGE_PERMISSION_CODE) {

                    //If permission is granted
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //Displaying a toast
                        Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_LONG).show();
                        loadImages();
                    } else {
                        //Displaying another toast if permission is not granted
                        Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_LONG).show();
                        requestStoragePermission();
                    }
                }
        }

    }

