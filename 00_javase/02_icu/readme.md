[TOC]

# 高效编程

# 函数编程

## Lambda 表达式简介

+ Java8 引入函数式编程风格
+ 可以理解为一种匿名函数的代替
+ 通过行为参数化传递代码

## Lambda 表达式的形式

+ (parameters) -> expression
+ (parameters)-> {statement;}

## 函数式接口

+ 接口中只有一个抽象方法
+ Java8 的函数式接口注解： @FunctionInterface

+ 函数式接口的抽象方法签名：函数描述符

## 常用函数式接口使用

| 接口              | 参数  | 返回类型 | 描述                                                     |
| ----------------- | ----- | -------- | -------------------------------------------------------- |
| Predicate         | T     | boolean  | 用于判断一个对象                                         |
| Consumer          | T     | void     | 用于接收一个对象进行处理但没有返回，比如打印一个人的名字 |
| Function<T,R>     | T     | R        | 转换一个对象为不同类型的对象                             |
| Supplier<T>       | None  | R        | 提供一个对象                                             |
| UnaryOperator<T>  | T     | T        | 接收对象并返回同类型对象                                 |
| BinaryOperator<T> | (T,T) | T        | 接收两个同类型对象，并返回一个原类型对象                 |

## 方法引用

调用特定方法的Lambda表达式的一种快捷写法，可以让你重复使用现有方法定义，并像Lambda表达式一样传递他们。

Sku::getSkuPrice 目标引用、双冒号分隔符、方法名

+ 指向静态方法的方法引用 ClassName::staticMethod
+ 指向任意类型实例方法的方法引用 ClassName::instanceMethod
+ 指向现有对象的实例方法的方法引用 object::instanceMethod

# 流编程

## 流是什么

+ JDK1.8引入的新成员，以声明式处理集合数据
+ 将基础操作链接起来，完成复杂的处理数据
+ 提供并行的处理

## 流的简介

从支持`数据处理操作`的`源`生存的`元素序列`

## 流与集合的区别

+ 时间与空间
  + 集合相当于 DVD 光碟，只有放在 DVD 机才能播放，面向存储，质数集合不存在
  + 流相当于流媒体播放，只需要获取当前播放帧，面向计算，质数流存在

+ 只能遍历一次
+ 外部迭代与内部迭代

## 流的组成

+ 数据源
+ 中间操作
+ 总端操作 collect

## 流操作分类

+ 中间操作 Intermediate
  + 无状态操作 filter/map/peek等，单个数据
  + 有状态操作 distinct/sorted/limit等，建立在所有数据

+ 终端操作 Terminal

  + 非短路操作 forEach/collect/count等

  + 短路操作 anyMatch/findFirst/findAny等

## 流的使用

+ 中间操作（无状态）
  + 过滤（filter）
  + 映射（map）
  + 扁平化（flatMap）
  + 遍历（peek）

+ 中间操作（有状态）
  + 去重（distinct）
  + 跳过（skip）
  + 截断（limit）
  + 排序（sorted）

+ 终端操作（短路）
  + 所有匹配（allMatch）
  + 任意匹配（anyMatch）
  + 不匹配（noneMatch）
  + 查找首个（findFirst）
  + 查找任意（findAny）
+ 终端操作（非短路）
  + 遍历（forEach）
  + 归约（reduce）
  + 最大值（max）
  + 最小值（min）
  + 聚合（collect）
  + 计数（count）

## 流的构建

+ 由值创建流
+ 由数组创建流
+ 由文件生成流
+ 由函数生成流（无限流）

## 收集器简介

+ 将流中的元素累积成一个结果
+ 作用于终端操作 collect() 上
+ collect / Collector / Collectors

## 预定义收集器功能

+ 将流元素归约或汇总一个值
+ 将流元素分组
+ 将流元素分区

# 资源关闭

## 垃圾回收（GC）特点

+ 垃圾回收机制只负责回收堆内存的资源，不会回收任何物理资源
+ 程序无法`精确控制`垃圾回收动作的具体发生时间
+ 在垃圾回收之前，总会调用它的 `finalize方法`

## 常见需手动释放的物理资源

+ 文件/流资源
+ 套接字资源
+ 数据库连接资源

## 物理资源可以不手动释放吗？

+ 资源被长时间无效占用
+ 超过最大限制后，将无资源可用
+ 导致系统无法正常运行

## try-with-resource 简介

+ Java7引入新特性
+ 优雅关闭资源
+ 一种Java语法糖

## try-with-resource 使用

+ 多资源自动关闭
+ 实现 AutoCloseable 接口
+ 避免异常屏蔽

## 关闭特殊情况

+ 资源对象被return的情况下，由调用方关闭

+ ByteArrayInputStream等不需要检查关闭的资源对象
+ 使用 Socket 获取的 InputStream 和 OutputStream 不需要关闭

# 工具集

## Guava 工具集简介

包含了若干被  Google 的 Java 项目广泛依赖的核心库，例如：集合、缓存、原生类型支持、并发哭、通用注解、字符串处理、I/O等

## 使用和避免 null

大多数情况下，使用null表示是某种缺失情况

Guava引入 Optional<T>,Java8将Optional作为新特性引入新类库。

## 不可变集合

创建对象的`不可变拷贝`是一项防御性编程

## 不可变对象优点

+ 当对象被不可信的库调用时，不可变形式是安全的
+ 不可变对象被多个线程调用时，不存在竞态条件问题
+ 不可变集合不需要考虑变化，因此可以节省时间和空间
+ 不可变对象因为有固定不变，所以可以作为常量来安全使用

## JDK提供unmodifiableXXX方法

