package com.whatnow.ui.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.whatnow.R;

public class Fragment_Skills_3 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText txtLogros;
    private EditText txtInstitucion;
    private Switch swtEgresado;
    private CalendarView dateGraduacion;

    View rootView;

    public Fragment_Skills_3() {
        // Required empty public constructor
    }

    public static Fragment_Skills_3 newInstance(String param1, String param2) {
        Fragment_Skills_3 fragment = new Fragment_Skills_3();
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
        rootView = inflater.inflate(R.layout.fragment__skills_3, container, false);

        txtLogros = rootView.findViewById(R.id.editTxtLogros);
        txtInstitucion = rootView.findViewById(R.id.editTxtInstitucion);
        swtEgresado = rootView.findViewById(R.id.swtEgresado);
        dateGraduacion = rootView.findViewById(R.id.dateGraduacion);

        return rootView;
    }
}