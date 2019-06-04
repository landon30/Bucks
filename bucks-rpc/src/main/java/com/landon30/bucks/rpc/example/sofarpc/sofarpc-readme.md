## 文档
1. [git仓库](https://github.com/sofastack/sofa-rpc)
2. [docs home](https://www.sofastack.tech/sofa-rpc/docs/Home)
    - 可切中文/english
3. [sofarpc概述](https://tech.antfin.com/docs/2/56207#)

## getting started（直连方式）
1. server 输出结果
```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Sofa-Middleware-Log:WARN No log util is usable, Default app logger will be used.
Sofa-Middleware-Log:WARN No log util is usable, Default app logger will be used.
Server receive: world
Server receive: world
...
```

2. client 输出结果
```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Sofa-Middleware-Log:WARN No log util is usable, Default app logger will be used.
Sofa-Middleware-Log:WARN No log util is usable, Default app logger will be used.
hello world ! 
hello world ! 
```

3. 关闭server后，client输出
```
Exception in thread "main" com.alipay.sofa.rpc.core.exception.SofaRouteException: RPC-02312: 当前服务[com.landon30.bucks.rpc.example.sofarpc.started.HelloService:1.0]的地址[bolt://127.0.0.1:12200,]不可用,或指定的地址不在可用的地址列表中 
	at com.alipay.sofa.rpc.client.AbstractCluster.unavailableProviderException(AbstractCluster.java:436)
	at com.alipay.sofa.rpc.client.AbstractCluster.select(AbstractCluster.java:397)
	at com.alipay.sofa.rpc.client.FailoverCluster.doInvoke(FailoverCluster.java:64)
	at com.alipay.sofa.rpc.client.AbstractCluster.invoke(AbstractCluster.java:288)
	at com.alipay.sofa.rpc.client.ClientProxyInvoker.invoke(ClientProxyInvoker.java:83)
	at com.landon30.bucks.rpc.example.sofarpc.started.HelloService_proxy_0.sayHello(HelloService_proxy_0.java)
	at com.landon30.bucks.rpc.example.sofarpc.started.QuickStartClient.main(QuickStartClient.java:32)
```

4. 直接启动client，未启动server，报错同上

### 使用zk
1. 启动zk，默认单机，监听2181端口
2. 分别启动两个服务HelloStartServer1和HelloStartServer2向zk监听
3. 使用zkCli查询，可以看到有2个providers

```
[zk: localhost:2181(CONNECTED) 3] ls /sofa-rpc/com.landon30.bucks.rpc.example.so
farpc.service.HelloService/providers
[bolt%3A%2F%2F10.2.144.12%3A13200%3Fversion%3D1.0%26accepts%3D100000%26weight%3D
100%26language%3Djava%26pid%3D15696%26interface%3Dcom.landon30.bucks.rpc.example
.sofarpc.service.HelloService%26timeout%3D0%26serialization%3Dhessian2%26protoco
l%3Dbolt%26delay%3D-1%26dynamic%3Dtrue%26startTime%3D1559620349534%26id%3Drpc-cf
g-0%26uniqueId%3D%26rpcVer%3D50504, bolt%3A%2F%2F10.2.144.12%3A13201%3Fversion%3
D1.0%26accepts%3D100000%26weight%3D100%26language%3Djava%26pid%3D32160%26interfa
ce%3Dcom.landon30.bucks.rpc.example.sofarpc.service.HelloService%26timeout%3D0%2
6serialization%3Dhessian2%26protocol%3Dbolt%26delay%3D-1%26dynamic%3Dtrue%26star
tTime%3D1559620360881%26id%3Drpc-cfg-0%26uniqueId%3D%26rpcVer%3D50504]
```
4. 启动client，指定zk，发现两个服务均有请求和输出，关闭其中一个不影响client

### 注意的地方
1. 使用zk的例子pom.xml指定的curator-recipes版本要是2.9.1，具体详细参考[issues](https://github.com/sofastack/sofa-rpc/issues/331)