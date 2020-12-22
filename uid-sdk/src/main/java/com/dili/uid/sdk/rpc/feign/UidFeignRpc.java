package com.dili.uid.sdk.rpc.feign;

import com.dili.uid.sdk.rpc.feign.fallback.UidRpcFallBackFactory;
import com.dili.ss.domain.BaseOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "dili-uid", contextId = "UidFeignRpc", url="${UidFeignRpc.url:}", fallbackFactory = UidRpcFallBackFactory.class)
public interface UidFeignRpc {

	/**
	 * 获取53位雪花算法id
	 * @return
	 */
	@GetMapping(value = "/api/bizNumber/sid53")
	BaseOutput<String> getSnowFlakeId53();

	/**
	 * 获取64位雪花算法id
	 * @return
	 */
	@GetMapping(value = "/api/bizNumber/sid64")
	BaseOutput<String> getSnowFlakeId64();

	/**
	 * 获取业务编号
	 * http://uid.diligrp.com:8282/api/bizNumber?type=hzsc_water
	 * @return
	 */
	@GetMapping(value = "/api/bizNumber")
	BaseOutput<String> getBizNumber(@RequestParam String type);

}
