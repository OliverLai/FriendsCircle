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

public class Register extends Activity {
    Bitmap bitmap;
    String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        findViewById(R.id.register_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }
        });
    }

    public void register(View v) {
        String url = "http://192.168.56.1:8080/HttpTest/test3_Register";
        final EditText user = (EditText) findViewById(R.id.register_User);
        final EditText pwd = (EditText) findViewById(R.id.register_Pwd);
        final EditText phone = (EditText) findViewById(R.id.register_Phone);
        Users users = new Users();
        users.setUser(user.getText().toString());
        users.setPassword(pwd.getText().toString());
        users.setPhone(phone.getText().toString());
        users.setIcon(FileUtils.bmToby(bitmap));
        users.setIconUrl(imagePath);
        ClientUtils clientUtils = new ClientUtils(url, JSONObject.toJSONString(users));
        clientUtils.start();
        try {
            clientUtils.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (clientUtils.getResult().equals("1")) {
            startActivity(new Intent(Register.this, Register_success.class));
                finish();
        } else {
            Toast.makeText(Register.this, "该用户已存在！", Toast.LENGTH_LONG).show();
            user.setText("");
            pwd.setText("");
            phone.setText("");
        }
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
                ((ImageView) findViewById(R.id.register_Icon)).setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
