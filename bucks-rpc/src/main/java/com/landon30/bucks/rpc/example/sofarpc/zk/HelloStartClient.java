/**
 * Copyright (c) 2019 landon30
 */

package com.landon30.bucks.rpc.example.sofarpc.zk;

import java.util.concurrent.TimeUnit;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.landon30.bucks.rpc.example.sofarpc.service.HelloService;

/**
 * 客户端实现，指定zk注册中心
 *
 * @date 2019-06-03
 * @author landon30
 */
public class HelloStartClient {

    public static void main(String[] args) throws Exception {
        // 指定注册中心
        RegistryConfig registryConfig = new RegistryConfig().setProtocol("zookeeper")
                .setAddress("127.0.0.1:2181");

        // 指定协议、注册中心
        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()).setProtocol("bolt")
                .setRegistry(registryConfig);

        // 生成代理类
        HelloService helloService = consumerConfig.refer();

        while (true) {
            System.out.println(helloService.sayHello("world"));
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
