package com.whatnow.ui.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.whatnow.R;

import java.util.ArrayList;

public class Fragment_Skills_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SearchView srchSkills;
    private ListView lstSkills;
    private ListView lstSelect;
    //private RecyclerView lstSelect;
    View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Skills_1() {
        // Required empty public constructor
    }

    public static Fragment_Skills_1 newInstance(String param1, String param2) {
        Fragment_Skills_1 fragment = new Fragment_Skills_1();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment__skills_1, container, false);

        srchSkills = rootView.findViewById(R.id.SearchSkills);
        lstSkills = rootView.findViewById(R.id.lstSkills);
        lstSelect = rootView.findViewById(R.id.lstSelect);

        ArrayList<Double> intNumero = new ArrayList<Double>();
        ArrayList<String> strSeleccionadas = new ArrayList<String>();

        for(int i=0;i<=Integer.parseInt("6");i++){
            intNumero.add(Math.pow(i,3));
        }

        // Arreglo con SKILLS
        ArrayAdapter<?> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, intNumero);
        lstSkills.setAdapter(adapter);

        // SKILLS seleccionadas
        ArrayAdapter<?> seleccionadas = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, strSeleccionadas);
        lstSelect.setAdapter(seleccionadas);

        lstSkills.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                strSeleccionadas.add(lstSkills.getItemAtPosition(i).toString());
                lstSelect.setAdapter(seleccionadas);
                return false;
            }
        });

        return rootView;
    }
}