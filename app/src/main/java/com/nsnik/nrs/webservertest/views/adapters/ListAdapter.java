package com.nsnik.nrs.webservertest.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.nsnik.nrs.webservertest.R;
import com.nsnik.nrs.webservertest.data.UserEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private final Context mContext;
    private final CompositeDisposable mCompositeDisposable;
    private List<UserEntity> mUserList;

    public ListAdapter(final Context context, final List<UserEntity> userList) {
        mContext = context;
        mUserList = userList;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.single_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder, int position) {
        final UserEntity userEntity = mUserList.get(position);
        holder.mItemId.setText(String.valueOf(userEntity.getId()));
        holder.mItemName.setText(userEntity.getUserName());
        holder.mItemPhone.setText(String.valueOf(userEntity.getPhoneNo()));
    }

    @Override
    public int getItemCount() {
        return mUserList != null ? mUserList.size() : 0;
    }

    public void modifyList(List<UserEntity> userModelList) {
        mUserList = userModelList;
        notifyDataSetChanged();
    }

    private void cleanUp() {
        mCompositeDisposable.clear();
        mCompositeDisposable.dispose();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        cleanUp();
        super.onDetachedFromRecyclerView(recyclerView);
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
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                mCompositeDisposable.add(RxView.clicks(itemView).subscribe(v -> Toast.makeText(mContext, mUserList.get(getAdapterPosition()).getUserName(), Toast.LENGTH_SHORT).show(), throwable -> Timber.d(throwable.getMessage())));
            }
        }
    }
}
