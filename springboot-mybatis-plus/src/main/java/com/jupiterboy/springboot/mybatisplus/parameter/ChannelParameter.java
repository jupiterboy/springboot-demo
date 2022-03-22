package com.jupiterboy.springboot.mybatisplus.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelParameter implements Serializable {

    // TODO: 根据项目创建不同的通道参数

    private VibrateParameter vibrateParameter;
    private TemperatureParameter temperatureParameter;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VibrateParameter implements IChannelParameter {
        private String filed1;
        private String filed2;

        @Override
        public ChannelParameter toChannelParameter() {
            return ChannelParameter.builder().vibrateParameter(this).build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TemperatureParameter implements IChannelParameter{
        private String filed1;

        @Override
        public ChannelParameter toChannelParameter() {
            return ChannelParameter.builder().temperatureParameter(this).build();
        }
    }
}
