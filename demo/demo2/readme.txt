原贴
视频 https://pan.baidu.com/s/1qYcFDow
https://github.com/1913045515/MyBatis
https://blog.csdn.net/linzhiqiang0316/article/details/78310884

操作步骤

1 打开IDEA，新建 Spring Initializr
2 输入Group id，默认


1 在SpringBoot的启动类型里面增加扫描 Mapper 接口的注解
2 在application.properties配置文件中添加数据库的支持
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


