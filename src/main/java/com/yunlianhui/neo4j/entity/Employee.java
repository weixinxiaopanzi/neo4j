package com.yunlianhui.neo4j.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: </p>
 *
 * @author Maopz
 * @date 2018-04-13 15:28:00
 * @version V1.0
 */
@NodeEntity
public class Employee {

	/** 
     * Neo4j会分配的ID 
     */
	@GraphId
	private Long id;
	/** 
     * 属性 
     */
	private String mid;
	private String name;
	/**推荐人id*/
	private String refId;
	
	/**
	 * 关系是有方向性的,如果不定义,数据查询不到;
	 * 图形数据库中显示为:A --recommend--> this
	 */
	@Relationship(type="recommend", direction = Relationship.OUTGOING)
	private Set<Employee> employees;
	
	/**
	 * 推荐
	 * @param e 推荐人实体
	 */
	public void ref(Employee e) {
		if(null == employees) {
			employees = new HashSet<>();
		}
		employees.add(e);
	}
	
	public Employee() {
	}
	
	public Employee(String mid, String name, String refId) {
		super();
		this.mid = mid;
		this.name = name;
		this.refId = refId;
	}

	public Long getId() {
		return id;
	}	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}	
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}
