package com.bdqn.ssm.test;

import com.bdqn.ssm.entity.Emp;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


/**
 *
 * 使用Spring测试模块提供  的测试请求功能，测试crud的准确性
 * @author 贺威
 * @create 2018-10-12 11:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {

    //传入SpringMVC的ioc 容器
    @Autowired//要配合WebAppConfiguration注解使用
    WebApplicationContext webApplicationContext;
    //虚拟mvc请求，获取到请求的结果
    MockMvc mockMvc;

    @Before
    public  void initMockmvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public  void TestPage() throws Exception {
        //模拟请求拿到返回值

       MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("page", "1")).andReturn();

        //请求成功之后；请求域中 会有pageInfo；我们去除pageInfo进行验证
        MockHttpServletRequest request= result.getRequest();
        PageInfo info = (PageInfo) request.getAttribute("info");

        System.out.println("当前页码"+info.getPageNum());
        System.out.println("总页码"+info.getPages());
        System.out.println("总记录条数"+info.getTotal());
        System.out.println("在页面上需要连续显示的页码");

        int[] i = info.getNavigatepageNums();
        for (int i1 : i) {
            System.out.print(i);
        }
        //获取员工数据
         List<Emp> emps=info.getList();
        for (Emp emp : emps) {
            System.out.println(emp.getEmpId()+"" +emp.getEmpName());
        }



    }
}
