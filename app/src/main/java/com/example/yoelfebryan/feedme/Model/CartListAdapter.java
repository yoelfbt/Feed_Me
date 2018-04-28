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
 * Created by Yoel Febryan on 23/04/2018.
 */

public class CartListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Cart> itemlist;

    public CartListAdapter(Context context, int layout, ArrayList<Cart> itemlist) {
        this.context = context;
        this.layout = layout;
        this.itemlist = itemlist;
    }

    @Override
    public int getCount() {
        return  itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return itemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemlist.get(position).getId();
    }

    private class ViewHolder{
        TextView txtName, txtHarga, txtDesc, txtJumlah, txtTotal, txtKeterangan;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.txtName = (TextView) row.findViewById(R.id.textView);
            holder.txtHarga = (TextView) row.findViewById(R.id.textView2);
            holder.txtDesc = (TextView) row.findViewById(R.id.textView3);
            holder.txtJumlah = (TextView) row.findViewById(R.id.textView7);
            holder.txtTotal = (TextView) row.findViewById(R.id.textView8);
            holder.txtKeterangan = (TextView) row.findViewById(R.id.textView9);
            row.setTag(holder);
        }else {
            holder = (CartListAdapter.ViewHolder) row.getTag();
        }

        Cart item = itemlist.get(position);

        holder.txtName.setText("Nama :"+item.getName());
        holder.txtHarga.setText("Harga :"+item.getHarga());
        holder.txtDesc.setText("Deskripsi :"+item.getDesc());
        holder.txtJumlah.setText("Qty :"+item.getQty());
        holder.txtTotal.setText("Total :"+item.getTotal());
        holder.txtKeterangan.setText("Status :"+item.getKeterangan());

        return row;
    }
}
