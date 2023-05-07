package com.gdou.movieshop;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gdou.movieshop.adapter.MoviesAdapter;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.BitmapCache;


public class MoviesFragment extends Fragment {
    //类成员
    private MoviesAdapter adapter;
    List<MovieInfo> mList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    HashMap urlMap;


    public static MoviesFragment newInstance(String param1) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sharedPreferences = getActivity().getSharedPreferences("Login", Activity.MODE_PRIVATE);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_fragment, container, false);
//        Button card_btn_buy1 = getView().findViewById(R.id.card_btn_buy1);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.container);
        tv.setText(agrs1);

        System.out.println("list view");
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.card_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        getNetInfo();
        adapter = new MoviesAdapter(getActivity(),mList);
        mRecyclerView.setAdapter(adapter);

        return view;
    }


    public void getNetInfo(){

        RequestQueue mQueue = Volley.newRequestQueue(getActivity());

        Map<String,Object> params=new HashMap<>();

        params.put("findMovie",sharedPreferences.getString("findMovie",""));
        params.put("status",sharedPreferences.getInt("status",300));
        JSONObject paramsJsonObject=new JSONObject(params);
        if (sharedPreferences.contains("findMovie")){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.remove("findMovie");
            editor.apply();
        }



        urlMap = new HashMap<>();

        mList.add(new MovieInfo("Movie_id0","Movie_name0",
                "90","Actor0","http://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage109.360doc.com%2FDownloadImg%2F2020%2F09%2F1418%2F202146469_3_2020091406123537&refer=http%3A%2F%2Fimage109.360doc.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1685978076&t=fa0a2515f019d3eeb03b6b29bf7e565f"));
        mList.add(new MovieInfo("Movie_id1","Movie_name1",
                "91","Actor1","http://file06.16sucai.com/2017/0601/bb40ff1424d20cbeef4282a7156cbbfc.jpg"));
        mList.add(new MovieInfo("Movie_id2","Movie_name2",
                "92","Actor2","http://img0.baidu.com/it/u=678881018,1969403144&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=667"));
        mList.add(new MovieInfo("Movie_id3","Movie_name3",
                "95","Actor3","http://feitianwu7.com/data/attachment/forum/201808/07/202558x2nc2j3ah2qn23e2.jpg"));
        mList.add(new MovieInfo("Movie_id4","Movie_name4",
                "99","Actor4","http://hbimg.b0.upaiyun.com/083b48b4506fe9da647973eb51ac57a5b95d1d161da52-63bYJZ_fw658"));

    }
}

