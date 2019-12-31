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
 