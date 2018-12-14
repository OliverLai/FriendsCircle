package com.example.alex.friendscircle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by 15587 on 2018/12/3.
 */
public class FileUtils {
    public static Bitmap byTobm(byte[] bytes, BitmapFactory.Options opts){
        if(bytes != null)
            if(opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

   public static byte[] bmToby(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
         return baos.toByteArray();
    }
}
