package com.example.k_files.Presenter;

import android.content.Context;

import com.example.k_files.Model.FileInfo;
import com.example.k_files.Model.StorageData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OthersFunctionFile implements OthersFunction {

    @Override
    public void copyData(String srcPath, Context context) {
        StorageData storageData = new StorageData();
        storageData.storageData(srcPath,context);
    }

    @Override
    public void pasteData(String desPath, Context context) throws IOException {
        StorageData storageData = new StorageData();
        String newDesPath = setPathDes(storageData.getDataResponsitory(context),desPath);
        File des = new File(newDesPath);
        File src = new File(storageData.getDataResponsitory(context)) ;
        InputStream inputStream = new FileInputStream(src);
        try {
            OutputStream outputStream = new FileOutputStream(des);
            try{
                byte[] buf = new byte[1024];
                int len;
                while((len = inputStream.read(buf)) > 0){
                    outputStream.write(buf,0,len);
                }
            }finally {
                outputStream.close();
            }
        }finally {
            inputStream.close();
        }
    }

    @Override
    public void deleteData(FileInfo file, Context context) {

    }
    private String setPathDes(String srcPath, String desPath){
        File file = new File(srcPath);
        String name = file.getName();
        return desPath + File.separator + name;
    }

}
