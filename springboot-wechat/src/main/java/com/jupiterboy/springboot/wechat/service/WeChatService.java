package com.jupiterboy.springboot.wechat.service;

public interface WeChatService {

    void pushMessage(String openId, String content, String remark);

}
