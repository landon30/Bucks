/**
 * Copyright (c) 2019 landon30
 */

package com.landon30.bucks.rpc.example.sofarpc.service;

/**
 * Hello服务接口实现
 *
 * @date 2019-06-03
 * @author landon30
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String string) {
        System.out.println("Server receive: " + string);
        return "hello " + string + " ! ";
    }
}
