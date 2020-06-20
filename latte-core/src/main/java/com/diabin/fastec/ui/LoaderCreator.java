package com.diabin.fastec.ui;

import android.content.Context;

import com.diabin.fastec.latte_core.app.ConfigType;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

public final class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP= new WeakHashMap<>();

    static AVLoadingIndicatorView creator(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type)==null){
            final Indicator indicator = getIndicator((type));
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassname = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassname.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassname.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassname.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
