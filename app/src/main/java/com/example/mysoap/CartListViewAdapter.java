package com.example.mysoap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.wallet.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartListViewAdapter extends BaseAdapter {
    //variables
    Context mContext;
    LayoutInflater inflater;
    List<ModelCart> modellist;
    ArrayList<ModelCart> arrayList;
    private int item = 0, totalPrice;
    private String showItem;


    //constructor
    public CartListViewAdapter(Context context, List<ModelCart> modellist) {
        this.modellist = modellist;
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<ModelCart>();
        this.arrayList.addAll(modellist);

    }

    public class ViewHolder {
        TextView mNameTv, mPriceTv, mAmountTv, mNoteTv;
        ImageView mImgIv, Clear;
        Button add, remove;
    }


    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final CartListViewAdapter.ViewHolder holder;
        if (view == null) {
            holder = new CartListViewAdapter.ViewHolder();
            view = inflater.inflate(R.layout.activity_cart_list_item_view, null);

            //locate the views in ActivityCartListItemViewemView.xml
            holder.mNameTv = view.findViewById(R.id.tv1);
            holder.mImgIv = view.findViewById(R.id.image);
            holder.mPriceTv = view.findViewById(R.id.tv2);
            holder.mAmountTv = view.findViewById(R.id.tv3);
            holder.add = view.findViewById(R.id.add);
            holder.remove = view.findViewById(R.id.remove);
            holder.Clear = view.findViewById(R.id.clear);
            view.setTag(holder);


        } else {
            holder = (CartListViewAdapter.ViewHolder) view.getTag();
        }
        holder.mNameTv.setText(modellist.get(position).getName() + " ");
        holder.mImgIv.setImageDrawable(modellist.get(position).getImg());



        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelCart modelCart = new ModelCart(
                        modellist.get(position).getId(), modellist.get(position).getImg(), modellist.get(position).getName(),
                        modellist.get(position).getAmount() + 1, modellist.get(position).getPrice());
                MainActivity.CartList.set(position, modelCart);
                String[] price = modellist.get(position).getPrice().split("\\s+");
                CartActivity.PriceAll += Integer.parseInt(price[0]);
                CartActivity.priceAllTv.setText("ราคารวมทั้งหมด : " + CartActivity.PriceAll + " บาท");
                holder.mPriceTv.setText(Integer.parseInt(price[0])*modellist.get(position).getAmount() + " บาท");
                notifyDataSetChanged();
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelCart modelCart = new ModelCart(
                        modellist.get(position).getId(), modellist.get(position).getImg(), modellist.get(position).getName(),
                        modellist.get(position).getAmount() - 1, modellist.get(position).getPrice());
                MainActivity.CartList.set(position, modelCart);
                notifyDataSetChanged();
                String[] price = modellist.get(position).getPrice().split("\\s+");
                CartActivity.PriceAll -= Integer.parseInt(price[0]);
                CartActivity.priceAllTv.setText("ราคารวมทั้งหมด : " + CartActivity.PriceAll + " บาท");
                holder.mPriceTv.setText(Integer.parseInt(price[0])*modellist.get(position).getAmount() + " บาท");
            }
        });
        holder.mPriceTv.setText(modellist.get(position).getPrice() + " บาท");
        holder.mAmountTv.setText(modellist.get(position).getAmount() + " ชิ้น");


        holder.Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setMessage("คุณต้องการลบลจริงๆๆหรือไม่").setPositiveButton("ลบ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] price = modellist.get(position).getPrice().split("\\s+");
                        CartActivity.PriceAll -= Integer.parseInt(price[0]);
                        CartActivity.priceAllTv.setText("ราคารวมทั้งหมด : " + CartActivity.PriceAll + " บาท");
                        modellist.remove(position);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });

        return view;
    }

}
