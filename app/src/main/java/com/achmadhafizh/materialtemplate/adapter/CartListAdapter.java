package com.achmadhafizh.materialtemplate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.achmadhafizh.materialtemplate.R;
import com.achmadhafizh.materialtemplate.holder.CartListViewHolder;
import com.achmadhafizh.materialtemplate.model.Item;

import java.util.List;

/**
 * Created by achmad.hafizh on 11/7/2017.
 */

public class CartListAdapter extends RecyclerView.Adapter<CartListViewHolder> {
    private Context context;
    private List<Item> cartList;

    public CartListAdapter(Context context, List<Item> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public void onBindViewHolder(CartListViewHolder cartListViewHolder, int i) {
        final Item model = cartList.get(i);
        cartListViewHolder.bind(context, model);
    }

    @Override
    public CartListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list_item, viewGroup, false);
        return new CartListViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(int position) {
        cartList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Item item, int position) {
        cartList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
