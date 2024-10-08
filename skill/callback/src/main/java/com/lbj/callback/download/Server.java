package com.lbj.callback.download;


import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-10-08
 * @Description:
 */


public class Server {
    private IDownload downloadCallback;
    private String downloadUrl;
    private Timer timer;
    private int progress;

    public Server(IDownload downloadCallback, String downloadUrl) {
        this.downloadCallback = downloadCallback;
        this.downloadUrl = downloadUrl;
        this.progress = 0;
    }

    public void run() {
        downloadCallback.showDownloadURL(downloadUrl);
        downloadCallback.startDownload();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                downloadCallback.showDownloadProgress(progress);
                if (progress >= 100) {
                    downloadCallback.stopDownload();
                    timer.cancel();
                }
                progress += 1;
            }
        }, 0, 100);
    }
}
