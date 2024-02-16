package ch09.mybatis;

import java.util.List;

import ch09.model.StudentDO;

public interface StudentMapper {
	
	public List<StudentDO> getAll();
	
	public void insert(StudentDO s);
	
	public void delete(String id);
}
