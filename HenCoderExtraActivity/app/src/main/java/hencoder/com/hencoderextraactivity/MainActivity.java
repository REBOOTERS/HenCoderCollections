package hencoder.com.hencoderextraactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hencoder.com.hencoderextraactivity.flipboard.FlipboardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlipboardView mFlipboardView = (FlipboardView) findViewById(R.id.flipboard);
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flipboard);
        mFlipboardView.setBitmap(mBitmap);
    }
}
