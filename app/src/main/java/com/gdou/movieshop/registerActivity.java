package com.gdou.movieshop;

import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.gdou.movieshop.databases.UserHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_userid;
    private EditText et_psd;
    private EditText et_againpsd;
    private EditText et_username;
    private EditText et_phone;
    private Button bt_register;
    private Button bt_back;

    UserHelper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userDb = new UserHelper(this);
        initView();
    }

    private void initView() {
        ImageView iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        et_userid = (EditText) findViewById(R.id.et_userid);
        et_psd = (EditText) findViewById(R.id.et_psd);
        et_againpsd = findViewById(R.id.et_againpsd);
        et_username = findViewById(R.id.et_username);
        et_phone = findViewById(R.id.et_phone);

        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
        bt_back = (Button) findViewById(R.id.bt_back);
        bt_back.setOnClickListener(this);

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_phone.getText().toString().trim().length() == 11) {
                    bt_register.setEnabled(true);
                } else {
                    bt_register.setEnabled(false);
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
            case R.id.bt_back:
                Intent intent = new Intent(registerActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_register:
                String id = et_userid.getText().toString();
                String psd = et_psd.getText().toString();
                String anginpsd = et_againpsd.getText().toString();
                String username = et_username.getText().toString();
                String phone = et_phone.getText().toString();
                System.out.println("register");
                //Remind users if passwords are inconsistent
                if (!psd.equals(anginpsd)) {
                    Toast.makeText(getApplicationContext(), "The passwords are inconsistent, please re-enter", Toast.LENGTH_LONG).show();
                }
                else {
                    String where=" account='"+id+"' and passwd='"+psd+"'";
                    Cursor res =userDb.query(where);
                    if (res.getCount()==0){
                        userDb.insertData(id, username,phone,1,psd) ;
                        Toast.makeText(getApplicationContext(), "Sign up was successful", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(registerActivity.this, LoginActivity.class);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(getApplicationContext(), "Account already exists", Toast.LENGTH_LONG).show();
                    }



                }
                break;
        }
    }

}
