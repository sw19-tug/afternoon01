package at.tugraz.ist.swe.photogallery;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShowPictureActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ShareActionProvider shareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fullscreen, menu);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
        setShareIntent(getDefaultShareIntent());
        return true;
    }


    public void triggerShareAction() {
        Intent intent = getDefaultShareIntent();
        if(intent!=null)
            shareActionProvider.setShareIntent(intent);
    }

    private Intent getDefaultShareIntent(){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getDataString()));
        return intent;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_share:
                triggerShareAction();
                break;
            default:
                break;
        }

        return true;
    }

    private void setShareIntent(Intent shareIntent) {
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_picture);

        toolbar = findViewById(R.id.toolbar_fullscreen);
        toolbar.inflateMenu(R.menu.menu_fullscreen);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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