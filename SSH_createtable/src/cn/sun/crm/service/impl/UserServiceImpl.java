package cn.sun.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.sun.crm.dao.UserDao;
import cn.sun.crm.domain.User;
import cn.sun.crm.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {
	//注入userDao
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public void regiser(User user) {
		/*
		 * 对密码进行加密处理
		 */
		//先从user中获取密码然后加密
		//String md5Pwd = Md5Utils.getMd5Pwd(user.getUser_password());
		//然后把加密的密码在存入user中
	//	user.setUser_password(md5Pwd);
	   userDao.save(user);	
	}


	//@Override
	//public User login(User user) {
		
		//先从数据库中获取加密的密码
		//String md5Pwd = Md5Utils.getMd5Pwd(user.getUser_password());
		//然后在存入user中，相当于解码一样
	//	user.setUser_password(md5Pwd);
		
	//	return userDao.login(user);
	//}

}
