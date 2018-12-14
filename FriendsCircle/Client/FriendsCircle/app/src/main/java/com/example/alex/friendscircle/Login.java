package com.example.alex.friendscircle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        findViewById(R.id.login_Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    public void login(View v)
    {
        final String userName = ((EditText) findViewById(R.id.login_User)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.login_Pwd)).getText().toString();
        final String url = "http://192.168.56.1:8080/HttpTest/test3_Login";
        Users user = new Users();
        user.setUser(userName);
        user.setPassword(pwd);
        ClientUtils clientUtils = new ClientUtils(url, JSONObject.toJSONString(user));
        clientUtils.start();
        try {
            clientUtils.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String re = clientUtils.getResult();
        if(re == null){
            Toast.makeText(Login.this, "网络繁忙，请稍后重试", Toast.LENGTH_LONG).show();
        }else if(re.equals("0")){
            Toast.makeText(Login.this, "账号或密码错误，请重新输入！", Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.login_Pwd)).setText("");
        }else if (re.equals("1")) {
            Toast.makeText(Login.this, "登录成功！", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Login.this, Login_success.class);
            intent.putExtra("user", userName);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
