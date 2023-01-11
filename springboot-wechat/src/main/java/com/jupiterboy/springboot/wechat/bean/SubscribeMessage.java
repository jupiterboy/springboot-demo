package com.jupiterboy.springboot.wechat.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscribeMessage {

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("url")
    private String url;
    @JsonProperty("data")
    private SubscribeMessageData data;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SubscribeMessageData{

        @JsonProperty("first")
        private DataValue first;
        @JsonProperty("keyword1")
        private DataValue keyword1;
        @JsonProperty("keyword2")
        private DataValue keyword2;
        @JsonProperty("keyword3")
        private DataValue keyword3;
        @JsonProperty("keyword4")
        private DataValue keyword4;
        @JsonProperty("keyword5")
        private DataValue keyword5;
        @JsonProperty("remark")
        private DataValue remark;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataValue {
        @JsonProperty("value")
        private String value;
        @JsonProperty("color")
        private String color = "#000000";

    }
}
