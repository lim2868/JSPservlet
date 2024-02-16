package ch13.services;

import ch13.repositories.EmpDTO;
import ch13.repositories.EmpRepository;
import ch13.repositories.EmpRepositoryImpl;

public class EmpServiceImpl implements EmpService {
	
	@Override
	public EmpDTO searchForEmp(int emp) {
		EmpRepository empRepo = new EmpRepositoryImpl();
		return empRepo.selectEmp(emp);
	}
}
