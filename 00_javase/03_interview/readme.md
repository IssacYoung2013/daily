[TOC]

#网络知识
##OSI开放式互联参考模型
+ 七层协议
    + 物理层，二进制，机器之间通信，传输比特流，网卡
    + 数据链路层，比特流转为帧逻辑传输
    + 网络层，网络地址翻译成物理地址，数据包路由器，IP协议
    + 传输层，端对端，解决了主机之间的数据传输，解决传输质量问题，同时进行流量控制，TCP/UDP
    + 会话层，自动收发包、自动寻址
    + 表示层，数据格式化，代码转换，数据加密，不同系统之间通信语法问题
    + 应用层，规定发送方和接收方使用固定长度的数据包，HTTP
+ 先自上而下，后自下而上处理数据头部    
##TCP/IP
+ OSI 实现 TCP/IP
##TCP三次握手
传输控制协议TCP
+ 面向连接的、、可靠的、基于字节流的传输层通信协议
+ 将应用层的数据流分割成报文段并发送目标节点的TCP层
+ 数据包都有序列，对方收到则发送ACK确认，未收到则重传
+ 使用校验和来校验数据在传输过程中是否有误

TCP报文头
+ source port 套接字
+ destination port
+ sequence number 4字节
+ Acknowledgement Number 期望收到下一个数据序号
+ offset 数据偏移

TCP Flags
+ URG 紧急指针标志
+ ACK 确认序号标志
+ PSH push 标志
+ RST 重置连接标志
+ SYN 同步序号，用于建立连接过程
+ FIN finish标志，用于释放连接

握手是为了建立连接
1. A与B Close 状态
2. A主动打开，B打开进入 LISTEN
3. A发送 SYN =1,seq=x，不携带数据，A进入SYN-SENT状态
4. B接收到信号并发送确认信号，SYN=1,seq=y,ack=x+1，B进入SYN-RCVD状态
5. A接收确认信号，发送确认信号 ACK=1,seq=x+1,ack=y+1，建立连接，A进入ESTAB-LISHED
6. 数据传输

为什么需要三次握手
+ 为了初始化 Sequence Number 的初始值
+ 问题起因分析
    + Server 收到 Client 的 SYN 回复 SYN-ACK的时候未收到 ACK 确认
    + Server 不断重试直至超时，Linux 默认等待63秒断开连接

首次我收到的隐患 -- SYN超时
+ 针对 SYN Flood 的防护措施
    + SYN队列满后，通过tcp_syncookies 参数回发Cookie
    
建立连接后，Client出现故障
保活机制
+ 向对方发送保活探测报文，如果未收到响应则继续发送
+ 尝试次数超过保活机制上限，则认为不可达
##TCP的四次挥手
终止连接
1. A和B都是 ESAB-LISHED 状态
2. A发送停止序号，FIN=1,seq=u，A进入FIN-WAIT-1状态，关闭A传B的数据传输
3. B收到报文，释放确认报文，ACK=1,seq=v,ack=u+1，B进入CLOSE-WAIT
4. A收到确认信号，进入半关闭状态 FIN-WAIT2
5. B继续数据传输，结束后发送结束标志，FIN=1,ACK=1,seq=w,ack=u+1，B进入LACK-ACK，关闭B传A的数据传输
6. A接收到结束报文，发送 ACK=1,seq=u+1,ack=w+1，进入TIME-WAIT状态
7. B服务端接收到结束确认报文，CLOSED
8. A等待 2MSL 时间CLOSED
    + 保证足够的时间让对方收到 ACK 包
    + 避免新旧连接混淆
    
