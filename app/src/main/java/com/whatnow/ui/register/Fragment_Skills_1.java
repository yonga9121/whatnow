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
import com.whatnow.controllers.users.SkillsController;
import com.whatnow.models.Skill;
import com.whatnow.utils.Utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Skills_1 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText srchSkills;
    private TagView lstSelect;
    private TagView tagGroup;
    private ArrayList<Tag> tlist;
    private ArrayList<Skill> skills;
    public Map<Tag, Skill> selectedSkills = new HashMap<>();
    private String token;

    private ArrayList<String> tagList = new ArrayList<>();

    View rootView;

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
        token = Utils.getString("token", this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment__skills_1, container, false);

        lstSelect = rootView.findViewById(R.id.lstSkills);
        tagGroup = rootView.findViewById(R.id.tag_view);
        srchSkills = rootView.findViewById(R.id.SearchSkills);

        //set click listener
        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                Skill skill = skills.get(position);
                selectedSkills.put(tag, skill);
                lstSelect.addTag(tag);
                srchSkills.setText("");
            }
        });

        //set long click listener
        lstSelect.setOnTagLongClickListener(new TagView.OnTagLongClickListener() {
            @Override
            public void onTagLongClick(Tag tag, int position) {
                lstSelect.remove(position);
                selectedSkills.remove(tag);
            }
        });

        srchSkills.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("PRINTING ON TEXT CHANGED ");
                System.out.println(s.length());
                if(s.length() > 1){
                    SkillsController.index(token, s.toString()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            System.out.println("RESPONSE Skills index");
                            Gson gson = new Gson();
                            String auxResponse = null;
                            try {
                                auxResponse = response.body().string();
                                System.out.println("Response:");
                                System.out.println(auxResponse);

                                JsonParser parser = new JsonParser();
                                JsonElement json = parser.parse(auxResponse);

                                tlist = new ArrayList<>();
                                skills = new ArrayList<>();

                                for(JsonElement e: json.getAsJsonArray()){
                                    Skill skill = gson.fromJson(e, Skill.class);
                                    skills.add(skill);
                                    tlist.add(new Tag(skill.getName()));
                                }
                                tagGroup.addTags(tlist);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //JsonElement careersJson = json.getAsJsonObject().get("careers");
                            //JsonElement softSkillsJson = json.getAsJsonObject().get("soft_skills");
                            //JsonElement descVideoUrl = json.getAsJsonObject().get("desc_video").getAsJsonObject().get("url");
                            //JsonElement collegesJson = json.getAsJsonObject().get("colleges");
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

    private void setTags(CharSequence cs) {

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