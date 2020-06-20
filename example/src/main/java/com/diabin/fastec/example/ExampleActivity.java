package com.diabin.fastec.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.diabin.fastec.delegates.LatteDelegate;
import com.diabin.fastec.latte.activities.ProxyActivity;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
