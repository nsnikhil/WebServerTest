package com.nsnik.nrs.webservertest.views.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nsnik.nrs.webservertest.R;
import com.nsnik.nrs.webservertest.model.UserModel;
import com.nsnik.nrs.webservertest.viewModel.ListViewModel;
import com.nsnik.nrs.webservertest.views.adapters.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */

public class ListFragment extends Fragment {

    @BindView(R.id.fragmentList)
    RecyclerView mList;
    @BindView(R.id.fragmentSwipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;

    private Unbinder mUnbinder;
    private ListAdapter mListAdapter;
    private List<UserModel> mDemoList;
    private ListViewModel mListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initialize();
        return view;
    }

    /**
     *
     */
    private void initialize() {

        final FragmentActivity fragmentActivity = getActivity();
        mSwipeRefresh.setRefreshing(true);

        mDemoList = new ArrayList<>();

        mListAdapter = new ListAdapter(fragmentActivity, mDemoList);
        mList.setLayoutManager(new LinearLayoutManager(fragmentActivity));
        mList.addItemDecoration(new DividerItemDecoration(fragmentActivity, RecyclerView.VERTICAL));


        mList.setAdapter(mListAdapter);

        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        mListViewModel.getDemoList().observe(this, this::updateList);
    }

    private void updateList(List<UserModel> newList){
        mDemoList = newList;
        mListAdapter.modifyList(mDemoList);
        mSwipeRefresh.setRefreshing(false);
    }

    /**
     *
     */
    private void cleanUp() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        cleanUp();
        super.onDestroy();
    }
}
