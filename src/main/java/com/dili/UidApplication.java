package com.dili;

import com.dili.ss.dto.DTOScan;
import com.dili.ss.retrofitful.annotation.RestfulScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.dili.ss", "com.dili.uid", "com.dili.uap.sdk"})
@RestfulScan({"com.dili.uid.rpc", "com.dili.uap.sdk.rpc"})
@MapperScan(basePackages = {"com.dili.uid.mapper", "com.dili.ss.uid.dao", "com.dili.ss.dao", "com.dili.ss.quartz.dao"})
@DTOScan(value={"com.dili.ss", "com.dili.uid"})
@EnableDiscoveryClient
@EnableFeignClients
public class UidApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UidApplication.class, args);
	}

	@Override
	public void run(String... args) {
//		maxMemory=Eden+Survivor+Old Gen
//		maxMemory是拿到的程序最大可以使用的内存，
//		我们知道 ，Survivor有两个，但只有1个会用到，另一个一直闲置。
//		所以这个值maxMemory是去掉一个Survivor空间的值。
		long maxMemory = Runtime.getRuntime().maxMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("maxMemory:"+maxMemory+",totalMemory:"+totalMemory+",freeMemory:"+freeMemory);
		System.out.println("项目启动完成!");
	}
}
