package br.com.mysql.application;

import java.sql.Connection;

import br.com.mysql.dao.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn= DB.getConnection();
		DB.closeConnection();
	}

}
