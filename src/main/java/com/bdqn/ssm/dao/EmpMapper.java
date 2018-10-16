package com.bdqn.ssm.dao;

import com.bdqn.ssm.entity.Emp;
import com.bdqn.ssm.entity.EmpExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    long countByExample(EmpExample example);

    int deleteByExample(EmpExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Emp record);

    int insertSelective(Emp record);

    List<Emp> selectByExample(EmpExample example);

    Emp selectByPrimaryKey(Integer empId);

    List<Emp> selectByExampleWithDept(EmpExample example);

    Emp selectByPrimaryKeyWirhDept(Integer empId);

    int updateByExampleSelective(@Param("record") Emp record, @Param("example") EmpExample example);

    int updateByExample(@Param("record") Emp record, @Param("example") EmpExample example);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);
}