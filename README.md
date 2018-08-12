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

src/resources/template ##构建模版目录
src/resources/daoBuildConfig.properties # 配置文件
```
## 使用说明：
1. 修改resources/template下的模版，修改成可适用于你项目的模版
2. 修改配置文件daoBuildConfig.properties，根据说明进行配置
3. 运行DaoBuildMain.java类，将会生成文件到相应目录

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
#如果不要某类可以不写 model,mapper,mapperXML,service,serviceImpl
use.type=model,mapper,mapperXML,service,serviceImpl
```
## 模版参数说明：
### 模版参数：
|参数 | 类| 说明|
|---|------|---|
|table | TableInfo| 表信息 |
|modelClassFile | FileInfo | model创建信息|
|mapperClassFile | FileInfo | mapper创建信息|
|mapperXMLFile | FileInfo | mapperXML创建信息|
|serviceClassFile | FileInfo | service创建信息|
|serviceImplClassFile | FileInfo | serviceImpl创建信息|


### 类介绍
1. TableInfo类

|参数 | 类| 说明|
|---|------|---|
|tableName | String| 表名|
|multipleTableNames | NameModel| 各类名字格式|
|columns | List<ColumnInfo>| 列信息|
|primaryKeys | Set<String>| 主键信息|
|remark | String| 表备注 |

2. ColumnInfo类

|参数 | 类| 说明|
|---|------|---|
|columnName | String| 列名 |
|multipleColumnNames | NameModel| 各类名字格式|
|columnType | String| 字段类型,如：VARCHAR|
|multipleColumnType | TypeModel| 字段各种类型 |
|nullable | Boolean| 是否允许为空 |
|isKey | Boolean| 是否为主键 |
|remark | String| 备注 |

3. NameModel类

|参数 | 类| 说明|
|---|------|---|
|dbName | String| 数据库字段名称或表名，如：T_USER|
|modelName | String| 去除下划线，并将下划线后第一个字母大写的java变量名，如：tUser|
|modelNameFirstUp | String| 第一个字母大写的java变量名，如：T_user|
|dbNameUpper | String| 原始名称的全大写，如：T_USER|
|dbNameLower | String| 原始名称的全小写 如：t_user|
|modelNameUpper | String| java变量名全大写 如：TUSER|
|modelNameLower | String| java变量名全小写 如：tuser|

4. TypeModel类

|参数 | 类| 说明|
|---|------|---|
|dbType | String| 数据库类型，如：VARCHAR|
|modelType | String| 对应java类型，如：java.lang.String|
|modelSimpleType | String| java类简短的名称，如：String|

5. FileInfo类

|参数 | 类| 说明|
|---|------|---|
|name | String| 文件名，如：TUser|
|packageName | String| 包名，如：com.dai.jigsaw.web.model|
|path | String| 所在位置，如：H:\\aa\\com\\dai\\jigsaw\\web\\model|
|fileName | String| 文件全名，如：TUser.java|
|templatePath | String| 模版地址 如：template/modelClass.flt|
