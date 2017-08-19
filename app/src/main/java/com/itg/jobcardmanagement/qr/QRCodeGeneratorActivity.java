package com.itg.jobcardmanagement.qr;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.common.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  QRCodeGeneratorActivity extends AppCompatActivity {

    public final static int QRcodeWidth = 500;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_info_qr)
    TextView txtInfoQr;
    @BindView(R.id.img_qr)
    ImageView imgQr;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_generator);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//        if(Prefs.getString(CommonMethod.USERNAME,null)==null){
//            Prefs.putString(CommonMethod.USERNAME,"6862f519-81e8-4d8c-9b3b-2a0dbc682b08");
//        }
        final String carId = Prefs.getString(CommonMethod.SELECTED_CAR, "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
//                    bitmap = TextToImageEncode(userID);
//
//                    imgQr.setImageBitmap(bitmap);
                    textToImageQr(carId);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        }, 500);
    }


    private Bitmap textToImageQr(String value) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(value, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            imgQr.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);

    }
}
