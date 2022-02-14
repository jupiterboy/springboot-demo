package com.jupiterboy.springboot.jpa.json.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoModel implements Serializable {
    private String name;// 货物名称
    private String count;// 货物数量
    private String weight;// 货物重量
    private String volume;// 货物体积
    private String type;// 货物类型
    private String price;// 货物价格
    private String remark;// 货物备注
}