因为全双工，发送方和接收方都需要两次挥手
##UDP
特点
+ 面向非连接，源端和终端不建立连接
+ 不维护连接状态，同时向多个客户单传输相同的消息
+ 数据包包头只有8个字节
+ 吞吐量只限于数据生成效率、传输速率和机器性能
+ 尽最大努力交付，不保证可靠交付，不需要维持复杂的链接状态表    
+ 面向报文，不对应用程序提交的报文信息进行拆分或者合并
##TCP和UDP的区别
+ 面向连接vs无连接（1对多）
+ 可靠性        
+ 有序性
+ 速度，UDP（在线直播、游戏）
+ 量级
##TCP的滑动窗口
###RTT和RTO
+ RTT 发送一个数据包到收到对应的 ACK，所话费的时间
+ RTO 重传时间间隔
###TCP使用滑动窗口做流量控制与乱序重排
+ 保证TCP的可靠性
+ 保证TCP的流控特性
###窗口数据的计算过程
+ AdvertisedWindow = MaxRcvBuffer - (LastByteRcvd-LastByteRead)
+ EffectiveWindow = AdvertisedWindow - (LastByteSent - LastByteAcked)
##HTTP
超文本传输协议
+ 支持客户/服务器模式
+ 简单快速
+ 灵活
+ 无连接
+ 无状态
###步骤
+ 客户端连接Web服务器
+ 发送HTTP请求
+ 服务器接受请求并返回HTTP响应
+ 释放连接TCP
+ 客户端浏览器解析HTML内容
###浏览器键入URL
1. DNS解析
2. TCP连接
3. 发送HTTP请求
4. 服务器处理请求并返回HTTP报文
5. 浏览器解析渲染页面
6. 连接结束
###HTTP状态码
+ 1xx: 请求已接收，继续处理
+ 2xx: 成功，已被成功接收、理解、接受
+ 3xx: 重定向，完成请求必须进行更进一步的操作
+ 4xx: 客户端错误，请求有语法错误或请求无法实现
+ 5xx: 服务端错误，服务器未能实现合法的请求
###GET请求和POST请求的区别
从三个层面解答
+ HTTP报文层面：GET将请求信息放在URL，POST放在报文体，浏览器对URL有长度限制
+ 数据库层面：GET符合幂等性和安全性，POST不符合
+ 其他层面：GET可以被缓存（CDN）、被存储，而POST不行
###Cookie和Session的区别
Cookie简介
+ 由服务器发送客户端的特殊信息，以文本的形式存放在客户端
+ 客户端再次请求的时候，会把Cookie回发
+ 服务器接收到后，会解析Cookie生成与客户端相应的值

Session简介
+ 服务端的机制，在服务器上保存的信息
+ 解析客户端请求并操作 session id，按需保存状态信息

Session实现方式
+ 使用Cookie来实现
    1. Request
    2. Response Set-Cookie:JSESSIONID=xxx
    3. Request Cookie:JSESSIONID=xxxx
    4. Response
+ 使用URL回写

###HTTP与HTTPS区别
HTTPS简介
SSL or TLS 安全版的HTTP

SSL(Security Sockets Layer)
+ 为网络通信提供安全及数据完整性的一种安全协议
+ 是操作系统对外的API，SSL3.0后更HTTPS
+ 采用身份验证和数据加密保证

加密方式
+ 对称加密：加密与解密使用同一个密钥
+ 非对称加密：加密使用的密钥与解密使用的密钥是不相同
+ 哈希算法：不可逆，MD5，转换固定长度
+ 数字签名：证明某个消息或者文件是某人发送的

HTTPS数据传输流程
+ 浏览器将支持的加密算法信息发送给服务器
+ 服务器选择一套浏览器支持的加密算法，以证书的形式回发浏览器
+ 浏览器验证证书合法性，并结合证书公钥加密信息发送给服务器
+ 服务器使用私钥机密，验证哈希，加密响应信息回发浏览器
+ 浏览器解密响应消息，并对消息进行验真，之后进行数据加密交互

HTTP和HTTPS的区别
+ HTTPS需要CA申请证书，HTTP不需要
+ HTTPS密文传输，HTTP明文传输
+ 连接方式不同，HTTPS默认443端口，HTTP使用80端口
+ HTTPS更安全，HTTP+加密+认证+完整性保护

