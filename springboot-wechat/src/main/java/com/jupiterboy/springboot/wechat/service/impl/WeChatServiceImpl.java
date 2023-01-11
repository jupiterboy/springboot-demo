package com.jupiterboy.springboot.wechat.service.impl;

import com.jupiterboy.springboot.wechat.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
//@Service
public class WeChatServiceImpl implements WeChatService {

    private static final String TEMPLATE_ID = "WYrR2-yBCWdwjrdjOpIVLkzE5qrZLIJpl2YidebNu-g";

    @Autowired
    private WxMpService wxMpService;

    public void pushMessage(String openId, String content, String remark) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 创建微信模板消息对象
        WxMpTemplateMessage message = new WxMpTemplateMessage();
        message.setToUser(openId);
        message.setTemplateId(TEMPLATE_ID);

        message.getData().add(new WxMpTemplateData("thing3", content));
        message.getData().add(new WxMpTemplateData("time2", sdf.format(new Date())));
        message.getData().add(new WxMpTemplateData("thing4", remark));

        // 推送模板消息
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(message);
            log.info("推送成功!");
        } catch (WxErrorException e) {
            log.error("推送失败!");
            e.printStackTrace();
        }

    }

    @Override
    public void pushMessage(String openId, String first, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String remark) {

    }
}
