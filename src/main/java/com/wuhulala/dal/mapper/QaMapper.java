package com.wuhulala.dal.mapper;

import com.wuhulala.dal.model.Qa;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by coding on 2017/5/15.
 */
@Mapper
public interface QaMapper extends BaseMapper<Qa, Long> {

    /**
     * 获取最新的Qa
     * @param number
     * @return
     */
    List<Qa> findLeastQa(int number);
}
