package br.com.andersonaltissimo.festafimdeano.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anderson on 13/07/17.
 */

public class SecurityPreferences {

    private final SharedPreferences sharedPreferences;

    public SecurityPreferences (Context context){
        this.sharedPreferences = context.getSharedPreferences("FimdeAno", Context.MODE_PRIVATE);
    }

    public void storedString(String key, String value){
        this.sharedPreferences.edit().putString(key,value).apply();
    }

    public String getStoredString(String key){
        return this.sharedPreferences.getString(key, "");
    }
}
