
<%@page import="java.util.*"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath }/admin/images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body {
    margin-left: 0px;
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 0px;
    background-color: #EEF2FB;
}
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
                                <td class="line_table" align="center" colspan="11" height="20"><span
                                    class="left_bt2">菜单信息列表</span></td>
                            </tr>
                            <tr>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">菜单名称</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">展示图片</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">原料</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">类型</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">说明</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">市场价格</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">市场价销售数量</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">会员单价</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">会员价销售数量</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">&nbsp;</span></td>
                                <td class="line_table" align="center"><span
                                    class="left_bt2">&nbsp;</span></td>
                            </tr>

                            <%-- <c:forEach var="item" items="${currentPage.data}"> --%>
                            <c:forEach var="item" items="${currentPage.data}">
                                <tr>
                                    <td class="line_table" align="center"><a
                                        href="${pageContext.request.contextPath }/updateMenus?menusName=${item.menusName }">${item.menusName }</a></td>
                                    <td class="line_table" align="center">
                                    <a href="${pageContext.request.contextPath}/${item.menusImgpath}"><img src="${pageContext.request.contextPath}/${item.menusImgpath}"
                                            width="30" height="30"></a></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt">${item.menusBurden }</span></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt">${item.typeName }</span></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt">${item.menusBrief }</span></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt">${item.menusPrice }</span></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt">${item.menusSums }</span></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt">${item.menusPricel }</span></td>
                                    <td class="line_table" align="center"><span
                                        class="left_txt">${item.menusSumsl }</span></td>
                                    <td class="line_table" align="center"><a
                                        href="${pageContext.request.contextPath}/beforeUpdMenus?menusName=${item.menusName }">修改</a></td>
                                    <td class="line_table" align="center"><a
                                        href="${pageContext.request.contextPath}/delMenus?menusName=${item.menusName }">删除</a></td>
                                    <%-- <td>${pageContext.request.contextPath}/MenusServlet?action=deleteMenus&menusName=${item.menusName }</td> --%>
                                </tr>
                            </c:forEach>
                            
                        
                        
                    
                            
                            <tr>
                                <td class="line_table" align="center" colspan="11" height="20">
                                <span class="left_bt2">第${currentPage.curPage }页
                                        &nbsp;&nbsp;共${currentPage.totalPage }页
                                </span>&nbsp;&nbsp; 
                                    <a href="${pageContext.request.contextPath}/selMenusByPage?curPage=1">[首页]</a>
                                    <a href="${pageContext.request.contextPath}/selMenusByPage?curPage=${currentPage.totalPage}">[尾页]</a>&nbsp;&nbsp; 
                                   
                                    <c:if test="${currentPage.curPage != 1}">
                                        <a href="${pageContext.request.contextPath}/selMenusByPage?curPage=${currentPage.curPage-1}">[上一页]</a>                                  
                                    </c:if>    
                                    <c:if test="${currentPage.curPage == 1}">
                                        <a href="javascript:void(0)">[上一页]</a>                                  
                                    </c:if>    
                                    
                                    <c:if test="${currentPage.curPage != currentPage.totalPage }">
                                        <a href="${pageContext.request.contextPath}/selMenusByPage?curPage=${currentPage.curPage+1}">[下一页]</a>
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
