package com.lbj.callback.concept;


/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-10-08
 * @Description:
 */


public class Receiver {
    public void receive(INotice notice) {
        String result = "mission complete";
        notice.onComplete(result);
    }
}
