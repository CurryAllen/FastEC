package com.diabin.fastec.net;

import android.content.Context;

import com.diabin.fastec.net.callback.IError;
import com.diabin.fastec.net.callback.IFailure;
import com.diabin.fastec.net.callback.IRequest;
import com.diabin.fastec.net.callback.ISuccess;
import com.diabin.fastec.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    private String mUrl;
    private static final Map<String,Object> PARAMS=RestCreator.getParams();
    private IRequest mIRequest=null;
    private ISuccess mISuccess=null;
    private IFailure mIFailure=null;
    private RequestBody mBody=null;
    private IError mIError=null;
    private Context mContext=null;
    private LoaderStyle  mLoaderStyle=null;
    private File mFile=null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }
    public final RestClientBuilder file(File file ) {
        this.mFile=file;
        return this;
    }

    public final RestClientBuilder file(String file ) {
        this.mFile=new File(file);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style ){
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody,mFile,mContext,mLoaderStyle);
    }

}
