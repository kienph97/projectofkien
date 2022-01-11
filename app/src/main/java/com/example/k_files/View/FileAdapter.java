package com.example.k_files.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.k_files.Model.FileInfo;
import com.example.k_files.Model.OldPath;
import com.example.k_files.Model.RootPath;
import com.example.k_files.Presenter.ItemClickListener;
import com.example.k_files.Presenter.LoadListFileLocal;
import com.example.k_files.Presenter.OthersFunctionFile;
import com.example.k_files.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<FileInfo> mFileInfo;
    private  List<FileInfo> mFileOld = new ArrayList<>();

    public FileAdapter() {
    }

    public FileAdapter(Context mContext, ArrayList<FileInfo> mFileInfo) {
        this.mContext = mContext;
        this.mFileInfo = mFileInfo;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{
        private ImageView mImageFile;
        private TextView mTextName;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageFile = itemView.findViewById(R.id.imageThumb);
            mTextName = itemView.findViewById(R.id.nameFile);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
//            itemView.setOnLongClickListener((View.OnLongClickListener) this);
        }


        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),false);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Copy = menu.add(Menu.NONE, 1, 1, "Copy");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");
            Copy.setOnMenuItemClickListener(onCopyMenu);
            Delete.setOnMenuItemClickListener(onCopyMenu);
        }


        MenuItem.OnMenuItemClickListener onCopyMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FileInfo fileInfo;
                switch (item.getItemId()){
                    case 1:
                        //context = getActivity();
                        fileInfo = mFileInfo.get(getPosition());
                        OthersFunctionFile othersFunctionFile = new OthersFunctionFile();
                        othersFunctionFile.copyData(fileInfo.getPathFile(),mContext);
                        break;
                    case 2:
                        fileInfo = mFileInfo.get(getPosition());
                        int pos = getPosition();
                        String path = fileInfo.getPathFile();
                        File file = new File(path);
                        file.delete();
                        mFileInfo.remove(pos);
                        notifyItemRemoved(pos);
                        notifyItemRangeChanged(pos, mFileInfo.size());
                        break;
                }
                return false;
            }
        };
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View fileView = inflater.inflate(R.layout.row_file,parent,false);
        ViewHolder viewHolder = new ViewHolder(fileView);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        FileInfo fileInfo = mFileInfo.get(position);
        Log.d("kien.ph","" + fileInfo.getMimeType());
        if(fileInfo.getMimeType() != null && (fileInfo.getMimeType().equals("image/png") || fileInfo.getMimeType().equals("image/jpeg"))){
            Glide.with(mContext).load(fileInfo.getPathFile()).into(holder.mImageFile);
        } else {
            Glide.with(mContext).load(R.drawable.ic_baseline_folder_24).into(holder.mImageFile);
        }
        holder.mTextName.setText(fileInfo.getNameFile());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!isLongClick) {
                    FileInfo fileInfo = mFileInfo.get(position);
                    if (fileInfo.getMimeType() == null) {
                        OldPath oldPath = new OldPath();
                        oldPath.mOldPath.add(mFileInfo.get(0).getPathFile());

                        LoadListFileLocal loadListFileLocal = new LoadListFileLocal();
                        List<FileInfo> mListFiles = loadListFileLocal.loadFileLocal(fileInfo.getPathFile());
                        RootPath rootPath = new RootPath();
                        rootPath.setRootPath(fileInfo.getPathFile());
                        mFileInfo.clear();
                        for(FileInfo fileInfo1:mListFiles){
                            mFileInfo.add(fileInfo1);
                        }
                        notifyDataSetChanged();
                    }
                    else if (fileInfo.getMimeType().equals("image/png") || fileInfo.getMimeType().equals("image/jpeg")) {
                        Intent intent = new Intent(mContext, DisplayImage.class);
                        intent.putExtra("image",fileInfo.getPathFile());
                        mContext.startActivity(intent);
                    }
                    else if (fileInfo.getMimeType().equals("video/mp4")) {
                        Intent intent = new Intent(mContext, DisplayVideo.class);
                        intent.putExtra("video",fileInfo.getPathFile());
                        mContext.startActivity(intent);
                    }
                    else {
                        String path = fileInfo.getPathFile();
                        String mime = fileInfo.getMimeType();
                        File file2 = new File(path);
                        Uri uri = Uri.fromFile(file2).normalizeScheme();
                        Intent intent = new Intent();
                        intent.setData(uri);
                        intent.setType(mime);
                        mContext.startActivity(Intent.createChooser(intent,"Open file with"));
                    }

                }
            }
        });
    }

    public int backPress() {
        FileInfo fileInfo = mFileInfo.get(0);
        if(fileInfo.getNameFile().equals("<<")) {
            if (fileInfo.getMimeType() == null) {
                LoadListFileLocal loadListFileLocal = new LoadListFileLocal();
                List<FileInfo> mListFiles = loadListFileLocal.loadFileLocal(fileInfo.getPathFile());
                RootPath rootPath = new RootPath();
                rootPath.setRootPath(fileInfo.getPathFile());
                mFileInfo.clear();
                for(FileInfo fileInfo1:mListFiles){
                    mFileInfo.add(fileInfo1);
                }
                notifyDataSetChanged();
            }
            return 1;
        }
        return 0;
    }
    @Override
    public int getItemCount() {
        return mFileInfo.size();
    }
}
