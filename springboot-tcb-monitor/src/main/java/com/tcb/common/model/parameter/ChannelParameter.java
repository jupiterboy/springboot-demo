package com.tcb.common.model.parameter;

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

    private VibrateParameterField vibrateParameter;
    private TemperatureParameterField temperatureParameter;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VibrateParameterField implements ChannelParameterField {
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
    public static class TemperatureParameterField implements ChannelParameterField {
        private String filed1;

        @Override
        public ChannelParameter toChannelParameter() {
            return ChannelParameter.builder().temperatureParameter(this).build();
        }
    }
}
