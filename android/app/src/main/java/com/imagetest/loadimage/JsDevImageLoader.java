package com.imagetest.loadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.Log;


import com.imagetest.MainApplication;

import java.io.IOException;
import java.net.URL;

public class JsDevImageLoader {

    /**
     * RN开发者模式下加载图片资源
     * @param iconDevUri
     * @return
     */
    public static Drawable loadIcon(String iconDevUri) {
        try {
            StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

            Drawable drawable = tryLoadIcon(iconDevUri);

            StrictMode.setThreadPolicy(threadPolicy);
            return drawable;
        } catch (Exception e) {
            Log.e("JsDevImageLoader", "Unable to load icon: " + iconDevUri);
            return new BitmapDrawable();
        }
    }

    /**
     * 因为是开发者模式，图片资源的uri都是从package server加载的图片，所以uri是以http://开头
     * 所以我们需要用URL获取流，继而转成bitmap
     * @param iconDevUri
     * iconDevUri: http://10.0.3.2:8081/assets/Images/icon.jpg?platform=android&hash=c951e2c8cf473f91811ea292c6cd364c
     * @return
     * @throws IOException
     */
    @NonNull
    private static Drawable tryLoadIcon(String iconDevUri) throws IOException {
        URL url = new URL(iconDevUri);
        Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
        BitmapDrawable bitmapDrawable = new BitmapDrawable(MainApplication.instance.getResources(), bitmap);
        Log.e("JsDevImageLoader", "bitmap drawable width：" + bitmapDrawable.getIntrinsicWidth());
        return bitmapDrawable;
    }
}