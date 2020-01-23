package com.dili.uid.service;

import com.dili.ss.base.BaseService;
import com.dili.uid.domain.BizNumberRuleDomain;

/**
 * 由MyBatis Generator工具自动生成
 * This file was generated on 2020-01-21 14:38:55.
 */
public interface BizNumberRuleService extends BaseService<BizNumberRuleDomain, Long> {
    /**
     * 根据业务类型查询规则
     * @param type
     * @return
     */
    BizNumberRuleDomain getByType(String type);
}