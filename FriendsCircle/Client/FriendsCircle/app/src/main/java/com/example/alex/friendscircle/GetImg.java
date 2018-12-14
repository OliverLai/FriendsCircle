package com.example.alex.friendscircle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 15587 on 2018/12/9.
 */
public class GetImg extends Thread {
    private Bitmap bm;
    private String imgUrl;
    private String Url = "http://192.168.56.1:8080/HttpTest/pic/";

    public GetImg(){}
    public GetImg(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public void run() {
        try{
            URL url = new URL(Url + imgUrl);
            HttpURLConnection httpURLConnection  = (HttpURLConnection) url.openConnection();
            InputStream is = httpURLConnection.getInputStream();
            setBm(BitmapFactory.decodeStream(is));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public Bitmap getBm(){
        return this.bm;
    }

    public void setBm(Bitmap bm){
        this.bm = bm;
    }
}
