package br.com.mysql.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn=null; // conex�o
	private static Statement st = null; //consulta
	private static ResultSet rs = null; // recebe o resultado
	
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
	public static void atualizarDados() {
		
		PreparedStatement st =null;
		
		try {
			conn= DB.getConnection(); //conecta o banco de dados

			st = conn.prepareStatement("UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+"WHERE "
					+"(DepartmentId= ?)");
			
			st.setDouble(1, 200.0);
			st.setInt(2,2);
			
			int rowsAffected = st.executeUpdate(); // Qtd de linhas que foram afetadas.
			
			System.out.println("Done! Rows affected : "+rowsAffected);
			
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			closeStatement(st);
			closeResultSet(rs);
			closeConnection();
		}	
	}
	public static void recuperarDados() {

		try {
			conn= DB.getConnection(); //conecta o banco de dados

			st = conn.createStatement(); //instanciei um objeto Statement

			rs = st.executeQuery("select * from department");

			while(rs.next()) {
				System.out.println(rs.getInt("id")+", "+rs.getString("Name"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			closeStatement(st);
			closeResultSet(rs);
			closeConnection();
		}	
	}
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet (ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
