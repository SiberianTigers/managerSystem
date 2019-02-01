# managerSystem





















**项目说明** 
 Spring Boot实现的J2EE快速开发平台
  
 


**具有如下特点** 
- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端完全分离，前端再也不用关注后端技术
- 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 页面交互使用Vue2.x，极大的提高了开发效率
- 完善的代码生成机制，可在线生成entity、xml、dao、service、html、js、sql代码，减少70%以上的开发任务
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云等
- 引入swagger文档支持，方便编写API接口文档
- 引入路由机制，刷新页面会停留在当前页



 **技术选型：** 
- 核心框架：Spring Boot 2.0
- 安全框架：Apache Shiro 1.3
- 视图框架：Spring MVC 4.3
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.0
- 日志管理：logback
- 页面交互：Vue 2.x
- 前端框架：Bootstrap 3.3.7


 **软件需求** 
- JDK1.8+
- MySQL5.5+
- Tomcat7.0+
- Maven3.0+


 **访问路径**
- 本地访问路径
  - 后台管理端：http://localhost:8000/seal
  - 企业端：https://localhost/seal
- 外网访问路径
  - 后台管理端：http://sign.ieseals.cn:8000/seal
  - 企业端：https://sign.ieseals.cn/seal
 
- API文档路径：http://localhost:8000/seal/swagger-ui.html
- 账号密码：admin/admin

 **也可以NGINX部署**
 - 解压doc/nginx-1.11.10.zip,放到一个常用目录下
 - 修改nginx解压后的nginx.conf,设置root指向自己项目的的 c:\src\main\resources\site实际物理路径
 - 运行nginx.exe
 - 项目访问路径：http://localhost:1000/syslogin.html



 

