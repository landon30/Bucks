/**
 * Copyright (c) 2019 landon30
 */

package com.landon30.bucks.rpc.example.sofarpc.started;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.landon30.bucks.rpc.example.sofarpc.service.HelloService;
import com.landon30.bucks.rpc.example.sofarpc.service.HelloServiceImpl;

/**
 * 服务端
 *
 * @date 2019-06-03
 * @author landon30
 */
public class QuickStartServer {

    public static void main(String[] args) {
        // 协议、端口、非守护线程
        ServerConfig serverConfig = new ServerConfig().setProtocol("bolt").setPort(12200)
                .setDaemon(false);

        // 指定接口、实现、服务端
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()).setRef(new HelloServiceImpl())
                .setServer(serverConfig);

        // 发布服务
        providerConfig.export();
    }
}
