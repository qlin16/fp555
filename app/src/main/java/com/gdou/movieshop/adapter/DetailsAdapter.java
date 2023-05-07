package com.gdou.movieshop.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gdou.movieshop.DetailsInfo;
import com.gdou.movieshop.R;

import java.util.List;


public class DetailsAdapter extends RecyclerView.Adapter
        <DetailsAdapter.ContactViewHolder> {
    private List<DetailsInfo> detailsInfoList;
    private AdapterView.OnItemClickListener onItemClickListener;

    private Context context;

    public DetailsAdapter(Context context,List<DetailsInfo> detailsInfoList) {
        this.detailsInfoList = detailsInfoList;
        this.context=context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder
    (ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.movies_details,parent,false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder
            (ContactViewHolder holder, int position) {


        DetailsInfo ci = detailsInfoList.get(position);


        holder.vTime.setText(ci.getTime());
        holder.vRoom.setText(ci.getRoom());
        holder.vPrice.setText(ci.getPrice());

        int adapterPosition = holder.getAdapterPosition();
        if (onItemClickListener == null) {
            holder.vButton.setOnClickListener(new MyOnClickListener(position,detailsInfoList.get(adapterPosition)));
        }
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int position;
        private DetailsInfo data;

        public MyOnClickListener(int position, DetailsInfo data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            showNormalDialog();
        }

        private void showNormalDialog(){

            final AlertDialog.Builder normalDialog =
                    new AlertDialog.Builder(context);
            normalDialog.setTitle("Confirm purchase");
            normalDialog.setMessage("Are you sure to purchase?");
            normalDialog.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context,"You have successfully purchased", Toast.LENGTH_LONG).show();
                        }
                    });
            normalDialog.setNegativeButton("closed",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //...To-do
                        }
                    });
            // 显示
            normalDialog.show();
        }
    }



    @Override
    public int getItemCount() {
        return detailsInfoList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        //create the viewHolder class

        protected TextView vTime;
        protected TextView vRoom;
        protected TextView vPrice;
        protected Button vButton;
        public ContactViewHolder(View itemView) {
            super(itemView);
            vTime = itemView.findViewById(R.id.time);
            vRoom = itemView.findViewById(R.id.room);
            vPrice = itemView.findViewById(R.id.price);
            vButton=itemView.findViewById(R.id.Movie_detail_buy);
        }

    }
}