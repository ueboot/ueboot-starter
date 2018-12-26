# 快速使用
- 前提条件，需要有Mysql数据库、Redis服务、JDK1.8
- 代码checkout后，使用IDEA导入，或者Eclipse都可以,当前项目采用maven构建
- 修改backend工程当中的src/main/resources/application.yml 数据库配置文件和reids配置文件
- 导入项目中的sql目录下的ueboot-shiro.sql文件到指定的数据库
- backend后台管理系统启动方式
    - 后端Java服务，找到com.ueboot.starter.backend.BackEndApplication 类，直接右键Run即可
    - 前端页面，使用命令窗口进入backend/backend-view目录，第一次执行需要先执行yarn install 或者npm install，之后执行 npm run start
    - 前端具体的使用方式查看README.md
    - 默认账号： root 密码：111111
- frontend启动方式与后台管理类似，但是没有默认的登录界面等

# 项目结构与约定说明

## 1. backend
- 后台管理系统
    - 用来配置给小程序项目进行相关配置

### 1.1 项目结构
```
|- backend
    |- backend-view/            UI界面项目
        |- src/                 UI界面源码目录
        |- package.json         UI界面配置文件
    |- src/                  后台项目
        |- main/                后台项目源码目录
        |- test/                后台项目单元测试目录
    |- pom.xml              项目POM文件
```

## 2. frontend
- 前端API服务
    - 提供给移动端或者C端用户的Java接口服务

### 2.1 项目结构
```
|- frontend
    |- frontend-view/            前台项目
        |- src/                 前台项目源码目录
        |- package.json         前台项目配置文件
    |- src/                  前端Java接口项目
        |- main/               前端Java接口项目源码目录
        |- test/                前端Java接口项目单元测试目录
    |- pom.xml             项目POM文件
```


## 4. common
- 公用工程，放置一些工具类使用，其余三个模块都会依赖这个模块

## 5. entity
- 依赖common模块
- 只存放数据库模型类
- 采用JPA注解方式定义
- 所有模型继承BaseEntity类，这个类会有几个固定的字段。同时添加了监听器，自动对更新时间，创建时间进行赋值，无需代码赋值
- 模型类无get,set方法，采用lombok注解方式实现。
- 类名都以Entity结尾，对应的表结构为NBO_开头，但是无ENTITY结尾。
- 默认设置关联属性的级联查询为懒加载模式，程序需要使用级联对象时，需要保证在同一个事物内查询，或者额外调用接口查询。

## 6. repository
- 依赖entity模块
- 数据库模型仓储访问层
- 采用 Spring Data JPA方式实现，只需要定义接口即可，无需编写实现类。
- 每个Entity类都有一个对应的Repository类
- 所有类名已Repository结尾