package com.example.yoelfebryan.feedme.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoelfebryan.feedme.R;

import java.util.ArrayList;

/**
 * Created by Yoel Febryan on 29/03/2018.
 */

public class SellerListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Seller> itemlist;

    public SellerListAdapter(Context context, int layout, ArrayList<Seller> itemlist) {
        this.context = context;
        this.layout = layout;
        this.itemlist = itemlist;
    }

    @Override
    public int getCount() {
        return itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return itemlist.get(position);
    }

    @Override
    public long getItemId(int position) {return itemlist.get(position).getId();}

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtNohp, txtAlamat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.txtName = (TextView) row.findViewById(R.id.textView);
            holder.txtNohp = (TextView) row.findViewById(R.id.textView2);
            holder.txtAlamat = (TextView) row.findViewById(R.id.textView3);
            row.setTag(holder);
        }else {
            holder = (ViewHolder) row.getTag();
        }

        Seller seller = itemlist.get(position);

        holder.txtName.setText(seller.getName());
        holder.txtNohp.setText(seller.getNohp());
        holder.txtAlamat.setText(seller.getAlamat());

        return row;
    }
}
