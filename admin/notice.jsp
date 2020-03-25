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
								<td class="line_table" align="center" colspan="8" height="20">
									<span class="left_bt2">公告信息列表</span>
								</td>
							</tr>
							<tr>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">标题</span></td>
								<td class="line_table" align="center" width="40%"><span
									class="left_bt2">内容</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">发布时间</span></td>
								<td class="line_table" align="center" width="10%"></td>
							</tr>
							
							<c:forEach var="item" items="${currentPage.data }">
								<tr>
									<td class="line_table" align="center" width="20%"><span
										class="left_txt">${item.noticeName }</span></td>
									<td class="line_table" align="center" width="40%"><span
										class="left_txt">${item.noticeContent }</span></td>
									<td class="line_table" align="center" width="20%"><span
										class="left_txt">${item.noticeTimes }</span></td>
									<td class="line_table" align="center" width="20%"><a
										href="${pageContext.request.contextPath}/delNotice?noticeName=${item.noticeName}">删除</a></td>
								</tr>
							</c:forEach>
							
							<tr>
								<td class="line_table" align="center" colspan="11" height="20">
								<span class="left_bt2">第${currentPage.curPage }页
										&nbsp;&nbsp;共${currentPage.totalPage }页
								</span>&nbsp;&nbsp; 
								    <a href="${pageContext.request.contextPath}/selNoticeByPage?curPage=1">[首页]</a>
								    <a href="${pageContext.request.contextPath}/selNoticeByPage?curPage=${currentPage.totalPage}">[尾页]</a>&nbsp;&nbsp; 
								   
									<c:if test="${currentPage.curPage != 1}">
										<a href="${pageContext.request.contextPath}/selNoticeByPage?curPage=${currentPage.curPage-1}">[上一页]</a>									
									</c:if>	   
									<c:if test="${currentPage.curPage == 1}">
										<a href="javascript:void(0)">[上一页]</a>									
									</c:if>	   
								    
								    <c:if test="${currentPage.curPage != currentPage.totalPage }">
										<a href="${pageContext.request.contextPath}/selNoticeByPage?curPage=${currentPage.curPage+1}">[下一页]</a>
									</c:if>
								
									<c:if test="${currentPage.curPage == currentPage.totalPage }">
										<a href="javascript:void(0)">[下一页]</a>
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
