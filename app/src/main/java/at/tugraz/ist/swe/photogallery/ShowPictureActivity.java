package at.tugraz.ist.swe.photogallery;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

        ImageView rotateLeftIcon = findViewById(R.id.icon_rotate_left);
        ImageView rotateRightIcon = findViewById(R.id.icon_rotate_right);

        final ImageView rotateLeftAnimation = findViewById(R.id.icon_rotate_left_animation);
        final ImageView rotateRightAnimation = findViewById(R.id.icon_rotate_right_animation);

        rotateLeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AnimationSet animationSet = new AnimationSet(true);

                Animation fadeInAnimation = new AlphaAnimation(0.f, 1.f);
                fadeInAnimation.setDuration(800);
                Animation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
                scaleAnimation.setDuration(1000);
                Animation fadeOutAnimation = new AlphaAnimation(1.f, 0.f);
                fadeOutAnimation.setDuration(200);
                fadeOutAnimation.setStartOffset(800);

                animationSet.addAnimation(fadeInAnimation);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(fadeOutAnimation);

                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        rotateLeftAnimation.setVisibility(View.INVISIBLE);
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                        rotateLeftAnimation.setVisibility(View.VISIBLE);
                    }
                });
                rotateLeftAnimation.startAnimation(animationSet);
            }
        });
        rotateRightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AnimationSet animationSet = new AnimationSet(true);

                Animation fadeInAnimation = new AlphaAnimation(0.f, 1.f);
                fadeInAnimation.setDuration(800);
                Animation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
                scaleAnimation.setDuration(1000);
                Animation fadeOutAnimation = new AlphaAnimation(1.f, 0.f);
                fadeOutAnimation.setDuration(200);
                fadeOutAnimation.setStartOffset(800);

                animationSet.addAnimation(fadeInAnimation);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(fadeOutAnimation);

                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        rotateRightAnimation.setVisibility(View.INVISIBLE);
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                        rotateRightAnimation.setVisibility(View.VISIBLE);
                    }
                });
                rotateRightAnimation.startAnimation(animationSet);
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
}