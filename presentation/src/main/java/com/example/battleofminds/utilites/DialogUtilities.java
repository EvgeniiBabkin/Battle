package com.example.battleofminds.utilites;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.battleofminds.R;
import com.example.battleofminds.constants.AppConstants;

public class DialogUtilities extends DialogFragment {

    private Activity mActivity;

    private String dialogTitle, dialogText, positiveText, negativeText, viewIdText;
    private TextView txtDialogTitle, txtDialogText;

    public interface OnCompleteListener {
        void onComplete(Boolean isOkPressed, String viewIdText);
    }

    private OnCompleteListener mListener;

    public static DialogUtilities newInstance(String dialogTitle, String dialogText, String yes, String no, String viewIdText){
        Bundle args = new Bundle();
        args.putString(AppConstants.BUNDLE_KEY_TITLE, dialogTitle);
        args.putString(AppConstants.BUNDLE_KEY_MESSAGE, dialogText);
        args.putString(AppConstants.BUNDLE_KEY_YES, yes);
        args.putString(AppConstants.BUNDLE_KEY_NO, no);
        args.putString(AppConstants.BUNDLE_KEY_VIEW_ID, viewIdText);
        DialogUtilities fragment = new DialogUtilities();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;

        try {
            this.mListener = (OnCompleteListener) activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View rootView = LayoutInflater.from(mActivity).inflate(R.layout.fragment_custom_dialog, null);
        initVar();
        initView(rootView);
        initFunctionality();

        return new AlertDialog.Builder(mActivity)
                .setView(rootView)
                .setPositiveButton(positiveText, (dialogInterface, i) -> {
                    mListener.onComplete(true, viewIdText);
                })
                .setNegativeButton(negativeText, (dialogInterface, i) -> {
                    if (dialogInterface != null)
                        mListener.onComplete(false, viewIdText);
                })
                .create();

    }

    public void initVar() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            dialogTitle = getArguments().getString(AppConstants.BUNDLE_KEY_TITLE);
            dialogText = getArguments().getString(AppConstants.BUNDLE_KEY_MESSAGE);
            positiveText = getArguments().getString(AppConstants.BUNDLE_KEY_YES);
            negativeText = getArguments().getString(AppConstants.BUNDLE_KEY_NO);
            viewIdText = getArguments().getString(AppConstants.BUNDLE_KEY_VIEW_ID);
        }
    }

    public void initView(View rootView) {
        txtDialogTitle = rootView.findViewById(R.id.dialog_title);
        txtDialogText = rootView.findViewById(R.id.dialog_text);
    }

    public void initFunctionality() {
        txtDialogTitle.setText(dialogTitle);
        txtDialogText.setText(dialogText);
    }
}
