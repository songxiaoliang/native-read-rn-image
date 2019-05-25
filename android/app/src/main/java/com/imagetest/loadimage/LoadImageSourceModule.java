package com.imagetest.loadimage;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import javax.annotation.Nonnull;

public class LoadImageSourceModule extends ReactContextBaseJavaModule {

    private static final String MODULE_NAME = "LoadImageSourceModule";

    public LoadImageSourceModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    /**
     *  release模式下 uri为图片名称,例如, 在rn项目的Images目录下有张icon_splash名称的图片
     *  那么 uri 为 images_icon_splash
     *  开发者模式下，图片格式为package server 地址，例如: http: // 192.xxx
     * @param params
     */
    @ReactMethod
    public void showRNImage(ReadableMap params) {
        String rnImageUri;
        try {
            rnImageUri = params.getString("uri");
            Log.i("showRNImage", "uri : " + rnImageUri);
            BitmapUtil.loadImage(rnImageUri);
        } catch (Exception e) {
            return;
        }
    }
}
