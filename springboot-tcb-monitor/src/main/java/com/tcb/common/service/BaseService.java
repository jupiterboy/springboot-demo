package com.tcb.common.service;

import java.util.List;

/**
 * @author yuhai
 * @param <E> 实体类
 *
 * 定义基础的操作实体类的接口
 */
public interface BaseService<E, ID> {

    /**
     * 保存实体类
     * @param e 实体类
     * @return 保存后的实体类(包含主键)
     */
    public E saveEntity(E e);

    /**
     * 删除实体类
     * @param id 实体类的主键
     */
    public void deleteEntity(ID id);

    /**
     * 更新实体类
     * @param e 实体类
     */
    public void updateEntity(E e);

    /**
     * 根据主键查询实体类
     * @param id 实体类的主键
     * @return 查询到的实体类
     */
    public E getEntity(ID id);

    /**
     * 查询所有实体类
     * @return  所有实体类
     */
    public List<E> findAllEntities();
    
}
