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
public class ModuleParameter implements Serializable {
    // TODO: 根据项目创建不同的采集器模块参数
    private String field1;
}