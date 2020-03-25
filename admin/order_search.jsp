<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath }/admin/images/skin.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/admin/js/verify.js"></script>
<script src="${pageContext.request.contextPath }/admin/js/date.js"
	type="text/javascript"></script>
<script>
function verifyNotN1() {
   if(!verifyNotNull(document.form1.userId.value)){
	   alert("用户Id不能为空！");
	   return false;
   }
}
function verifyNotN2() {
   if(!verifyNotNull(document.form2.menusName.value)){
	   alert("菜单名称不能为空！");
	   return false;
   }
}
function verifyNotN3() {
   if(!verifyNotNull(document.form3.orderTimes.value)){
	   alert("创建订单时间不能为空！");
	   return false;
   }
}

</script>
</head>

<body>


	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">

				<div align="center" width="120">
					<%-- 	<form
						action="${pageContext.request.contextPath }/selOrder?type=selectType"
						name="form1" method="post">
						<table id="table1" class="line_table"
							style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
							cellPadding="0">
							<tbody style="margin: 0; padding: 0">
								<tr>



									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按用户ID查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="userId" size="20"><input
										type="submit" value="查询" name="selectType"></td>
									</form>

								</tr>


								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按菜品名称查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="menusName" size="20"><input
										type="submit" value="查询" name="selectType"></td>
								</tr>


								<tr>

									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按销售日期查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="orderTimes" size="20" readOnly
										onClick="setDay(this);"><input type="submit"
										value="查询" name="selectType"></td>
								</tr>
						</table>
					</form> --%>

					<form
						action="${pageContext.request.contextPath }/selOrderById?type=userId"
						name="form1" method="post" onsubmit="return verifyNotN1()">
						<table id="table1" class="line_table"
							style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
							cellPadding="0">
							<tbody style="margin: 0; padding: 0">
								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按用户ID查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="userId" size="20"><input
										type="submit" value="查询" name="selectType"></td>
								</tr>
						</table>
					</form>
					<form
						action="${pageContext.request.contextPath }/selOrderByName?type=menusName"
						name="form2" method="post" onsubmit="return verifyNotN2()">
						<table id="table1" class="line_table"
							style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
							cellPadding="0">
							<tbody style="margin: 0; padding: 0">
								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按菜品名称查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="menusName" size="20"><input
										type="submit" value="查询" name="selectType"></td>
								</tr>
						</table>
					</form>
					<form
						action="${pageContext.request.contextPath }/selOrderByTime?type=orderTimes"
						name="form3" method="post" onsubmit="return verifyNotN3()">
						<table id="table1" class="line_table"
							style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
							cellPadding="0">
							<tbody style="margin: 0; padding: 0">
								<tr>
									<td class="line_table" align="right" width="40%"><span
										class="left_bt2">按销售日期查询</span></td>
									<td class="line_table" align="left" width="60%"><input
										type="text" name="orderTimes" size="20" readOnly
										onClick="setDay(this);"><input type="submit"
										value="查询" name="selectType"></td>
								</tr>
						</table>
					</form>


				</div>



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

							</tr>

							<c:if test="${orders=='nothing' }">
							     <script>alert("数据库不存在符合该条件的订单");</script>
							</c:if>
							<c:if test="${orders!=null && orders!='nothing'}">
							     <c:forEach var="item" items="${orders }">
                                <tr>
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
                                        class="left_txt"><c:set var="sum"
                                                value="${ item.orderMenusum *item.menus.menusPricel}"></c:set>${sum }</span></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt">${item.orderTimes }</span></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt"> <c:if
                                                test="${item.orderDelivery  == '1'}">是</c:if> <c:if
                                                test="${item.orderDelivery  == '0'}">否</c:if>
                                    </span></td>

                                </tr>
                            </c:forEach>
							</c:if>
					</table>
				</div>

			</td>

		</tr>
	</table>

</body>
</html>
