package com.tcb.common.entity;

import java.util.Date;

/**
 * @author yuhai
 *
 * 定制化字段实体类，从数据库中查询出指定字段的数据
 */
public interface AlarmCustomEntity {

    Double getValue();

    Integer getStatus();

    Date getCreateTime();
}
