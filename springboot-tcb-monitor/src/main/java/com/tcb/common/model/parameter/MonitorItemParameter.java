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
public class MonitorItemParameter implements Serializable {

    // TODO: 根据项目创建不同的监测项参数
    private VibrateItemParameterField vibrateItemParameter;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VibrateItemParameterField implements MonitorItemParameterField {
        private String field1;

        @Override
        public MonitorItemParameter toMonitorItemParameter() {
            return MonitorItemParameter.builder().vibrateItemParameter(this).build();
        }
    }
}
