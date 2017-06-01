package com.wuhulala.dal.mapper;

import com.wuhulala.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基础Mapper类
 *
 * @param <T> 实体
 * @param <K> 主键
 */
public interface BaseMapper<T,K> {



    /**
     * 根据实体查询实体
     *
     * @param object 实体
     *
     * @return 实体
     */
    T find(T object);

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    T findById(K id);

    /**
     * 根据主键列表查询实体列表
     *
     * @param ids 主键列表
     * @return 实体列表
     */
    List<T> findByIdList(List<K> ids);

    /**
     * 查询所有的实体
     *
     * @return 实体列表
     */
    List<T> findAll();

    /**
     * 根据查询条件返回
     * @param page
     * @return 实体集合
     */
    List<T> findWithPage(@Param("page") Page<T> page);

    /**
     * 插入单个实例
     */
    int insert(T t);

    /**
     * 插入多个实例
     */
    int insertList(List<T> objects);


    /**
     * 更新实体
     */
    void update(T t);

    /**
     * 删除实体
     *
     * @param object 实体

     */
    void remove(T object);

    /**
     * 删除实体
     *
     * @param id 主键
     */
    void removeById(K id);

    /**
     * 根据主键列表查询实体列表
     *
     * @param ids 主键列表
     */
    void removeByIdList(List<K> ids);

    int findCountByPage(@Param("page") Page<T> page);

    int selectCount();
}
