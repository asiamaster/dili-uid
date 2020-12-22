package com.dili.uid.sdk.rpc.feign.fallback;

import com.dili.uid.sdk.rpc.feign.UidFeignRpc;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.domain.BaseOutput;

/**
 * @description:
 * @author: WM
 * @time: 2020/10/15 10:20
 */
public class UidRpcFallBack implements UidFeignRpc {

    private Throwable throwable;

    UidRpcFallBack(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public BaseOutput<String> getSnowFlakeId53() {
        return BaseOutput.failure("服务熔断").setCode(ResultCode.FLOW_LIMIT);
    }

    @Override
    public BaseOutput<String> getSnowFlakeId64() {
        return BaseOutput.failure("服务熔断").setCode(ResultCode.FLOW_LIMIT);
    }

    @Override
    public BaseOutput<String> getBizNumber(String type) {
        return BaseOutput.failure("服务熔断").setCode(ResultCode.FLOW_LIMIT);
    }
}
