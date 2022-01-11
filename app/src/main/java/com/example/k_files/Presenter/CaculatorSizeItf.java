package com.example.k_files.Presenter;

import android.content.Context;
import android.util.Log;

public interface CaculatorSizeItf {
    int caculatorImage(Context context);
    int caculatorVideo(Context context);
    int caculatorAudio(Context context);
    Long caculatorUsaSize(String path);
}
