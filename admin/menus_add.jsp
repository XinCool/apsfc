<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath }/admin/images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/admin/js/verify.js"></script>
<script type="text/javascript">
	 function verifyInfo() { 
		
			//菜品名称
			if(!verifyNotNull(document.form1.menusName.value)) {
				alert("菜品名称不能为空!");
				return false;
			}
			//原料
			if(!verifyNotNull(document.form1.menusBurden.value)) {
				alert("原料不能为空");
				return false;
			}
			//市场价格
			/* debugger; */
		if(!verifyPositiveInt(document.form1.menusPrice.value)) {
				alert("市场价格必须为正整数");
				return false;
			}
			//会员价格
			if(!verifyPositiveInt(document.form1.menusPricel.value)) {
				alert("会员价格必须为正整数");
				return false;
			}
			//说明
			if(!verifyNotNull(document.form1.menusBrief.value)) {
				alert("说明不能为空");
				return false;
			}
			//图片
			if(!verifyNotNull(document.form1.menusImgpath.value)) {
				alert("图片不能为空");
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

				<p>添加菜单</p>

				<div align="center">
<!-- enctype="multipart/form-data" -->
					<form action="${pageContext.request.contextPath }/addMenus" method="post" name="form1"
						onSubmit="return verifyInfo()">


						<table id="table2" class="line_table"
							style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
							cellPadding="0">
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">菜品名称：</span></td>
								<td class="line_table" height="25" width="80%"><input
									type="text" name="menusName" size="45" ></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">原&nbsp;&nbsp;&nbsp; 料：</span></td>
								<td class="line_table" height="25" width="80%"><input
									type="text" name="menusBurden" size="45" ></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">市场价格：</span></td>
								<td height="25" width="80%"><input type="text" name="menusPrice"
									size="45" ></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">会员价格：</span></td>
								<td height="25" width="80%"><input type="text"
									name="menusPricel" size="45"></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">说&nbsp;&nbsp;&nbsp; 明：</span></td>
								<td class="line_table" height="25" width="80%"><textarea
										rows="12" name="menusBrief" cols="100"></textarea></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">菜品类别：</span></td>
								<td class="line_table" height="25" width="80%">
								<select name="typeId">
								
									<c:forEach var="item" items="${types}">
										<option value="${item.typeId }">${item.typeName}</option>
									</c:forEach>
										
								</select>
								</td>
							</tr>

							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">上传图片：</span></td>
								<td class="line_table" height="25" width="80%"><input
									type="file" name="menusImgpath" size="50"></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="center" colspan="2"><input
									type="submit" name="button" value="添加"></td>
							</tr>
						</table>
					</form>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
