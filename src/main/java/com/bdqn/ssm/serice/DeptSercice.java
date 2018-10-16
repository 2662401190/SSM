package com.bdqn.ssm.serice;

import com.bdqn.ssm.dao.DeptMapper;
import com.bdqn.ssm.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 贺威
 * @create 2018-10-16 10:37
 */
@Service
public class DeptSercice {

    @Autowired
    DeptMapper deptMapper;

    public List<Dept> getDepts(){

        return deptMapper.selectByExample(null);
    }
}
