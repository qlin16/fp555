package com.gdou.movieshop;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import utils.BitmapCache;

/**
 * Home page
 */
public class HomeFragment extends Fragment {
    //Button
    TextView login;
    EditText findMovie;
    ImageView iv_find;
    ImageView iv_AD;
    ImageView iv_hot1;
    ImageView iv_hot2;
    ImageView iv_hot3;
    Button btn_buy1;
    Button btn_buy2;
    Button btn_buy3;
    TextView tv_all;
    TextView tv_hotMovie;
    TextView hot_text1;
    TextView hot_text2;
    TextView hot_text3;
    String AD_id,hot1_id,hot2_id,hot3_id;
    HashMap<String, String> urlMap;
    HomeFragment fragment;
    SharedPreferences sharedPreferences;

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取sharedPreferences对象
        sharedPreferences = getActivity().getSharedPreferences("Login", Activity.MODE_PRIVATE);

    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //intiView();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        Bundle bundle = getArguments();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        //init
        login = getView().findViewById(R.id.tv_login);
        findMovie = getView().findViewById(R.id.findMovie);
        iv_find = getView().findViewById(R.id.iv_find);
        iv_AD = getView().findViewById(R.id.iv_AD);
        iv_hot1 = getView().findViewById(R.id.iv_hot1);
        iv_hot2 = getView().findViewById(R.id.iv_hot2);
        iv_hot3 = getView().findViewById(R.id.iv_hot3);
        btn_buy1 = getView().findViewById(R.id.btn_buy1);
        btn_buy2 = getView().findViewById(R.id.btn_buy2);
        btn_buy3 = getView().findViewById(R.id.btn_buy3);
        tv_all=getView().findViewById(R.id.tv_all);
        tv_hotMovie = getView().findViewById(R.id.tv_hotMovie);
        hot_text1 = getView().findViewById(R.id.hot_text1);
        hot_text2 = getView().findViewById(R.id.hot_text2);
        hot_text3 = getView().findViewById(R.id.hot_text3);
        iv_hot1.setImageResource(R.drawable.hot1);
        iv_hot2.setImageResource(R.drawable.hot2);
        iv_hot3.setImageResource(R.drawable.hot3);
        hot_text1.setText("Pokemon");
        hot_text2.setText("A dog");
        hot_text3.setText("Avenger");
        System.out.println("init home");


        String user_name = sharedPreferences.getString("user_name", "Login");
        login.setText(user_name);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), LoginActivity.class);

                startActivity(intent);
            }
        });

        iv_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("findMovie");

                String name = findMovie.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("findMovie", name);
                editor.apply();

                MainActivity activity = (MainActivity) getActivity();
                activity.setBottomBarSelection(1);
            }
        });

        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("To all");
                //MainActivity activity = (MainActivity) getActivity();
                //activity.setBottomBarSelection(1);
            }
        });

        iv_AD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("Movie_id",AD_id);
                startActivity(intent);
            }
        });

        btn_buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("Movie_id",hot1_id);
                startActivity(intent);
            }
        });
        btn_buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("Movie_id",hot2_id);
                startActivity(intent);
            }
        });
        btn_buy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("Movie_id",hot3_id);
                startActivity(intent);
            }
        });
    }


}

