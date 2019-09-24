package com.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.finalproject.entity.FinalProjectEntity;

public class FinalProjectDAO implements FinalProjectDAOInterface {

	public static FinalProjectDAOInterface createObjectDAO(String string) {
		// TODO Auto-generated method stub
		return new FinalProjectDAO();
	}

	@Override
	public int createProfileDAO(FinalProjectEntity fpe) {
		int i=0;
		Connection con = null;
		try {
			
			Context inCtx = new InitialContext();
			DataSource ds = (DataSource) inCtx.lookup("java:/final");
			con = ds.getConnection();
			
			PreparedStatement ps = con.prepareStatement("insert into finalproject values(?,?,?)");
			ps.setString(1, fpe.getName());
			ps.setString(2, fpe.getEmail());
			ps.setString(3, fpe.getPass());
			
			i = ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		return i;
		
		
	}

	@Override
	public int createloginProfileDAO(FinalProjectEntity fpe) {
//		int i=0;
		Connection con = null;
		ResultSet rs = null;
		try {
			
			Context inCtx = new InitialContext();
			DataSource ds = (DataSource) inCtx.lookup("java:/final");
			con = ds.getConnection();
			
			PreparedStatement ps = con.prepareStatement("select email,pass from finalproject where email=? and pass=?");
			ps.setString(1, fpe.getEmail());
			ps.setString(2, fpe.getPass());
			
			rs = ps.executeQuery();
			System.out.println("dsfsdfsdfsdf : "+rs);
			if (rs.next()) {
                return 1;
            }
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		return 0;
	}

}
