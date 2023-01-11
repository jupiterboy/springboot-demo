package com.jupiterboy.springboot.wechat.service;

public interface WeChatService {

    void pushMessage(String openId, String content, String remark);

    void pushMessage(String openId, String first, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String remark);

}
