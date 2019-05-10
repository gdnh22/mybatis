【请关闭VPN再使用postman测试连接，否则500错误】
原贴
视频 https://pan.baidu.com/s/1qYcFDow
https://github.com/1913045515/MyBatis
https://blog.csdn.net/linzhiqiang0316/article/details/78310884

操作步骤

1 打开IDEA，新建 Spring Initializr
2 输入
Group id: com.joy
package : com.joy

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

1 在SpringBoot的启动类型里面增加扫描 Mapper 接口的注解
////--------
package com.joy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.joy.*")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
////--------


2 在application.properties配置文件中添加数据库的支持【对应IEA201901】
//////////////////----------------------------- start
#DB Configuration:
spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC
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

新建数据库mybatis
新建表
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

备份数据库mybatis，命名【mybatis_db20190509】
默认的数据库位置在哪？E:\users\gdnh\Documents\Navicat\MySQL\servers\local\mybatis

3 新建 映射数据库的类UserEntity

注：mybatis和jpa不一样，不会通过映射实体类反向生成数据库的表和字段。


6 编写UserMapper接口
注解方式： @Insert @Delete @Update @Select
///////////////////////////////////////
package com.joy.entity;

import java.util.Date;

public class UserEntity {
	private long userId;
	private String userCode;
	private String userName;
	private String nickName;
	private String userPwd;
	private Date createDate;
	private Date updateDate;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
////////////////////////////////////////////////////////


7 在DAO目录写接口UserMapper，但是没有实现。
新建CLASS com.joy.dao.UserMapper 
////////////////////////////////////////////////////////
package com.joy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.joy.entity.UserEntity;

public interface UserMapper {
	@Select("select * from user ")
	@Results({
            @Result(property = "userId", column = "user_id"),
			@Result(property = "nickName", column = "nick_name"),
			@Result(property = "userCode", column = "user_code"), 
			@Result(property = "userName", column = "user_name"),
			@Result(property = "userPwd", column = "user_pwd"),
			@Result(property = "createDate", column = "create_date"),
			@Result(property = "updateDate", column = "update_date") })
	public List<UserEntity> queryList();

    @Select("SELECT * FROM USER WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date") })
    UserEntity findById(long userId);


    @Insert("INSERT INTO USER(nick_name, user_code) VALUES(#{nickName}, #{userCode})")
    int insertParam(@Param("nickName") String nickName, @Param("userCode") String userCode);

    @Insert("INSERT INTO USER(nick_name, user_code) VALUES(#{nickName,jdbcType=VARCHAR}, #{userCode,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into user(nick_name,user_code,user_name,user_pwd,create_date,update_date) values(#{nickName},#{userCode},#{userName},#{userPwd},#{createDate},#{updateDate})")
	public int insertEntity(UserEntity entity);

    @Update("UPDATE user SET nick_name=#{nickName} WHERE user_id=#{userId}")
    int updateEntity(UserEntity user);

    @Delete("DELETE FROM user WHERE user_id =#{userId}")
    int delete(Long userId);

    @Delete("DELETE FROM user WHERE user_id =#{userId}")
    int deleteEntity(UserEntity entity);
}





////////////////////////////////////////////////////////

--------------二、调用Mapper接口-----------------
1、com.joy.service.UserService
/////////////////////////////////////////////////////////
package com.joy.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy.dao.UserMapper;
import com.joy.entity.UserEntity;
@Service
public class UserService {
	@Autowired(required = false)
	private UserMapper mapper;
	
	public List<UserEntity> queryList(){
		List<UserEntity> userList=mapper.queryList();
		return userList;
	}

    public UserEntity findById(long userId){
        System.out.println("userId:"+userId);
        return mapper.findById(userId);
    }

	public int insertEntity() {
		UserEntity entity=new UserEntity();
		entity.setUserName("lisi");
		entity.setUserCode("lisi"+new Date());
		entity.setNickName("郭靖");
		entity.setUserPwd("123");
		entity.setCreateDate(new Date());
		entity.setUpdateDate(new Date());
        return mapper.insertEntity(entity);
	}

    public int insertParam() {
        return mapper.insertParam("linzhiqiang","lzq");
    }

