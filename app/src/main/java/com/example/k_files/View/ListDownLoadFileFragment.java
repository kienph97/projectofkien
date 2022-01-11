package com.example.k_files.View;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.k_files.Model.FileInfo;
import com.example.k_files.Presenter.LoadListFileLocal;
import com.example.k_files.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListDownLoadFileFragment extends Fragment {
    List<FileInfo> listFiles;
    RecyclerView mRecycleView;
    FileAdapter mFileAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_file, container, false);
        String path = Environment.getExternalStorageDirectory().toString()+ File.separator+Environment.DIRECTORY_DOWNLOADS;
        mRecycleView = view.findViewById(R.id.recycleView);
        LoadListFileLocal mLoadLocal = new LoadListFileLocal();
        listFiles = mLoadLocal.loadFileLocal(path);
        mFileAdapter = new FileAdapter(getContext(), (ArrayList<FileInfo>) listFiles);
        mRecycleView.setAdapter(mFileAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
