package com.yunlianhui.neo4j.service;


import java.util.Set;

import com.yunlianhui.neo4j.entity.Employee;

/**
 * <p>
 * Copyright (c) 广东云联国骥投资管理有限公司
 * </p>
 *
 * <p>
 * Title:
 * </p>
 *
 * @author Maopz
 * @date 2018-04-16 13:54:31
 * @version V1.0
 */
public interface IEmployeeService {

	/**
	 * 保存一个会员
	 * @param employee
	 * @return
	 */
	Employee save(Employee employee);

	/**
	 * 删除一个会员
	 * @param employee
	 */
	void delete(Employee employee);

	/**
	 * 根据id查询会员,spring-data-neo4j支持方法命名约定查询方法 findBy{Employee的属性名}
	 * @param id
	 * @return
	 */
	Employee findById(Long id);
	
	/**
	 * 根据name查询会员,spring-data-neo4j支持方法命名约定查询方法 findBy{Employee的属性名}
	 * @param name
	 * @return
	 */
	Employee findByName(String name);
	
	/**
	 * 根据mid查询会员,spring-data-neo4j支持方法命名约定查询方法 findBy{Employee的属性名}
	 * @param mid
	 * @return
	 */
	Employee findByMid(String mid);

	/**
	 * 查询所有会员信息
	 * @return
	 */
	Iterable<Employee> findAll();
	
	/**
	 * 根据会员mid获取上级推荐链
	 * @param mid
	 * @return
	 */
	Set<String> getRefList(String mid);
	
}
