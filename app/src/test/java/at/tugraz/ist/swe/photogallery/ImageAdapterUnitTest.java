package at.tugraz.ist.swe.photogallery;

import android.util.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;

import at.tugraz.ist.swe.photogallery.adapter.ImageAdapter;
import at.tugraz.ist.swe.photogallery.adapter.ImageLoader;
import at.tugraz.ist.swe.photogallery.dummy.DummyView;
import at.tugraz.ist.swe.photogallery.dummy.TestLoader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class ImageAdapterUnitTest {

    static ImageLoader testLoader;
    static DummyView view;
    ImageAdapter adapter;

    static ArrayList<String> nameSorted;
    static ArrayList<String> dateSorted;
    static ArrayList<String> sizeSorted;

    @BeforeClass
    public static void setUp() {
        view = new DummyView();

        String images[] = new String[]{
                "A_0_2KB", "B_3_1KB", "C_2_1B"
        };
        nameSorted = new ArrayList<>();
        dateSorted = new ArrayList<>();
        sizeSorted = new ArrayList<>();

        nameSorted.addAll(Arrays.asList(images));

        dateSorted.add(images[0]);
        dateSorted.add(images[2]);
        dateSorted.add(images[1]);

        sizeSorted.add(images[2]);
        sizeSorted.add(images[1]);
        sizeSorted.add(images[0]);

        testLoader = new TestLoader(sizeSorted, dateSorted, nameSorted, view);
    }

    @Before
    public void beforeTest() {
        PowerMockito.mockStatic(Log.class);
        adapter = new ImageAdapter(testLoader);
    }

    @Test
    public void initialGalleryIsSortedByDate() {
        assertArrayEquals(adapter.getImages().toArray(), dateSorted.toArray());
    }

    @Test
    public void sortByDate() {
        assertArrayEquals(adapter.getImages().toArray(), dateSorted.toArray());
        adapter.sortImages("Date");
        assertArrayEquals(adapter.getImages().toArray(), dateSorted.toArray());
    }

    @Test
    public void sortByName() {
        assertArrayEquals(adapter.getImages().toArray(), dateSorted.toArray());
        adapter.sortImages("Name");
        assertArrayEquals(adapter.getImages().toArray(), nameSorted.toArray());
    }

    @Test
    public void sortBySize() {
        assertArrayEquals(adapter.getImages().toArray(), dateSorted.toArray());
        adapter.sortImages("Size");
        assertArrayEquals(adapter.getImages().toArray(), sizeSorted.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortInvalidArg() {
        assertArrayEquals(adapter.getImages().toArray(), dateSorted.toArray());
        adapter.sortImages("Location");
    }

    @Test
    public void testGetCount() {
        assertEquals(dateSorted.size(), adapter.getCount());
    }

    @Test
    public void testGetItem() {
        int position = 1;
        assertEquals(position, adapter.getItem(position));
    }

    @Test
    public void testGetItemId() {
        int position = 1;
        assertEquals(position, adapter.getItemId(position));
    }

    @Test
    public void testGetView() {
        assertEquals(view, adapter.getView(0, null, null));
    }

}