package com.imagetest.loadimage;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.imagetest.MainApplication;

import java.io.IOException;


public class BitmapUtil {

    private static final String FILE_SCHEME = "file";

    public static Drawable loadImage(String iconUri) {
        if (TextUtils.isEmpty(iconUri)) {
            return null;
        }
        Log.e("BitmapUtil", "isDebug: " + MainApplication.instance.isDebug());
        if (MainApplication.instance.isDebug()) {
            return JsDevImageLoader.loadIcon(iconUri);
        } else {
            Uri uri = Uri.parse(iconUri);
            if (isLocalFile(uri)) {
                // 本地文件
                return loadFile(uri);
            } else {
                return loadResource(iconUri);
            }
        }
    }

    /**
     * 判断图片是否存在手机本地目录
     * @param uri
     * @return
     */
    private static boolean isLocalFile(Uri uri) {
        return FILE_SCHEME.equals(uri.getScheme());
    }

    /**
     * 加载手机本地目录图片
     * @param uri
     * @return
     */
    private static Drawable loadFile(Uri uri) {
        Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());
        return new BitmapDrawable(MainApplication.instance.getResources(), bitmap);
    }

    /**
     * 加载drawable目录下的图片
     * @param iconUri
     * @return
     */
    private static Drawable loadResource(String iconUri) {
        return ResourceDrawableIdHelper
                .instance
                .getResourceDrawable(MainApplication.instance, iconUri);
    }

    public static Bitmap getBitmap(Activity activity, String uri) {

        if (activity == null || uri == null || TextUtils.isEmpty(uri)) {
            return null;
        }

        Uri mImageCaptureUri;
        try {
            mImageCaptureUri = Uri.parse(uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (mImageCaptureUri == null) {
            return null;
        }

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), mImageCaptureUri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return bitmap;
    }
}