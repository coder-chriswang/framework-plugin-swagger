# framework-plugin-swagger
swagger插件，基于SpringBoot 2.0.6

## 使用步骤
- 1、下载源码，install至本地仓库
- 2、本地工程pom文件中引入：
```xml
    <dependency>
      <groupId>com.chris</groupId>
      <artifactId>framework-plugin-swagger</artifactId>
      <version>1.0.0</version>
    </dependency>
```
- 3、配置文件中增加redis config:
```properties
    swagger.chris.basePackage=com.swagger.test.demo # 自己controller的包路径，@Api @ApiOperation注解修饰类和方法
    swagger.chris.title=Demo测试
    swagger.chris.version=1.0.0
    swagger.chris.contact=Chris
```

# 联系
- Chris chris_coder@163.com
