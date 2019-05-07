package at.tugraz.ist.swe.photogallery.adapter;

import android.app.Activity;

public final class ImageAdapterFactory {
    public static ImageAdapter generateImageAdapter(Activity context)
    {
        ImageLoader loader = new AndroidImageLoader(context);
        return new ImageAdapter(loader);
    }
}