##Socket简介
Socket是对TCP/IP协议的抽象，是操作系统对外开放的接口

###Socket通信流程
+ Server 创建 socket

#数据库
如何设计一个关系型数据库
+ 存储（文件系统）
+ 存储管理（块和页）
+ 缓存机制
+ SQL解析
+ 日志管理 binlog
+ 权限划分
+ 容灾机制
+ **索引管理**
+ **锁管理**
##索引模块
为什么要使用索引
参考字典，避免全表索引

什么样的信息能成为索引
+ 主键、唯一键、区分性字段

索引的数据结构
+ 生成索引，建立二叉查找树进行二分查找
+ B-Tree B+-Tree
###二叉查找树
容易退化成链表，考虑降低树的深度，使用B+-Tree
###B-Tree
+ 根节点至少包含两个孩子
+ 树中每个节点最多含有m个孩子（m>=2）
+ 除了根节点和叶即诶单，其他每个即诶单至少有ceil(m/2)孩子
+ 叶子节点都位于同一层
+ 限定B树关键字数量和大小
###B+-Tree
+ 非叶子节点的子树与关键字相同
+ 非叶子节点的子树指针P[i]，指向关键字值K[i]、K[i+1]的子树
+ 非叶子节点仅用来索引，数据都保存在叶子节点中
+ 所有叶子节点均有一个链指针指向下一个叶子节点
###B+Tree更适合用来做存储索引
+ B+树的磁盘读写代价耕地
+ B+树的查询效率更加稳定
+ B+树更有利于对数据库的扫描（范围查询）
###Hash索引
缺点
+ 仅仅能满足 "="、"IN"，不能使用范围查询
+ 无法避免数据的排序操作
+ 不能利用部分索引键查询
+ 不能避免表扫描
+ 遇到大量Hash值相等的情况性能并不一定就会比B-Tree索引高

###密集索引
+ 密集索引文件的每一个搜索码值都对应一个索引值
+ 稀疏索引文件只为索引码的某些值建立索引项

InnoDB
+ 若一个主键被定义，密集索引
+ 若没有主键被定义，该表第一个唯一非空索引作为密集索引
+ 若不满足上条件，innodb内部会生成一个隐藏索引
+ 非主键索引存储

MyISAM 
+ 稀疏索引

###如何定位并优化慢查询
+ 根据慢日志定位查询sql
```sql
show variables like '%quer%';
show status like '%slow_queries%';

set global slow_query_log=on;
set global long_query_time=1;

```
+ explain 分析执行计划
    + type system>const>eq_ref>ref>fulltext>ref_or_null>index>merge
    \>unique_subquery>index_subquery>`index>all`
    + extra Using filesort Using temporary
+ 修改sql 尽量走索引
##锁模块
###MyISAM与InnoDB关于锁方面的区别
+ MyISAM默认用的表级锁，不支持行级锁
+ InnoDB默认是行级锁，也支持表级锁
+ 读锁-共享锁-lock in share mode 写锁-排它锁-for update
###数据库事务四大特性
ACID
+ 原子性 Atomic
+ 一致性 Consistency
+ 隔离性 Isolation
+ 持久性 Durability
###事务的隔离级别，并发访问问题
+ 更新丢失
+ 脏读- READ-COMMITTED
#Java
##平台无关性
+ 编译时
+ 运行时

Java 源码首先会被编译成字节码，再有不同平台的 JVM进行解析，Java语言在不同的平台上
运行时不需要进行重新编译，Java虚拟机在执行字节码时，将字节码转换成具体平台上机器指令

##JVM如何加载.class文件
Java虚拟机
JVM是内存虚拟机
+ Class Loader 依据特定格式，加载class文件到内存
+ Execution Engine 对命令进行解析
+ Native Interface 融合不同的开发语言的原生库为 Java 所用
+ Runtime Data Area JVM内存模型

