package services;

import repositories.EmpDTO;
import repositories.EmpRepository;
import repositories.EmpRepositoryImpl;

public class EmpServiceImpl implements EmpService {
	public EmpDTO searchForEmp(int emp) {
		EmpRepository empRepo = new EmpRepositoryImpl();
		return empRepo.selectEmp(emp);
	}
}
