package br.com.mysql.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.mysql.dao.DB;
import br.com.mysql.dao.DbException;

public class Program {

	public static void main(String[] args) {
		
		//DB.recuperarDados();
		
		//DB.atualizarDados();
		DB.transacoes();
	}	
}
