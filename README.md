# processor
java  自定义注解器开发


[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] 服务配置文件不正确, 或构造处理程序对象javax.annotation.processing.Processor: Provider com.demo.processor.CheckSetterProcessor not found时抛出异常错误
[INFO] 1 error
编译打jar包遇到上述错误解决：
先删除：javax.annotation.processing.Processor  文件里的内容：com.demo.processor.CheckSetterProcessor
再次执行编译。打包成功：
再次写入com.demo.processor.CheckSetterProcessor。
再次编译即可

如果有解决的请告知 linghongkang9@163.com
