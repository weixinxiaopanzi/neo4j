<pre>
Neo4j使用说明

1.环境准备: 依赖Java的JVM虚拟机
java -version

将解压后的文件重命名放置任意盘符下:
如:D:\ neo4j-community-3.4.0

系统环境变量配置 
NEO4J_HOME = D:\\neo4j-community-3.4.0
Path = %NEO4J_HOME%\bin;

启动:
CMD管理员身份运行:
neo4j.bat console
访问:
http://localhost:7474 默认跳转到 http://localhost:7474/browser
默认用户：neo4j
默认密码：neo4j
(参考: https://www.cnblogs.com/ljhdo/archive/2017/05/19/5521577.html) 

Maven: (配置)
	 &lt;parent&gt;
		&lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
		&lt;artifactId&gt;spring-boot-starter-parent&lt;/artifactId&gt;
		&lt;version&gt;1.4.2.RELEASE&lt;/version&gt;
	&lt;/parent&gt;

配置: 
	spring.data.neo4j.username=neo4j 数据库账号
	spring.data.neo4j.password=neo4j 数据库密码
	spring.data.neo4j.uri=http://localhost:7474 数据库uri地址
	
2.启动springBoot项目 
保存: 

url: employee/save 

data: { 
	"name":"top",	
	"mid":"ylh001"
} 
{
	"name":"a1",
	"mid":"ylh011",
	"refId":" ylh001"
}
{ 
	"name":"aa",
	"mid":"ylh012",
	"refId":" ylh011"
} 
{ 
	"name":"a3", 
	"mid":"ylh013", 
	"refId":" ylh012" 
} 
… 

查询上级链: 

Employee/getRefList?mid=ylh013

[ 
	"ylh012", 
	"ylh011", 
	"ylh001" 
] 


Java api使用方法: 

实体类: 

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
* 关系是有方向性的 
* 图形数据库中显示为:A --recommend--&gt; this 
*/ 
@Relationship(type="recommend", direction = Relationship.OUTGOING) 
private Set&lt;Employee&gt; employees; 

/** 
* 推荐 
* @param e 推荐人实体 
*/ 
public void ref(Employee e) { 
	if(null == employees) { 
		employees = new HashSet&lt;&gt;(); 
	} 
	employees.add(e); 
} 

//getter and setter 


其中: @NodeEntity 表示是一个节点; 
	@GraphId 表示数据库id,自动分配,被删除后会被重新分配; 
	@Relationship(type="recommend", direction = Relationship.OUTGOING) 表示一个关系,有方向性; 

各种字段表示节点的属性. 


@Repository 
public interface EmployeeRepository extends GraphRepository&lt;Employee&gt;{ 
	/** 
	* 实现自己的接口,通过名称查询 
	* spring-data-neo4j支持方法命名约定查询方法 findBy{Employee的属性名} 
	* @param name 
	* @return 
	*/ 
	Employee findByName(String name); 
	Employee findByMid(String mid); 
} 

其中: 定义一个接口继承GraphRepository&lt;T&gt; 

即可使用neo4j的api. 此接口相当于mybatis的mapper.java文件, 但是有一个@Repository注解. 

findByXxx spring-data-neo4j支持方法命名约定查询方法 findBy{Employee的属性名} 
比如实体类有name属性, 这里有findByName, findByMid方法. 其它service层,controller层基本不变. 

其它学习: https://www.w3cschool.cn/neo4j/neo4j-5anu1xf7.html 
</pre>