package at.tugraz.ist.swe.photogallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ShowPictureActivity extends AppCompatActivity {
    private boolean isUIhidden;
    private String file_location;
    private ImageView iv;


    public void triggerShareAction() {
        Intent share_intent = getDefaultShareIntent();
        startActivity(Intent.createChooser(share_intent, "Share images..."));
    }

    private Intent getDefaultShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file_location));
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_picture);
        hideUI();
        isUIhidden = true;
        file_location = getIntent().getDataString();
        iv = findViewById(R.id.fullscreen_picture);

        try {
            updateImage();
        } catch(FileNotFoundException ex) {
            Toast toast = Toast.makeText(this.getApplicationContext(), "Could not rotate image: File not found!", Toast.LENGTH_LONG);
            toast.show();
        }

        ConstraintLayout cl = findViewById(R.id.fullscreen_layout);
        cl.setOnClickListener(new ConstraintLayout.OnClickListener() {
            @Override
            public void onClick(View v){
                if(isUIhidden){
                    showUI();
                    isUIhidden = false;
                } else {
                    hideUI();
                    isUIhidden = true;
                }

            }
        });

        ImageButton ib_rotate_left = findViewById(R.id.button_rotate_left);
        ImageButton ib_rotate_right = findViewById(R.id.button_rotate_right);
        ImageButton ib_share = findViewById(R.id.button_share);


        ib_share.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v){
                triggerShareAction();
            }
        });

        ib_rotate_left.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    rotateImage(270);
                } catch (FileNotFoundException ex){
                    Toast toast = Toast.makeText(v.getContext(), "Could not rotate image: File not found!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        ib_rotate_right.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    rotateImage(90);
                } catch (FileNotFoundException ex){
                    Toast toast = Toast.makeText(v.getContext(), "Could not rotate image: File not found!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    protected void hideUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
        LinearLayout ab = findViewById(R.id.layout_action_bar);
        ab.setVisibility(View.INVISIBLE);

    }
    protected void showUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        LinearLayout ab = findViewById(R.id.layout_action_bar);
        ab.setVisibility(View.VISIBLE);
    }

    protected FileInputStream getImageStream (String file_location) throws FileNotFoundException {
            File file = new File(file_location);
            return new FileInputStream(file);
    }

    protected void updateImage() throws FileNotFoundException {
        iv.setImageDrawable(Drawable.createFromStream(getImageStream(file_location), file_location));
    }

    protected void rotateImage(int degrees) throws FileNotFoundException {
        Bitmap image = BitmapFactory.decodeStream(getImageStream(file_location));
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        Bitmap rotated = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(),
                matrix, true);
        FileOutputStream out = new FileOutputStream(file_location);
        rotated.compress(Bitmap.CompressFormat.PNG, 100, out);
        updateImage();
    }
}