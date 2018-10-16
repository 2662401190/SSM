package com.bdqn.ssm.test;

import com.bdqn.ssm.dao.DeptMapper;
import com.bdqn.ssm.dao.EmpMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 贺威
 * @create 2018-10-10 17:24
 * 推荐 ：Spring 的项目可以使用Spring单元测试，可以自动注入我们的组件
 * 导入Spring单元测试的包
 * @ContextConfiguration指定Spring文件的位置       暂时用不了（版本不对应 4.3.18 应用不进来）
 * 在 @RUNWith(测试类)
 * @Autowired 要使用的组件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MapperTest {

    @Autowired
    DeptMapper  deptMapper;

    @Autowired
    EmpMapper empMapper;

    //批量的sqlsession
    @Autowired
    SqlSession sqlSession;
    /**
     * 测试deptmapper
     */
    @Test
   public  void TedtCRUD(){
        //1,创建Spring Ioc容器
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        //DeptMapper deptMapper=applicationContext.getBean(DeptMapper.class);
        System.out.println("进来");
       //1 , 插入部门
        //deptMapper.insertSelective(new Dept(null, "开发部"));
        //2. 插入员工数据

       //empMapper.insertSelective(new Emp(null, "TanLe", "F", "1183564195@qq.com",4));

        // 3, 批量添加emp
//        EmpMapper mapper =sqlSession.getMapper(EmpMapper.class) ;
//        for (int i = 0; i <1000 ; i++) {
//              String uuid=  UUID.randomUUID().toString().substring(0, 5)+" "+i;
//            mapper.insertSelective(new Emp(null, uuid, "F", uuid+"@qq.com", 4));
//        }
//        System.out.println("批量完成");





    }

}
