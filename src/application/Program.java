package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entitites.Department;
import model.entitites.Seller;

public class Program {

	public static void main(String[] args) {
			
		SellerDao sellerDao = DaoFactory.createSellerDao();
		SellerDao sellerListfindAlddDao = DaoFactory.createSellerDao();
		System.out.println("============= Teste 1 : seller findById =============");
			
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);
		
		System.out.println("============= Teste 2 : seller findByDepartment =============");
		//Department department = new Department(2,null);

		List<Seller> sellerList = sellerDao.finbByDepartment(new Department(2,null));
		
		sellerList.forEach(list -> System.out.println(list.toString()));
		
		System.out.println("============= Teste 3 : seller findAldd() =============");
		
		List<Seller> sellerListfindAldd = sellerListfindAlddDao.findAldd();

		sellerListfindAldd.forEach(e -> System.out.println(e.toString()));
	}		
}
