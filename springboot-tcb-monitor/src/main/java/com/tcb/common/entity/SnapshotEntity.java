package com.tcb.common.entity;

import com.tcb.common.model.value.MonitorValue;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

/**
 * @author yuhai
 *
 * 每日快照记录实体类
 */
@Setter
@Getter
@Entity(name = "snapshot")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class SnapshotEntity extends ValueEntity {

    /**
     * 设备编号
     */
    private String deviceNumber;

    /**
     * 测点编号
     */
    private String measurePointNumber;

    /**
     * 监测项类型
     */
    private Integer monitorItemType;

    /**
     * 快照监测数据集合
     */
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<MonitorValue> monitorValues;

}
