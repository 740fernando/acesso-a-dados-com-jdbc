package application;

import java.util.Date;

import model.entitites.Department;
import model.entitites.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1,"Livros");
		System.out.println(obj);
		
		Seller seller = new Seller(13,"Bob","bob@gmail.com",new Date(), 3000.0,obj);
		
		System.out.println(seller);
	}		
}
