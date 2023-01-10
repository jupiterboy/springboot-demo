package com.jupiterboy.springboot.uniapp.controller;

import com.jupiterboy.springboot.uniapp.service.UniAppService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Slf4j
public class UniAppController {

    @Autowired private UniAppService uniAppService;

    @RequestMapping("/uniapp/push")
    public void pushMessage() {
        String openId = "ohfLP5KYvFPFcp5jWd2MBFNglTt8";
        uniAppService.pushMessage(openId, "测试内容", "测试备注");
    }

    @RequestMapping(value = "/service")
    public String service(HttpServletRequest request) throws IOException {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info("signature: " + signature + " timestamp: " + timestamp + " nonce: " + nonce + " echostr: " + echostr);

        if (StringUtils.isNotBlank(echostr)) {
            return echostr;
        }
        return null;
    }

}
