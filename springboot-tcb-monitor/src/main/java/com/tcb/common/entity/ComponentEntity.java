package com.tcb.common.entity;

import com.tcb.common.constant.ComponentType;
import com.tcb.common.model.parameter.ComponentParameter;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yuhai
 *
 * 组件实体类, 该类为树形结构,可以嵌套,具体树形节点根据类型进行区分
 *
 * 联维项目：设备组(DEVIC), 组件（MODULE），部件（COMPONENT）
 *
 * @see ComponentType
 */
@Setter
@Getter
@Entity(name = "component")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ComponentEntity extends TreeEntity<ComponentEntity, ComponentParameter> {

    /**
     * 采集器所属组织机构
     */
    private String orgId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "componentEntity")
    protected Set<MeasurePointEntity> measurePointEntities = new HashSet<MeasurePointEntity>();

    /**
     * 存储用户的ID列表，用于授权
     */
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<String> users;

    public void addChild(ComponentEntity child) {
        child.setParent(this);
        this.getChildren().add(child);
    }

    public void addMeasurePointEntity(MeasurePointEntity measurePointEntity) {
        measurePointEntity.setComponentEntity(this);
        this.measurePointEntities.add(measurePointEntity);
    }

}
