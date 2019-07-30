## cms测试

## 基础参数
 - 指定-Xmx2g -Xms2g
 - 默认的新生代大小S0C + S1C + EC = 34048.0 + 34048.0 + 272640.0 = 332.75M
 - 而默认的NewRatio是2，表示新生代和老年代比例是1:2，即占堆的1/3 = 682.66M，所以不对
 - 手动增加了参数XX:NewRatio=2后，新生代比率正确
 - 另外注意[JDK-6862534 : -XX:NewRatio completely ignored when combined with -XX:+UseConcMarkSweepGC](https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6862534),不过新版本已修复
 - 所以建议手动指定新生代大小参数(-XX:NewRatio或者-Xmn或者-XX:NewSize/-XX:MaxNewSize)
 - The flag -XmnNNN is equivalent to -XX:NewSize=NNN and -XX:MaxNewSize=NNN
 - There is also -Xmn, but that is just a short form for NewSize==MaxNewSize

```
-XX:+UseConcMarkSweepGC
-XX:CMSInitiatingOccupancyFraction=75
-XX:+UseCMSInitiatingOccupancyOnly
```