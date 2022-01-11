package com.example.k_files.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.k_files.Model.FileInfo;
import com.example.k_files.Presenter.LoadListFileCategory;
import com.example.k_files.R;

import java.util.ArrayList;
import java.util.List;

public class ListVideoFileFragment extends Fragment {
    List<FileInfo> listFiles;
    RecyclerView mRecycleView;
    FileAdapter mFileAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_file, container, false);
        mRecycleView = view.findViewById(R.id.recycleView);
        LoadListFileCategory mLoadCategory = new LoadListFileCategory();
        listFiles = mLoadCategory.loadListFileCategory(getContext(), "VIDEO");
        mFileAdapter = new FileAdapter(getContext(), (ArrayList<FileInfo>) listFiles);
        mRecycleView.setAdapter(mFileAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
