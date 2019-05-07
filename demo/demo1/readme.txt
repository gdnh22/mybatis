视频教程 https://edu.csdn.net/course/play/4662/84196
官方教程 http://www.mybatis.org/mybatis-3/zh/getting-started.html
navicat，建库 mybatis 字符集utf8 排序规则utf8_general_ci

建表tbl_employee
create table tbl_employee(
id int(11) primary key auto_increment,
last_name varchar(255),
gender char(1),
email varchar(255)
)

eclipse，新建 Employee.java
Package填写：com.hydata.mybatis.bean
输入
private Integer id;
private String lastname;
private String email;
private String gender;
右键 source>Generate getters and setters to create
右键 source>Generate toString

设置字体：windows>preferences>General>Appearance>Colors and Fonts，双击text Font设置字体大小。

新建lib文件夹，和src平级
拖动mybatis-3.4.1.jar到lib下，然后选【link】
导入依赖包：
1 log4j
2 mybatis-3.4.1.jar
3 mysql-connector-java.jar

添加log4j.xml???
选中所有jar，【build path】

官方：从 XML 中构建 SqlSessionFactory
新建mybatis-config.xml

配置节点【dataSource】
driver com.mysql.jdbc.Driver
url = jdbc:mysql://localhost:3306/mybatis

复制官方代码：
String resource = "org/mybatis/example/mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

新建一个测试类New JUnit 4 Test：
New JUnit Test Case
Name填 MyBatisTest，
Package填 com.hydata.mybatis.test
粘贴上述代码
打包【ctrl+shift+O】，选
inputStream
抛异常 add。

修改默认xml路径。

从XML新建一个SqlSessionFactory
获取SqlSession

import com.hydata.mybatis.bean.Employee;	//导入Employee类


提交GIT可以选择【Force overwrite existing】避免错误

Eclipse配置maven
window>preferences，Maven>Installations>add>Directory>选目录，勾选新加的maven。
maven本地仓库，preferences>Maven>User Settings>LocalRepository
参考
https://www.cnblogs.com/pengyan-9826/p/7767070.html


查出1号数据数据，封装对象

