package com.lbj.callback.download;

public interface IDownload {
    void startDownload();
    void stopDownload();
    void showDownloadURL(String url);
    void showDownloadProgress(int progress);
}
