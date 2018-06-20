package com.androidlibrary.test;

/**
 * Created by Administrator on 2018-1-8.
 */

public class ResultPictoken {

    private String msg ;
    private String token;
    private String errcode;

    @Override
    public String toString() {
        return "ResultPictoken{" +
                "msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", errcode='" + errcode + '\'' +
                '}';
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
