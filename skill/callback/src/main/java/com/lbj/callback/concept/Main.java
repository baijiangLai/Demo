package com.lbj.callback.concept;


/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-10-08
 * @Description:
 */


public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();

        receiver.receive(new INotice() {
            @Override
            public void onComplete(String result) {
                System.out.println("Callback received: " + result);
            }
        });
    }
}
