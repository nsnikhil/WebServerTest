package com.nsnik.nrs.webservertest.views.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.nsnik.nrs.webservertest.R;
import com.nsnik.nrs.webservertest.views.fragments.dialogFragments.AddItemDialogFragment;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.homeAdd)
    FloatingActionButton mAddNew;

    private CompositeDisposable mCompositeDisposable;
    private Unbinder mUnbinder;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initialize();
        return view;
    }

    private void initialize() {
        FragmentManager fragmentManager = getFragmentManager();
        mCompositeDisposable = new CompositeDisposable();
        if (fragmentManager != null) {
            mCompositeDisposable.add(RxView.clicks(mAddNew).subscribe(v -> new AddItemDialogFragment().show(fragmentManager, "addItem"), throwable -> Timber.d(throwable.getMessage())));
            fragmentManager.beginTransaction().add(R.id.homeContainer, new ListFragment()).commit();
        }
    }

    /**
     *
     */
    private void cleanUp() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable.dispose();
        }
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
