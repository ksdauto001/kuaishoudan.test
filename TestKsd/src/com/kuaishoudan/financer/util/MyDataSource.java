package com.kuaishoudan.financer.util;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;
import com.kuaishoudan.financer.util.JdbcUtils;
 
public class MyDataSource implements DataSource {

	 //通过 linkList充当 池
    private LinkedList<Connection> pool = new LinkedList<Connection>();
    //构造 函数 初始化 连接 数目
    public MyDataSource(){
     //   System.out.println("构造函数 创建3个连接");
        for (int i = 0; i < 3; i++) {
            //创建 连接
            Connection connection = JdbcUtils.getConnection();
            //把 创建的连接 放入池子中
            pool.add(connection);
        }
    }
    //用完 之后  将传递的连接放回  池中
    public void addBackToPool(Connection connection){
        pool.add(connection);
    }
    public   void closeCon( ){
    	for(int i=0;i<pool.size();i++){
   // 		System.out.println("!!!"+pool.get(i));
    		try {
				pool.get(i).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
    //从线程池 中 取得 第一个 
    @Override
    public Connection getConnection() throws SQLException {
        // TODO Auto-generated method stub
        //首先 判断 是否为空
                if(pool.isEmpty()){
                    //为空的话  继续创建 3个连接
                    for (int i = 0; i < 3; i++) {
                        Connection connection = JdbcUtils.getConnection();
                        pool.add(connection);
                    }
                }
                //有连接的话  就取出第一个
                Connection con = pool.removeFirst();
    //            System.out.println("取得一个连接 使用");
                return con;
    }

    @Override
    public Connection getConnection(String username, String password)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
}
