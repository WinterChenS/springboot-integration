## 环境要求：
* JDK版本：1.8
* 数据库： MySQL


## 主要功能：

* 持久化框架：使用了简洁简便的Spring-data-jpa;
* 定时任务：使用了@Scheduled创建定时任务;
* 日志：使用了Log4j进行日志的输出;
* spring AOP: 使用了AOP进行了web请求日志的统一处理
* Spring Security：权限验证使用了 数据库的账号密码 和 QQ授权 登录;
* 邮件发送：使用JavaMailSender进行邮件的发送;
* 本地缓存：使用EhCache将查找到的数据进行本地缓存，当数据进行更新就会清除该条缓存;
* 更多功能正在开发中...
