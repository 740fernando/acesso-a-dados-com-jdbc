package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entitites.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn= conn;
	}
	
	@Override
	public void insert(Department department) {
		
		PreparedStatement st = null;
		StringBuilder query = new StringBuilder();
		
		try {

			query.append("INSERT INTO department ");
			query.append("(Name) ");
			query.append("VALUES ");
			query.append("(?)");

			st = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);

			st.setString(1, department.getName());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado, nenhuma linha foi afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}


	@Override
	public void update(Department department) {
		
		PreparedStatement st = null;
		StringBuilder query = new StringBuilder();
		
		try {
			
			query.append("UPDATE department SET ");
			query.append("(Name = ?)");
			query.append("WHERE Id = ?");
			
			st = conn.prepareStatement(query.toString());
		
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAldd() {
		// TODO Auto-generated method stub
		return null;
	}

}