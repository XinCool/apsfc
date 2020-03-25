<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>我的订单</title>
<meta content="" name=keywords />
<meta content="" name=description />
<link href="${pageContext.request.contextPath }/before/css/skin.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/before/js/date.js"
	type="text/javascript"></script>
</head>

<body style='background: transparent'>
	<table width="900" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align="left" valign="top"><jsp:include flush="fasle"
					page="top.jsp" /></td>
		</tr>
		<tr>
			<td height="50px"></td>

		</tr>

		<tr>
			<td align="center" valign="top" height="420px">

				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="left" valign="top">

							<div align="center" width="120">

								<table id="table1" class="line_table"
									style="width: 500px; margin: 0; padding: 0" cellSpacing="0"
									cellPadding="0">
									<tbody style="margin: 0; padding: 0">
										<tr>
											<form action="${pageContext.request.contextPath }/byMenusName"
												name="form1" method="post">

												<td class="line_table" align="right" width="40%"><span
													class="left_bt2">按菜品名称查询</span></td>
												<td class="line_table" align="left" width="60%"><input
													type="text" name="menusName" size="20"> <input
													name="stype" type="submit" value="查询1"></td>
											</form>
										</tr>
										<tr>
											<form action="${pageContext.request.contextPath }/byDay"
												name="form2" method="post">

												<td class="line_table" align="right" width="40%"><span
													class="left_bt2">按销售日期查询</span></td>
												<td class="line_table" align="left" width="60%"><input
													type="text" name="orderTimes" size="20" readOnly
													onClick="setDay(this);"> <input type="submit"
													name="stype" value="查询2"></td>

											</form>
										</tr>
										<tr>
											<td class="line_table" align="center" colspan="3"><a
												href="${pageContext.request.contextPath }/myOrder">我的所有订单</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a
												href="${pageContext.request.contextPath }/finishedOrder?delivery=0">未已派送订单</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a
												href="${pageContext.request.contextPath }/finishedOrder?delivery=1">已派送订单</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
								</table>

							</div>
						</td>
					</tr>
					<tr>
						<td align="left" valign="top" height="20px"></td>
					</tr>
					<tr>
						<td align="left" valign="top">


							<div align="center">
								<table id="table2" class="line_table"
									style="width: 900px; margin: 0; padding: 0" cellSpacing="0"
									cellPadding="0">
									<tbody style="margin: 0; padding: 0">
										<tr>
											<td class="line_table" align="center" colspan="9"><span
												class="left_bt2">订单查询结果信息列表</span></td>
										</tr>
										<tr>
											<td class="line_table" align="center"><span
												class="left_bt2">菜品名称</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">真实姓名</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">订购电话</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">派送地址</span></td>
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
										</tr>


										<c:forEach var="i" items="${orders}">
											<tr>
												<td class="line_table" align="center"><span
													class="left_txt">${i.menus.menusName }</span></td>
												<td class="line_table" align="center"><span
													class="left_txt">${i.users.userRealname }</span></td>
												<td class="line_table" align="center"><span
													class="left_txt">${i.users.userPhone }</span></td>
												<td class="line_table" align="center"><span
													class="left_txt">${i.users.userAddress }</span></td>
												<td class="line_table" align="center"><span
													class="left_txt">${i.orderMenusum }</span></td>
												<td class="line_table" align="center"><span
													class="left_txt">${i.menus.menusPricel }</span></td>
												<td class="line_table" align="center"><span
													class="left_txt"><c:set var="sumMoney"
															value="${i.orderMenusum*i.menus.menusPricel }"></c:set>${sumMoney }</span></td>
												<td class="line_table" align="center"><span
													class="left_txt">${i.orderTimes }</span></td>
												<td class="line_table" align="center"><span
													class="left_txt"> <c:if
															test="${i.orderDelivery == '0'}">否</c:if> <c:if
															test="${i.orderDelivery == '1'}">是</c:if>
												</span></td>
											</tr>
										</c:forEach>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10px">&nbsp;</td>
		</tr>
		<tr>
			<td height="50px" align="center" valign="middle"><jsp:include
					flush="fasle" page="copyright.jsp" /></td>
		</tr>
	</table>
</body>
</html>
