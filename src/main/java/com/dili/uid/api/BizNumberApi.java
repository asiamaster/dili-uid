package com.dili.uid.api;

import com.dili.ss.domain.BaseOutput;
import com.dili.uid.component.BizNumberFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 业务号Api
 */
@RestController
@RequestMapping("/bizNumberApi")
public class BizNumberApi {

    /**
     * 用于处理不同环境的编号重复问题
     */
    @Value("${spring.profiles.active:prod}")
    private String profile;
    @Autowired
    private BizNumberFunction bizNumberFunction;

    @RequestMapping(value = "/leaseOrderCode.api", method = { RequestMethod.GET, RequestMethod.POST})
    public BaseOutput<String> getOrderCode() {
        return BaseOutput.success().setData(bizNumberFunction.getBizNumberByType("leaseOrder"));
    }


}