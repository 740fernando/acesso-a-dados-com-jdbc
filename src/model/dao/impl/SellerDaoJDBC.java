package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entitites.Department;
import model.entitites.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn= conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Retorna o Vendendor por Id
	 * 
	 * @return Seller
	 * 
	 * @author Fernando
	 */
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName"
					+ " From seller INNER JOIN department"
					+" ON seller.DepartmentId = department.Id"
					+" Where seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();//recebe o resultado da operação executeQuery()
			
			//O rs aponta para pos. 0 ( null), só na pos. 1 que é armazenado os dados
			// É chamado a operação next para verificar se há dados na posição 1.
			// Se minha consulta não retornou nenhum registro, meu rs vai dar falso, pular o if
			// e vai retorna null
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);			
				Seller seller = instantiateSeller(rs,dep);				
				return seller;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	@Override
	public List<Seller> finbByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? "
					+"Order BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Seller> seller = new ArrayList<>();
			Map<Integer,Department> map = new HashMap<>(); // Foi criado um map vazio, onde será guardado qlq departamento instaciado 
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId")); //busca um departamento com o DepartmentId
				
				if(dep == null) {
					dep = instantiateDepartment(rs); 
					map.put(rs.getInt("DepartmentId"), dep);//guarda a chava e o dep, na proxima repeticao do while, se já existir o dep dentro do map , não será instanciado
				}
				
				Seller sellerList = instantiateSeller(rs, dep);
				seller.add(sellerList);
			}
			return seller;			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		
		Department dep =new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAldd() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st= conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {						
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller= instantiateSeller(rs, dep);
				
				sellerList.add(seller);
			}
			return sellerList;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
	
			
			
					

	