+ 笨重且累赘
+ 不安全
+ 低效

## 不可变集合的三种创建方式

+ copyOf
+ of
+ builder工具

## 新集合类型

Guava引入了很多JDK没有的，但明显有用的新集合类型

## Multiset

Set 和 List 的结合

+ 没有元素顺序限制的 ArrayList<E>
  + add(E) 添加单个给定元素
  + iterator() 返回一个迭代器，包括重复元素
  + size() 返回所有元素的总个数

+ Map<E,Integer>，键为元素，值为计数
  + count(Object) 返回给定元素的个数
  + entrySet() 返回 Set<Multiset.Entry<E>> 和 Map 的 entrySet类似
  + elementSet() 返回所有不重复元素的 Set<E> 和 Map的keySet类似

## Multiset 与 Map 的区别

+ 元素计数只能是正数
+ multiset的size 返回集合大小
+ multiset 的iterator 返回重复元素
+ multiset 支持直接设置元素的计数

## 多种 Multiset 的实现

+ HashMultiset
+ TreeMultiset
+ LinkedHashMultiset

+ ConcurrentHashMultiset
+ ImmutableSMultiset

## 集合工具类
Guava提供了很多工具方法
## 对字节流/字符流提供的工具方法
+ ByteStreams 提供对 InputStream/OutputStream
+ CharStreams Reader/Writer
## 对源 Source 与 汇 Sink 抽象
+ 源是可读的：ByteSource / CharSource
+ 汇是可写的：ByteSink / CharSink

#线程池
##线程池简介
+ 什么是线程池

线程池顾名思义就是事先创建若干个可执行的线程放入一个池（容器）中，需要的时候从池
中获取不用自行创建，使用完毕不需要销毁而是放回池中，从而减少创建和销毁线程对象的开销

## 线程池带来的好处
+ 降低资源消耗
+ 提高响应速度
+ 提高线程的可管理性
## 简单线程池设计

池子，开启/初始化/关闭，提供外部获取线程、归还线程
+ 创建多少？
+ 线程没有了，怎么处理？

池子，使用者提交任务，异步结果返回，任务队列，执行器从任务队列中拿任务，从池子中拿线程执行，执行任务
后返回结果，归还线程
+ 创建多少？
+ 队列数组多长？
+ 任务队列满了，怎么办？

## 线程池的核心参数
+ corePoolSize 核心线程数量
+ maximumPoolSize 最大线程数量
+ keepAliveTime 线程空闲后的存活时间
+ unit 时间单位
+ workQueue 用于存放任务的阻塞队列
+ threadFactory 线程工厂类
+ handler 当队列和最大线程池都满了之后的饱和策略
## 线程池的处理流程
+ 提交任务
+ 检测核心线程池是否已满
+ 没有满，创建新线程执行任务
+ 满，阻塞队列是否已满
+ 没有满，任务存储在阻塞队列中
+ 满，线程池是否已满
+ 没有满，创建新线程执行任务
+ 满，按照饱和策略处理
## 线程池可选择的阻塞队列
+ 无界队列
+ 有届队列
+ 同步移交队列
## 线程池可选择的饱和策略
+ AbortPolicy 的终止策略
+ DiscardPolicy抛弃策略
+ DiscardOldestPolicy抛弃旧任务策略
+ CallerRunsPolicy调用者运行策略
##线程池执行意图
##常用线程池
+ cachedThreadPool
+ fixedThreadPool
+ singleThreadPool
#实用工具
##Lombok简介
java库，提供了常用注解，会在编译期自动生成代码
##Lombok实现原理
注解的两种解析方式
+ 运行时解析
+ 编译时解析

编译时解析的两种机制
+ Annotation Processing Tool 注解处理器 JDK1.8移除
+ Pluginable Annotation Processing API 插入式注解处理器
+ javac 生成抽象语法树-》插入式注解处理器处理-》判断语法树是否变动-》是，重新生成语法树-》无，生成字节码
##Lombok常用注解
+ @Slf4j 添加日志支持
+ @SneakyThrows 自动生成 try/catch 
+ @Synchronized 自动生成同步锁
+ @Builder 自动生成构造者模式
+ @Cleanup 自动调用变量的close方法释放资源
+ @NonNull 自动生成控制校验
+ 构造方法 @Getter @Setter @Data @AllArgsConstructor
##Lombok优点
+ 通过注解自动生成样板代码，提高开发效率
+ 代码简洁，只关注属性
+ 修改属性，不用修改方法
##Lombok缺点
+ 降低源代码的可读性和完整性
+ 加大了排查问题的难度
+ 需要ide插件支持
#验证框架
##分层验证与JavaBean验证
+ 分层验证模型
+ JavaBean 验证模型
## Bean Validation 简介
Bean Validation 验证API
##JCP,JSR简介
JCP Java Community Process 定义Java特性
JCP使用 JSR
+ JSR303 BeanValidation 1.0 验证邮箱
+ JSR349 BeanValidation 1.1 el表达式
+ JSR380 BeanValidation 2.0 容器类型及联验证
## Bean Validation 与 Hibernate Validator
+ Bean Validation 1.0 实现 Hibernate Validator 4.3.1.Final
+ Bean Validation 1.1 实现 Hibernate Validator 5.1.1.Final
+ Bean Validation 2,0 实现 Hibernate Validator 6.0.1.Final
## Hibernate Validator 与 Spring Validation
Spring Validation 是二次封装
##常用约束注解
+ 空值校验：@Null @NotNull @NotEmpty(去除左右空字符) @NotBlank
+ 范围校验类：@Min @Size @Digits @Future @Negative
+ 其他校验类：@Email @Url @Pattern 