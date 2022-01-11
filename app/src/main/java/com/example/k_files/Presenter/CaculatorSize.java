package com.example.k_files.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.k_files.Model.FileInfo;

import java.io.File;

public class CaculatorSize implements CaculatorSizeItf {

    @Override
    public int caculatorImage(Context mContext) {
        int size = 0;
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,
                null,null,null,null);
        if(cursor != null){
            int sizeCollum = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
            while (cursor.moveToNext()){
                size += cursor.getInt(sizeCollum);
            }
        }
        return size/1024/1024;
    }

    @Override
    public int caculatorVideo(Context mContext) {
        int size = 0;
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,
                null,null,null,null);
        if(cursor != null) {
            int sizeCollum = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
            while (cursor.moveToNext()) {
                size += cursor.getInt(sizeCollum);
            }
        }
        return size/1024/1024;
    }

    @Override
    public int caculatorAudio(Context mContext) {
        int size = 0;
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,
                null,null,null,null);
        if(cursor != null){
            int sizeCollum = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE);
            while (cursor.moveToNext()){
                size += cursor.getInt(sizeCollum);
            }
        }
        return size/1024/1024;
    }

    @Override
    public Long caculatorUsaSize(String path) {
        File file = new File(path);
        return file.getUsableSpace()/1024/1024;
    }
}