##什么是反射
运行状态获取类、方法等执行
##类从编译到执行的过程
+ Robot.java -> Robot.class
+ ClassLoader 将字节码 -> Class<Robot>
+ JVM 将Class<Robot>实例化对象

##ClassLoader
种类
+ BootStrapClassLoader C++ java.*
+ ExtClassLoader Java编写，扩展库 javax.* 
+ AppClassLoader Java编写，加载程序所在目录
+ 自定义ClassLoader Java编写
##自定义ClassLoader编写
+ Class<?> findClass(String name)
+ Class<?> defineClass(byte[] b,int off,int len)
##ClassLoader 双亲委派机制
1. 自底向上查看是否已经加载
2. 自顶向下尝试加载类

+ 避免多份同样字节码的加载
##类的加载方式
+ 隐式加载：new
+ 显示加载：loadClass，forName等
    + 运行时，调用任意方法和属性
    + Class.forName得到的class是已经完成初始化的
    + ClassLoader.loadClass 得到的class还没有链接

类的装载过程
+ 加载，通过loadClass 加载class文件字节码，生成Class对象
+ 链接，校验（检查class正确性、安全性），准备（为类变量分配存储空间），解析（将常量池的符号引用转为直接引用）    

##Java内存模型
地址空间的划分
+ 内核空间
+ 用户空间

JVM内存模型-JDK8

+ 线程私有：程序计数器、虚拟机栈、本地方法栈
+ 线程共享：MetaSpace、Java堆

程序计数器
+ 当前线程所执行的字节码行号指示器
+ 改变计数器的值来选取下一条需要执行的字节码指令
+ 和线程一对一
+ 对Java方法计数，如果是Native方法则计数器值是Undefined
+ 不会发生内存泄漏

Java虚拟机栈
+ Java方法执行的内存模型
+ 包含多个栈帧，栈帧持有局部变量、操作栈、动态链接、返回地址

局部变量表和操作数栈
+ 包含方法执行过程的所有变量
+ 操作数栈：入栈、出栈、复制、交换、产生消费变量

执行add(1,2)

递归引发 StackOverFlow 异常
+ 递归过深，栈帧超出虚拟机栈深度

虚拟机栈过多，会引起 OutOfMemory

本地方法栈
+ 与虚拟机栈相似，主要作用于操作系统api

元空间（MetaSpace）与永久代（PermGen）
+ 元空间使用本地内存，而永久代使用的是jvm的内存
+ 字符串常量池存在永久代中，容易出现性能问题和内存溢出
+ 类和方法的信息大小难以确定，给永久代的大小指定带来困难
+ 永久代会将GC带来不必要的复杂性
+ 方便HotSpot与其他的JVM的集成

Java堆（Heap）
+ 对象实例的分配区域
+ GC管理的主要区域

JVM 三大性能调优参数 -Xms -Xmx -Xss
+ -Xss 规定每个线程虚拟机（堆栈）的大小，256k
+ -Xms 堆的初始值
+ -Xmx 堆能达到最大值

堆和栈的区别-内存分配策略
+ 静态存储：编译时确定每个数据目标在运行时的存储空间需求
+ 栈式存储：数据区需求在编译时未知，运行时模块入口前确定
+ 堆式存储：编译时或运行时模块入口都无法确定，动态分配

堆和栈的区别-JVM内存模型
+ 联系：引用对象、数组，栈中定义变量保存堆中目标的首地址
+ 管理方式：栈自动释放，堆需要GC
+ 空间大小：栈比堆小
+ 碎片相关：栈产生的碎片远小于堆
+ 分配方式：栈支持静态和动态分配，而堆支持动态分配
+ 效率：栈的效率比堆高

元空间、堆、栈、线程独占 - 内存角度
+ 元空间：
    + Class:HelloWorld 
    + Class:System
