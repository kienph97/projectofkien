package com.example.k_files.Presenter;

import android.content.Context;

import com.example.k_files.Model.FileInfo;

import java.io.IOException;

public interface OthersFunction {
    void copyData(String srcPath, Context context);
    void pasteData(String desPath, Context context) throws IOException;
    void deleteData(FileInfo file, Context context);
}
