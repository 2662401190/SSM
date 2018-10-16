package com.bdqn.ssm.serice;

import com.bdqn.ssm.dao.EmpMapper;
import com.bdqn.ssm.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 贺威
 * @create 2018-10-12 8:53
 */
@Service
public class EmpService {

    @Autowired
    private  EmpMapper empMapper;

    /**
     * 查询所有员工
     * @return
     */
    public List<Emp> getAll() {

      List<Emp> emps=empMapper.selectByExampleWithDept(null);
      return emps;
    }

    /**
     * 员工添加
     * @param emp
     */
    public int saveEmp(Emp emp) {
       return empMapper.insertSelective(emp);
    }
}
