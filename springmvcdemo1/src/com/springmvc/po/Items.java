package com.springmvc.po;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Items implements Serializable {
	
	/*`id` int(11) NOT NULL AUTO_INCREMENT,
	  `name` varchar(32) NOT NULL COMMENT '商品名称',
	  `price` float(10,1) NOT NULL COMMENT '商品定价',
	  `detail` varchar(240) DEFAULT NULL COMMENT '商品描述',
	  `pic` varchar(64) DEFAULT NULL COMMENT '商品图片',
	  `createtime` datetime DEFAULT NULL COMMENT '生产日期',*/
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private double price;
	private String detail;
	private String pic;
	
	//方式一：使用 @DateTimeFormat注解
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//加上此注解，就可以更改时间  方式一：
	private Date createtime;
	
	
	
	public Items() {
	}
	
	public Items(Integer id, String name, double price, String detail, String pic, Date createtime) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.detail = detail;
		this.pic = pic;
		this.createtime = createtime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", detail=" + detail + ", pic=" + pic
				+ ", createtime=" + createtime + "]";
	}
	
	
	
	
}
