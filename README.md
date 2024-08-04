# LF 权限管理系统

## 项目介绍

`lf-admin` 是一个基于前后端分离架构的权限管理系统。它提供了一个高效、安全的用户权限管理平台。该系统包括前端用户界面和后端服务，通过 RESTful API 进行数据交互，支持灵活的权限配置和管理功能。

## 主要特性

### 用户管理

- 支持用户的添加、修改、删除和查询。
- 用户信息包括基本资料、所属部门、岗位等。
- 可以对用户进行角色分配来管理用户权限。

### 角色管理

- 定义和管理不同角色的权限。
- 角色与用户的关联管理。
- 支持自定义权限配置，精确到系统的每一个功能控制。

### 菜单管理

- 动态配置菜单项，可扩展菜单功能。
- 支持菜单的增删改查操作。
- 菜单权限控制，确保不同角色能访问不同的菜单项。

### 界面与用户体验

- 现代化的用户界面设计，使用 Vue.js 和 Element UI 实现。
- 提供面包屑导航和标签页导航，增强用户操作的便捷性。
- 集成搜索功能，帮助用户快速找到所需的信息。

### 安全性

- 基于 Spring Security 的身份验证和授权机制。
- 支持 token 认证，确保接口安全。
- 集成验证码功能（Kaptcha），增强登录及敏感操作的安全性。
- 提供跨域支持和 CSRF 防护。

## 技术栈

- **后端**:
  - **Spring Boot**: 用于快速构建后端服务。
  - **MySQL**: 关系型数据库管理系统。
  - **MyBatis-Plus (MP)**: 简化 MyBatis 的数据库操作。
  - **Spring Security**: 提供安全认证和授权机制。
  - **Aliyun OSS**: 实现云存储功能。
  - **Kaptcha**: 实现验证码功能。
  - **Hutool**: 提供各种工具类库。
  - **Redis**: 高性能缓存解决方案。
  - **Fastjson**: 用于处理 JSON 数据的序列化和反序列化。
- **前端**:
  - **Vue.js**: 渲染和管理用户界面的 JavaScript 框架。
  - **Element UI**: 提供一套基于 Vue 的 UI 组件库。
  - **Node.js**: 用于构建和运行前端构建工具和开发服务器。



## 项目运行指南

1.克隆项目仓库:

```bash
git clone https://github.com/lfybtx/lf-admin.git
```

2.导入 SQL 脚本lfadmin.sql到 MySQL 数据库。

### 阿里云 OSS 配置

3.在 resources 目录下创建 oss.properties 文件，并添加以下内容:

``` properties
aliyun.oss.file.endpoint=your.endpoint
aliyun.oss.file.keyid=your.keyid
aliyun.oss.file.keysecret=your.keysecret
aliyun.oss.file.bucketname=your.bucketname
```
4.修改 application.yml 文件中的数据库配置。

```yml
driver-class-name: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://localhost:3306/lfadmin?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
username: your.username
password: your.password
```
5.启动后端项目

6.进入前端项目目录并安装依赖后启动:
```bash
cd vue-lfadmin
npm install
npm run dev
```
访问应用程序:
打开浏览器并访问 http://localhost:8000

## 预览图

![image-20240804171011069](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171011069.png)

![image-20240804171202798](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171202798.png)![image-20240804171216395](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171216395.png)

![image-20240804171056036](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171056036.png)![image-20240804171106235](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171106235.png)![image-20240804171113511](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171113511.png)![image-20240804171120453](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171120453.png)![image-20240804171128843](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171128843.png)![image-20240804171141777](C:\Users\12785\AppData\Roaming\Typora\typora-user-images\image-20240804171141777.png)
