package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

// Instancia os Daos
/**
 * createSellerDao - return @ SellerDaoJDBC()
 * 
 * 
 * Dessa forma, a classe DaoFactory vai expor o tipo da interface, 
 * mas internamente vai instanciar a implementacao.
 * � uma forma de fazer a inje��o de depedencia, sem explicitar a implementa��o.
 * 
 * 
 * @author Fernando
 *
 */

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
}
