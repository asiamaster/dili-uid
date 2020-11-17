package com.dili.uid.api;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.sid.dto.SnowflakeId;
import com.dili.ss.sid.service.SnowFlakeIdService;
import com.dili.ss.sid.util.IdUtils;
import com.dili.ss.uid.component.BizNumberFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 业务号Api
 */
@RestController
@RequestMapping("/api/bizNumber")
public class BizNumberApi {

    /**
     * 用于处理不同环境的编号重复问题
     */
    @Value("${spring.profiles.active:prod}")
    private String profile;

    @Autowired
    private BizNumberFunction bizNumberFunction;

    @Autowired
    private SnowFlakeIdService snowFlakeIdService;

    /**
     * 获取租赁订单号
     * @return
     */
    @SentinelResource(value = "dili-uid.bizCode", entryType= EntryType.IN)
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST})
    public BaseOutput<String> bizCode(@RequestParam("type") String type) {
        try {
            return BaseOutput.success().setData(bizNumberFunction.getBizNumberByType(type));
        } catch (Exception e) {
            return BaseOutput.failure(e.getMessage());
        }
    }

    /**
     * 64位雪花算法
     * @return
     */
    @GetMapping("sid64")
    @SentinelResource(value = "dili-uid.sid64", entryType= EntryType.IN)
    public BaseOutput<String> sid64() {
        return BaseOutput.success().setData(snowFlakeIdService.nextId());
    }

    /**
     * 53位雪花算法
     * @return
     */
    @GetMapping("sid53")
    @SentinelResource(value = "dili-uid.sid53", entryType= EntryType.IN)
    public BaseOutput<String> sid53() {
        return BaseOutput.success().setData(IdUtils.nextId());
    }

    /**
     * sid64解析为SnowflakeId
     * @param id
     * @return
     */
    @GetMapping("expId64")
    public BaseOutput<SnowflakeId> expId64(Long id) {
        return BaseOutput.success().setData(snowFlakeIdService.expId(id));
    }

    /**
     * sid53解析为SnowflakeId
     * @param id
     * @return
     */
    @GetMapping("expId53")
    public BaseOutput<SnowflakeId> expId53(Long id) {
        return BaseOutput.success().setData(IdUtils.expId(id));
    }

    /**
     * sid64的时间(timeStamp)解析为Date
     * @param time
     * @return
     */
    @GetMapping("transTime64")
    public BaseOutput<Date> transTime64(Long time) {
        return BaseOutput.success().setData(snowFlakeIdService.transTime(time));
    }

    /**
     * sid53的时间(timeStamp)解析为Date
     * @param time
     * @return
     */
    @GetMapping("transTime53")
    public BaseOutput<Date> transTime53(Long time) {
        return BaseOutput.success().setData(IdUtils.transTime(time));
    }

}