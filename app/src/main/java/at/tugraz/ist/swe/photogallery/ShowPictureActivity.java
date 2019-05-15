package at.tugraz.ist.swe.photogallery;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShowPictureActivity extends AppCompatActivity {

    boolean isUIhidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_picture);
        hideUI();
        isUIhidden = true;

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

    protected void hideUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

    }
    protected void showUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}