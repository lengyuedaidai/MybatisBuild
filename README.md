# MybatisBuild
最近在学mybatis，使用Generator创建自定义的模版感觉不是很爽，就决定用freemarker去生成DAO和Service组件，这样，只用考虑模版如何写就可以了
目录结构
## 主要基本目录架构:

```
test/java/com/tpt/vehicle/test/build/
├── entity/    ##构建用的实例bean
├── JDBC/      ##数据库交互，获取表结构
├── main/                        ## 入口
│   │	├── DaoBuildMain.java       ## 构建运行入口
└── render               ## 模版渲染层

test/resources/template ##构建模版目录
test/resources/daoBuildConfig.properties # 配置文件
```

##配置文件
```
###JDBC配置
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/vehicle?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true
jdbc.username=root
jdbc.password=root
###Dao文件相关配置
model.package = com.tpt.vehicle.web.model
model.template=template/modelClass.flt

mapper.package = com.tpt.vehicle.web.dao
mapper.template=template/mapperClass.flt

mapperXML.package = com.tpt.vehicle.web.dao
mapperXML.template=template/mapperXML.flt

service.package = com.tpt.vehicle.web.service
service.template=template/serviceClass.flt

serviceImpl.package = com.tpt.vehicle.web.service.impl
serviceImpl.template=template/serviceImplClass.flt
###项目包路径
target.project=src/main/java
###创建表名
build.table=my_test
###是创建还是删除，创建Dao文件为create，删除已创建好的为delete
build.type=create
###若文件存在是否覆盖
build.overwrite=create
```
##使用说明：
1. 修改配置文件build.table属性为你要生成Dao层的对应表名
2. 运行DaoBuildMain.java类，将会生成文件到相应目录
3. 测试新表的增删改查
