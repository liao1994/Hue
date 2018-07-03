package dk.liao.shinemyhue;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class AuthenticationDialogFragment extends DialogFragment {
    AuthenticationDialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if(getActivity()==null)
            throw new NullPointerException("getActivity is null");

        dialog = new AuthenticationDialog(getActivity());
        return dialog.getDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.run();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        dialog.dismiss();
    }


}

