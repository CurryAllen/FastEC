package com.diabin.fastec.example;

import android.app.Application;
import com.diabin.fastec.latte_core.app.Latte;
import com.diabin.fastec.latte_ec.icon.FontEcModule;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;



public class ExampleApp extends Application {
    @Override
    public  void onCreate(){
        super.onCreate();
        Latte.init(this)
                .withIcons(new FontAwesomeModule())
                .withIcons((IconFontDescriptor) new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
