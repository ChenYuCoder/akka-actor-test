# akkaActorTst 
> 该项目使用SpringBoot2.1.7+akka-actor框架组成，主要提供定时器执行任务，http触发任务，记录每次任务执行记录，
每个任务执行索引；每个任务要保证在执行过程中不可以抛出异常到外部，发生异常的时候，可以发送任务异常消息


* Application 
> 启动类排除mongo默认配置

* SpringContextUtil
> SpringContext工具类，用于没有注入到spring内的bean引用spring的bean

* analytics
> 提供原始数据读取

* config
  * AbstractMongoConfigure：抽象mongo配置
  * AnalyticsMongoConfig：原数据mongo配置
  * TaskMongoConfig：task产出mongo配置

* task
  * AbstractTask：抽象task任务，在构造函数中初始化taskID，在task.preStart中初始化开始时间，在doTask中记录计算条件calculate，
  原数据数量dataSize，结果数据数量resultSize，在task.postStop中记录任务结束时间end，并且会发送TaskFinish消息，
  根据判断是否是根任务并且成功发送根任务索引消息。所有任务必须各自处理自己的异常，并且发送异常消息
  * Calculate：计算条件
  * ReCalculate：重新计算条件
  * TaskController：任务接口
  * TaskFactory：任务工厂，提供任务触发功能，如果某任务完成过，并且非重算触发则直接返回上次执行taskID
  * TaskScheduled：任务定时器，根据当前时间-任务索引时间抓取航班，进行派发任务
  * finish：任务完成监听器，记录任务完成消息和任务异常消息
  * flow：流处理任务
  * index：根任务执行索引
  * root：某个批处理的根任务，内部包含其他子任务，每个任务包含：抓取数据，统计，存储





