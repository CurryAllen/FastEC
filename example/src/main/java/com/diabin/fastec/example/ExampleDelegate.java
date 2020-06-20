package com.diabin.fastec.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.diabin.fastec.delegates.LatteDelegate;
import com.diabin.fastec.net.RestClient;
import com.diabin.fastec.net.callback.IError;
import com.diabin.fastec.net.callback.IFailure;
import com.diabin.fastec.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext() )
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
       //                 Toast.makeText(getContext(),response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
