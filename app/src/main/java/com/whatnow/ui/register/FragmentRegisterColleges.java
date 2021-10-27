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
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.whatnow.R;
import com.whatnow.controllers.users.CollegesController;
import com.whatnow.models.Career;
import com.whatnow.models.College;
import com.whatnow.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRegisterColleges#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegisterColleges extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText srchColleges;
    private TagView lstCollege;
    private TagView tagGroup;
    private ArrayList<Tag> tlist;
    private ArrayList<College> colleges;
    public Map<Tag, College> selectedColleges = new HashMap<>();
    private String token;
    private ArrayList<String> tagList = new ArrayList<>();
    View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentRegisterColleges() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRegisterColleges.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegisterColleges newInstance(String param1, String param2) {
        FragmentRegisterColleges fragment = new FragmentRegisterColleges();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = Utils.getString("token", getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_register_colleges, container, false);

        lstCollege = rootView.findViewById(R.id.lstSkills);
        tagGroup = rootView.findViewById(R.id.tag_view);
        srchColleges = rootView.findViewById(R.id.SearchSkills);

        //set click listener
        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                College college = colleges.get(position);
                selectedColleges.put(tag, college);
                lstCollege.addTag(tag);
                srchColleges.setText("");
            }
        });

        //set long click listener
        lstCollege.setOnTagLongClickListener(new TagView.OnTagLongClickListener() {
            @Override
            public void onTagLongClick(Tag tag, int position) {
                lstCollege.remove(position);
                selectedColleges.remove(tag);
            }
        });

        srchColleges.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("PRINTING ON TEXT CHANGED ");
                System.out.println(s.length());
                if(s.length() > 1){
                    CollegesController.index(token, s.toString()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            System.out.println("RESPONSE Careers index");
                            Gson gson = new Gson();
                            String auxResponse = null;
                            try {
                                auxResponse = response.body().string();
                                System.out.println("Response:");
                                System.out.println(auxResponse);

                                JsonParser parser = new JsonParser();
                                JsonElement json = parser.parse(auxResponse);

                                tlist = new ArrayList<>();
                                colleges = new ArrayList<>();

                                for(JsonElement e: json.getAsJsonArray()){
                                    College college = gson.fromJson(e, College.class);
                                    colleges.add(college);
                                    tlist.add(new Tag(college.getName()));
                                }
                                tagGroup.addTags(tlist);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }
}