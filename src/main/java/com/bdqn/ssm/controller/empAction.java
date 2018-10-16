package com.bdqn.ssm.controller;

import com.bdqn.ssm.entity.Emp;
import com.bdqn.ssm.entity.Mas;
import com.bdqn.ssm.serice.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理crud请求
 * @author 贺威
 * @create 2018-10-12 8:45
 */
@Controller
public class empAction {


    @Autowired
    private EmpService empService;

    /**
     *  emp添加
     * @return
     */
    @RequestMapping(value = "/empSave", method = RequestMethod.POST)
    @ResponseBody
    public Mas saveEmp(Emp emp){

        empService.saveEmp(emp);
        return  Mas.success();
    }

    /**
     * 查询员工数据（分页查询）
     * @return
     */
  //  @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "page" ,defaultValue = "1") Integer pn, Model model){
        // 1，这不是个分页查询
        // 2， 引入PageHelper分页插件
        // 3, 在查询之前调用 ,页码 ，每页显示的条数
        // 4,  startPage 紧跟着的查询就是分页查询
        PageHelper.startPage(pn,5 );
       List<Emp> emps= empService.getAll();
        //使用Pageinfo来包装查询之后的结果 包括了详细的分页信息，和查询出的信息 ,传入连续显示的页数（5）
        PageInfo info=new PageInfo(emps,5);
        model.addAttribute("info",info);
        return  "list";
    }

    /**
     *  返回jSon数据到页面
     * @param pn
     * @param
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody//会自动的把返回对象转为Json字符串,要想正常工作要导入JackSon包
    public Mas getEmpWithJson(@RequestParam(value = "page" ,defaultValue = "1") Integer pn){
        System.out.println("进入");
        // 1，这不是个分页查询
        // 2， 引入PageHelper分页插件
        // 3, 在查询之前调用 ,页码 ，每页显示的条数
        // 4,  startPage 紧跟着的查询就是分页查询
        PageHelper.startPage(pn,5 );
        List<Emp> emps= empService.getAll();
        //使用Pageinfo来包装查询之后的结果 包括了详细的分页信息，和查询出的信息 ,传入连续显示的页数（5）
        PageInfo info=new PageInfo(emps,5);

        System.out.println("Emp:"+info);
        return Mas.success().add("info", info);
    }
}
