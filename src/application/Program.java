package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import model.entitites.Department;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1,"Livros");
		System.out.println(obj);
		
	}	
}
