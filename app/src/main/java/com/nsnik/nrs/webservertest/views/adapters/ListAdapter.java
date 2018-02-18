package com.nsnik.nrs.webservertest.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nsnik.nrs.webservertest.R;
import com.nsnik.nrs.webservertest.model.UserModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<UserModel> mUserList;
    private final Context mContext;

    public ListAdapter(final Context context, final List<UserModel> userList) {
        mContext = context;
        mUserList = userList;
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.single_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder, int position) {
        final UserModel userModel = mUserList.get(position);
        holder.mItemId.setText(String.valueOf(userModel.id()));
        holder.mItemName.setText(userModel.name());
        holder.mItemPhone.setText(String.valueOf(userModel.phone()));
    }

    @Override
    public int getItemCount() {
        return mUserList != null ? mUserList.size() : 0;
    }

    public void modifyList(List<UserModel> userModelList) {
        mUserList = userModelList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemId)
        TextView mItemId;
        @BindView(R.id.itemName)
        TextView mItemName;
        @BindView(R.id.itemPhone)
        TextView mItemPhone;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
