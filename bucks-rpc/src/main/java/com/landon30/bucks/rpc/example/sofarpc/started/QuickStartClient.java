/**
 * Copyright (c) 2019 landon30
 */

package com.landon30.bucks.rpc.example.sofarpc.started;

import java.util.concurrent.TimeUnit;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.landon30.bucks.rpc.example.sofarpc.service.HelloService;

/**
 * 客户端实现
 * 
 * 1. 拿到服务端接口，一般服务端会通过jar的形式将接口提供给客户端。本例中，服务端和客户端在一个工程所有跳过 <br>
 * 2. 编程客户端代码
 *
 * @date 2019-06-03
 * @author landon30
 */
public class QuickStartClient {

    public static void main(String[] args) throws Exception {
        // 指定协议、接口、直连地址
        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()).setProtocol("bolt")
                .setDirectUrl("bolt://127.0.0.1:12200");

        // 生成代理类
        HelloService helloService = consumerConfig.refer();

        while (true) {
            System.out.println(helloService.sayHello("world"));
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
