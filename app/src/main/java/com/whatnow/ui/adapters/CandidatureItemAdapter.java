package com.whatnow.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.whatnow.R;
import com.whatnow.models.Candidature;
import com.whatnow.ui.VideoActivity;

import java.util.ArrayList;

public class CandidatureItemAdapter extends BaseAdapter {

    ArrayList<Candidature> candidatures;
    LayoutInflater iflter;
    String token;

    public CandidatureItemAdapter(Context context , ArrayList<Candidature> candidatures, String token){
        this.candidatures = candidatures;
        this.token = token;
        this.iflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return this.candidatures.size();
    }

    @Override
    public Object getItem(int i) {
        if(i < this.candidatures.size()){
            return this.candidatures.get(i % this.candidatures.size());
        }else{
            return this.candidatures.get(this.candidatures.size() - 1);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = this.iflter.inflate(R.layout.activity_candidature_list_item, null);
        Candidature c = (Candidature) getItem(i);
        TextView name = (TextView) view.findViewById(R.id.candidatureItemName);
        TextView company = (TextView) view.findViewById(R.id.candidatureItemCompany);
        TextView desc = (TextView) view.findViewById(R.id.candidatureItemDesc);
        RoundedImageView logo = (RoundedImageView) view.findViewById(R.id.candidatureItemImg);
        System.out.println(c.getOffer().getCompany().getLogo_url());
        Glide.with(view).load(c.getOffer().getCompany().getLogo_url()).apply(new RequestOptions().override(60, 60)).into(logo);
        if(c.getOffer().getDesc_video_url() != null)
            if(!c.getOffer().getDesc_video_url().isEmpty()){
                System.out.println("VIDEOFOUND");
                logo.setBorderColor(Color.parseColor("#FFFFBF00"));
                logo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), VideoActivity.class);
                        intent.putExtra("url", c.getOffer().getDesc_video_url());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                    }
                });
            }
        name.setText(c.getOffer().getName());
        company.setText(c.getOffer().getCompany().getName());
        desc.setText(c.getOffer().getDesc());
        return view;
    }
}
