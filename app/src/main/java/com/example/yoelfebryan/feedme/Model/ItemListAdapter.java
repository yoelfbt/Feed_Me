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

public class ItemListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Item> itemlist;

    public ItemListAdapter(Context context, int layout, ArrayList<Item> itemlist) {
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
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtHarga, txtDesc;
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
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);
        }else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = itemlist.get(position);

        holder.txtName.setText(item.getName());
        holder.txtHarga.setText(item.getHarga());
        holder.txtDesc.setText(item.getDesc());

        byte[] itemImage = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage,0,itemImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
