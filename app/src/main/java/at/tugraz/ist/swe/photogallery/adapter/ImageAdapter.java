package at.tugraz.ist.swe.photogallery.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {


    public enum ImageOrder {
        NAME, SIZE, DATE
    }

    private ImageLoader loader;
    private ArrayList<String> images;

    public ImageAdapter(ImageLoader oLoader) {
        loader = oLoader;
        images = loader.fetchGalleryImages(ImageOrder.DATE);
    }

    public void sortImages(String spinnerValue) {
        this.updateGallery(spinnerValue);
    }

    public void updateGallery(String order) {
        ImageOrder orderBy;
        if (order.equals("Size")) {
            orderBy = ImageOrder.SIZE;
        } else if (order.equals("Name")) {
            orderBy = ImageOrder.NAME;
        } else if (order.equals("Date")) {
            orderBy = ImageOrder.DATE;
        } else {
            throw new IllegalArgumentException("Unknown Sorting criteria: " + order);
        }
        Log.i("ImageAdapter", "Fetching images, ordered by " + order);
        images = loader.fetchGalleryImages(orderBy);
    }

    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        return loader.getView(position, convertView, parent, images);
    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public Uri getItemUri(int position){
        return Uri.parse(images.get(position));
    }

    public long getItemId(int position) {
        return position;
    }

    public ArrayList<String> getImages() {
        return images;
    }
}
