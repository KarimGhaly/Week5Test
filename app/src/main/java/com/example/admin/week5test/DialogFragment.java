package com.example.admin.week5test;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.admin.week5test.model.Item;

/**
 * Created by Admin on 9/29/2017.
 */

public class DialogFragment extends android.app.DialogFragment {
    public static final String TAG = "DialogFragmentTAG";
    DialogListner Listner;

    public DialogFragment(DialogListner listner) {
        Listner = listner;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Options")
                .setItems(R.array.options_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Listner.onShowFull();
                                break;
                            case 1:
                                Listner.onShowSmall();
                                break;
                        }
                    }
                });
        return builder.create();

    }
    public interface DialogListner {
        void onShowFull();
        void onShowSmall();
    }
}
