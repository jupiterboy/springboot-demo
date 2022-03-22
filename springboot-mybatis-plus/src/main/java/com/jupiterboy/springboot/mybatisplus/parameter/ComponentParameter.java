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
public class ComponentParameter implements Serializable {

    // TODO: 根据项目创建不同的组件参数

    private String field1;
}
