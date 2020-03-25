<%@page import="com.cxx.util.Page"%>
<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath }/admin/images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
</head>

<body>

	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">


				<div align="center">
					<table id="table2" class="line_table"
						style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
						cellPadding="0">
						<tbody style="margin: 0; padding: 0">
							<tr>
								<td class="line_table" align="center" colspan="12"><span
									class="left_bt2">销售订单查询结果信息列表</span></td>
							</tr>
							<tr>
								<td class="line_table" align="center"><span
									class="left_bt2">订单ID</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">用户ID</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">真实姓名</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">联系方式</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">家庭住址</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">菜品名称</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购数量</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">单价(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">合计(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购时间</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">是否派送</span></td>
								<td class="line_table" align="center" colspan="2"><span
									class="left_bt2">确认订单</span></td>
							</tr>
						
                             <c:forEach var="item" items="${currentPage.data }">
                             	<tr>
									<td class="line_table" align="center"><span
										class="left_txt">${item.orderId }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.users.userId }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.users.userRealname }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.users.userPhone }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.users.userAddress }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.menus.menusName }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.orderMenusum }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.menus.menusPricel }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt"><c:set var="sum" value="${item.orderMenusum*item.menus.menusPricel}"></c:set>${sum }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${item.orderTimes }</span></td>
									<td class="line_table" align="center">
									<span  class="left_txt">
										<c:if test="${item.orderDelivery  == '1'}">是</c:if>
										<c:if test="${item.orderDelivery  == '0'}">否</c:if>
									</span>
									</td>
	
									<td class="line_table" align="center">
									   <c:if test="${item.orderDelivery  == '1'}">
									       <a href="javascript:void(0)">已确认</a>
									   </c:if>
									   <c:if test="${item.orderDelivery  == '0'}"> 
									       <a href="${pageContext.request.contextPath }/updOrder?orderId=${item.orderId}">确认</a>
									   </c:if>
									</td>
									<td class="line_table" align="center"><a
										href="${pageContext.request.contextPath }/delOrder?orderId=${item.orderId}">取消</a></td>
								</tr>
                             </c:forEach>
							
							<tr>
								<td class="line_table" align="center" colspan="12" height="20">
								<span class="left_bt2">第${currentPage.curPage }页
										&nbsp;&nbsp;共${currentPage.totalPage }页
								</span>&nbsp;&nbsp; 
								    <a href="${pageContext.request.contextPath }/selOrdersByPage?curPage=1">[首页]</a>
								    <a href="${pageContext.request.contextPath }/selOrdersByPage?curPage=${currentPage.totalPage}">[尾页]</a>&nbsp;&nbsp; 
								    
								    <c:if test="${currentPage.curPage  != 1}">
								    	<a href="${pageContext.request.contextPath }/selOrdersByPage?curPage=${currentPage.curPage-1}">[上一页]</a>
								    </c:if>
								    <c:if test="${currentPage.curPage == 1}">
								    	<a href="javascript:void(0)">[上一页]</a>
								    </c:if>
								    <c:if test="${currentPage.curPage == currentPage.totalPage }">
								    	<a href="javascript:void(0)">[下一页]</a>
								    </c:if>
								    <c:if test="${currentPage.curPage != currentPage.totalPage }">
								    	<a href="${pageContext.request.contextPath }/selOrdersByPage?curPage=${currentPage.curPage+1}">[下一页]</a>
								    </c:if>
								    
									
									
								</td>
							</tr>
							
					</table>
				</div>
				
				
				
				
			</td>
		</tr>
	</table>
</body>
</html>