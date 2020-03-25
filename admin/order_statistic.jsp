
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath }/admin/images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
    window.onload=function(){
        
        
    }
</script>
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
                                    <span class="left_bt2">本日销售额统计</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="line_table" align="center" width="25%"><span
                                    class="left_bt2">菜品名称</span></td>
                                <td class="line_table" align="center" width="25%"><span
                                    class="left_bt2">订购数量</span></td>
                                <td class="line_table" align="center" width="25%"><span
                                    class="left_bt2">单价</span></td>
                                <td class="line_table" align="center" width="25%"><span
                                    class="left_bt2">合计</span></td>
                            </tr>


                            
                            <c:forEach var="item" items="${currentPage.data }">
                                <tr>
                                    <td class="line_table" align="center" width="25%"><span
                                        class="left_txt">${item.menus.menusName } </span></td>
                                    <td class="line_table" align="center" width="25%"><span
                                        class="left_txt">${item.orderMenusum }</span></td>
                                    <td class="line_table" align="center" width="25%"><span
                                        class="left_txt">${item.menus.menusPricel}</span></td>
                                    <td class="line_table" align="center" width="25%"><span
                                        class="left_txt"><c:set var="sumMoney" value="${item.orderMenusum*item.menus.menusPricel}"></c:set>${sumMoney }元</span></td>
                                    <c:set var="sum" value="${sum+sumMoney}"></c:set>
                                </tr>
                            </c:forEach>
                            
                            <tr>
                                <td class="line_table" align="center" colspan="8"><span
                                    class="left_bt2">
                                    <c:if test="${sum == 0}">本日销售总额：0.0元</c:if>
                                    <c:if test="${sum != 0}">本日销售总额：${sum }&nbsp;元</c:if>
                                </span></td>
                            </tr>
                        
                            <tr>
                                <td class="line_table" align="center" colspan="12" height="20">
                                <span class="left_bt2">第${currentPage.curPage }页
                                        &nbsp;&nbsp;共${currentPage.totalPage }页
                                </span>&nbsp;&nbsp; 
                                    <a href="${pageContext.request.contextPath }/selDayByPage?curPage=1">[首页]</a>
                                    <a href="${pageContext.request.contextPath }/selDayByPage?curPage=${currentPage.totalPage}">[尾页]</a>&nbsp;&nbsp; 
                                    
                                    <c:if test="${currentPage.curPage  != 1}">
                                        <a href="${pageContext.request.contextPath }/selDayByPage?curPage=${currentPage.curPage-1}">[上一页]</a>
                                    </c:if>
                                    <c:if test="${currentPage.curPage == 1}">
                                        <a href="javascript:void(0)">[上一页]</a>
                                    </c:if>
                                    <c:if test="${currentPage.curPage == currentPage.totalPage }">
                                        <a href="javascript:void(0)">[下一页]</a>
                                    </c:if>
                                    <c:if test="${currentPage.curPage != currentPage.totalPage }">
                                        <a href="${pageContext.request.contextPath }/selDayByPage?curPage=${currentPage.curPage+1}">[下一页]</a>
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
