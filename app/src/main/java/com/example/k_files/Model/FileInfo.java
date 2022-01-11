package com.example.k_files.Model;

public class FileInfo {
    private String nameFile;
    private String pathFile;
    private String mimeType;
    private String size;

    public FileInfo() {
    }

    public FileInfo(String nameFile, String pathFile, String mimeType) {
        this.nameFile = nameFile;
        this.pathFile = pathFile;
        this.mimeType = mimeType;
    }

    public FileInfo(String nameFile, String pathFile, String mimeType, String size) {
        this.nameFile = nameFile;
        this.pathFile = pathFile;
        this.mimeType = mimeType;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
