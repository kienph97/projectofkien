package com.example.k_files.Model;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class StorageData {
    public void storageData(String srcPath, Context context){
        SharedPreferences preferences = context.getSharedPreferences("file",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("key",srcPath);
        editor.commit();
    }
    public String getDataResponsitory(Context context){
        SharedPreferences preferences = context.getSharedPreferences("file", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        return preferences.getString("key","");
    }
}
