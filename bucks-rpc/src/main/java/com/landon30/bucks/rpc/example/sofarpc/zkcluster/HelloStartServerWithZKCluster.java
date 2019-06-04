/**
 * Copyright (c) 2019 landon30
 */

package com.landon30.bucks.rpc.example.sofarpc.zkcluster;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.landon30.bucks.rpc.example.sofarpc.service.HelloService;
import com.landon30.bucks.rpc.example.sofarpc.service.HelloServiceImpl;

/**
 * 服务端Hello，注册zk集群
 *
 * @date 2019-06-04
 * @author landon30
 */
public class HelloStartServerWithZKCluster {

    public static void main(String[] args) {
        // 指定注册中心-集群
        RegistryConfig registryConfig = new RegistryConfig().setProtocol("zookeeper")
                .setAddress("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");

        // 协议、端口、非守护线程
        ServerConfig serverConfig = new ServerConfig().setProtocol("bolt").setPort(13201)
                .setDaemon(false);

        // 指定接口、实现、服务端 + 注册中心-集群
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()).setRef(new HelloServiceImpl())
                .setRegistry(registryConfig)
                .setServer(serverConfig);

        // 发布服务
        providerConfig.export();
    }
}