+ Java堆：
    + Object:String("test") 
    + Object: Hello World
+ 线程独占：
    + Parameter reference: "test" to String object
    + Variable reference: "hw" to HelloWorld object
    + Local Variables: a with 1,lineNo

intern()方法-JDK6 VS JDK6+
+ JDK6 如果字符串常量池先前已创建，则返回引用，否则添加字符串对象引用到常量池，并返回该对象引用
+ JDK6+  如果字符串常量池先前已创建，则返回引用，否则，如果对象已经存在于 Java堆中，则将对象引用添加到字符串常量池
中，并返回该引用；如果堆中不存在，则在池中创建该字符串并返回引用

#Java垃圾回收机制

## 对象判断垃圾标准
+ 对象不被引用

## 引用技术算法
+ 判断对象的引用数量来决定对象是否可以被回收
+ 每个对象实例都有一个引用计数器，被引用+1，完成引用则-1
+ 任何引用计数为0可以当成垃圾回收
+ 优点：执行效率高，程序执行受影响较小
+ 缺点：无法检测出循环引用的情况，导致内存泄漏

## 可达性分析算法
+ 通过判断对象的引用链是否可达来决定对象是否可以被回收
+ 可以作为 GC Root 对象
    + 虚拟机栈中引用的对象（栈帧中的本地变量表）
    + 方法区的常量引用对象
    + 方法区中类静态属性引用的对象
    + 本地方法栈中 JNI 的引用对象
    + 活跃线程的引用对象
    
##垃圾回收算法
标记-清除算法
+ 标记：从根集合进行扫描，对存活对象进行标记
+ 清除：对堆内存从头到尾进行线性遍历，回收不可达对象内存
+ 碎片化，分配大对象找不到大的区间，需要GC

复制算法
+ 分为对象面和空闲面
+ 对象在对象面创建
+ 存活的对象被从对象面复制到空闲面
+ 将对象面所有对象内存清除
+ 年轻代
+ 解决碎片化问题
+ 顺序分配内存，简单高效
+ 适用于对象存活率较低

标记-整理算法
+ 标记：从根集合进行扫描，对存活对象进行标记
+ 清除：移动所有存活的对象，且按照内存地址次序排列，然后将末端内存地址以后的内存全部回收
+ 避免内存的不连续
+ 不用设置两块内存互换
+ 适用于对象存活率较高

##分代收集算法

+ 组合拳

GC的分类
+ Minor GC 复制算法，对象申请、存放
+ Full GC 

年轻代
+ Eden区
+ 两个Survivor区，From To
+ 1/3Young(8/10 Eden 1/10 From 1/10 To) 2/3 Old

对象如何晋升老年代
+ 经历一定MinorGC次数依然存活的对象
+ Survivor 区中存放不下对象
+ 新生成的大对象（-XX:+PretenuerSizeThreshold）

常用的调优参数
+ -XX:SurvivorRatio Eden 和 Survivor 的比值，默认8:1
+ -XX:NewRatio: 老年代和年轻代内存大小的比例
+ -XX:MaxTenuringThreshold 对象从年轻代晋升到老年代经过GC次数的最大阈值

老年代：存放生命周期较长的对象
+ 标记-整理
+ 标记-清除
+ Full GC 和 Major GC
+ Full GC 比 Minor GC慢，一般10倍以上

触发Full GC 条件
+ 老年代空间不足
+ 永久代空间不足 JDK7
+ CMS GC 出现 promotion failed concurrent mode failure
+ Minor GC 晋升到老年代的平均大小大于老年代的剩余空间
+ 调用 System.gc()
+ 使用 RMI 进行 RPC 或管理JDK应用，每小时执行1次Full GC

Stop-the-World
+ JVM 由于执行GC而停止了应用程序的执行
+ 任何一种GC算法都会发生
+ 多数GC优化通过减少Stop-the-world发生的时间来提高程序性能

