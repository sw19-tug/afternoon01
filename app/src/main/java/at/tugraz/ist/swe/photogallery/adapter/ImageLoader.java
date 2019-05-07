package at.tugraz.ist.swe.photogallery.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class ImageLoader {
    public abstract ArrayList<String> fetchGalleryImages(ImageAdapter.ImageOrder order);

    public abstract View getView(int position, View convertView, ViewGroup parent, ArrayList<String> images);
}
