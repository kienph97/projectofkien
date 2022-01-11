package com.example.k_files.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.k_files.Presenter.ChangeFragment;
import com.example.k_files.R;

public class HomeFragment extends Fragment implements View.OnClickListener{
    FragmentManager fm;
    private TextView tvImage, tvAudio, tvVideo, tvDocument, tvDownload, tvLocal;
    private ImageView imImage, imAudio, imVideo, imDocument, imDownload, imLocal;
    private Button mAnalysis;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        mapping(view);
        tvVideo.setOnClickListener(this);
        tvAudio.setOnClickListener(this);
        tvLocal.setOnClickListener(this);
        tvImage.setOnClickListener(this);
        tvDownload.setOnClickListener(this);
        tvDocument.setOnClickListener(this);
        imVideo.setOnClickListener(this);
        imAudio.setOnClickListener(this);
        imLocal.setOnClickListener(this);
        imDownload.setOnClickListener(this);
        imImage.setOnClickListener(this);
        imDocument.setOnClickListener(this);
        mAnalysis.setOnClickListener(this);
        return view;
    }
    private void mapping(View view){
        tvAudio = view.findViewById(R.id.tv_audio);
        tvDocument = view.findViewById(R.id.tv_document);
        tvDownload = view.findViewById(R.id.tv_download);
        tvImage = view.findViewById(R.id.tv_image);
        tvLocal = view.findViewById(R.id.tv_local);
        tvVideo = view.findViewById(R.id.tv_video);
        imAudio = view.findViewById(R.id.image_audio);
        imDocument = view.findViewById(R.id.image_document);
        imDownload = view.findViewById(R.id.image_download);
        imImage = view.findViewById(R.id.image_image);
        imLocal = view.findViewById(R.id.image_local);
        imVideo = view.findViewById(R.id.image_video);
        mAnalysis = view.findViewById(R.id.analysis);
    }

    @Override
    public void onClick(View view) {
        ChangeFragment changeFragment = new ChangeFragment();
        switch (view.getId()){
            case R.id.image_audio:
                fm = getActivity().getSupportFragmentManager();

                FragmentTransaction ftra = fm.beginTransaction();
                ftra.addToBackStack(null);
                ftra.replace(R.id.frameLayout,new ListAudioFileFragment());
                ftra.commit();
                break;
            case R.id.tv_audio:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrat = fm.beginTransaction();
                ftrat.replace(R.id.frameLayout,new ListAudioFileFragment());
                ftrat.addToBackStack(null);
                ftrat.commit();
                break;
            case R.id.image_document:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrd = fm.beginTransaction();
                ftrd.replace(R.id.frameLayout,new ListDocumentFileFragment());
                ftrd.addToBackStack(null);
                ftrd.commit();
                break;
            case R.id.tv_document:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrdt = fm.beginTransaction();
                ftrdt.replace(R.id.frameLayout,new ListDocumentFileFragment());
                ftrdt.addToBackStack(null);
                ftrdt.commit();
                break;
            case R.id.image_download:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrt = fm.beginTransaction();
                ftrt.replace(R.id.frameLayout,new ListDownLoadFileFragment());
                ftrt.addToBackStack(null);
                ftrt.commit();
                break;
            case R.id.tv_download:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrtt = fm.beginTransaction();
                ftrtt.replace(R.id.frameLayout,new ListDownLoadFileFragment());
                ftrtt.addToBackStack(null);
                ftrtt.commit();
                break;
            case R.id.image_image:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftri = fm.beginTransaction();
                ftri.replace(R.id.frameLayout,new ListImageFileFragment());
                ftri.addToBackStack(null);
                ftri.commit();
                break;
            case R.id.tv_image:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrit = fm.beginTransaction();
                ftrit.replace(R.id.frameLayout,new ListImageFileFragment());
                ftrit.addToBackStack(null);
                ftrit.commit();
                break;
            case R.id.image_video:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrv = fm.beginTransaction();
                ftrv.replace(R.id.frameLayout,new ListVideoFileFragment());
                ftrv.addToBackStack(null);
                ftrv.commit();
                break;
            case R.id.tv_video:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrvt = fm.beginTransaction();
                ftrvt.replace(R.id.frameLayout,new ListVideoFileFragment());
                ftrvt.addToBackStack(null);
                ftrvt.commit();
                break;
            case R.id.image_local:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrl = fm.beginTransaction();
                ftrl.replace(R.id.frameLayout,new ListLocalFileFragment());
                ftrl.addToBackStack(null);
                ftrl.commit();
                break;
            case R.id.tv_local:
                fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ftrlt = fm.beginTransaction();
                ftrlt.replace(R.id.frameLayout,new ListLocalFileFragment());
                ftrlt.addToBackStack(null);
                ftrlt.commit();
                break;
            case R.id.analysis:
                Intent intent = new Intent(getContext(), AnalysisMemoryActivity.class);
                startActivity(intent);
                break;

        }
    }
}
