package com.example.k_files.Presenter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.k_files.R;

public class ChangeFragment implements ChangePageItf{

    @Override
    public void replacePage(Fragment newFragment, FragmentManager fragmentManager) {
        FragmentTransaction ftr = fragmentManager.beginTransaction();
        ftr.replace(R.id.frameLayout, newFragment);
        ftr.addToBackStack(null);
        ftr.commit();
    }
}
