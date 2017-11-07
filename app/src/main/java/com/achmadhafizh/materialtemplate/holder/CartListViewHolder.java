package com.achmadhafizh.materialtemplate.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.achmadhafizh.materialtemplate.R;
import com.achmadhafizh.materialtemplate.model.Item;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by achmad.hafizh on 11/7/2017.
 */

public class CartListViewHolder extends RecyclerView.ViewHolder {
    public @BindView(R.id.name)
    TextView name;

    public @BindView(R.id.description)
    TextView description;

    public @BindView(R.id.price)
    TextView price;

    public @BindView(R.id.thumbnail)
    ImageView thumbnail;

    public @BindView(R.id.view_background)
    RelativeLayout viewBackground;

    public @BindView(R.id.view_foreground)
    RelativeLayout viewForeground;

    public CartListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(Context context, Item item) {
        name.setText(item.getName());
        description.setText(item.getDescription());
        price.setText("₹" + item.getPrice());

        Glide.with(context)
                .load(item.getThumbnail())
                .into(thumbnail);
    }
}
