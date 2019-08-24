package com.example.example3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    LayoutInflater inflater;
    ArrayList<String> list;

    public RecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        list = new ArrayList<String>();
        list.add("İstanbul");
        list.add("Ankara");
        list.add("Erzincan");
        list.add("İzmir");
        list.add("Manisa");
        list.add("Karaman");
        list.add("Burdur");
        list.add("Isparta");
        list.add("Aksaray");
        list.add("Edirne");
        list.add("Kırklareli");
        list.add("Kırıkkale");
        list.add("Konya");
        list.add("Mersin");
        list.add("Antalya");
        list.add("Aydın");
        list.add("Muğla");
        list.add("Denizli");
        list.add("Iğdır");
        list.add("Muş");
        list.add("Van");
        list.add("Hatay");
        list.add("Gaziantep");
        list.add("Şanlıurfa");
        list.add("Amasya");
        list.add("Trabzon");
        list.add("Bolu");
        list.add("Artvin");
        list.add("Giresun");
        list.add("Sinop");
        list.add("Samsun");
        list.add("Bursa");
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);

        }

    }
}