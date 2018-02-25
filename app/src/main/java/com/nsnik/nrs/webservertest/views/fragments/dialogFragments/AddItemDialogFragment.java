package com.nsnik.nrs.webservertest.views.fragments.dialogFragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.nsnik.nrs.webservertest.R;
import com.nsnik.nrs.webservertest.viewModel.ListViewModel;

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
public class AddItemDialogFragment extends DialogFragment {

    @BindView(R.id.dialogName)
    TextInputEditText mDialogName;
    @BindView(R.id.dialogPhone)
    TextInputEditText mDialogPhone;
    @BindView(R.id.dialogSubmit)
    Button mSubmit;
    @BindView(R.id.dialogCancel)
    Button mCancel;

    private Unbinder mUnbinder;
    private CompositeDisposable mCompositeDisposable;
    private Resources mResources;
    private ListViewModel mListViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_item_dialog, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initialize();
        return view;
    }

    /**
     *
     */
    private void initialize() {
        if (getActivity() != null) mResources = getActivity().getResources();
        mCompositeDisposable = new CompositeDisposable();
        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mCompositeDisposable.add(RxView.clicks(mCancel).subscribe(v -> dismiss(), throwable -> Timber.d(throwable.getMessage())));
        mCompositeDisposable.add(RxView.clicks(mSubmit).subscribe(v -> {
            if (isNotEmpty()) {
                mListViewModel.insertValue(mDialogName.getText().toString(), mDialogPhone.getText().toString());
                dismiss();
            }
        }, throwable -> Timber.d(throwable.getMessage())));
    }


    /**
     * @return true if the fields are not empty
     */
    private boolean isNotEmpty() {
        if (mDialogName.getText().toString().isEmpty()) {
            mDialogName.setError(mResources.getString(R.string.dialogErrorEmptyName));
            return false;
        }
        if (mDialogPhone.getText().toString().isEmpty()) {
            mDialogPhone.setError(mResources.getString(R.string.dialogErrorEmptyPhone));
            return false;
        }
        return true;
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
