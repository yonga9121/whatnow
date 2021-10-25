package com.whatnow.ui.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import com.whatnow.R;

public class Fragment_Skills_4 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView txtVideo;
    private VideoView videoDescripcion;

    View rootView;

    public Fragment_Skills_4() {
        // Required empty public constructor
    }

    public static Fragment_Skills_4 newInstance(String param1, String param2) {
        Fragment_Skills_4 fragment = new Fragment_Skills_4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment__skills_4, container, false);

        txtVideo = rootView.findViewById(R.id.txtVideo);
        videoDescripcion = rootView.findViewById(R.id.videoDescripcion);

        return rootView;
    }
}