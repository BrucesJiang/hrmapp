package com.hrm.service.impl;


import com.hrm.dao.*;
import com.hrm.entity.*;
import com.hrm.service.HrmService;
import com.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 人事管理系统服务层接口实现类
 */
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService {
	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private DocumentDao documentDao;
	
	/*****************用户服务接口实现*************************************/
	/**
	 * HrmServiceImpl接口login方法实现
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public User login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		return userDao.findByLoginnameAndPassword(loginname, password);
	}

	/**
	 * HrmServiceImpl接口findUser方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public List<User> findUser(User user, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<User> users = userDao.findByPage(params);
		 
		return users;
	}
	
	/**
	 * HrmServiceImpl接口findUserById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	public void removeUserById(Integer id) {
		userDao.deleteById(id);
		
	}
	
	/**
	 * HrmServiceImpl接口addUser方法实现
	 * @see { HrmService }
	 * */
	public void modifyUser(User user) {
		userDao.update(user);
		
	}
	
	/**
	 * HrmServiceImpl接口modifyUser方法实现
	 * @see { HrmService }
	 * */
	public void addUser(User user) {
		userDao.save(user);
		
	}
	
	/*****************部门服务接口实现*************************************/
	@Transactional(readOnly=true)
	public List<Dept> findAllDept() {
		
		return deptDao.selectAllDept();
	}
	
	/**
	 * HrmServiceImpl接口findDept方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public List<Dept> findDept(Dept dept, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dept", dept);
		int recordCount = deptDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Dept> depts = deptDao.selectByPage(params);
		 
		return depts;
	}
	
	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	public void removeDeptById(Integer id) {
		deptDao.deleteById(id);
	}

	/**
	 * HrmServiceImpl接口addDept方法实现
	 * @see { HrmService }
	 * */
	public void addDept(Dept dept) {
		deptDao.save(dept);
		
	}
	
	/**
	 * HrmServiceImpl接口findDeptById方法实现
	 * @see { HrmService }
	 * */
	public Dept findDeptById(Integer id) {
		
		return deptDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口modifyDept方法实现
	 * @see { HrmService }
	 * */
	public void modifyDept(Dept dept) {
		deptDao.update(dept);
		
	}
	/*****************员工服务接口实现*************************************/
	/**
	 * HrmService接口findEmployee方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("employee", employee);
		
		int recordCount = employeeDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Employee> employees = employeeDao.selectByPage(params);
	    return employees;
	}
	/**
	 * HrmService接口removeEmployeeById方法实现
	 * @see { HrmService }
	 * */
	public void removeEmployeeById(Integer id) {
		employeeDao.deleteById(id);
		
	}
	/**
	 * HrmService接口findEmployeeById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public Employee findEmployeeById(Integer id) {
		
		return employeeDao.selectById(id);
	}
	
	/**
	 * HrmService接口addEmployee方法实现
	 * @see { HrmService }
	 * */
	public void addEmployee(Employee employee) {
		employeeDao.save(employee);
		
	}
	
	/**
	 * HrmService接口modifyEmployee方法实现
	 * @see { HrmService }
	 * */
	public void modifyEmployee(Employee employee) {
		employeeDao.update(employee);
	}
	
	/*****************职位接口实现*************************************/

	/**
	 * HrmService接口findAllJob方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public List<Job> findAllJob() {
		
		return jobDao.selectAllJob();
	}

	/**
	 * HrmService接口findJob方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public List<Job> findJob(Job job, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("job", job);
		int recordCount = jobDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Job> jobs = jobDao.selectByPage(params);
		 
		return jobs;
	}
	
	/**
	 * HrmService接口removeJobById方法实现
	 * @see { HrmService }
	 * */
	public void removeJobById(Integer id) {
		jobDao.deleteById(id);
		
	}
	
	/**
	 * HrmService接口addJob方法实现
	 * @see { HrmService }
	 * */
	public void addJob(Job job) {
		jobDao.save(job);
		
	}
	
	/**
	 * HrmService接口findJobById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public Job findJobById(Integer id) {
		
		return jobDao.selectById(id);
	}
	
	/**
	 * HrmService接口modifyJob方法实现
	 * @see { HrmService }
	 * */
	public void modifyJob(Job job) {
		jobDao.update(job);
		
	}
	
	/*****************公告接口实现*************************************/
	@Transactional(readOnly=true)
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("notice", notice);
		int recordCount = noticeDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Notice> notices = noticeDao.selectByPage(params);
		 
		return notices;
	}

	/**
	 * HrmService接口findNoticeById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public Notice findNoticeById(Integer id) {
		
		return noticeDao.selectById(id);
	}

	/**
	 * HrmService接口removeNoticeById方法实现
	 * @see { HrmService }
	 * */
	public void removeNoticeById(Integer id) {
		noticeDao.deleteById(id);
		
	}
	
	/**
	 * HrmService接口addNotice方法实现
	 * @see { HrmService }
	 * */
	public void addNotice(Notice notice) {
		noticeDao.save(notice);
		
	}
	
	/**
	 * HrmService接口modifyNotice方法实现
	 * @see { HrmService }
	 * */
	public void modifyNotice(Notice notice) {
		noticeDao.update(notice);
		
	}

	/*****************文件接口实现*************************************/

	/**
	 * HrmService接口findDocument方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public List<Document> findDocument(Document document, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("document", document);
		int recordCount = documentDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Document> documents = documentDao.selectByPage(params);
		 
		return documents;
	}

	/**
	 * HrmService接口addDocument方法实现
	 * @see { HrmService }
	 * */
	public void addDocument(Document document) {
		documentDao.save(document);
		
	}
	/**
	 * HrmService接口removeDocumentById方法实现
	 * @see { HrmService }
	 * */
	public void removeDocumentById(Integer id) {
		documentDao.deleteById(id);
		
	}
	/**
	 * HrmService接口modifyDocument方法实现
	 * @see { HrmService }
	 * */
	public void modifyDocument(Document document) {
		documentDao.update(document);
		
	}
	/**
	 * HrmService接口findDocumentById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public Document findDocumentById(Integer id) {
		
		return documentDao.selectById(id);
	}

	
}
