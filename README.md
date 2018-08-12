# MybatisBuild
最近在学mybatis，使用Generator创建自定义的模版感觉不是很爽，就决定用freemarker去生成DAO和Service组件，这样，只用考虑模版如何写就可以了
目录结构
## 主要基本目录架构:

```
src/java/com/dai/build/
├── entity/    ##构建用的实例bean
├── JDBC/      ##数据库交互，获取表结构
├── main/                        ## 入口
│   │	├── DaoBuildMain.java       ## 构建运行入口
└── render               ## 模版渲染层

test/resources/template ##构建模版目录
test/resources/daoBuildConfig.properties # 配置文件
```

## 配置文件
```
#JDBC配置
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/jigsaw?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true
jdbc.username=root
jdbc.password=root
###oracle
#jdbc.driver=oracle.jdbc.OracleDriver
#jdbc.url=jdbc:oracle:thin:@localhost:1521:orcl
#jdbc.username=demo
#jdbc.password=demo

#参数配置
model.package = com.dai.jigsaw.web.model
model.template=template/modelClass.flt

mapper.package = com.dai.jigsaw.web.dao
mapper.template=template/mapperClass.flt

mapperXML.package = com.dai.jigsaw.web.dao
mapperXML.template=template/mapperXML.flt

service.package = com.dai.jigsaw.web.service
service.template=template/serviceClass.flt

serviceImpl.package = com.dai.jigsaw.web.service.impl
serviceImpl.template=template/serviceImplClass.flt
#项目包路径，可以是当前项目的相对路径：src\\main\\java
target.project=H:\\aa
#创建表名,多个表的话用逗号隔开
build.table=t_user,t_role,t_org,t_perm_func,t_role_perm,t_user_role
#是创建还是删除，创建Dao文件为create，删除已创建好的为delete
build.type=create
#若文件存在是否覆盖
build.overwrite=true
#model,mapper,mapperXML,service,serviceImpl
use.type=model,mapper,mapperXML,service,serviceImpl
```
## 使用说明：
1. 修改resources/template下的模版，修改成可适用于你项目的模版
2. 修改配置文件daoBuildConfig.properties，根据说明进行配置
3. 运行DaoBuildMain.java类，将会生成文件到相应目录
