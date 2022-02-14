package com.jupiterboy.springboot.jpa.json.entity;

import com.jupiterboy.springboot.jpa.json.model.AddedServiceModel;
import com.jupiterboy.springboot.jpa.json.model.CargoModel;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ExpressOrder{
    /**主键自增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**商品相关信息 */
    @Type(type = "json")
    @Column(columnDefinition = "json" )
    private CargoModel cargoModel;

    /**增值服务信息 */
    @Type(type = "json")
    @Column(columnDefinition = "json" )
    private List<AddedServiceModel> addedServiceModelList;
}