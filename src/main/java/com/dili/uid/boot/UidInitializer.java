package com.dili.uid.boot;

import com.dili.ss.dto.DTOUtils;
import com.dili.ss.uid.domain.BizNumber;
import com.dili.ss.uid.service.BizNumberService;
import com.dili.ss.uid.util.BizNumberUtils;
import com.dili.ss.util.DateUtils;
import com.dili.uid.constants.BizNumberConstant;
import com.dili.uid.domain.BizNumberRuleDomain;
import com.dili.uid.service.BizNumberRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 业务规则初始化器
 */
@Component
public class UidInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private BizNumberRuleService bizNumberRuleService;
    @Autowired
    private BizNumberService bizNumberService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<BizNumberRuleDomain> bizNumberRuleDomains = bizNumberRuleService.list(null);
        bizNumberRuleDomains.stream().forEach(t -> {
            BizNumber condition = DTOUtils.newInstance(BizNumber.class);
            condition.setType(t.getType());
            BizNumber bizNumber = bizNumberService.selectOne(condition);
            //初始化biz_number表数据
            if(bizNumber == null){
                bizNumber = DTOUtils.newInstance(BizNumber.class);
                bizNumber.setType(t.getType());
                String dateformat = t.getDateFormat() == null ? null : DateUtils.format(t.getDateFormat());
                bizNumber.setValue(BizNumberUtils.getInitBizNumber(dateformat, t.getLength()));
                bizNumber.setMemo(t.getName());
                bizNumber.setVersion(1L);
                bizNumberService.insertSelective(bizNumber);
            }
            //将业务规则加载到内存
            BizNumberConstant.bizNumberCache.put(t.getType(), t);
        });

    }



}
