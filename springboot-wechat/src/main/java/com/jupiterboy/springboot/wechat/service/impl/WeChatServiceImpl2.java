package com.jupiterboy.springboot.wechat.service.impl;

import com.jupiterboy.springboot.wechat.bean.AccessTokenResponse;
import com.jupiterboy.springboot.wechat.bean.SubscribeMessage;
import com.jupiterboy.springboot.wechat.bean.SubscribeMessageResponse;
import com.jupiterboy.springboot.wechat.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class WeChatServiceImpl2 implements WeChatService {

    private static final String APP_ID = "wx4f8732f26d4364a4";
    private static final String SECRET = "5ac7c9bd6d8c2120ddf6a67ad2136048";
    private static final String TEMPLATE_ID = "fJIKdSwJYX8ZgaH8XOlaIIyr60FszObCsXDohPWU7Zc";
    @Autowired
    private RestTemplate restTemplate;

    private String accessToken;

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void init() {
        accessToken = getAccessToken();
        log.info("init access_token: " + accessToken);
    }


    @Scheduled(cron = "* 0/30 * * * ?")
    public void refreshAccessToken(){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                accessToken = getAccessToken();
                log.info("refresh access_token: " + accessToken);
            }
        });
    }

    public void pushMessage(String openId, String content, String remark) {
        // TODO
    }

    @Override
    public void pushMessage(String openId, String first, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String remark) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" +  accessToken;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SubscribeMessage.SubscribeMessageData data = SubscribeMessage.SubscribeMessageData.builder()
                        .first(new SubscribeMessage.DataValue(first, "#173177"))
                        .keyword1(new SubscribeMessage.DataValue(keyword1, "#173177"))
                        .keyword2(new SubscribeMessage.DataValue(keyword2, "#173177"))
                        .keyword3(new SubscribeMessage.DataValue(keyword3, "#173177"))
                        .keyword4(new SubscribeMessage.DataValue(keyword4, "#173177"))
                        .keyword5(new SubscribeMessage.DataValue(keyword5, "#173177"))
                        .remark(new SubscribeMessage.DataValue(remark, "#173177"))
                        .build();
                SubscribeMessage subscribeMessage = SubscribeMessage.builder()
                        .toUser(openId).templateId(TEMPLATE_ID).url("http://www.baidu.com").data(data).build();
                SubscribeMessageResponse response = restTemplate.postForObject(url, subscribeMessage, SubscribeMessageResponse.class);
                log.info("response: " + response);
                if(response.getErrcode() == 0){
                    log.info("推送成功");
                }else{
                    log.error("推送失败:" + response);
                }
            }
        });
    }

    private String getAccessToken() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APP_ID, SECRET);
        AccessTokenResponse response = restTemplate.getForObject(url, AccessTokenResponse.class);
        return response.getAccessToken();
    }
}
