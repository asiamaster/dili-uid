package com.dili.uid.boot;
import java.util.Date;

import com.dili.ss.dto.DTOUtils;
import com.dili.ss.uid.domain.BizNumber;
import com.dili.ss.uid.service.BizNumberService;
import com.dili.ss.util.DateUtils;
import com.dili.uid.constants.BizNumberConstant;
import com.dili.uid.domain.BizNumberRuleDomain;
import com.dili.uid.service.BizNumberRuleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
                bizNumber.setValue(getInitBizNumber(DateUtils.format(t.getDateFormat()), t.getLength()));
                bizNumber.setMemo(t.getName());
                bizNumber.setVersion(1L);
                bizNumberService.insertSelective(bizNumber);
            }
            //将业务规则加载到内存
            BizNumberConstant.bizNumberCache.put(t.getType(), t);
        });

    }

    /**
     * 获取日期加每日计数量的初始化字符串，最低位从1开始
     * @param dateStr
     * @param length 编码位数(不包含日期位数)
     * @return
     */
    private Long getInitBizNumber(String dateStr, int length) {
        return StringUtils.isBlank(dateStr) ? 1 : NumberUtils.toLong(dateStr) * Double.valueOf(Math.pow(10, length)).longValue() + 1;
    }

}