Safepoint
+ 分析过程中对象引用关系不会发生变化的点
+ 产生Safepoint 方法调用；循环跳转；异常跳转

##常见的垃圾回收器
Serial收集器
+ -XX:+UseSerialGC 复制算法
+ 单线程收集，必须暂停所有工作线程
+ 简单高效，Client模式默认的年轻代收集器

ParNew收集器
+ -XX:+UseParNewGC 复制算法
+ 多线程收集，其余的行为、特点和Serial收集器一样
+ 单核执行效率不如Serial，在多核下执行才有优势

Parallel Scavenge 收集器
+ -XX:+UseParallelGC 复制算法
+ 吞吐量=运行用户代码时间 / （运行用户代码时间+垃圾回收时间）
+ 更关注系统的吞吐量
+ 在多核下才有优势，Server模式下默认的年轻代收集器

Serial Old 收集器
+ -XX:+UseSerialOldGC 标记-整理算法
+ 单线程收集，必须暂停所有工作线程
+ 简单高效，Client模式默认的老年代收集器

Parallel Old 收集器
+ -XX:+UseParallelOldGC 标记-整理算法
+ 多线程收集，吞吐量

CMS 收集器
+ -XX:+UseConcMarkSweepGC 标记-清除算法
+ 初始化标记：stop-the-world
+ 并发标记：并发追溯标记，程序不会停顿
+ 并发预清理：查找执行并发阶段从年轻代晋升到老年代的对象
+ 重新标记：暂停虚拟机，扫描CMS堆中的剩余对象
+ 并发清理：清理垃圾对象，程序不会停顿
+ 并发重置：重置CMS收集器的数据结构

Garbage First 收集器
+ -XX:UseG1GC 复制+标记-整理算法 
+ 并行和并发
+ 分代收集
+ 空间整合
+ 可预测的停顿空间
+ 将整个java堆内存划分多个大小相等的Region
+ 年轻代和老年代不再物理隔离

Epsilon GC ZGC

##面试题

对象finalize()方法与c++析构函数
+ 析构函数调用不确定
+ 将未被引用的对象放置于F-Queue队列
+ 方法执行随时可能被终止
+ 重生的机会

强引用、软引用、弱引用、虚引用
+ 强引用
    + 最普遍的引用：Object obj = new Object()
    + 抛出OutOfMemoryError终止程序也不会回收的具有强引用的对象
    + 通过将对象设置为null来弱化引用，使其被回收
+ 软引用
    + 对象在有用但非必须的状态
    + 只有内存空间不足，GC还会回收该引用的对象的内存
    + 可以用来实现高速缓存
+ 弱引用
    + 非必须的对象，比软引用更弱一些
    + GC时会被回收
    + 被回收的概率也不大，因为GC线程优先级比较低
    + 适用于引用偶尔被使用且不影响垃圾收集的对象
+ 虚引用
    + 不会决定对象的生命周期
    + 任何时候都可能被来及收集器回收
    + 跟中对象被垃圾收集器回收互动        
+ 强引用 > 软引用 > 弱引用 > 虚引用
 
类层次结构
+ Object->Reference、ReferenceQueue
+ Reference->SoftReference->WeakReference->PhantomReference

#多线程和并发
##进程和线程区别
进程和线程的由来
+ 串行，需要等待
+ 批处理，批量串行处理用户指令
+ 进程独占内存空间，保存各自运行状态，相互间不干扰而且可以互相切换，为并发处理任务提供了可能
+ 线程，共享进程内存资源，支持更细粒度的任务控制，进程内部并发

区别
+ 进程是资源分配的最小单位，线程是CPU调度的最小单位
+ 所有与进程相关的资源，都被记录在PCB
+ 进程是抢占处理机的调度单位；线程属于某个进程，共享其资源
+ 线程只由堆栈寄存器、程序计数器和TCB组成

