package com.lbj.callback.download;


/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-10-08
 * @Description:
 */


public class Client implements IDownload {

    private Server mServer = null;

    public Client() {
    }

    public void download(String url) {
        Client client = new Client();
        mServer = new Server(Client.this, url);
        mServer.run();
    }

    @Override
    public void startDownload() {
        System.out.println("startDownload");
    }

    @Override
    public void stopDownload() {
        System.out.println("download complete stopDownload");
    }

    @Override
    public void showDownloadURL(String url) {
        System.out.println("Download URL: " + url);
    }

    @Override
    public void showDownloadProgress(int progress) {
        System.out.println("DownloadProgress: " + progress + "%");
    }

}
