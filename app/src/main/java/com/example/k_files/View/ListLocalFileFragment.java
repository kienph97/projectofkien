package com.example.k_files.View;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.k_files.Model.FileInfo;
import com.example.k_files.Model.OldPath;
import com.example.k_files.Model.RootPath;
import com.example.k_files.Model.StorageData;
import com.example.k_files.Presenter.LoadListFileLocal;
import com.example.k_files.Presenter.OthersFunctionFile;
import com.example.k_files.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListLocalFileFragment extends Fragment {
    List<FileInfo> listFiles;
    List<String> listNames;
    RecyclerView mRecycleView;
    FileAdapter mFileAdapter;
    Context context = getContext();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_file, container, false);
        mRecycleView = view.findViewById(R.id.recycleView);
        loadFiles(view, null);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.layout_option_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itempaste:
                RootPath rootPath = new RootPath();
                context = getActivity();
                OthersFunctionFile othersFunctionFile = new OthersFunctionFile();
                try {
                    StorageData storageData = new StorageData();
                    String s = storageData.getDataResponsitory(getContext());
                    int pos = listFiles.size();
                    listFiles.clear();
                    othersFunctionFile.pasteData(rootPath.getRootPath() ,context);
                    LoadListFileLocal loadListFileLocal = new LoadListFileLocal();
                    listFiles = loadListFileLocal.loadFileLocal(rootPath.getRootPath());
                    mFileAdapter = new FileAdapter(getContext(), (ArrayList<FileInfo>) listFiles);
                    mRecycleView.setAdapter(mFileAdapter);
                    mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //loadListAgain();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void backPress() {
        OldPath oldPath = new OldPath();
        loadFiles(getView(), oldPath.mOldPath.get(oldPath.mOldPath.size()-1));
        oldPath.mOldPath.remove(oldPath.mOldPath.size()-1);
    }

    private void loadFiles(View view, String path) {
        String pathRoot;
        if (path != null) {
            pathRoot = path;
        } else {
            pathRoot = Environment.getExternalStorageDirectory()+"/";
        }

        RootPath rootPath = new RootPath();
        rootPath.setRootPath(pathRoot);

        LoadListFileLocal loadListFileLocal = new LoadListFileLocal();
        listFiles = loadListFileLocal.loadFileLocal(pathRoot);
        listNames = loadListFileLocal.loadNameFile(listFiles);
        mFileAdapter = new FileAdapter(getContext(), (ArrayList<FileInfo>) listFiles);
        mRecycleView.setAdapter(mFileAdapter);
        registerForContextMenu(mRecycleView);
        setHasOptionsMenu(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
