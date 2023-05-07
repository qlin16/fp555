package com.gdou.movieshop;

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.gdou.movieshop.databases.UserHelper;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

     EditText et_userid;
     EditText et_psd;
     ImageView iv_clear;
     TextView register;
     Button bt_login;
    UserHelper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        ImageView iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        et_userid = (EditText) findViewById(R.id.et_userid);
        et_psd = (EditText) findViewById(R.id.et_psd);

        register=findViewById(R.id.register);
        register.setOnClickListener(this);
        iv_clear = (ImageView) findViewById(R.id.iv_clear);
        iv_clear.setOnClickListener(this);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

        et_userid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(et_userid.getText().toString().trim())) {
                    iv_clear.setVisibility(View.VISIBLE);
                } else {
                    iv_clear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_clear:
                et_userid.setText("");
                et_psd.setText("");
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, registerActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_login:
                //Login Logic
                String account = et_userid.getText().toString();
                String passwd = et_psd.getText().toString();
                userDb = new UserHelper(this);
                String where=" account='"+account+"' and passwd='"+passwd+"'";
                Cursor res =userDb.query(where);
                System.out.println(res.getCount());

                if (res.getCount()==0){

                    String user_id="";
                    Integer status=0;
                    String name="";
                    String mobile="";


                    while (res.moveToNext()) { //cycle thru result set
                        user_id=res.getString(0) ;
                        account=res.getString(1) ;
                        name=res.getString(2) ;
                        mobile=res.getString(3) ;
                        status=res.getInt(4) ;
                    }

                    //print info
                    Toast.makeText(getApplicationContext(), "login succeeded", Toast.LENGTH_LONG).show();
                    //login
                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_name", name);
                    editor.putString("user_id", user_id);
                    editor.putInt("status", status);
                    editor.apply();

                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(getApplicationContext(), "Account or password error", Toast.LENGTH_LONG).show();
                }


        }
    }

}
