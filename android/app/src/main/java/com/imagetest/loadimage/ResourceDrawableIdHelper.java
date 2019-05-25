package com.imagetest.loadimage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import com.facebook.common.util.UriUtil;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;


public class ResourceDrawableIdHelper {

    public static final ResourceDrawableIdHelper instance = new ResourceDrawableIdHelper();

    private Map<String, Integer> mResourceDrawableIdMap;

    public ResourceDrawableIdHelper() {
        mResourceDrawableIdMap = new HashMap<>();
    }

    /**
     * 获取 drawable资源 id
     * @param context
     * @param name
     * @return
     */
    public int getResourceDrawableId(Context context, @Nullable String name) {
        if (name == null || name.isEmpty()) {
            return 0;
        }
        name = name.toLowerCase().replace("-", "_");
        if (mResourceDrawableIdMap.containsKey(name)) {
            return mResourceDrawableIdMap.get(name);
        }
        int id = context.getResources().getIdentifier(
                name,
                "drawable",
                context.getPackageName());
        mResourceDrawableIdMap.put(name, id);
        return id;
    }

    /**
     * 根据 drawable 资源 id 获取 Drawable
     * @param context
     * @param name
     * @return
     */
    @Nullable
    public Drawable getResourceDrawable(Context context, @Nullable String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? context.getResources().getDrawable(resId) : null;
    }

    /**
     * 获取drawable资源Uri
     * @param context
     * @param name
     * @return
     */
    public Uri getResourceDrawableUri(Context context, @Nullable String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(resId))
                .build() : Uri.EMPTY;
    }
}