package com.whatnow.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

    public static final boolean saveString( String key, String value, Context context){
        try {
            SharedPreferences sp = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor spe = sp.edit();
            spe.putString(key, value);
            spe.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final String getString( String key, Context context){
        try {
            SharedPreferences sp = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            String token = sp.getString(key, null);
            System.out.println("TOKEEEEN " + token);
            if(token != null)
                return token;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
