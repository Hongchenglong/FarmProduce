# 农产品溯源管理系统

## 项目文档

### 接口文档

ApiPost：https://console-docs.apipost.cn/preview/3079de68fd2a0eca/2da34accb64849e1

### 设计文档

[项目需求和设计文档](./doc/项目需求和设计文档.md)

[项目系统架构和技术文档](./doc/项目系统架构和技术文档.md)

## 项目结构

```
doc 			项目文档
common 			公共模块
code-generate	代码生成器
eureka 			注册中心
gateway 		网关模块
oauth2 			权限模块
web 			前端项目
system 			后端项目
```

## 启动项目

### 前端

```shell
cd web
npm i 
npm run dev
```

### 后端

1. 新建数据库，导入`doc\sql\traceap.sql`

2. 在`system\src\main\resources\application.yml`中配置数据源：

    ```
    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/traceap?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
        username: root
        password: root
    ```
    
3. 启动eureka注册中心

4. 启动gateway网关模块

5. 启动oauth2权限模块

6. 启动system后端项目





