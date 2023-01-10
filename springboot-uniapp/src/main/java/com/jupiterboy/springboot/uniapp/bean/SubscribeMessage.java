package com.jupiterboy.springboot.uniapp.bean;

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
    @JsonProperty("data")
    private SubscribeMessageData data;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SubscribeMessageData{

        @JsonProperty("thing3")
        private DataValue thing3;
        @JsonProperty("time2")
        private DataValue time2;
        @JsonProperty("thing4")
        private DataValue thing4;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataValue {
        @JsonProperty("value")
        private String value;
    }
}
