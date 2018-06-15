package dk.liao.shinemyhue;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.todddavies.components.progressbar.ProgressWheel;

public class AuthenticationDialog extends AlertDialog{
    private final static String TAG = "LOG/" + AuthenticationDialog.class.getName();
    private Builder builder;
    private Context context;
    private AlertDialog alertDialog;
    private static AuthenticationDialogTask dialogTask;
    ProgressWheel pw;
    public AuthenticationDialog(@NonNull Context context) {
        super(context);
        LayoutInflater inflater = getLayoutInflater();
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.authentication_progress_bar, null);
        pw = relativeLayout.findViewById(R.id.pw_spinner);
        builder = new AlertDialog.Builder(context);
        this.context = context;
        alertDialog = builder.setView(relativeLayout)
                .setTitle(context.getString(R.string.authentication_required))
                .setNegativeButton(R.string.enter_ip_btn_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                })
                .setPositiveButton(R.string.cancel_btn_txt, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                        //TODO do a boardcast
                    }
                })
                .create();
        dialogTask = new AuthenticationDialogTask();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void run(){
        dialogTask.execute();
    }
    public void dismiss(){
        alertDialog.dismiss();
        dialogTask.cancel(true);
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
                for (int i = 30; i > 0; i--){
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
            alertDialog.dismiss();
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d(TAG, "task cancelled");
        }
    }

}