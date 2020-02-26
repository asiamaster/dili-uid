//package com.dili.uid.rpc;
//
//import com.dili.ss.domain.BaseOutput;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * @author wm
// * @date 2020/2/25
// */
//@FeignClient(name = "dili-uid")
//public interface UidRpc {
//
//    /**
//     * 根据业务类型获取业务号
//     * @param type
//     * @return
//     */
//    @RequestMapping(value = "/api/bizNumber", method = RequestMethod.POST)
//    BaseOutput<String> bizCode(@RequestParam(value = "type") String type);
//
//    /**
//     * 获取租赁单业务号
//     * @return
//     */
//    @RequestMapping(value = "/api/bizNumber/leaseOrderCode", method = RequestMethod.POST)
//    BaseOutput<String> leaseOrderCode();
//}
