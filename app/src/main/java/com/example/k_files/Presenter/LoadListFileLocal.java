package com.example.k_files.Presenter;

import android.app.Activity;
import android.util.Log;

import com.example.k_files.Model.FileInfo;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class LoadListFileLocal extends Activity implements LoadListLocal {
    @Override
    public List<FileInfo> loadFileLocal(String path) {
        File file = new File(path);
        String root = file.getParentFile().toString();
        File files[] = file.listFiles();
        List<FileInfo> list = new ArrayList<>();
        Log.d("kien.ph", root + "");
        if(checkRoot(root)){
            FileInfo folderOrFile1 = new FileInfo("<<",root, null);
            list.add(folderOrFile1);
        }
        for(File f:files){
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            String type = fileNameMap.getContentTypeFor(f.getPath());
            FileInfo folderOrFile = new FileInfo(f.getName(), f.getPath(), type);
            list.add(folderOrFile);
        }
        return list;
    }

    @Override
    public List<String> loadNameFile(List<FileInfo> list) {
        List<String> listNames = new ArrayList<>();
        for(FileInfo folderOrFile:list){
            listNames.add(folderOrFile.getNameFile());
        }
        return listNames;
    }

    private boolean checkRoot(String root){
        if(root.equals("/storage/emulated")){
            return false;
        }
        return true;
    }

}
