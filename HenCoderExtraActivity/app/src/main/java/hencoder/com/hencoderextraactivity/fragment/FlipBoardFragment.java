package hencoder.com.hencoderextraactivity.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hencoder.com.hencoderextraactivity.R;
import hencoder.com.hencoderextraactivity.flipboard.FlipboardView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlipBoardFragment extends Fragment {


    public FlipBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_flip_board, container, false);
        FlipboardView mFlipboardView = mView.findViewById(R.id.flipboard);
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flipboard);
        mFlipboardView.setBitmap(mBitmap);
        return mView;
    }

}
