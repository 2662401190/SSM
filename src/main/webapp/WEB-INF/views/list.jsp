<%--
  Created by IntelliJ IDEA.
  User: HeWei
  Date: 2018/10/12
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工；列表</title>
    <%
        request.setAttribute("path",request.getContextPath());
    %>

    <link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" >
    <script src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="${path}/static/js/jquery-3.2.1.min.js"></script>

</head>

<body>
    <!--搭载页面显示-->
        <div class="container">
            <!--标题-->
            <div class="row">
                <div class="col-md-push-12">
                    <h1>SSM-CRUD</h1>
                </div>
            </div>
            <!--按钮-->
            <div class="row">
                <div class="col-md-4 col-md-offset-8">
                    <button class="btn btn-primary  ">添加</button>
                    <button class="btn btn-danger ">删除</button>
                </div>
            </div>
            <!--显示表格数据-->
            <div class="row">
                <div class="col-md-push-12">
                    <table class="table   table-hover">
                        <tr>
                            <th>ID</th>
                            <th>EmpName</th>
                            <th>Gender</th>
                            <th>Email</th>
                            <th>DeptName</th>
                            <th>Operate </th>
                        </tr>
                        <C:forEach items="${info.list}" var="emp">
                            <tr>
                                <th>${emp.empId}</th>
                                <th>${emp.empName}</th>
                                <th>${emp.gender=="M"?"男":"女"}</th>
                                <th>${emp.email}</th>
                                <th>${emp.dept.deptName}</th>
                                <th>
                                    <button class="btn btn-primary btn-sm">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        编辑
                                    </button>
                                    <button class="btn btn-danger btn-sm">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        删除
                                    </button>
                                </th>
                            </tr>
                        </C:forEach>
                    </table>
                </div>
            </div>
            <!--分页-->
            <div class="row">
                <!--分页文字信息-->
                <div class="col-md-push-6">
                    当前第${info.pageNum}页,总${info.pages}页,总${info.total}记录
                </div>
                <%--分页条信息--%>
                <div class="col-md-push-6">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">

                            <li>
                                <a href="${path}/emps?page=1">首页</a>
                            </li>

                            <!--上一页-->
                            <c:if test="${info.hasPreviousPage}">
                                <li>
                                    <a href="${path}/emps?page=${info.pageNum-1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                          <!--页码数-->
                            <c:forEach items="${info.navigatepageNums}" var="page">
                                <!--当前页-->
                                <c:if test="${page==info.pageNum}">
                                    <li><a class="disabled" href="#">${page}</a></li>
                                </c:if>

                                <c:if test="${page!=info.pageNum}">
                                    <li><a  href="${path}/emps?page=${page}">${page}</a></li>
                                </c:if>


                            </c:forEach>
                            <!--下一页-->
                            <c:if test="${info.hasNextPage}">
                                <li>
                                    <a href="${path}/emps?page=${info.pageNum+1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <li>
                                <a href="${path}/emps?page=${info.pages}">尾页</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

        </div>

</body>
</html>
