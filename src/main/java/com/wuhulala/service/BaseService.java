package com.wuhulala.service;

import com.wuhulala.dal.mapper.BaseMapper;
import com.wuhulala.model.Page;
import com.wuhulala.model.PageResult;

import java.util.List;

/**
 * Created by coding on 2017/5/16.
 */
public class BaseService<T, K> {

    private BaseMapper<T, K> baseMapper;

    public BaseService(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 根据实体查询实体
     *
     * @param object 实体
     * @return 实体
     */
    public T find(T object) {
        return baseMapper.find(object);
    }

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    public T findById(K id) {
        return baseMapper.findById(id);
    }

    /**
     * 根据主键列表查询实体列表
     *
     * @param ids 主键列表
     * @return 实体列表
     */
    public List<T> findByIdList(List<K> ids) {
        return baseMapper.findByIdList(ids);
    }

    /**
     * 查询所有的实体
     *
     * @return 实体列表
     */
    public List<T> findAll() {
        return baseMapper.findAll();
    }

    /**
     * 根据查询条件返回
     *
     * @param page
     * @return 实体集合
     */
    public PageResult<T> findWithPage(Page<T> page) {
        return fillPageResult(baseMapper, page);
    }

    /**
     * 插入单个实例
     */
    public int insert(T t){
        return baseMapper.insert(t);
    }

    /**
     * 插入多个实例
     */
    public int insertList(List<T> objects){
        return baseMapper.insertList(objects);
    }


    /**
     * 更新实体
     */
    public void update(T t){
         baseMapper.update(t);
    }

    /**
     * 删除实体
     *
     * @param object 实体
     */
    public void remove(T object){
        baseMapper.remove(object);
    }

    /**
     * 删除实体
     *
     * @param id 主键
     */
    void removeById(K id){
        baseMapper.removeById(id);
    }

    /**
     * 根据主键列表查询实体列表
     *
     * @param ids 主键列表
     */
    public void removeByIdList(List<K> ids){
        baseMapper.removeByIdList(ids);
    }

    public int findCountByPage(Page<T> page){
        return baseMapper.findCountByPage(page);
    }

    public int selectCount(){
        return baseMapper.selectCount();
    }


    public PageResult<T> fillPageResult(BaseMapper<T, K> mapper, Page<T> page) {
        PageResult<T> result = new PageResult<>();

        //处理page的初始下标
        page.setStart((page.getStart() - 1) * page.getLength());

        result.setData(mapper.findWithPage(page));
        result.setRecordsFiltered(mapper.findCountByPage(page));
        result.setRecordsTotal(mapper.selectCount());
        return result;
    }
}
