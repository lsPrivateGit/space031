<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %><!--导入JSTL标签库 --> 
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
</head>
<body> 
	<form action="${pageContext.request.contextPath }/item/queryitem.action" method="post">
	查询条件：
	<table width="100%" border=1>
	<tr>
	<td><input type="submit" value="查询"/></td>
	</tr>
	</table>
		商品列表：
	<table width="100%" border=1>
			<tr>
				<td>选择</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
				<!-- <td>商品图片</td> -->
				<td>操作</td>
			</tr>
		<c:forEach items="${itemList}" var="item" varStatus="status">
		 <tr>
		 	<td>
		 		<input type="checkbox" name="ids" value="${item.id}" />
		 		<input type="hidden" name="itemList[${status.index}].id" value="${item.id}" />
		 	</td>
			<td><input type="text" name="itemList[${status.index}].name"  value="${item.name}" /></td>
			<td><input type="text" name="itemList[${status.index}].price" value="${item.price}" /></td>
			<td><input type="text" name="itemList[${status.index}].createtime" value="<fmt:formatDate value="${item.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />" /></td>
			<%-- <td><img src="/pic/${item.pic}" width=50 height=50/></td> --%>
			<td><input type="text" name="itemList[${status.index}].detail" value="${item.detail}" /></td>
			<td><a href="${pageContext.request.contextPath}/item/itemEdit.action?id=${item.id}">修改</a></td>
		  </tr>	
		</c:forEach>		
	
	</table>
	</form>
</body>

</html>