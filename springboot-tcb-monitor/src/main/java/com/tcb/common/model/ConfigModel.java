package com.tcb.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.entity.ConfigEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ConfigModel<E extends ConfigEntity, P> implements Serializable {

    @JsonIgnore
    protected E entity;

    public ConfigModel(E entity) {
        this.entity = entity;
    }

    public String getId() {
        return entity.getId();
    }

    public void setId(String id) {
        entity.setId(id);
    }

    public String getName() {
        return entity.getName();
    }

    public void setName(String name) {
        entity.setName(name);
    }

    public Integer getType() {
        return entity.getType();
    }

    public void setType(Integer type) {
        entity.setType(type);
    }

    public String getNumber() {return entity.getNumber();}

    public void setNumber(String number) {
        entity.setNumber(number);
    }

    public String getDescription() {
        return entity.getDescription();
    }

    public void setDescription(String description) {
        entity.setDescription(description);
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return entity.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        entity.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateTime() {
        return entity.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        entity.setUpdateTime(updateTime);
    }

    public String getUpdateUser() {
        return entity.getUpdateUser();
    }

    public void setUpdateUser(String updateUser) {
        entity.setUpdateUser(updateUser);
    }

    public P getParameter() {return (P)entity.getParameter();}

    public void setParameter(P parameter) { entity.setParameter(parameter); }

    public void update(E entity) { this.entity = entity; }


}
