<%--
  Created by IntelliJ IDEA.
  User: HeWei
  Date: 2018/10/12
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工；列表</title>
    <%
        pageContext.setAttribute("path",request.getContextPath());
    %>

    <link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" >
    <script src="${path}/static/js/jquery-3.2.1.min.js" ></script>
    <script src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" ></script>


</head>
<body>
<!--BootStrap模态框-->
<!--emp添加   -->
<div class="modal fade" id="empAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="emps">
                    <%--Name--%>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-8">
                            <input type="text" name="empName" class="form-control" id="empName" placeholder="员工姓名">
                            <span  class="help-block"></span>
                        </div>
                    </div>
                        <%--Email--%>
                    <div class="form-group">
                        <label   class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-8">
                            <input type="text" name="email" class="form-control" id="Email" placeholder="邮箱">
                            <span  class="help-block"></span>
                        </div>
                    </div>
                        <%--性别--%>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">Gender</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio"  name="gender" id="Gender1" value="M" checked> 男
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="gender" id="Gender2" value="F"> 女
                                </label>
                            </div>
                        </div>
                        <%--部门--%>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">deptName</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="dId" id="Depts">
                                </select>
                            </div>
                        </div>

                </form>
            </div>
            <div id="cs"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save">保存</button>
            </div>
        </div>
    </div>
</div>
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
            <button class="btn btn-primary  " id="emp_add" data-toggle="modal">添加</button>
            <button class="btn btn-danger ">删除</button>
        </div>
    </div>
    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-push-12">
            <table class="table  table-hover emp_table" >
                <tbody>
                    <tr>
                        <th>ID</th>
                        <th>EmpName</th>
                        <th>Gender</th>
                        <th>Email</th>
                        <th>DeptName</th>
                        <th>Operate </th>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <!--分页-->
    <div class="row">
        <!--分页文字信息-->
        <div class="col-md-push-6" id="page_info">

        </div>
        <%--分页条信息--%>
        <div class="col-md-push-6" >
            <nav aria-label="Page navigation">
                <ul class="pagination" id="page_nav">

                </ul>
            </nav>
        </div>
    </div>
</div>


