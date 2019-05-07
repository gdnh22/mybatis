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

提交GIT可以选择【FORCE】避免错误

Eclipse配置maven
window>preferences，Maven>Installations>add>Directory>选目录，勾选新加的maven。
maven本地仓库，preferences>Maven>User Settings>LocalRepository
参考
https://www.cnblogs.com/pengyan-9826/p/7767070.html


查出1号数据数据，封装对象

