package br.com.mysql.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection conn=null;
	
	
	// m�todo respons�vel por conectar com o banco de dados . Conectar no banco de dados em jdbc � instanciar um objeto do tipo "Connection"
	public static Connection getConnection() {
		if(conn==null) {
			try {
				Properties props= loadProperties();
				String url= props.getProperty("dburl");
				conn = DriverManager.getConnection(url,props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}		
		}
		return conn;
	}
	
	//fecha a conex�o
	public static void closeConnection() {
		if(conn!=null) {
			try {
				conn.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//m�todo est�tico auxiliar -- carrega as propriedades do banco de dados retornando esses dados no objeto props
	private static Properties loadProperties() {
		
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props= new Properties();
			props.load(fs);// load- faz a leitura do arquivo properties apontato pelo Input Stream fs e guardar os dados dentro do objeto props 
			return props;
		}
		catch(IOException e) {
			throw new DbException(e.getMessage()); // exce�o personalizada
		}
	}
}