    public int insertByMap() {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("nickName","zhaotong");
        map.put("userCode","zt");
        return mapper.insertByMap(map);
    }

    public int updateEntity() {
        UserEntity entity=new UserEntity();
        entity.setUserId(1);
        entity.setNickName("郭靖");
        return mapper.updateEntity(entity);
    }

    public int deleteEntity() {
        UserEntity entity=new UserEntity();
        entity.setUserId(11);
        return mapper.deleteEntity(entity);
    }
}

/////////////////////////////////////////////////////////
2、com.joy.controller.UserController

pom.xml添加
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper-spring-boot-starter -->
		<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper</artifactId>
			<version>4.1.0</version>
		</dependency>		
		
		/////////////////////////////////////
package com.joy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.joy.entity.UserEntity;
import com.joy.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/userlist")
	public List<UserEntity> queryList(){
		PageHelper.startPage(1, 2);
		return userService.queryList();
	}

    @RequestMapping("/queryUser")
    public UserEntity queryUserEntity(long userId){
        UserEntity userEntity=userService.findById(userId);
        return userEntity;
    }

    @RequestMapping("/insert")
	public int insertEntity() {
        return userService.insertEntity();
	}

    @RequestMapping("/insertParam")
    public int insertParam() {
        return userService.insertParam();
    }

    @RequestMapping("/insertByMap")
    public int insertByMap() {
        return userService.insertByMap();
    }

    @RequestMapping("/updateEntity")
    public int updateEntity() {
        return userService.updateEntity();
    }

    @RequestMapping("/deleteEntity")
    public int deleteEntity() {
        return userService.deleteEntity();
    }
}
		

		/////////////////////////////////////

上传GIT。

三、添加config类
//////////////////////////////////////////////////////////
package com.joy.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

@Configuration
public class MybatisConfig {
	@Bean
	public PageHelper pageHelper () {
		System.out.println("...pageHelper...");
		PageHelper pageHelper=new PageHelper();
		Properties p=new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithoutCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}
}

//////////////////////////////////////////////////////////
--------------四、执行结果-----------------
查询
127.0.0.1:8080/userlist
通过ID查询



新增
UserController @RequestMapping("/insert")
127.0.0.1:8080/insert

报错：
{
    "timestamp": "2019-05-10T06:17:45.337+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'user_code' at row 1\r\n### The error may exist in com/joy/dao/UserMapper.java (best guess)\r\n### The error may involve com.joy.dao.UserMapper.insertEntity-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into user(nick_name,user_code,user_name,user_pwd,create_date,update_date) values(?,?,?,?,?,?)\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'user_code' at row 1\n; Data truncation: Data too long for column 'user_code' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'user_code' at row 1",
    "path": "/insert"
}

使用navicat将user_code列的长度改成200。
再次执行insert。

中文不显示？https://blog.csdn.net/qq_36533690/article/details/82184720
修改字符集
show VARIABLES like '%char%'
检查是不是有latin1
everything搜索my.ini

找到[mysql]，在# default-character-set=的下一行添加default-character-set=utf8（图中第67行）
找到[mysqld]，在# character-set-server=的下一行添加character-set-server=utf8（图中第102行）
打开MySQL Workbench，INSTANCE，Startup/shutdown，关闭，重启。
show VARIABLES like '%char%' 再次查询

问题解决。

只显示两条数据？
PageHelper.startPage(1, 20); //修改第二个参数。
改成传参模式，在body里面输入page，number

3、更新数据
修改1号数据
    public int updateEntity() {
        UserEntity entity=new UserEntity();
        entity.setUserId(1);
        entity.setNickName("郭靖修改了怪物猎人");
        return mapper.updateEntity(entity);
    }

4、删除数据
删除11号数据

    public int deleteEntity() {
        UserEntity entity=new UserEntity();
        entity.setUserId(11);
        return mapper.deleteEntity(entity);
    }

备份数据库mybatis，命名【mybatis_db20190510】


clean项目
File->Invalidate Caches
-------------------------------------------
打印SQL语句
application.properties添加
//////////////////////////////////////////////////////
# --- {Logging}
logging.level.com.joy=DEBUG
//////////////////////////////////////////////////////
修改端口号
application.properties添加
server.port=8080


GIT中文乱码
右键，Encoding，选择【UTF8】