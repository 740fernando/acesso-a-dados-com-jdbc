package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entitites.Department;

public class Program2 {
	public static void main(String[] args) {

		System.out.println("============= Teste 1 : Department insert() =============");

		Department dep = new Department();
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		dep.setName("RH");
		depDao.insert(dep);
		System.out.println("Inserido novo dep id : " + dep.getId() + " nome : " + dep.getName());

		System.out.println("============= Teste 2 : Department finbyId() =============");
		Department depFinById = new Department();
		DepartmentDao depDaoFinById = DaoFactory.createDepartmentDao();

		depFinById = depDaoFinById.findById(3);

		System.out.println(depFinById.getId() + " " + depFinById.getName());

		System.out.println("============= Teste 3 : Department update() =============");
		Department depUpdate = new Department();
		DepartmentDao depDaoUpdate = DaoFactory.createDepartmentDao();
		depFinById.setName("Data Science");
		depDaoUpdate.update(depFinById);

		System.out.println("============= Teste 4 : Department deleteById() =============");

//		DepartmentDao depDaoDeleteById = DaoFactory.createDepartmentDao();
//		depDaoDeleteById.deleteById(16);
//		depDaoDeleteById.deleteById(17);
//		depDaoDeleteById.deleteById(18);
//		depDaoDeleteById.deleteById(19);

		System.out.println("============= Teste 4 : Department findall() =============");

		DepartmentDao departmentListDao = DaoFactory.createDepartmentDao();
		List<Department> departmentList = departmentListDao.findAldd();

		departmentList.forEach(d -> System.out.println(d.toString()));
	}
}
