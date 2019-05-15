package at.tugraz.ist.swe.photogallery;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShowPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.show_picture);

        ImageView iv = findViewById(R.id.fullscreen_picture);
        Intent intent = getIntent();

        try {
            File file = new File(intent.getDataString());
            FileInputStream fileInputStream = new FileInputStream(file);
            iv.setImageDrawable(Drawable.createFromStream(fileInputStream, intent.getDataString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}