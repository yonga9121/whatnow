package com.whatnow.ui.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.whatnow.R;

import java.util.ArrayList;

public class Fragment_Skills_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private EditText srchSkills;
    private TagView lstSelect;
    private TagView tagGroup;

    private ArrayList<String> tagList = new ArrayList<>();

    View rootView;


    public Fragment_Skills_2() {
        // Required empty public constructor
    }

    public static Fragment_Skills_2 newInstance(String param1, String param2) {
        Fragment_Skills_2 fragment = new Fragment_Skills_2();
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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment__skills_2, container, false);

        lstSelect = rootView.findViewById(R.id.lstSkills);
        tagGroup = rootView.findViewById(R.id.tag_view);
        srchSkills = rootView.findViewById(R.id.SearchSkills);

        tagList.add("hola");
        tagList.add("prueba");
        tagList.add("facebook");
        tagList.add("youutube");

        //set click listener
        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                lstSelect.addTag(tag);
                srchSkills.setText("");
            }
        });

        //set long click listener
        lstSelect.setOnTagLongClickListener(new TagView.OnTagLongClickListener() {
            @Override
            public void onTagLongClick(Tag tag, int position) {
                lstSelect.remove(position);
            }
        });

        srchSkills.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTags(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

    private void setTags(CharSequence cs) {

        if (cs.toString().equals("")) {
            tagGroup.addTags(new ArrayList<Tag>());
            return;
        }

        String text = cs.toString();
        ArrayList<Tag> tags = new ArrayList<>();
        Tag tag;


        for (int i = 0; i < tagList.size(); i++) {
            if (tagList.get(i).toLowerCase().startsWith(text.toLowerCase())) {
                tag = new Tag(tagList.get(i).toString());
                tags.add(tag);
            }
        }

        tagGroup.addTags(tags);
    }
}