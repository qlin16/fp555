package com.gdou.movieshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gdou.movieshop.adapter.DetailsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Movie information page, including information such as movies, performances, ticket prices, etc
 *And ticket purchase
 */
public class DetailsActivity extends AppCompatActivity {
    //类成员
    private DetailsAdapter adapter;
    private ImageView details_img;
    private TextView details_name;
    private TextView details_actor;
    private ExpandableTextView details_description;
    private Intent intent;
    private Context context;
    List<DetailsInfo> mList = new ArrayList<>();

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details_img = findViewById(R.id.details_img);
        details_name=findViewById(R.id.details_name);
        details_actor=findViewById(R.id.details_actor);
        details_description=findViewById(R.id.details_description);
        context=getApplicationContext();


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.card_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        getNetInfo();
        adapter = new DetailsAdapter(DetailsActivity.this,mList);
        mRecyclerView.setAdapter(adapter);

    }
    public void getNetInfo(){

        RequestQueue mQueue = Volley.newRequestQueue(DetailsActivity.this);

        Map<String,Object> params=new HashMap<>();
        intent=getIntent();
        String Movie_id=intent.getStringExtra("Movie_id");
        params.put("Movie_id",Movie_id);
        System.out.println("getNetInfo"+Movie_id);


        ExpandableTextView details_description = findViewById(R.id.details_description);
        details_name.setText("Movie_name");
        details_actor.setText("Movie_actor");
        details_description.setText("Movie_details_description");
        /*
        Glide.with(context).load(details_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading)
                .into(details_img);

         */
        mList.add(new DetailsInfo("10：20","NO.1", "30：00"));
        mList.add(new DetailsInfo("11：20","NO.2", "30：00"));
        mList.add(new DetailsInfo("12：20","NO.3", "30：00"));
        mList.add(new DetailsInfo("13：20","NO.1", "30：00"));
        mList.add(new DetailsInfo("14：20","NO.2", "30：00"));
        mList.add(new DetailsInfo("15：20","NO.3", "30：00"));
        mList.add(new DetailsInfo("16：20","NO.1", "30：00"));
        mList.add(new DetailsInfo("17：20","NO.2", "30：00"));
        mList.add(new DetailsInfo("18：20","NO.3", "30：00"));
        mList.add(new DetailsInfo("19：20","NO.4", "30：00"));




    }
}
