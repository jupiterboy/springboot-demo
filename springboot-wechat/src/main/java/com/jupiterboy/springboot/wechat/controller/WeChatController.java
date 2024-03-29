package com.jupiterboy.springboot.wechat.controller;

import com.jupiterboy.springboot.wechat.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Slf4j
public class WeChatController {

    @Autowired private WeChatService weChatService;

    /**
     * 微信API核心Service对象
     */
    @Autowired
    private WxMpService mWxMpService = null;

    @RequestMapping("/wechat/push1")
    public void push1() {
        String openId = "ohfLP5KYvFPFcp5jWd2MBFNglTt8";
        weChatService.pushMessage(openId, "测试内容", "测试备注");
    }

    @RequestMapping("/wechat/push2")
    public void push2() {
        String openId = "oWIrY1WuF8Mymbk4BkjPgQ6XrRPU";
        weChatService.pushMessage(openId, "尊敬客户，您的设备有新通知，请及时查看", "设备预警", "天津",
                "通广科技园", "测试样机", "2023-01-11 09:36:32", "设备编号为D0000001");
    }

    @RequestMapping(value = "/service")
    public String service(HttpServletRequest request) throws IOException {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info("signature: " + signature + " timestamp: " + timestamp + " nonce: " + nonce + " echostr: " + echostr);

        // 如果验证不通过，则不是来自微信的请求，即非法请求
        if (mWxMpService.checkSignature(timestamp, nonce, signature) && StringUtils.isNotBlank(echostr)) {
            return echostr;
        }
        return "非法请求";
    }

}
