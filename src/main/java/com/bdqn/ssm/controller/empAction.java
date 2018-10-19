package com.bdqn.ssm.controller;

import com.bdqn.ssm.entity.Emp;
import com.bdqn.ssm.entity.Mas;
import com.bdqn.ssm.serice.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理crud请求
 * @author 贺威
 * @create 2018-10-12 8:45
 */
@Controller
public class empAction {


    @Autowired
    private EmpService empService;


    @RequestMapping(value = "/getEmp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Mas getEmp(@PathVariable("id") Integer id){

        Emp emp= empService.getEmpId(id);

        return Mas.success().add("emp", emp);
    }

    /**
     * 检查用户名是否可用
     * @return
     */
    @RequestMapping("/checkEmpName")
    @ResponseBody
    public Mas checkEmpName(@RequestParam String empName){
        Boolean aBoolean = empService.checkEmpName(empName);
        String name="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        //String email="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        if (!empName.matches(name)){
            return Mas.fail().add("slip", "用户名只能是6——12的英文或者2-6的中文字母组合");
        }

        if (aBoolean){
            return Mas.success();
        }else {
            return  Mas.fail().add("slip", "用户名不可用");
        }
    }
    /**
     *  emp添加
     *
     *  支持JsR303校验 导入Hibernate validator
     *
     *
     * @return
     */
    @RequestMapping( value = "/empSave", method = RequestMethod.POST)
    @ResponseBody
    public Mas saveEmp(@Valid Emp emp, BindingResult result){//Valid 代表 封装的属性要校验

        if (result.hasErrors()) {
            //校验失败；在模态框框中打印错误信息
            List<FieldError> errors=result.getFieldErrors();//错误信息
            Map<String,Object> map=new HashMap<>();
            for (FieldError error : errors) {
                System.out.println("错误字段"+error.getField());//错误字段
                System.out.println("错误信息" + error.getDefaultMessage());
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Mas.fail().add("errorField", map);
        }else {
            empService.saveEmp(emp);
            return  Mas.success();
        }


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
