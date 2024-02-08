package ch09.oracle.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import ch09.oracle.model.StudentDAO_oracle;
import ch09.oracle.model.StudentDO_oracle;

public class StudentService_oracle {
	
private StudentDAO_oracle dao;
	
	public StudentService_oracle() {
		dao = new StudentDAO_oracle();
	}
	public List<StudentDO_oracle> getAll(){
		return dao.getAll();
	}
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		StudentDO_oracle s = new StudentDO_oracle();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		dao.insert(s);
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		StudentDO_oracle s = new StudentDO_oracle();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		dao.delete(s);
	}
}