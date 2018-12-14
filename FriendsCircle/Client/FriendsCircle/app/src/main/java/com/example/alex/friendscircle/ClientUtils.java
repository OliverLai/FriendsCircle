package com.example.alex.friendscircle;

import android.os.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 15587 on 2018/11/30.
 */
public class ClientUtils extends Thread{
    private String url;
    private String str;
    private String result;

    public ClientUtils(String url, String str){
        this.url = url;
        this.str = str;
    }

    public String getResult(){
        return result;
    }

    public void setResult(String result){
        this.result = result;
    }

                @Override
    public void run() {
        try {
            URL destUrl = new URL(url);
            HttpURLConnection urlConn = (HttpURLConnection) destUrl.openConnection();
            urlConn.setRequestProperty("charset", "UTF-8");
            urlConn.setConnectTimeout(5000);
            urlConn.setReadTimeout(4000);
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setAllowUserInteraction(false);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod("POST");
            BufferedWriter rw = new BufferedWriter(new OutputStreamWriter(urlConn.getOutputStream()));
            rw.write(str);
            rw.flush();
//            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
//            StringBuffer sb = new StringBuffer(br.readLine());
//            String re = sb.toString();
            String re = new StringBuffer().append(new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8")).readLine()).toString();
            setResult(re);
            rw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
