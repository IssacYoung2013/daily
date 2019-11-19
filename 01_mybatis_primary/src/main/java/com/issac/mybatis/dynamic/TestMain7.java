package com.issac.mybatis.dynamic;

import com.issac.mybatis.beans.Dept;
import com.issac.mybatis.beans.Employee;
import com.issac.mybatis.mapper.DeptMapper;
import com.issac.mybatis.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ywy
 * @date: 2019-10-25
 * @desc:
 */
public class TestMain7 {
    static DeptMapper mapper;
    static EmpMapper empMapper;
    static SqlSession session;

    public static void main(String[] args) throws Exception {

        InputStream is = Resources.getResourceAsStream("myBatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
        mapper = session.getMapper(DeptMapper.class);
        empMapper = session.getMapper(EmpMapper.class);
        test10();
        session.close();
    }

    static void test1() {
        Dept dept = new Dept();
        dept.setDeptNo(20);
        dept.setDname("SE");
        List<Dept> deptList = mapper.findDept2(dept);
        System.out.println(deptList);
    }

    static void test2() {
        Employee employee = new Employee();
        employee.setSal(4000.0);
        List<Employee> employees = empMapper.findEmp2(employee);
        System.out.println(employees);
    }

    static void test3() {
        Dept dept = new Dept();
        dept.setDeptNo(20);
        dept.setDname("SE");
        List<Dept> deptList = mapper.findDept3(dept);
        System.out.println(deptList);
    }

    static void test4() {
        Dept dept = new Dept();
        List<Dept> deptList = mapper.findDept3(dept);
        System.out.println(deptList);
    }

    static void test5() {
        Dept dept = new Dept();
        dept.setDeptNo(20);
        dept.setDname("RESEARCH_1");
        dept.setLoc("DALLAS_1");
        mapper.updateDept(dept);
        List<Dept> deptList = mapper.findDept3(dept);
        System.out.println(deptList);
    }

    static void test6() {
        Dept dept = new Dept();
        dept.setDname("RE");
        dept.setLoc("DALLAS");
        List<Dept> deptList = mapper.findDept4(dept);
        System.out.println(deptList);
    }

    static void test7() {
        Dept dept = new Dept();
        dept.setDname("开发一部");
        dept.setLoc("BEIJING");
        Dept dept2 = new Dept();
        dept2.setDname("开发二部");
        dept2.setLoc("BEIJING");
        mapper.saveDept(Arrays.asList(dept, dept2));
        session.commit();
        List<Dept> deptList = mapper.findDept3(dept);
        System.out.println(deptList);
    }

    static void test8() {
        List<Dept> deptList = mapper.findDeptByList(Arrays.asList(20, 30));
        System.out.println(deptList);
    }

    static void test9() {
        List<Dept> deptList = mapper.findDeptByArray(new int[]{20, 30});
        System.out.println(deptList);
    }

    static void test10() {
        Map map = new HashMap();
        map.put("key1",20);
        map.put("key2",30);
        List<Dept> deptList = mapper.findDeptByMap(map);
        System.out.println(deptList);
    }
}