总结
+ 线程不能看做独立应用，而进程可看作独立应用
+ 进程有独立的地址空间，相互不影响，线程只是进程的不同执行路径
+ 线程没有独立的地址空间，多进程程序比多线程程序健壮
+ 进程的切换比线程的切换开销大

关系
+ Java提供进程和线程的封装
+ 每个进程对应一个JVM实例，多个线程共享JVM里的堆
+ Java采用单线程编程模型，程序会自动创建主线程

##Thread
run 和 start 区别
+ start 方法会创建一个新的子线程并启动，JVM_StartThread->thread_entry->Thread#run
+ run()方法只是Thread的一个普通方法调用

Thread 和 Runnable 
+ Thread 实现了Runnable
+ 因类的单一继承原则，推荐使用Runnable实现线程

如何给run()方法传参
+ 构造函数
+ 成员变量
+ 回调函数

如何实现处理线程的返回值
+ 主线程等待方法，需要循环等待
+ Thread.join()
+ 通过Callable接口实现：FutureTask or 线程池

线程的状态
+ NEW 新建，创建后尚未启动
+ Runnable 运行，包含Running 和Ready
+ 无限期等待（Waiting）
    + 没有设置Timeout的Object.wait
    + 没有设置Timeout的Thread.join
+ 限期等待 Time Waiting
    + Thread.sleep    
    + 设置Timeout的Object.wait
    + 设置Timeout的Thread.join
+ 阻塞 Blocked 等待获取排它锁
+ 结束 

sleep和wait方法
+ sleep是Thread方法 wait是Object方法
+ sleep 可以在任何地方使用
+ wait 只能在 synchronized方法内使用
+ sleep只会让出CPU，不会导致锁行为的改变
+ wait不仅让出CPU，还会释放锁

notify 和notifyAll的区别
+ notifyAll 会让所有处于等待池的线程全部进入锁池去竞争锁
+ notify 会随机取等待线程获取锁
锁池
线程A拥有一个对象的锁
等待池

yield
暗示当前线程愿意让出CPU

如何中断线程
+ 调用interrupt()，通知线程应该中断
    + 如果线程处于阻塞状态，立即抛出异常
    + 如果线程处于运行状态，只是设置中断标志设置为true

##synchronized
互斥锁
+ 互斥性，同一时间只允许一个线程持有某个对象锁
+ 可减刑，必须确保在锁被释放之前，对共享变量de修改，对应随后的获取锁线程是可见的
+ 锁的不是代码，锁的都是对象

获取对象锁
+ 同步代码块 synchronized(this) synchronized(类实例对象)
+ 同步非静态方法（synchronized method）

获取类锁
+ 同步代码块 synchronized(类.class)
+ 同步静态方法 synchronized static method 锁的是当前的类对象

synchronized底层原理
+ Java对象头
    + Mark Word 存储对象的hashCode 分代年龄、锁类型、锁标志
+ monitor
    + 每个Java对象天生自带一把锁，等待池、锁池
+ 早期属于重量锁，依赖Mutex Lock实现，线程之间切换需要用户态到核心态，开销较大
+ Java6以后优化如下：
    + Adaptive Spinning
    + Lightweight Locking
    + Lock Eliminate
    + Lock Coarsening
    + Biased Locking

自旋锁与自适应锁
自旋锁
+ 通过让线程执行忙循环等待锁的释放，不让出CPU
+ 缺点：若锁被其他线程长时间占用，会带来许多性能上的开销

自适应自旋锁
+ 自旋的次数不再固定
+ 由前一次在同一个锁上的自旋时间及锁的拥有者的状态来决定

锁消除
+ JIT编译时，对运行上下文进行扫描，去除不存在竞争的锁  

锁粗化
+ 通过扩大加锁的范围  

synchronized的四种状态
+ 无锁、偏向锁、轻量锁、重量锁

偏向锁
+ 大多数情况下，锁不存在多线程竞争，总是由同一线程多次获得

