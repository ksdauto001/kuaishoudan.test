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

import com.kuaishoudan.financer.bean.Employee;
import com.kuaishoudan.financer.bean.Finance;
import com.kuaishoudan.financer.bean.FinanceAdvence;
import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class UserDaoImpl {

	public static void main(String[] args) {

		/*
		 * Finance f=UserDaoImpl.getFinance("71238");
		 * System.out.println(f.getFinanceid
		 * ()+f.getUsername()+f.getBrandid()+f.getRate()+f.getProductname());
		 */
		// FinanceAdvence f=UserDaoImpl.getAdvence("1062474");
		// System.out.println(f.getFinanceid()+f.getUsername());
		/*
		 * int a=gethave_system("易鑫融资租赁"); System.out.println(a);
		 */
		/*
		 * KSDCase ksd=RandomValue.getRandom();; ksd.setIdentitytype(1);
		 * ksd.setIdentitynum("620921195909158870"); Map<String,String> map=
		 * getCustomer(ksd);
		 * 
		 * System.out.println(map.get("phone")+" "+map.get("address")+map.get(
		 * "status"));
		 */

		/*
		 * KSDCase ksd = getCustomer_KSD("弓心"); System.out.println("@#$" +
		 * ksd.getIdentitytype());
		 */
		/*
		 * List<Integer> list1=new ArrayList<Integer>();
		 * list1.add(100);list1.add(103); int aa=getImgType(0+1,list1);
		 * System.out.println(aa);
		 */
		KSDCase ksd = RandomValue.getRandom();
		;
		ksd.setIdentitynum("653226200903048932");
		ksd.setIdentitytype(1);
		ksd.setCartype(0);
		// getLoanname(ksd);
		ksd.setProduct("平安银行-简易贷及常规产品一区");// 浩天国际
		ksd.setZx(1);
		// int aa=getRisk_type(ksd);
		/*
		 * List<Integer> ssf=getOMaterial2(ksd,1); for(int
		 * i=0;i<ssf.size();i++){ System.out.println("!!"+ssf.get(i)); }
		 * 
		 * System.out.println("@@@" +ssf.size());
		 */
		
/*		 List<Employee> list= getSpNameid(ksd,1); for(int
		  i=0;i<list.size();i++){
		  System.out.println(list.get(i).getUsername()+","
		  +list.get(i).getAccount()+list.get(i).getDesc()); }*/
		 
		//int faf = getUser_Count(ksd);
		List<Employee> fsds=getSpZxName(ksd);
		for(int i=0;i<fsds.size();i++)
		System.out.println(fsds.get(i).getAccount());
/*	int asd=	UserDaoImpl.getAdvanceStatue_id(ksd);
	System.out.println(asd+"asd");*/
	}

	public static KSDCase getCustomer_KSD(String name) {

		KSDCase ksd = null;
		String sql = "select * from tb_customer where name=? order by id desc limit 1 ; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ksd = new KSDCase();

				ksd.setUsername(rs.getString("name"));
				ksd.setPhone(rs.getString("phone"));
				ksd.setAddress("address2");
				int idtype = rs.getInt("id_type");
				ksd.setIdentitytype(idtype);
				if (idtype == 1) {
					ksd.setIdentitynum(rs.getString("id_num"));// 身份证.
				} else {
					ksd.setJgid(rs.getString("id_num"));// 军官证
				}

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return ksd;
	}

	public static int getUser_Count(KSDCase ksd) {

		int count = 0;
		String sql = "select count(id) from tb_finance where id_num=?; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {

				count = rs.getInt(1);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return count;
	}

	public static FinanceAdvence getAdvence(String finance_id) {

		FinanceAdvence f = null;
		String sql = " select *from tb_finance_advance where  finance_id= ?  order by id desc; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, finance_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				f = new FinanceAdvence();
				f.setPledgecity(rs.getString("pledge_city"));
				f.setPledgecityid(rs.getInt("pledge_city_id"));
				f.setFinanceid(rs.getInt("finance_id"));
				f.setUsername(rs.getString("user_name"));
				f.setTotalcharge(rs.getDouble("total_charge"));

				f.setSuppliername(rs.getString("supplier_name"));
				f.setPayname(rs.getString("pay_name"));
				f.setPayaccount(rs.getString("pay_account"));
				f.setPayopenbank(rs.getString("pay_open_bank"));
				f.setPurchasetax(rs.getDouble("purchase_tax"));
				f.setGpscharge(rs.getDouble("gps_charge"));
				f.setInsurance(rs.getDouble("insurance"));
				f.setServicecharge(rs.getDouble("service_charge"));

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return f;
	}

	public static int gethave_system(String organization_name) {
		int have_system = 1;

		String sql = " select min( have_system) from tb_organization where name =? and company_id=1000 ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, organization_name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				have_system = rs.getInt(1);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return have_system;
	}

	public static Map<String, String> getCustomer(KSDCase ksd) {

		String sql = " select * from tb_customer where id_num=?; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Map<String, String> map = new HashMap<String, String>();
		try {

			pstmt = conn.prepareStatement(sql);
			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				map.put("name", rs.getString("name"));
				map.put("status", rs.getString("status"));
				map.put("phone", rs.getString("phone"));
				map.put("id_type", rs.getString("id_type"));
				map.put("address", rs.getString("address"));
				// System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}

		return map;
	}

	public static Map<String, String> getFinance(KSDCase ksd) {

		DecimalFormat decimalFormat = new DecimalFormat(
				"###################.###########");
		String sql = " select * from tb_finance where customer_id=(select id from tb_customer where id_num=?) order by id desc  limit 1 ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Map<String, String> map = new HashMap<String, String>();
		try {

			pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				map.put("name", rs.getString("user_name"));
				map.put("status", rs.getString("status"));
				map.put("phone", rs.getString("phone"));
				map.put("car_type", rs.getString("car_type"));
				map.put("brand_name", rs.getString("brand_name"));
				map.put("series_name", rs.getString("series_name"));
				map.put("product_name", rs.getString("product_name"));
				map.put("car_price", "" + rs.getDouble("car_price"));// decimalFormat.format(rs.getDouble("car_price"))
				// System.out.println("car_price" + rs.getString("car_price"));
				// System.out.println("car_price" + rs.getDouble("car_price"));
				map.put("loan_amount", "" + rs.getDouble("loan_amount"));
				map.put("pay_periods", rs.getString("pay_periods"));
				map.put("supplier_name", rs.getString("supplier_name"));
				map.put("remark", rs.getString("remark"));
				map.put("loan_type", rs.getString("loan_type"));
				if (ksd.getQygr() == 2) {
					map.put("business_name", rs.getString("business_name"));
					map.put("business_license",
							rs.getString("business_license"));
				}

				map.put("rate", rs.getString("rate"));
				// map.put("vin", rs.getString("vin"));
				map.put("purchase_tax", decimalFormat.format(Double
						.parseDouble(rs.getString("purchase_tax"))));
				map.put("gps_charge", decimalFormat.format(Double
						.parseDouble(rs.getString("gps_charge"))));
				map.put("insurance", decimalFormat.format(Double.parseDouble(rs
						.getString("insurance"))));
				// System.out.println("===" + rs.getDouble("insurance"));
				// System.out.println("===" + "" + rs.getString("insurance"));
				/*
				 * System.out.println("===ddd" +
				 * decimalFormat.format(Double.parseDouble(rs
				 * .getString("service_charge"))));
				 */
				map.put("service_charge", decimalFormat.format(Double
						.parseDouble(rs.getString("service_charge"))));
				// System.out.println(rs.getString("name")+rs.getString("status")+rs.getString("phone")+rs.getString("id_type")+rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}

		return map;
	}

	public static Map<String, String> getAdvance(KSDCase ksd) {

		DecimalFormat decimalFormat = new DecimalFormat(
				"###################.###########");
		String sql = " select * from tb_finance_advance where finance_id=(select id from tb_finance where customer_id=(select id from tb_customer where id_num=?)order by id desc  limit 1)  ; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Map<String, String> map = new HashMap<String, String>();
		try {

			pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				map.put("vin", rs.getString("vin"));
				map.put("name", rs.getString("user_name"));
				map.put("car_type", rs.getString("car_type"));
				map.put("series_name", rs.getString("series_name"));
				map.put("product_name", rs.getString("product_name"));
				map.put("supplier_name", rs.getString("supplier_name"));
				map.put("vin", rs.getString("vin"));

				map.put("purchase_tax", decimalFormat.format(Double
						.parseDouble(rs.getString("purchase_tax"))));
 
				map.put("insurance", decimalFormat.format(Double.parseDouble(rs
						.getString("insurance"))));
		 
				if (!(ksd.getDeduction() == 0)) {
				 
					 map.put("deduction",
							  decimalFormat.format(Double.parseDouble(rs
							  .getString("deduction"))));	
				map.put("car_loan_charge", ""+rs.getDouble("car_loan_charge"));//车价贷款额
				 double toalcharge=rs.getDouble("car_loan_charge")+rs.getDouble("insurance")+rs.getDouble("purchase_tax")-rs.getDouble("deduction");
				map.put("toalcharge", decimalFormat.format(toalcharge));//车价计算后
/*				 System.out.println("1car_loan_charge"+rs.getDouble("car_loan_charge"));
				 System.out.println("1toto"+decimalFormat.format(toalcharge));*/
				
				} 
				 map.put("regist_type",rs.getString("regist_type"));
				 map.put("pledge_type", rs.getString("pledge_type" ));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}

		return map;
	}

	public static int getstatus_id(String name) {
		int have_system = 1;

		String sql = "select id from tb_status where name=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				have_system = rs.getInt(1);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return have_system;
	}

	public static int getFinanStatue_id(KSDCase ksd) {
		int statue = 0;

		String sql = " select status from tb_finance where customer_id=(select id from tb_customer where id_num=?) order by id desc  limit 1 ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Map<String, String> map = new HashMap<String, String>();
		try {

			pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				statue = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		// System.out.println(statue);
		return statue;
	}
	public static int getAdvanceStatue_id(KSDCase ksd) {
		int statue = 0;

		String sql = " select  tfa.advance_status from tb_finance  tf,tb_finance_advance tfa where tf.id=tfa.finance_id and tf.customer_id=(select id from tb_customer where id_num=?) order by tfa.id desc  limit 1  ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Map<String, String> map = new HashMap<String, String>();
		try {

			pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				statue = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		// System.out.println(statue);
		return statue;
	}
	public static List<Integer> getLoanname(KSDCase ksd) {

		List<Integer> list = new ArrayList<Integer>();
		String sql = "select * from tb_loan_file  tlf where tlf.finance_id= ( select max(tf.id) from tb_finance tf ,tb_customer tc where  tc.id=tf.customer_id and tc.id_num=?); ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);
			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println(rs.getInt("file_type"));
				list.add(rs.getInt("file_type"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return list;
	}

	public static List<Integer> getImgType(int type, List<Integer> list1) {
		List<Integer> list2 = new ArrayList<Integer>();
		KSDCase ksd = null;
		String sql = "select * from tb_material_data where   type=?;";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int ff = rs.getInt("id");
				list2.add(ff);

			}

			list2.retainAll(list1);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return list2;

	}

	public static int getRisk_type(KSDCase ksd) {
		int have_system = 1;

		String sql = " select risk_type from tb_finance_advance where finance_id=(select id from tb_finance where customer_id=(select id from tb_customer where id_num=?)order by id desc  limit 1) ; ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			if (ksd.getIdentitytype() == 1) {
				pstmt.setString(1, ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				pstmt.setString(1, ksd.getJgid());
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				have_system = rs.getInt(1);

			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return have_system;
	}

	// 必填
	public static List<Integer> getOMaterial(KSDCase ksd, int s) {
		List<Integer> list2 = new ArrayList<Integer>();

		String sql = "select tom.data_id from  tb_organization_material tom  where "
				+ "tom.organization_id=(select min(id) from tb_organization where name =?  and company_id=1000 )   "
				+ "  and tom.material_type=? and tom.is_must=1 and tom.car_type=? ;";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ksd.getProduct().split("-")[0]);
			pstmt.setInt(2, s);
			pstmt.setInt(3, ksd.getCartype());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String ff = rs.getString("data_id");
				// list2.add(ff);
				System.out.println(ff);
				String[] ss2 = ff.split(",");

				for (String ss : ss2) {
					int ss1 = Integer.parseInt(ss);
					list2.add(ss1);
				}

			}

			// list2.retainAll(list1);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return list2;

	}

	// 非必填
	public static List<Integer> getOMaterial2(KSDCase ksd, int s) {
		List<Integer> list2 = new ArrayList<Integer>();

		String sql = "select tom.data_id from  tb_organization_material tom  where  "
				+ "tom.organization_id=(select min(id) from  tb_organization where name =?  and company_id=1000 ) "
				+ "  and tom.material_type=? and tom.is_must=0 and tom.car_type=? ;";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ksd.getProduct().split("-")[0]);
			pstmt.setInt(2, s);
			pstmt.setInt(3, ksd.getCartype());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String ff = rs.getString("data_id");
				// list2.add(ff);
				System.out.println(ff);
				String[] ss2 = ff.split(",");

				for (String ss : ss2) {
					int ss1 = Integer.parseInt(ss);
					list2.add(ss1);
				}

			}

			// list2.retainAll(list1);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return list2;

	}

	// 审批名称id
	public static List<Employee> getSpNameid(KSDCase ksd, int s) {
		List<Integer> list2 = new ArrayList<Integer>();
		List<Employee> list = new ArrayList<Employee>();
		String repos = "";
		//String sql = "select responsible from tb_workflow  tbw  where applyto_city=100 and status=1 and  is_throw=0  and risk_type=? and applyto_business=?;";
		//String sql2 = "select  tbe.`name`,tbe.account ,tbe.position_desc from  tb_employee tbe where    id in  (?)";
		String sql="select tbe.`name`,tbe.account ,tbe.position_desc  from tb_workflow  tbw  ,tb_employee tbe where tbw.applyto_city=100 and tbw.status=1 and  tbw.is_throw=0  and tbw.risk_type=? and tbw.applyto_business=? and   FIND_IN_SET(tbe.id, tbw.responsible)";;
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, s);
			pstmt.setInt(2, ksd.getCartype());
			rs = pstmt.executeQuery();
			String ff = "";
			while (rs.next()) {

				String name = rs.getString("name");
				String account = rs.getString("account");
				String desc = rs.getString("position_desc");
				// System.out.println(account);
				Employee ep = new Employee();
				ep.setUsername(name);
				ep.setAccount(account);
				ep.setDesc(desc);
				list.add(ep);
			}

		
		} catch (SQLException e) {
			//System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return list;

	}
	
	//判断杂项审批人
	public static List<Employee> getSpZxName(KSDCase ksd) {

		List<Integer> list2 = new ArrayList<Integer>();
		List<Employee> list =new ArrayList<Employee>();
		int type=ksd.getCartype();
		String zxname="";
		switch (ksd.getZx()) {
		case 1:
			if(type==0){
			zxname="北京新车，廊坊，张家口返点费用支出"; // 返点
			}else{
			zxname="北京二手车返点费用支出"; // 返点	
			}
			break;
		case 2:
			if(type==0){
			zxname="北京新车，廊坊，张家口抵押费用支出";// 抵押费
			}else{
			zxname="北京二手车抵押费用支出";// 抵押费
			}
			break;
		case 3:
			if(type==0){
			zxname="北京新车杂项费用支出";// 杂项
			}else{
			zxname="北京二手车杂项费用支出";// 杂项	
			}
			break;
		default:
			System.out.println("default");
		}

		String sql="select tbe.`name`,tbe.account ,tbe.position_desc from tb_workflow  tbw,  tb_employee tbe where tbw.applyto_city=100 and tbw.status=1 and   tbw.applyto_business=? and type=2   and tbw.name=? and FIND_IN_SET(tbe.id, tbw.responsible) ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
	 
		try {

			pstmt = conn.prepareStatement(sql);
 
			pstmt.setInt(1,type );
		 
			pstmt.setString(2, zxname);
			
			rs = pstmt.executeQuery();
			String ff="";
			while (rs.next()) {
				String name = rs.getString("name");
				String account = rs.getString("account");
				String desc = rs.getString("position_desc");
				// System.out.println(account);
				Employee ep = new Employee();
				ep.setUsername(name);
				ep.setAccount(account);
				ep.setDesc(desc);
				list.add(ep);

			
			}
			
	
		} catch (SQLException e) {
			//System.out.println(e);
			e.printStackTrace();
		} finally {
			util.closeConn(conn, rs, pstmt);
		}
		return list;
	}
}