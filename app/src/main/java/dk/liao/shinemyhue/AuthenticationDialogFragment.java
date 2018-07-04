package dk.liao.shinemyhue;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class AuthenticationDialogFragment extends DialogFragment {
    AuthenticationDialog authDialog;
    private final String TAG = "LOG/" + AuthenticationDialogFragment.class.getName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if(getActivity()==null)
            throw new NullPointerException("getActivity is null");

        authDialog = new AuthenticationDialog(getActivity());
        return authDialog.getDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
        authDialog.run();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(TAG,"onCancel");

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(TAG,"onDismiss");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }
}

