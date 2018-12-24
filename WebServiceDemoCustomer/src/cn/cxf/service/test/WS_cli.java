package cn.cxf.service.test;

import java.util.List;

import cn.cxf.service.Car;
import cn.cxf.service.IUserService;
import cn.cxf.service.User;
import cn.cxf.service.UserService;

public class WS_cli {
	
	public static void main(String[] args) {
		UserService userService = new UserService();
		IUserService port = userService.getUserServiceImplPort();
		String str = port.sayHello("张三");
		System.out.println(str);
		User user =new User();
		user.setCity("深圳");
		user.setUsername("sun");
		user.setId(1);
		List<Car> cars = port.findCarByUser(user);
		for (Car car : cars) {
			System.out.println("名字："+car.getCarName()+",id："+car.getId()+",价格："+car.getPrice());
		}
	}
}
