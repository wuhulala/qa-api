package com.wuhulala.controller;

import com.alibaba.fastjson.JSON;
import com.wuhulala.ApiConstants;
import com.wuhulala.dal.model.Qa;
import com.wuhulala.service.QaService;
import com.wuhulala.util.BaseResult;
import com.wuhulala.util.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

/**
 * author： wuhulala
 * date： 2017/5/20
 * version: 1.0
 * description: Qa 控制器
 */
@CrossOrigin(ApiConstants.CROSS_ADDRESS)
@RestController
@RequestMapping("/api/qa")
public class QaController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(QaController.class);

    @Autowired
    private QaService qaService;

    @GetMapping("/{id}")
    public BaseResult<Qa> getQaById(@PathVariable("id") Long id) {
        logger.debug(MessageFormat.format("请求id【{0}】的qa", id));
        Qa qa = qaService.findById(id);
        return qa != null ? fillSuccessBaseResult(qa) : fillErrorBaseResult(ReturnCode.ERROR_404);
    }

    @GetMapping("/least")
    public BaseResult<List<Qa>> getLeastQa(){
        logger.debug("获取最新发布的QA");
        List<Qa> qas = qaService.findLeastTenQa();
        return fillSuccessBaseResult(qas);
    }

    @PostMapping
    public BaseResult<Qa> addOneQa(@RequestBody Qa qa){
        logger.debug(MessageFormat.format("添加一个 Qa【{0}】", JSON.toJSONString(qa)));
        int number = qaService.insert(qa);
        return number == 1 ? fillSuccessBaseResult(qa) : fillErrorBaseResult(ReturnCode.ERROR_SQL);
    }

}
