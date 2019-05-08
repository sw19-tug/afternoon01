package at.tugraz.ist.swe.photogallery.dummy;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import at.tugraz.ist.swe.photogallery.adapter.ImageAdapter;
import at.tugraz.ist.swe.photogallery.adapter.ImageLoader;

public class TestLoader extends ImageLoader {

    ArrayList<String> sizeSorted;
    ArrayList<String> dateSorted;
    ArrayList<String> nameSorted;
    View view;

    public TestLoader(ArrayList<String> size, ArrayList<String> date, ArrayList<String> name, DummyView view)
    {
        this.sizeSorted = size;
        this.dateSorted = date;
        this.nameSorted = name;
        this.view = view;
    }
    @Override
    public ArrayList<String> fetchGalleryImages(ImageAdapter.ImageOrder order) {
        if(order.equals(ImageAdapter.ImageOrder.SIZE))
        {
            return sizeSorted;
        }
        else if(order.equals(ImageAdapter.ImageOrder.DATE))
        {
            return dateSorted;
        }
        else
        {
            return nameSorted;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent, ArrayList<String> images) {
        return view;
    }
}
