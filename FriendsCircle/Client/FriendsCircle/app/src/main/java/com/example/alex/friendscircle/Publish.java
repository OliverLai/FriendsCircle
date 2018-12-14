package com.example.alex.friendscircle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
public class Publish extends Activity {

    Bitmap bitmap;
    boolean flag = false;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.publish);
        final String url = "http://192.168.56.1:8080/HttpTest/test3_Publish";
        final String userName = getIntent().getExtras().getString("user");
        ImageView imageView = (ImageView) findViewById(R.id.img);
        imageView.setDrawingCacheEnabled(true);
        findViewById(R.id.publish_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Publish.this, Login_success.class).putExtra("user", userName));
                finish();
            }
        });
        findViewById(R.id.publish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users user = new Users();
                user.setText(((EditText) findViewById(R.id.edit_Text)).getText().toString());
                user.setUser(userName);
                if(flag){
                    user.setPic(FileUtils.bmToby(bitmap));
                    user.setPicUrl(imagePath);
                }else {
                    user.setPicUrl("");
                }
                ClientUtils clientUtils = new ClientUtils(url, JSONObject.toJSONString(user));
                clientUtils.start();
                try {
                    clientUtils.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (clientUtils.getResult().equals("1")) {
                    startActivity(new Intent(Publish.this, Login_success.class).putExtra("user", userName));
                    finish();
                } else {
                    Toast.makeText(Publish.this, "发表失败，请稍后重试", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void add(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            imagePath = c.getString(columnIndex);
            c.close();
            flag = true;
            showImage(imagePath);
        }
    }

    private void showImage(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max(
                (double) options.outWidth / 1024f,
                (double) options.outHeight / 1024f)));
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        ((ImageView) findViewById(R.id.img)).setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_publish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
