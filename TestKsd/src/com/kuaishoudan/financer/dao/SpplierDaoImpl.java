package com.kuaishoudan.financer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaishoudan.financer.bean.Finance;
import com.kuaishoudan.financer.bean.FinanceAdvence;
import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.Supplier;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class SpplierDaoImpl {

	public static void main(String[] args) {

		KSDCase ksd = RandomValue.getRandom();
		;
		List<Supplier>fffs=getSuplier(ksd);
		for(int i=0;i<fffs.size();i++){
			System.out.println(fffs.get(i).getId()+","+fffs.get(i).getName());
		}
	}
	public static String  mgccCaptcha( String phone) {
		String cap= "";
		List<Supplier> list  = new ArrayList<Supplier>();
		String sql = " select token  from mgc_test.tb_token where phone =? and status=1   order by id desc  LIMIT 1";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
 
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			 
				pstmt.setString(1, phone);
		
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				cap=rs.getString(1);
			 
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn );
		}
		return cap;

	 
	}
	public static int  mgccStatus( String phone) {
		int  status=0;
		List<Supplier> list  = new ArrayList<Supplier>();
		String sql = " select status  from mgc_test.tb_user where phone =?  order by id desc  LIMIT 1";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
 
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			 
				pstmt.setString(1, phone);
		
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				status=rs.getInt(1);
			 
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn );
		}
		return  status;

	 
	}
	public static List<Supplier> getSuplier(KSDCase ksd) {
		String ff = "";
		List<Supplier> list  = new ArrayList<Supplier>();
		String sql = " SELECT ts.id,ts.`name`,ts.supplier_code FROM tb_supplier ts ,tb_employee e WHERE FIND_IN_SET(e.id, ts.follow_people) and ts.flag=3 and  e.`name`=? and e.position_desc like '%测试%' order by ts.start_with ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
 
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			 
				pstmt.setString(1, ksd.getLoginname());
		
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
			 
				Supplier sl=new Supplier();
				sl.setId(rs.getInt(1));
				sl.setName( rs.getString(2));
				sl.setSupplier_code(rs.getString(3));
				list.add(sl);
				// System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn );
		}

		return list;
	}
	public static void mgccUser(Supplier sl,String phone) {
		String ff = "";
		List<Supplier> list  = new ArrayList<Supplier>();
		String sql = " update mgc_test.tb_user t set t.supplier_id=? ,t.supplier_name=? where t.phone =? ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
 
		try {
			PreparedStatement stmt=	conn.prepareStatement(sql);
 
			  stmt .setInt(1, sl.getId());
			 stmt .setString(2, sl.getName());
			 stmt.setString(3, phone);
				 stmt.executeUpdate();//执行sql语句
 

		
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn );
		}

	 
	}

}