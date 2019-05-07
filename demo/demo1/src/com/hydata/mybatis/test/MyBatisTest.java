package com.hydata.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import com.hydata.mybatis.bean.Employee;	//µº»ÎEmployee¿‡

public class MyBatisTest {

	@Test
	public void test() throws IOException {
//		fail("Not yet implemented");
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Employee blog =  session.selectOne("com.hydata.mybatis.EmployeeMapper.selectEmp", 1);
			System.out.println(blog);
		}finally {
			session.close();
		}
	}

}
