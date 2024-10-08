package com.lbj.callback.download;


/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-10-08
 * @Description:
 */


public class Main {
    private static final String URL = "https://www.baidu.com";

    public static void main(String[] args) {
        Client client = new Client();
        client.download(URL);
    }
}