轻量级锁
+ 偏向锁存在，还有线程竞争锁，膨胀为轻量级锁

锁的内存语义
+ 当线程释放锁时，Java内存模型会把该线程对应的本地内存中的共享变量刷新到主内存中
+ 当线程获取锁，Java内存模型会把线程对应的本地内存为无效，从而使得被监视器保护的临界区代码必须从主内存中读取共享变量

synchronized 和 ReentrantLock的区别
ReentrantLock 再入锁    
+ java.util.concurrent.locks
+ 和 CountDownLatch FutureTask Semaphore 一样基于 AQS 实现
+ 能够实现比 synchronized 更细粒度的控制，如控制 fariness
+ 调用lock()之后，必须调用unlock()释放锁
+ 性能未必比synchronized高，并且也是可重入的

ReentrantLock公平性的设置
+ ReentrantLock fairLock = new ReentrantLock(true)
+ 参数为true时，倾向于将锁赋予等待时间最久的线程
+ 公平锁：获取锁的顺序按先后调用lock方法的顺序
+ 非公平锁：抢占的顺序不一定，看运气
+ synchronized 是非公平锁

ReentrantLock将锁对象化
+ 判断是否有线程，或者某个特定线程，在排队等待获取锁
+ 带超时的获取锁的尝试
+ 感知有没有成功获取锁
+ java.util.concurrent.Condition

区别
+ synchronized 是关键字，ReentrantLock是类
+ ReentrantLock 可以对获取锁的等待时间进行设置，避免死锁
+ ReentrantLock 可以获取各种锁的信息
+ ReentrantLock 可以灵活地实现多路通知
+ 机制：synchronized 操作 Mark Word lock调用Unsafe类的park()方法 

什么是Java内存模型的happens-before
Java内存模型
Java内存模型（Java Memory Model JMM）描述一组规则或规范，通过这组规范定义了程序中各个变量的访问方式

JMM的主内存
+ 存储Java实例对象
+ 成员变量、类信息、常量、静态变量
+ 共享区，存在线程安全

JMM的工作内存
+ 存储当前方法的所有本地变量信息，本地变量对其他线程不可见
+ 字节码行号指示器、Native方法信息
+ 属于线程私有数据区域，不存在线程安全问题

归纳
+ 方法里的基本数据类型将直接存储在工作内存的栈帧结构
+ 引用类型的本地变量：引用存储在工作内存中，实例存储在主内存中
+ 成员变量、static变量、类信息均会被存储在主内存中
+ 主内存共享方式各拷贝一份数据到工作内存，操作完成后刷新回主内存

JMM如何解决可见性
指令重排序需要满足条件
+ 在单线程环境不能改变程序运行的结果
+ 存在数据依赖关系的不允许重排序
+ 无法通过happens-before原则推导出来的，才能进行指令的重排序

A操作的结果需要对B操作可见，则A与B存在happens-before关系
happens-before八大原则
+ 程序次序规则：一个线程内，执行有序
+ 锁定规则：一个unlock操作先行与后面对同一个锁的lock操作
+ volatile 写操作先行后续读操作
+ 传递规则
+ 线程启动规则 Thread对象start先行于线程内的每一个工作
+ 线程中断规则
+ 线程终结规则：线程所有操作先行于终结
+ 对象终结规则：一个对象初始化先行于 finalize

volatile 
JVM提供了轻量同步机制
+ 保证被修饰的共享变量对所有线程总是可见的
+ 禁止指令重排序

如何实现
+ 当写一个volatile JMM会把该线程对应的工作内存中的共享变量值刷新到主内存中
+ 当读一个volatile JMM会把该线程对应的工作内存置为无效

如何禁止重排优化
内存屏障（Memory Barrier）
+ 保证特定操作的执行顺序
+ 保证某些变量的内存可见性
+ 单例模式双重检测实现

##CAS Compare and Swap
一种高效实现线程安全性的方法
+ 支持yuan zi

