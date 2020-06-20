package com.diabin.fastec.net;

import android.content.Context;

import com.diabin.fastec.net.callback.IError;
import com.diabin.fastec.net.callback.IFailure;
import com.diabin.fastec.net.callback.IRequest;
import com.diabin.fastec.net.callback.ISuccess;
import com.diabin.fastec.net.callback.RequestCallbacks;
import com.diabin.fastec.ui.LatteLoader;
import com.diabin.fastec.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT=context;
        this.FILE=file;
        this.LOADER_STYLE=loaderStyle;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service=RestCreator.getRestService();
        Call<String> call=null;
        if(REQUEST!=null){
            REQUEST.onRequestStart();;
        }
        if(LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method){
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case POST_RAM:
                call=service.postRaw(URL,BODY);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case PUT_RAM:
                call=service.putRaw(URL,BODY);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody=RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body=
                        MultipartBody.Part.createFormData("file",FILE.getName());
                call=RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;

        }
        if(call!=null){
           call.enqueue(getRequestingCallback());
        }
    }

    private Callback<String> getRequestingCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final  void get(){
        request(HttpMethod.GET);
    }

    public final  void post(){
        if (BODY==null){
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAM);
        }

    }

    public final  void put(){
        if (BODY==null){
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAM);
        }

    }

    public final  void delete(){
        request(HttpMethod.DELETE);
    }
}
