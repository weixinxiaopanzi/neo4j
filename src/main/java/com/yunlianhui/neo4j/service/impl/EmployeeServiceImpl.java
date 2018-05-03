package com.yunlianhui.neo4j.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yunlianhui.neo4j.entity.Employee;
import com.yunlianhui.neo4j.mapper.EmployeeRepository;
import com.yunlianhui.neo4j.service.IEmployeeService;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: </p>
 *
 * @author Maopz
 * @date 2018-04-16 13:58:33
 * @version V1.0
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Resource
	private EmployeeRepository employeeRepository;
	
	/** (non-Javadoc)
	 * @see com.yunlianhui.neo4j.service.IEmployeeService#save(com.yunlianhui.neo4j.entity.Employee)
	 */
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	/** (non-Javadoc)
	 * @see com.yunlianhui.neo4j.service.IEmployeeService#delete(com.yunlianhui.neo4j.entity.Employee)
	 */
	@Override
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}

	/** (non-Javadoc)
	 * @see com.yunlianhui.neo4j.service.IEmployeeService#findById(long)
	 */
	@Override
	public Employee findById(Long id) {
		return employeeRepository.findOne(id);
	}

	/** (non-Javadoc)
	 * @see com.yunlianhui.neo4j.service.IEmployeeService#findByName(java.lang.String)
	 */
	@Override
	public Employee findByName(String name) {
		return employeeRepository.findByName(name);
	}
	
	/** (non-Javadoc)
	 * @see com.yunlianhui.neo4j.service.IEmployeeService#findAll()
	 */
	@Override
	public Iterable<Employee> findAll() {
		Iterable<Employee> iterable = employeeRepository.findAll();
		return iterable;
	}

	/* (non-Javadoc)
	 * @see com.yunlianhui.neo4j.service.IEmployeeService#findByRefId(java.lang.String)
	 */
	@Override
	public Employee findByMid(String mid) {
		return employeeRepository.findByMid(mid);
	}

	/** (non-Javadoc)
	 * @see com.yunlianhui.neo4j.service.IEmployeeService#getRefList(java.lang.String)
	 */
	@Override
	public Set<String> getRefList(String mid) {
		Employee employee = employeeRepository.findByMid(mid);
		if(null != employee) {
			String refId = employee.getRefId();
			//上级链(使用LinkedHashSet避免重复,还保存了顺序)
			Set<String> sharePathList = new LinkedHashSet<String>();
			//添加当前会员的推荐人
			sharePathList.add(refId);
			return getSharePathList(refId,sharePathList);			
		}
		return null;
	}

	/**
	 * @param refId
	 * @param sharePathList
	 * @return
	 */
	private Set<String> getSharePathList(String refId, Set<String> sharePathList) {
		if(StringUtils.isEmpty(refId)) {
			return sharePathList;
		}
		Employee employee = employeeRepository.findByMid(refId);
		if(null != employee && !StringUtils.isEmpty(employee.getRefId())) {
			//避免环
			if(sharePathList.contains(employee.getRefId())) {
				return sharePathList;
			}
			sharePathList.add(employee.getRefId());
			refId = employee.getRefId();
		}else {
			refId = null;
		}
		getSharePathList(refId, sharePathList);
		
		return sharePathList;
	}

}
