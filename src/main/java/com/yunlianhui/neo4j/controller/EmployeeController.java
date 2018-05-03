package com.yunlianhui.neo4j.controller;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunlianhui.neo4j.entity.Employee;
import com.yunlianhui.neo4j.service.IEmployeeService;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: 会员关系controller</p>
 *
 * @author Maopz
 * @date 2018-04-13 15:40:17
 * @version V1.0
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {

	@Resource
	private IEmployeeService employeeService;
	
	/**
	 * 使用图形数据库的id查询
	 * @param id
	 * @return
	 */
	@GetMapping("findById")
	public Employee findById(String id) {
		Employee employee = employeeService.findById(Long.valueOf(id));
		return employee;
	}
	
	/**
	 * 使用名称查询
	 * @param name
	 * @return
	 */
	@GetMapping("findByName")
	public Employee findByName(String name) {
		Employee employee = employeeService.findByName(name);
		return employee;
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	@GetMapping("findAll")
	public Iterable<Employee> findAll() {
		return employeeService.findAll();
	}
	
	
	@PostMapping("delete")
	public String delete(@RequestBody Employee employee) {
		employeeService.delete(employee);
		return "delete success!";
	}
	
	/**
	 * 创建一个节点,如果id存在,则会更新
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)	
	public String save(@RequestBody Employee employee) {
		
		employeeService.save(employee);//保存用户信息(省略校对等)
		
		//推荐人
		Employee rEmployee;
		if(!StringUtils.isEmpty(employee.getRefId())) {
			rEmployee = employeeService.findByMid(employee.getRefId());
			if(!StringUtils.isEmpty(rEmployee)) {
				rEmployee.ref(employee);
				employeeService.save(rEmployee);//保存推荐关系				
			}else {
				return "推荐人不存在!";
			}
		}		
		return "success";
	}
	
	/**
	 * 查找上级链
	 * @param mid
	 * @return
	 */
	@GetMapping("getRefList")
	public Set<String> getRefList(String mid){
		if(null != mid) {
			return employeeService.getRefList(mid);
		}		
		return null;
	}
	
	
}
