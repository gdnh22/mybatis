原贴
视频 https://pan.baidu.com/s/1qYcFDow
https://github.com/1913045515/MyBatis
https://blog.csdn.net/linzhiqiang0316/article/details/78310884

操作步骤

1 打开IDEA，新建 Spring Initializr
2 输入Group id，默认
3 Dependencies，
右上角SpringBoot2.1.4，
勾选SQL/MySQL SQL/MyBatis（搜索mysql mybatis）
点next .
4 Project name填demo3
目录 F:\github\mybatis.git\demo\demo3\project
编译，不通过，因为没配置MYSQL。
上传GIT
--------------------------------------
连接MYSQL，首先NAVICAT要能正常连接


备份数据库mybatis
默认的数据库位置在哪？



1 在SpringBoot的启动类型里面增加扫描 Mapper 接口的注解
2 在application.properties配置文件中添加数据库的支持【对应IEA201901】
//////////////////----------------------------- start
#DB Configuration:
spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username = root
spring.datasource.password = Sa123sa4
//////////////////----------------------------- end

旧的 application.properties
Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
//////////////////----------------------------- start
#DB Configuration:
spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username = root
spring.datasource.password = Sa123sa4
//////////////////----------------------------- end


3 新建 映射数据库的类
4 新建一个user表和字段
注：mybatis和jpa不一样，不会通过映射实体类反向生成数据库的表和字段。

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `user_pwd` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '10001', 'user10001', 'no1', '123456', '2017-10-22 15:23:32', '2017-10-22 15:23:35');
INSERT INTO `user` VALUES ('2', '10002', 'user10002', 'no2', '123456', '2017-10-22 15:23:32', '2017-10-22 15:23:35');

6 编写Mapper接口
注解方式： @Insert @Delete @Update @Select

clean项目
File->Invalidate Caches
-------------------------------------------
GIT中文乱码
右键，Encoding，选择【UTF8】