<script type="text/javascript">

    var max;//最大记录数

    //1,页面加载完 直接发送Ajax请求
    $(function () {
        //首页
        to_page(1);
    });

    function to_page(page) {
        $.ajax({
            url:"${path}/emps",
            data:"page="+page,
            type:"get",
            success:function (result) {
                console.log(result);//将后台返回的数据打印出来
                //1,解析并显示员工数据
                Build_Emps_Table(result);
                //2,解析分页信息
                Build_page_info(result);
                //3,解析并显示分页信息
                Build_Page_nav(result);

            }
        });
    }
    //表格数据
    function Build_Emps_Table(result) {
        //清空表中的数据
        $(".emp_table tbody").empty();
        var emps=result.map.info.list;
        $.each(emps,function (index,item) {
            var empId=$("<td></td>").append(item.empId);
            var empName=$("<td></td>").append(item.empName);
            var gender=$("<td></td>").append(item.gender=="M"?"男":"女");
            var email=$("<td></td>").append(item.email);
            var deptName=$("<td></td>").append(item.dept.deptName);
            var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            var  Btn=$("<td></td>").append(editBtn).append(delBtn);
            $("<tr></tr>").append(empId).append(empName).append(gender).append(email).append(deptName).append(Btn).appendTo(".emp_table tbody");
        });
    }
    //分页信息
    function Build_page_info(result) {

        $("#page_info").empty();
        $("#page_info").append("当前第"+result.map.info.pageNum+"页,总"+result.map.info.pageSize+"页,总"+result.map.info.total+"记录");
        max=result.map.info.total;

    }
    //分页条
    function Build_Page_nav(results) {
        $("#page_nav").empty();

        //首页
        var firstPage=$("<li></li>").append($("<a></a>").attr("href","#").append("首页"));

        //上一页
        var prePage=$("<li></li>").append($("<a></a>").append("&laquo;"));

        if(results.map.info.hasPreviousPage==false){
            firstPage.addClass("disabled");
            prePage.addClass("disabled");
        }else {
            firstPage.click(function () {
                to_page(1);
            });
            prePage.click(function () {
                to_page(results.map.info.pageNum-1)
            });
        }
        $("#page_nav").append(firstPage).append(prePage);
        //页码
        $.each(results.map.info.navigatepageNums,function (index,item) {
            var num=$("<li></li>").append($("<a></a>").append(item));
            if (results.map.info.pageNum==item){
                num.addClass("active");
            }
            //点击页码跳转
            num.click(function () {
                to_page(item)
            });
            $("#page_nav").append(num);
        })
        //下一页
        var nextPage=$("<li></li>").append($("<a></a>").append("&raquo;"));

        //尾页
        var lastPage=$("<li></li>").append($("<a></a>").attr("href","#").append("尾页"));
        $("#page_nav").append(nextPage).append(lastPage);
        if(results.map.info.hasNextPage==false){
            nextPage.addClass("disabled");
            lastPage.addClass("disabled");
        }else {
            nextPage.click(function () {
                to_page(results.map.info.pageNum+1)
            });
            lastPage.click(function () {
                to_page(results.map.info.pages);
            });
        }
    }

    function reset_form(ele){
        $(ele)[0].reset();//清空内容
        $(ele +" div").removeClass("has-success has-error");
        $(ele).find(".help-block").text("");

    }


    ////点击添加按钮 弹出模态框
   $("#emp_add").click(function () {
    //弹出模态框之前，发送ajax请求；查询dept显示到DeptName
       $("#empAdd select >").remove();
       reset_form("#emps");//每次打开模态框；清楚里的内容和样式
       getDepts();
       $("#empAdd").modal({
           keyboard: "static"
       })
   });
    //
    $("#empName").change(function () {
        //发送ajax请求，校验用户名是否可用
        var  empName=this.value;
        $.ajax({
            url:"${path}/checkEmpName",
            data:"empName="+empName,
            type:"Post",
            success:function (result) {
              //  console.log(result);
                if (result.code == 100){
                    show_msg("#empName", "success", "用户可用");
                } else {
                    show_msg("#empName","error",result.map.slip);
                }
            }
        });
    });
    //查询部门信息
    function getDepts(){
        $.ajax({
            url:"${path}/getDepts",
            type:"GET",
            success:function (result) {
                console.log(result);

                $.each(result.map.depts,function (index,item) {

                    var delt= $("<option></option>").append(item.deptName).attr("value",item.deptId);
                    $("#empAdd select").append(delt);
                })
            }
        });
    }

        //保存员工前 数据效应
        function emp_save_from(){
            //1,要拿到药校验的数据，使用正则表达式
            //调用前每次清空元素之前的样式
            var name= $("#empName").val();
            var regName=/(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;

            if (!regName.test(name)) {
                show_msg("#empName","error","只能输入3-6个中文字符或者字符")
                return false;
            }else {

                show_msg("#empName","success","")

            }
            //校验邮箱
            var  email=$("#Email").val();
            var regEamil=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
            if (!regEamil.test(email)){

                show_msg("#Email","error","正确书写邮箱格式");
                return false;
            }else{
                show_msg("#Email","success","");
            }
            return true;
        }
        //校验信息
        function show_msg(ele,status,msg){

        //清楚元素状态
            $(ele).parent().removeClass("has-success has-error");
            if ("success"==status) {
                $(ele).parent().addClass("has-success");
                $(ele).next("span").text(msg);

            }else if("error"==status){
                $(ele).parent().addClass("has-error");
                $(ele).next("span").text(msg);
                return false;
            }
        }

        $("#emp_save").click(function () {
            //1,模态框中填写的表单提交给服务器
            //数据校验
            if (!emp_save_from()){

                return false;
            }

            //2，发送ajax请求
            var data= $("#emps").serialize();//获取from 表单的值
            //防止中文乱码
            data=decodeURIComponent(data,true)
            $.ajax({
            url: "${path}/empSave",
            type: "post",
            data:data,
                success:function (result) {
                    //添加成功之后 1，关闭模态框 2，调到最后一页
                    if (result.code==100){
                        $("#empAdd").modal("hide");
                        to_page(max);
                    }else {
                        //失败
                        console.log(result);
                    }

                }
            });
        });


</script>

</body>
</html>
