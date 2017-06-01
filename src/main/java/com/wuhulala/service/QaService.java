package com.wuhulala.service;

import com.wuhulala.dal.mapper.QaMapper;
import com.wuhulala.dal.model.Qa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by coding on 2017/5/16.
 */
@Service
public class QaService extends BaseService<Qa, Long> {

    private final QaMapper qaMapper;

    @Value("${index.least.number : 5}")
    private int indexLeastNumber;

    @Autowired
    public QaService(QaMapper qaMapper) {
        super(qaMapper);
        this.qaMapper = qaMapper;
    }


    public List<Qa> findLeastTenQa() {
        return qaMapper.findLeastQa(indexLeastNumber);
    }
}
