package com.dili.uid.service.impl;

import com.dili.ss.base.BaseServiceImpl;
import com.dili.ss.dto.DTOUtils;
import com.dili.uid.constants.BizNumberConstant;
import com.dili.uid.domain.BizNumberRuleDomain;
import com.dili.uid.mapper.BizNumberRuleMapper;
import com.dili.uid.service.BizNumberRuleService;
import org.springframework.stereotype.Service;

/**
 * 由MyBatis Generator工具自动生成
 * This file was generated on 2020-01-21 14:38:55.
 */
@Service
public class BizNumberRuleServiceImpl extends BaseServiceImpl<BizNumberRuleDomain, Long> implements BizNumberRuleService {

    public BizNumberRuleMapper getActualDao() {
        return (BizNumberRuleMapper)getDao();
    }

    @Override
    public BizNumberRuleDomain getByType(String type){
        BizNumberRuleDomain bizNumberRuleDomain = DTOUtils.newInstance(BizNumberRuleDomain.class);
        bizNumberRuleDomain.setType(type);
        return getActualDao().selectOne(bizNumberRuleDomain);
    }

    @Override
    public int updateSelective(BizNumberRuleDomain bizNumberRuleDomain) {
        int count = super.updateSelective(bizNumberRuleDomain);
        BizNumberConstant.bizNumberCache.put(bizNumberRuleDomain.getType(), bizNumberRuleDomain);
        return count;
    }

    @Override
    public int insertSelective(BizNumberRuleDomain bizNumberRuleDomain) {
        int count = super.insertSelective(bizNumberRuleDomain);
        BizNumberConstant.bizNumberCache.put(bizNumberRuleDomain.getType(), bizNumberRuleDomain);
        return count;
    }

    @Override
    public int delete(Long key) {
        String type = get(key).getType();
        int count = super.delete(key);
        BizNumberConstant.bizNumberCache.remove(type);
        return count;
    }
}