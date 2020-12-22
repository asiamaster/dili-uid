package com.dili.bpmc.sdk.rpc.restful;

import com.dili.ss.domain.BaseOutput;
import com.dili.ss.retrofitful.annotation.GET;
import com.dili.ss.retrofitful.annotation.ReqParam;
import com.dili.ss.retrofitful.annotation.Restful;

@Restful("${uid.contextPath}")
public interface UidRestfulRpc {

	/**
	 * 获取53位雪花算法id
	 * @return
	 */
	@GET(value = "/api/bizNumber/sid53")
	BaseOutput<String> getSnowFlakeId53();

	/**
	 * 获取64位雪花算法id
	 * @return
	 */
	@GET(value = "/api/bizNumber/sid64")
	BaseOutput<String> getSnowFlakeId64();

	/**
	 * 获取业务编号
	 * http://uid.diligrp.com:8282/api/bizNumber?type=hzsc_water
	 * @return
	 */
	@GET(value = "/api/bizNumber")
	BaseOutput<String> getBizNumber(@ReqParam(value = "type") String type);

}
