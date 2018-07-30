# java 知识点总结

> Fork From [Java-Interview](https://github.com/crossoverJie/Java-Interview)

Java 知识点，继续完善中。

> 多数是一些 Java 基础知识、底层原理、算法详解。也有上层应用设计，其中不乏一些大厂面试真题。

[TOC]

### 常用集合
- [ArrayList/Vector](./MD/ArrayList.md)
- [LinkedList](./MD/LinkedList.md)
- [HashMap](./MD/HashMap.md)
- [HashSet](./MD/collection/HashSet.md)
- [LinkedHashMap](./MD/collection/LinkedHashMap.md)

### Java 多线程
- [多线程中的常见问题](./MD/Thread-common-problem.md)
- [synchronize 关键字原理](./MD/Synchronize.md)
- [多线程的三大核心](./MD/Threadcore.md)
- [对锁的一些认知](./MD/Java-lock.md)
- [ReentrantLock 实现原理 ](./MD/ReentrantLock.md)
- [ConcurrentHashMap 的实现原理](./MD/ConcurrentHashMap.md)
- [线程池原理](./MD/ThreadPoolExecutor.md)
- [深入理解线程通信](./MD/concurrent/thread-communication.md)
- [交替打印奇偶数](./src/main/java/com/crossoverjie/actual/TwoThread.java)

### JVM
- [Java 运行时内存划分](./MD/MemoryAllocation.md)
-  [类加载机制](./MD/ClassLoad.md)
-  [OOM 分析](./MD/OOM-analysis.md)
- [垃圾回收](./MD/GarbageCollection.md)
- [对象的创建与内存分配](./MD/newObject.md)
- [你应该知道的 volatile 关键字](./MD/concurrent/volatile.md)

### 分布式相关

- [分布式限流](http://crossoverjie.top/2018/04/28/sbc/sbc7-Distributed-Limit/)
- [基于 Redis 的分布式锁](http://crossoverjie.top/2018/03/29/distributed-lock/distributed-lock-redis/)
- [分布式缓存设计](./MD/Cache-design.md)
- [分布式 ID 生成器](./MD/ID-generator.md)

### 常用框架\第三方组件

- [Spring Bean 生命周期](./MD/spring/spring-bean-lifecycle.md)
- [Spring AOP 的实现原理](./MD/SpringAOP.md) 
- [Guava 源码分析（Cache 原理）](https://crossoverjie.top/2018/06/13/guava/guava-cache/)
- SpringBoot 启动过程
- Tomcat 类加载机制


### 架构设计
- [秒杀系统设计](./MD/Spike.md)
- [秒杀架构实践](http://crossoverjie.top/2018/05/07/ssm/SSM18-seconds-kill/)

### DB 相关

- [MySQL 索引原理](./MD/MySQL-Index.md)
- [SQL 优化](./MD/SQL-optimization.md)
- [数据库水平垂直拆分](./MD/DB-split.md)

### 数据结构与算法
- [红包算法](./src/main/java/com/crossoverjie/red/RedPacket.java)
- [二叉树中序遍历](./src/main/java/com/crossoverjie/algorithm/BinaryNode.java#L76-L101)
- [是否为快乐数字](./src/main/java/com/crossoverjie/algorithm/HappyNum.java#L38-L55)
- [链表是否有环](./src/main/java/com/crossoverjie/algorithm/LinkLoop.java#L32-L59)
- [从一个数组中返回两个值相加等于目标值的下标](./src/main/java/com/crossoverjie/algorithm/TwoSum.java#L38-L59)
- [一致性 Hash 算法](./MD/Consistent-Hash.md)
- [限流算法](./MD/Limiting.md)
- [三种方式反向打印单向链表](./src/main/java/com/crossoverjie/algorithm/ReverseNode.java)
- [合并两个排好序的链表](./src/main/java/com/crossoverjie/algorithm/MergeTwoSortedLists.java)
- [两个栈实现队列](./src/main/java/com/crossoverjie/algorithm/TwoStackQueue.java)
- [动手实现一个 LRU cache](http://crossoverjie.top/2018/04/07/algorithm/LRU-cache/)
- [链表排序](./src/main/java/com/crossoverjie/algorithm/LinkedListMergeSort.java)
- [数组右移 k 次](./src/main/java/com/crossoverjie/algorithm/ArrayKShift.java)

### Netty 相关
- [SpringBoot 整合长连接心跳机制](https://crossoverjie.top/2018/05/24/netty/Netty(1)TCP-Heartbeat/)
- [从线程模型的角度看 Netty 为什么是高性能的？](https://crossoverjie.top/2018/07/04/netty/Netty(2)Thread-model/)

### 附加技能

- [TCP/IP 协议](./MD/TCP-IP.md)
- [一个学渣的阿里之路](https://crossoverjie.top/2018/06/21/personal/Interview-experience/)


### 联系作者

> crossoverJie#gmail.com

![](https://ws2.sinaimg.cn/large/006tKfTcly1fsa01u7ro1j30gs0howfq.jpg)