## gc日志参数

### 测试参数1
 - gc_pid13656.log 用%p生成的文件命名
 - 如果不加任何如%p或者%t，每次jvm重启后，则gc日志被重新覆盖
 
```
-Xloggc:gc_%p.log -verbose:gc -Xmx1M -Xms1M
```
 
  - jvm参数
  - -XX:+PrintGC
  - -XX:+PrintGCTimeStamps
  - -XX:+UseParallelGC
```
CommandLine flags: -XX:InitialHeapSize=1048576 -XX:MaxHeapSize=1048576 -XX:+PrintGC -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
```

### 测试参数2
  - gc_2019-07-23_18-08-57.log 用%t生成的文件命名
```
-Xloggc:gc_%t.log -verbose:gc -Xmx1M -Xms1M
```

### 测试参数3
  - 从测试包括cmdline flags可以看到PrintGCDetails默认包括了-verbose:gc（-XX:+PrintGC -XX:+PrintGCTimeStamps）

```
-Xloggc:gc_%t.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1M -Xms1M
```

```
CommandLine flags: -XX:InitialHeapSize=1048576 -XX:MaxHeapSize=1048576 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
```

### 总结1 - 常规gc日志3参数
```
-Xloggc:gc_%t.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps
```

### 测试参数4

```
``