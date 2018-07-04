package dk.liao.shinemyhue;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.DialogFragment;

public class LightActivity extends AppCompatActivity {

    private Button mDialogBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        mDialogBtn = findViewById(R.id.openDialogBtn);
        mDialogBtn.setText("authDialog open");
        mDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }


        });

        mDialogBtn = findViewById(R.id.openDialogBtn);
        mDialogBtn.setText("authDialog open");
        mDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }


        });

        //getWindow().setFormat(PixelFormat.RGBA_8888);
//        setContentView(R.layout.activity_color_picker);
//        init();

    }
    private void openDialog() {
        DialogFragment dialogFragment = new AuthenticationDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),getString(R.string.dialog_authentication_tag));
    }
}
//
//    private void init() {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        int initialColor = prefs.getInt("color_3", 0xFF000000);
//
//        mColorPickerView = (ColorPickerView) findViewById(R.id.colorpickerview__color_picker_view);
//        mOldColorPanelView = (ColorPanelView) findViewById(R.id.colorpickerview__color_panel_old);
//        mNewColorPanelView = (ColorPanelView) findViewById(R.id.colorpickerview__color_panel_new);
//
//        mOkButton = (Button) findViewById(R.id.okButton);
//        mCancelButton = (Button) findViewById(R.id.cancelButton);
//
//
//        ((LinearLayout) mOldColorPanelView.getParent()).setPadding(
//                mColorPickerView.getPaddingLeft(), 0,
//                mColorPickerView.getPaddingRight(), 0);
//
//
//        mColorPickerView.setOnColorChangedListener(this);
//        mColorPickerView.setColor(initialColor, true);
//        mOldColorPanelView.setColor(initialColor);
//
//        mOkButton.setOnClickListener(this);
//        mCancelButton.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onColorChanged(int newColor) {
//        mNewColorPanelView.setColor(mColorPickerView.getColor());
//    }
//
//    @Override
//    public void onClick(View v) {
//
//        switch(v.getId()) {
//            case R.id.okButton:
//                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
//                edit.putInt("color_3", mColorPickerView.getColor());
//                edit.commit();
//
//                finish();
//                break;
//            case R.id.cancelButton:
//                finish();
//                break;
//        }
//
//    }
//
//}
