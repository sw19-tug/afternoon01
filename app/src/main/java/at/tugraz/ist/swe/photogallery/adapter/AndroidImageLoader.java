package at.tugraz.ist.swe.photogallery.adapter;

import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import at.tugraz.ist.swe.photogallery.R;

public class AndroidImageLoader extends ImageLoader {

    private Activity context;

    public AndroidImageLoader(Activity context) {
        this.context = context;
    }

    @Override
    public ArrayList<String> fetchGalleryImages(ImageAdapter.ImageOrder order) {
        ArrayList<String> galleryImageUrls;
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        String name = "Name";
        String size = "Size";

        if (order == ImageAdapter.ImageOrder.NAME) {
            orderBy = MediaStore.Images.Media.TITLE;

        } else if (order == ImageAdapter.ImageOrder.DATE) {
            orderBy = MediaStore.Images.Media.DATE_TAKEN;

        } else {
            orderBy = MediaStore.Images.Media.SIZE;
        }

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");
        galleryImageUrls = new ArrayList<String>();

        for (int i = 0; i < imageCursor.getCount(); i++) {
            imageCursor.moveToPosition(i);
            int dataColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            galleryImageUrls.add(imageCursor. getString(dataColumnIndex));
        }
        return galleryImageUrls;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent, ArrayList<String> images) {

        ImageView picturesView;
        if (convertView == null) {
            picturesView = new ImageView(context);
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            picturesView
                    .setLayoutParams(new GridView.LayoutParams(270, 270));

        } else {
            picturesView = (ImageView) convertView;
        }

        Glide.with(context).load(images.get(position))
                .placeholder(R.drawable.ic_launcher_foreground).centerCrop()
                .into(picturesView);

        return picturesView;
    }
}
