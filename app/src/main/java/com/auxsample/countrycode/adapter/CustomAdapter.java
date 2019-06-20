package com.auxsample.countrycode.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.auxsample.countrycode.R;
import com.auxsample.countrycode.model.RetroCountry;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RetroCountry> dataList;
    private Context context;

    public CustomAdapter(Context context, List<RetroCountry> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        private final View mView;
        private TextView tvCountry;
        private TextView tvCode;
        private ImageView ivFlag;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tvCountry = mView.findViewById(R.id.tvCountry);
            tvCode = mView.findViewById(R.id.tvCode);
            ivFlag = mView.findViewById(R.id.ivFlag);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.tvCountry.setText(dataList.get(position).getName());
        String[] code = dataList.get(position).getCallingCodes();
        holder.tvCode.setText(code[0]);
        String flag = dataList.get(position).getFlag();
        SvgLoader.pluck()
                .with((Activity) context)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(flag, holder.ivFlag);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
