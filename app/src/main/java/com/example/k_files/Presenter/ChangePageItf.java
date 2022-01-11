package com.example.k_files.Presenter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public interface ChangePageItf {
    void replacePage(Fragment newFragment, FragmentManager fragmentManager);
}
