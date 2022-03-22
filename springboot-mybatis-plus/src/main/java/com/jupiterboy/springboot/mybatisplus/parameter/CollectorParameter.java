package com.jupiterboy.springboot.mybatisplus.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_NULL)
public class CollectorParameter implements Serializable {

    // TODO: 根据项目创建不同的采集器参数

    private String field1;

    private String field2;
}
