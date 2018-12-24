package com.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis.pojo.User;

public class UserTest {
	
	private SqlSessionFactory sqlSessionFactory;//mybatis 会话工厂
	
	
	@Before
	public void createSqlSessionFactory() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		
		// 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
	   sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testfindUserById(){
		SqlSession sqlSession = null;
		try{
			sqlSession= sqlSessionFactory.openSession();
			
			User user = sqlSession.selectOne("test.findUserById", 70);
			
			System.out.println(user);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	
	@Test
	public void testfindUserByUsername(){
		SqlSession sqlSession = null;
		try{
			sqlSession= sqlSessionFactory.openSession();
			
			
			List<User> list= sqlSession.selectList("test.findUserByUsername","张" );
			
			System.out.println(list);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testinsertUser(){
		SqlSession sqlSession = null;
		try{
			sqlSession= sqlSessionFactory.openSession();
			
			User uesr = new User(92, "郑桑", "女",new Date(), "上饶");
			sqlSession.insert("test.insertUser", uesr);
			
			sqlSession.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testdeleteUserById(){
		SqlSession sqlSession = null;
		try{
			sqlSession= sqlSessionFactory.openSession();
			
		
			sqlSession.delete("test.deleteUserById", 62);
			
			sqlSession.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	
	@Test
	public void testupdateUserById(){
		
		SqlSession sqlSession = null;
		try{
			sqlSession= sqlSessionFactory.openSession();
			// 添加用户信息
			User user = new User();
			user.setId(63);
			user.setUsername("李明明");
			user.setAddress("郑州");
			user.setBirthday(new Date());
			user.setSex("女");
			sqlSession.update("test.updateUserById", user);
			
			sqlSession.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}
