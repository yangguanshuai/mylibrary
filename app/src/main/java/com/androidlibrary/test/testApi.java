package com.androidlibrary.test;

import com.mylibrary.Api.BaseApi;
import com.mylibrary.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import retrofit2.Retrofit;
import rx.Observable;

public class testApi<T> extends BaseApi<T> {
    public testApi(RxAppCompatActivity pRxAppCompatActivity, HttpOnNextListener pListener) {
        super(pRxAppCompatActivity, pListener);
        setShowProgress(true);
        setCancel(true);
        setCache(true);
        setMethod("captcha/token");
        setCacheUrl("captcha/token");

        setCookieNetWorkTime(60);
        setCookieNoNetWorkTime(24*60*60);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService service = retrofit.create(HttpPostService.class);
        return service.getPicToken();
    }

}
