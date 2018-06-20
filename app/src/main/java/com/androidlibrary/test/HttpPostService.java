package com.androidlibrary.test;

import com.mylibrary.base.HttpResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 测试接口service-post相关
 * Created by WZG on 2016/12/19.
 */

public interface HttpPostService {

    /**
     * 获取图形验证码Token
     * @return
     */
    @GET("captcha/token")
    Observable<HttpResult<ResultPictoken>> getPicToken();

}
