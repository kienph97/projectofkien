package com.example.k_files.Presenter;

import android.content.Context;

import com.example.k_files.Model.FileInfo;

import java.util.List;

public interface LoadListCategory {
    List<FileInfo> loadListFileCategory(Context mContext, String type);
}
