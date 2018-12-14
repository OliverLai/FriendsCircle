package com.example.alex.friendscircle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class Login_success extends Activity {

    List<Users> list = new ArrayList<Users>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_success);
        final String user = getIntent().getExtras().getString("user");
        ((TextView) findViewById(R.id.headText)).setText(user + "的朋友圈");
        final String url = "http://192.168.56.1:8080/HttpTest/test3_Show";
        ClientUtils clientUtils = new ClientUtils(url, "");
        clientUtils.start();
        try {
            clientUtils.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list = JSONArray.parseArray(clientUtils.getResult(), Users.class);
        MyAdapter myAdapter = new MyAdapter(this, list);
        ((ListView) findViewById(R.id.list_View)).setAdapter(myAdapter);
        findViewById(R.id.roll_Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login_success.this, "退出成功！", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login_success.this, Login.class));
                finish();
            }
        });
        findViewById(R.id.Edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_success.this, Publish.class).putExtra("user", user));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_success, menu);
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
