package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entitites.Department;
import model.entitites.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
			
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
		
		System.out.println("============= Teste 4 : seller insert() =============");
		
		Seller sellerInsert = new Seller(null, "Fernando", "fernando@teste.com", new Date(), 5000.0,new Department(1,null));
		sellerDao.insert(sellerInsert);
		System.out.println("Inserted! New id = "+sellerInsert.getId());
		
		System.out.println("============= Teste 5 : seller update() =============");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Seller sellerUpdate = new Seller();
		sellerUpdate = sellerDao.findById(1);
		sellerUpdate.setName("Lauren");
		sellerUpdate.setBirthDate(sdf.parse("16/06/2017"));
		sellerDao.update(sellerUpdate);
	}		
}
