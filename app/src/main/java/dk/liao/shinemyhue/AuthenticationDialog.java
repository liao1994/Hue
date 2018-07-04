package dk.liao.shinemyhue;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.todddavies.components.progressbar.ProgressWheel;

public class AuthenticationDialog extends AlertDialog{
    private final static String TAG = "LOG/" + AuthenticationDialog.class.getName();
    private AlertDialog alertDialog;
    private final int authenticationLength = 29;
//
    public AlertDialog getDialog() {
        return alertDialog;
    }
    private static AuthenticationDialogTask dialogTask;
    ProgressWheel pw;
    TextView dialogTitle;
    public AuthenticationDialog(@NonNull Context context) {
        super(context);
        Log.d(TAG,"AuthenticationDialog.Constructor");
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.dialog_authentication,null);
        Builder builder = new Builder(context);
        alertDialog = builder.setView(linearLayout).create();
        RelativeLayout relativeLayout = linearLayout.findViewById(R.id.dialog_authentication_progress_bar);
        pw = relativeLayout.findViewById(R.id.pw_spinner);
        dialogTask = new AuthenticationDialogTask();
        ((TextView) linearLayout.findViewById(R.id.title)).setText(R.string.dialog_authentication_required);
        ((TextView) linearLayout.findViewById(R.id.positiveButton)).setText(R.string.enter_ip_btn_text);
        Button negBtn = linearLayout.findViewById(R.id.negativeButton);
        negBtn.setText(R.string.cancel_btn_txt);
        negBtn.setOnClickListener(cancelDialog());
    }

    private View.OnClickListener cancelDialog(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onCancelClicked");
                closeDialog();
            }
        };

    }
    private void closeDialog(){
        timeoutDialog();
        dialogTask.cancel(true);
    }

    private void timeoutDialog(){
        pw.stopSpinning();
        alertDialog.dismiss();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");

    }
    void run(){
        dialogTask.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"onStop");
        super.onStop();
        timeoutDialog();
    }

    private class AuthenticationDialogTask extends AsyncTask<Void,Integer,Void> {

        private final String TAG = "LOG/" + AuthenticationDialogTask.class.getName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pw.startSpinning();
            alertDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = authenticationLength; i > 0; i--){
                    Thread.sleep(1000);
                    publishProgress(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pw.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG, "task done");
            timeoutDialog();
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            closeDialog();
            Log.d(TAG, "task cancelled");
        }
    }
}