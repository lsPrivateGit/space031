package cn.rookie.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example {
		
	@RequestMapping("/")
	String home(){
		System.out.println("执行了home()方法....");
		return "Hello World!";
	}
	
	@RequestMapping("/hello/{myName}")
	String index(@PathVariable String myName){
		System.out.println("执行了index方法....");
		return "Hello"+myName+"!!!";
	}
	
}
