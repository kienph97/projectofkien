package com.example.k_files.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.k_files.Model.FileInfo;

import java.util.ArrayList;
import java.util.List;

public class LoadListFileCategory implements LoadListCategory {
    @Override
    public List<FileInfo> loadListFileCategory(Context mContext, String type) {
        List<FileInfo> list = new ArrayList<>();
        if(type.equals("IMAGE")){
            Cursor cursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,
                    null,null,null,null);
            if(cursor != null){
                int nameCollum = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
                int pathCollum = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                int mimeCollum = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE);
                int sizeCollum = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
                int size = 0;
                while (cursor.moveToNext()){
                    size += cursor.getInt(sizeCollum);
                    FileInfo fileInfo = new FileInfo(cursor.getString(nameCollum),
                            cursor.getString(pathCollum),cursor.getString(mimeCollum));

                    list.add(fileInfo);
                }
                Log.d("kien.ph", size/1024/1024+"");
            }
        } else if(type.equals("VIDEO")){
            Cursor cursor = mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,
                    null,null,null,null);
            if(cursor != null){
                int nameCollum = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                int pathCollum = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                int mimeCollum = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE);
                int sizeCollum = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
                int size = 0;
                while (cursor.moveToNext()){
                    size += cursor.getInt(sizeCollum);
                    FileInfo fileInfo = new FileInfo(cursor.getString(nameCollum),
                            cursor.getString(pathCollum),cursor.getString(mimeCollum));
                    list.add(fileInfo);
                }
                Log.d("kien.ph", size/1024/1024+"");
            }
        } else if(type.equals("AUDIO")){
            Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,
                    null,null,null,null);
            if(cursor != null){
                int nameCollum = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                int pathCollum = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                int mimeCollum = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE);
                while (cursor.moveToNext()){
                    FileInfo fileInfo = new FileInfo(cursor.getString(nameCollum),
                            cursor.getString(pathCollum),cursor.getString(mimeCollum));
                    list.add(fileInfo);
                }
            }
        }

        return list;
    }
}
