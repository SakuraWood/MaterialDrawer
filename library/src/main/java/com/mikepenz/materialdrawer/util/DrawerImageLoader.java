package com.mikepenz.materialdrawer.util;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by mikepenz on 24.03.15.
 */
public class DrawerImageLoader {
    private static DrawerImageLoader SINGLETON = null;

    private IDrawerImageLoader imageLoader;

    private DrawerImageLoader(IDrawerImageLoader loaderImpl) {
        imageLoader = loaderImpl;
    }

    public static DrawerImageLoader init(IDrawerImageLoader loaderImpl) {
        SINGLETON = new DrawerImageLoader(loaderImpl);
        return SINGLETON;
    }

    public static DrawerImageLoader getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new DrawerImageLoader(new IDrawerImageLoader() {
                @Override
                public void setImage(ImageView imageView, Uri uri, Drawable placeholder) {
                    //this won't do anything
                    Log.i("MaterialDrawer", "you have not specified a ImageLoader implementation through the DrawerImageLoader.init(IDrawerImageLoader) method");
                }
            });
        }
        return SINGLETON;
    }

    public void setImage(ImageView imageView, Uri uri, Drawable placeholder) {
        if (imageLoader != null) {
            imageLoader.setImage(imageView, uri, placeholder);
        }
    }

    public IDrawerImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(IDrawerImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public interface IDrawerImageLoader {
        public void setImage(ImageView imageView, Uri uri, Drawable placeholder);
    }
}
