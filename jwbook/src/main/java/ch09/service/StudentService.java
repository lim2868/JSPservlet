package ch09.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import ch09.model.StudentDAO;
import ch09.model.StudentDO;

public class StudentService {
	
	private StudentDAO dao;
	
	public StudentService() {
		dao = new StudentDAO();
	}
	public List<StudentDO> getAll(){
		return dao.getAll();
	}
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		StudentDO s = new StudentDO();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		dao.insert(s);
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		StudentDO s = new StudentDO();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		dao.delete(s);
	}
}