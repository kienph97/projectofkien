package com.example.k_files.Presenter;

import com.example.k_files.Model.FileInfo;

import java.util.List;

public interface LoadListLocal {
    List<FileInfo> loadFileLocal(String path);
    List<String> loadNameFile(List<FileInfo> list);
}
