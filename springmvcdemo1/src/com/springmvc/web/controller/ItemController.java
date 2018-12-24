package com.springmvc.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.po.Items;
import com.springmvc.po.QueryVo;
import com.springmvc.service.ItemService;
import com.springmvc.utils.CustomException;



/**
 * @author ex-sunjiamin
 *
 */
@Controller
public class ItemController {
	
	private static Logger logger = Logger.getLogger(ItemController.class);
	
	
	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping("/demo")
	public ModelAndView demo(){
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("message", "Hello world !");
		
		//modelAndView.setViewName("/WEB-INF/jsp/demo.jsp");
		modelAndView .setViewName("demo");
		logger.info("执行了 demo方法.....");
		//modelAndView :封装了模型数据和视图信息
		return modelAndView;
	}
	
	
	/**
	 * @descriptin:查找所有商品
	 * @return modelAndView 返回接收的视图信息
	 */
	
	@RequestMapping("/itemList")
	public ModelAndView getItemList(){
		List<Items> list = itemService.getItemLists();
		logger.info(list.toString());
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("itemList", list);
		modelAndView.setViewName("itemList");//设置要转发的jsp 名字
		return modelAndView;
	}
	
	
	/**
	 * @description:编辑的单个商品信息
	 * @param model  封装视图信息的对象
	 * @param request  请求对象
	 * @return
	 * @throws  
	 */
	//@RequestMapping(value="/item/itemEdit",method = {RequestMethod.POST,RequestMethod.GET})
	@RequestMapping("/item/itemEdit")
	public String itemEdit(Model model ,HttpServletRequest request) {
		
		//接收itemEdit 方法传来的id
		String sId = request.getParameter("id");
		
		Integer id =null;
		//判断当前接收的id是否为空
		if(sId != null && !"".equals(sId)){
			id = new Integer(sId);
		}else{
			return null;
		}
		//int i = 1/0;  模拟自定义异常
		Items item = itemService.findItemsById(id);
		
		if(item == null){
			try {
				throw new CustomException("商品信息不存在");
			} catch (CustomException e) {
				//打印出异常堆栈信息
				StringWriter s = new StringWriter();
				PrintWriter printWriter = new PrintWriter(s);
				e.printStackTrace(printWriter);
				s.toString();
			}
		}
		
		
		logger.info(item);
		model.addAttribute("item", item);
		return "editItem";
	}
	
	/**
	 * 修改商品信息
	 * @param: items
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="/updateitem" ,method = {RequestMethod.POST,RequestMethod.GET}) //修改后提交时post请求
	public String updateitem(Items items ,MultipartFile pictureFile) throws IllegalStateException, IOException{
		
		//获取原始文件名称
		String originalFilename = pictureFile.getOriginalFilename();
		logger.info(originalFilename);
		//新文件名称
		logger.info(originalFilename.lastIndexOf("."));
		
		/*
		 * 说明：如果这里修改的时候，不添加图片的话，获取的originalFilename就为空，再调用下面的方法就会报越界，因为“ ”空串也是字符串，但是长度为0
		 */
		String subString = originalFilename.substring(originalFilename.lastIndexOf("."));//用于截取图片后面的字符串 .jpg
		
		String newFileName = UUID.randomUUID().toString()+subString;
		
		//上传图片
		File uploadPic = new File("D:/Users/ex-sunjiamin/Pictures/"+newFileName);
		
		//向磁盘写入
		pictureFile.transferTo(uploadPic);
		
		items.setPic(newFileName);
		
		itemService.updateitem(items);
		
		return "redirect:/itemList.action";//重定向action
	}
	
	
	/**
	 * 查询特定条件的商品信息
	 * @return
	 */
	//@RequestMapping("/item/queryitem")
	@RequestMapping(value="/item/queryitem",method=RequestMethod.POST) //设置为只能允许为post请求
	public String queryitem(QueryVo queryVo,String[] ids,HttpServletRequest request){
		//TODO ：为何Items为null呢？
	
//			logger.info(queryVo.getItems().getName());
		 //Items items = queryVo.getItems();
		
			//System.out.println(queryVo.getItems().getName());
			//logger.info(queryVo.getItems().getPrice());
		logger.info(ids.toString());
		List<Items> itemList = queryVo.getItemList();	
		
		if(itemList!=null && itemList.size()>0){
			request.setAttribute("itemList", itemList);
		}
		return "itemList";
	}
	
	
	@RequestMapping(value="/sendJson",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String sendJsonTest(@RequestBody Items items,HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("applicatoin/json;charset=utf-8");
		response.getWriter().write("{\"id\":88,\"name\":\"测试json\"}");
		return "null";
	}
	
	
	
	
}
