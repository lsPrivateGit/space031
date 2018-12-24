package cn.sun.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import cn.sun.crm.domain.User;
import cn.sun.crm.service.UserService;

/**
 * 使用Hibernate创建表
 * @author ex-sunjiamin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest {
	
	/**
	 * 使用org.springframework.util类中提供的DigestUtils类提供的md5加密方法
	 */
	@Test
	public  void testMd5(){
		
		String pwd="12345";
		String newPwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
		System.out.println(newPwd);
	}
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testuserRegister(){
		User user=new User();
		user.setUsername("张三丰");
		user.setBirthday(new Date());
		user.setSex("男");
		user.setAddress("武当山");
	
		userService.regiser(user);
		System.out.println("注册成功！！！");
	}
}
