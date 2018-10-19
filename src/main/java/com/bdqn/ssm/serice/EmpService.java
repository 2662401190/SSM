package com.bdqn.ssm.serice;

import com.bdqn.ssm.dao.EmpMapper;
import com.bdqn.ssm.entity.Emp;
import com.bdqn.ssm.entity.EmpExample;
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

    /**
     * 检查用户是否可用
     * @param empName
     * @return
     *  返回 true 可用  false不可用
     */
    public Boolean checkEmpName(String empName) {
        EmpExample emp=new EmpExample();
        EmpExample.Criteria criteria=emp.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        Long aLong = empMapper.countByExample(emp);
        return aLong==0;
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    public Emp getEmpId(Integer id) {

      return empMapper.selectByPrimaryKey(id);

    }

    public void delectEmp(Integer id) {
        empMapper.deleteByPrimaryKey(id);
    }

    public void updateEmp(Emp emp) {
        empMapper.updateByPrimaryKeySelective(emp);
    }
}
