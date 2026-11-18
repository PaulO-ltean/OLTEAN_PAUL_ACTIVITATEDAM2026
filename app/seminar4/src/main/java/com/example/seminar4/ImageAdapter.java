package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private List<ImaginiDomeniu>  imagini = null;
    private Context ctx;
    private int resourceLayout;

    public ImageAdapter(List<ImaginiDomeniu> imagini, Context ctx, int resourceLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v = inflater.inflate(resourceLayout, parent, false);

        ImageView iv = v.findViewById(R.id.imagineIV);
        TextView tw = v.findViewById(R.id.tw_img);


        return v;

    }
}
