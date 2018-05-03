package com.yunlianhui.neo4j.mapper;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.yunlianhui.neo4j.entity.Employee;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: </p>
 *
 * @author Maopz
 * @date 2018-04-13 15:31:48
 * @version V1.0
 */
@Repository
public interface EmployeeRepository extends GraphRepository<Employee>{

	/**
	 * 实现自己的接口,通过名称查询
	 * spring-data-neo4j支持方法命名约定查询方法 findBy{Employee的属性名}
	 * @param name
	 * @return
	 */
	Employee findByName(String name);
	
	Employee findByMid(String mid);
}
