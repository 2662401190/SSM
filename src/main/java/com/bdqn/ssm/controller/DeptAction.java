package com.bdqn.ssm.controller;

import com.bdqn.ssm.entity.Dept;
import com.bdqn.ssm.entity.Mas;
import com.bdqn.ssm.serice.DeptSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 贺威
 * @create 2018-10-16 10:35
 */
@Controller
public class DeptAction {

    @Autowired
    DeptSercice deptSercice;

    @RequestMapping("/getDepts")
    @ResponseBody
    public Mas getDepts(){
        List<Dept> depts = deptSercice.getDepts();

        return Mas.success().add("depts", depts);
    }
}
