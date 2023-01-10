package com.jupiterboy.springboot.uniapp.service.impl;

import com.jupiterboy.springboot.uniapp.bean.AccessTokenResponse;
import com.jupiterboy.springboot.uniapp.bean.SubscribeMessage;
import com.jupiterboy.springboot.uniapp.bean.SubscribeMessageResponse;
import com.jupiterboy.springboot.uniapp.service.UniAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Service
public class UniAppServiceImpl implements UniAppService {

    private static final String APP_ID = "wx4f3c0c7b50459789";
    private static final String SECRET = "b953bc752b650a917bed314dd4686a1b";
    private static final String TEMPLATE_ID = "WYrR2-yBCWdwjrdjOpIVLkzE5qrZLIJpl2YidebNu-g";
    @Autowired
    private RestTemplate restTemplate;

    private String accessToken;

    @PostConstruct
    public void init() {
        accessToken = getAccessToken();
        log.info("init access_token: " + accessToken);
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAccessToken(){
        accessToken = getAccessToken();
        log.info("refresh access_token: " + accessToken);
    }

    @Override
    public void pushMessage(String openId, String content, String remark) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" +  accessToken;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SubscribeMessage.SubscribeMessageData data = SubscribeMessage.SubscribeMessageData.builder()
                .thing3(new SubscribeMessage.DataValue(content))
                .time2(new SubscribeMessage.DataValue(sdf.format(new Date())))
                .thing4(new SubscribeMessage.DataValue(remark)).build();
        SubscribeMessage subscribeMessage = SubscribeMessage.builder().toUser(openId).templateId(TEMPLATE_ID).data(data).build();
        SubscribeMessageResponse response = restTemplate.postForObject(url, subscribeMessage, SubscribeMessageResponse.class);
        log.info("response: " + response);
        if(response.getErrcode() == 0){
            log.info("推送成功");
        }else{
            log.error("推送失败:" + response);
        }
    }

    private String getAccessToken() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APP_ID, SECRET);
        AccessTokenResponse response = restTemplate.getForObject(url, AccessTokenResponse.class);
        return response.getAccessToken();
    }

}
