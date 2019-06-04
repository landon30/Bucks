/**
 * Copyright (c) 2019 landon30
 */

package com.landon30.bucks.rpc.example.sofarpc.zk;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.landon30.bucks.rpc.example.sofarpc.service.HelloService;
import com.landon30.bucks.rpc.example.sofarpc.service.HelloServiceImpl;

/**
 * 服务端Hello2，注册zk
 *
 * @date 2019-06-03
 * @author landon30
 */
public class HelloStartServer2 {

    public static void main(String[] args) {
        // 指定注册中心
        RegistryConfig registryConfig = new RegistryConfig().setProtocol("zookeeper")
                .setAddress("127.0.0.1:2181");

        // 协议、端口、非守护线程
        ServerConfig serverConfig = new ServerConfig().setProtocol("bolt").setPort(13201)
                .setDaemon(false);

        // 指定接口、实现、服务端 + 注册中心
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()).setRef(new HelloServiceImpl())
                .setRegistry(registryConfig)
                .setServer(serverConfig);

        // 发布服务
        providerConfig.export();
    }
}
