package com.dili.uid.controller;

import com.dili.ss.domain.BaseOutput;
import com.dili.ss.uid.constants.BizNumberConstant;
import com.dili.ss.uid.domain.BizNumberRule;
import com.dili.ss.uid.service.BizNumberRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 由MyBatis Generator工具自动生成
 * This file was generated on 2020-01-21 14:38:55.
 */
@Controller
@RequestMapping("/bizNumberRule")
public class BizNumberRuleController {
    @Autowired
    BizNumberRuleService bizNumberRuleService;

//    @Autowired
//    UidRestfulRpc uidRestfulRpc;
    /**
     * 跳转到BizNumberRule页面
     * @param modelMap
     * @return String
     */
    @RequestMapping(value="/index.html", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
//        System.out.println(uidRestfulRpc.bizNumber("privateCustomer").getData());
        return "bizNumberRule/index";
    }

    /**
     * 分页查询BizNumberRule，返回easyui分页信息
     * @param bizNumberRuleDomain
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/listPage.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody String listPage(BizNumberRule bizNumberRuleDomain) throws Exception {
        return bizNumberRuleService.listEasyuiPageByExample(bizNumberRuleDomain, true).toString();
    }

    /**
     * 新增BizNumberRule
     * @param bizNumberRuleDomain
     * @return BaseOutput
     */
    @RequestMapping(value="/insert.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody BaseOutput insert(BizNumberRule bizNumberRuleDomain) {
        bizNumberRuleService.insertSelective(bizNumberRuleDomain);
        return BaseOutput.success("新增成功");
    }

    /**
     * 修改BizNumberRule
     * @param bizNumberRuleDomain
     * @return BaseOutput
     */
    @RequestMapping(value="/update.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody BaseOutput update(BizNumberRule bizNumberRuleDomain) {
        bizNumberRuleService.updateExactSimple(bizNumberRuleDomain);
        return BaseOutput.success("修改成功");
    }

    /**
     * 删除BizNumberRule
     * @param id
     * @return BaseOutput
     */
    @RequestMapping(value="/delete.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody BaseOutput delete(Long id) {
        bizNumberRuleService.delete(id);
        return BaseOutput.success("删除成功");
    }

    /**
     * 启/禁用
     * @param id
     * @return BaseOutput
     */
    @RequestMapping(value="/doEnable.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody BaseOutput doEnable(Long id, Boolean enable) {
        bizNumberRuleService.updateEnable(id, enable);
        return BaseOutput.success("操作成功");
    }

    /**
     * 清空(当前实例)缓存
     * @return BaseOutput
     */
    @GetMapping(value="/clearCache.action")
    public @ResponseBody BaseOutput clearCache(@RequestParam String type) {
        BizNumberConstant.bizNumberCache.remove(type);
        return BaseOutput.success("操作成功");
    }

}