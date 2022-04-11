package com.tcb.common.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author yuhai
 *
 * 配置相关实体类的基类
 *
 */
@Setter
@Getter
@MappedSuperclass
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ConfigEntity<P> extends UpdateEntity<String> {

    /**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    /**
     * 配置实体名称
     */
    private String name;

    /**
     * 配置实体类型
     */
    private Integer type;

    /**
     * 配置实体编号
     */
    private String number;

    /**
     * 配置实体描述
     */
    private String description;

    /**
     * 配置实体参数
     */
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private P parameter;
}
