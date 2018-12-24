package cn.sun.crm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.sun.crm.dao.UserDao;
import cn.sun.crm.domain.User;

public class UserDaoImpl  extends HibernateDaoSupport implements UserDao {
	
	
	@Override
	public void save(User user) {
			System.out.println("执行了UserDaoImpl的save方法....");
			getHibernateTemplate().save(user);
	}
	/*@Override
	public User login(User user) {
		//使用的是hql查询语句
		String sql="from User where user_code =? and user_password=? and user_state =1";
		
		List<User> list = (List<User>) getHibernateTemplate().find(sql, user.getUser_code(),user.getUser_password());
		
		if(list !=null && list.size()>0){
			//System.out.println(list.get(0).toString());
			return list.get(0);
		}
		
		return null;
	}*/

}
