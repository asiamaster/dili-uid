package com.dili.uid.controller;

import com.dili.ss.domain.BaseOutput;
import com.dili.uid.domain.BizNumberRuleDomain;
import com.dili.uid.service.BizNumberRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api("/bizNumberRule")
@Controller
@RequestMapping("/bizNumberRule")
public class BizNumberRuleController {
    @Autowired
    BizNumberRuleService bizNumberRuleService;

    /**
     * 跳转到BizNumberRule页面
     * @param modelMap
     * @return String
     */
    @ApiOperation("跳转到BizNumberRule页面")
    @RequestMapping(value="/index.html", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        return "bizNumberRule/index";
    }

    /**
     * 分页查询BizNumberRule，返回easyui分页信息
     * @param bizNumberRuleDomain
     * @return String
     * @throws Exception
     */
    @ApiOperation(value="分页查询BizNumberRule", notes = "分页查询BizNumberRule，返回easyui分页信息")
    @ApiImplicitParams({
		@ApiImplicitParam(name="BizNumberRule", paramType="form", value = "BizNumberRule的form信息", required = false, dataType = "string")
	})
    @RequestMapping(value="/listPage.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody String listPage(BizNumberRuleDomain bizNumberRuleDomain) throws Exception {
        return bizNumberRuleService.listEasyuiPageByExample(bizNumberRuleDomain, true).toString();
    }

    /**
     * 新增BizNumberRule
     * @param bizNumberRuleDomain
     * @return BaseOutput
     */
    @ApiOperation("新增BizNumberRule")
    @ApiImplicitParams({
		@ApiImplicitParam(name="BizNumberRule", paramType="form", value = "BizNumberRule的form信息", required = true, dataType = "string")
	})
    @RequestMapping(value="/insert.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody BaseOutput insert(BizNumberRuleDomain bizNumberRuleDomain) {
        bizNumberRuleService.insertSelective(bizNumberRuleDomain);
        return BaseOutput.success("新增成功");
    }

    /**
     * 修改BizNumberRule
     * @param bizNumberRuleDomain
     * @return BaseOutput
     */
    @ApiOperation("修改BizNumberRule")
    @ApiImplicitParams({
		@ApiImplicitParam(name="BizNumberRule", paramType="form", value = "BizNumberRule的form信息", required = true, dataType = "string")
	})
    @RequestMapping(value="/update.action", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody BaseOutput update(BizNumberRuleDomain bizNumberRuleDomain) {
        bizNumberRuleService.updateSelective(bizNumberRuleDomain);
        return BaseOutput.success("修改成功");
    }

    /**
     * 删除BizNumberRule
     * @param id
     * @return BaseOutput
     */
    @ApiOperation("删除BizNumberRule")
    @ApiImplicitParams({
		@ApiImplicitParam(name="id", paramType="form", value = "BizNumberRule的主键", required = true, dataType = "long")
	})
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


}