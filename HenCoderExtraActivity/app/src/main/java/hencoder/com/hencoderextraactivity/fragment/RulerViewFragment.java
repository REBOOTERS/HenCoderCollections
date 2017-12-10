package hencoder.com.hencoderextraactivity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hencoder.com.hencoderextraactivity.R;
import yanzhikai.ruler.BooheeRuler;
import yanzhikai.ruler.KgNumberLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class RulerViewFragment extends Fragment {

    private BooheeRuler br_top_head, br_bottom_head, br_left_head, br_right_head;
    private KgNumberLayout knl_top_head, knl_bottom_head, knl_left_head, knl_right_head;

    public RulerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_ruler_view, container, false);
        br_top_head = (BooheeRuler) mView.findViewById(R.id.br_top_head);
        knl_top_head = (KgNumberLayout) mView.findViewById(R.id.knl_top_head);
        knl_top_head.bindRuler(br_top_head);

        br_bottom_head = (BooheeRuler) mView.findViewById(R.id.br_bottom_head);
        knl_bottom_head = (KgNumberLayout) mView.findViewById(R.id.knl_bottom_head);
        knl_bottom_head.bindRuler(br_bottom_head);

        br_left_head = (BooheeRuler) mView.findViewById(R.id.br_left_head);
        knl_left_head = (KgNumberLayout) mView.findViewById(R.id.knl_left_head);
        knl_left_head.bindRuler(br_left_head);

        br_right_head = (BooheeRuler) mView.findViewById(R.id.br_right_head);
        knl_right_head = (KgNumberLayout) mView.findViewById(R.id.knl_right_head);
        knl_right_head.bindRuler(br_right_head);
        return mView;
    }

}
