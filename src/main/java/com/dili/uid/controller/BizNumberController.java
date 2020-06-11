package com.dili.uid.controller;

import com.dili.ss.uid.domain.BizNumber;
import com.dili.ss.uid.service.BizNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 由MyBatis Generator工具自动生成
 * This file was generated on 2020-01-21 14:38:55.
 */
@Controller
@RequestMapping("/bizNumber")
public class BizNumberController {
    @Autowired
    BizNumberService bizNumberService;

    /**
     * 跳转到BizNumber页面
     * @param modelMap
     * @return String
     */
    @RequestMapping(value="/index.html", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        return "bizNumber/index";
    }

    /**
     * 分页查询BizNumber，返回easyui分页信息
     * @param bizNumber
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/listPage.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody String listPage(BizNumber bizNumber) throws Exception {
        return bizNumberService.listEasyuiPageByExample(bizNumber, true).toString();
    }


}