package com.divesh.util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.divesh.config.JDBCConfig;

public class JDBCUtil 
{
	private static Connection con=null;
	private static String DbUrl = "jdbc:postgresql://localhost/sportsdb";
	private static String user = "root";
	private static String password = "root@123";
	
	public static synchronized Connection getConnection()
	{
		if(con==null)
		{
			try
			{
				Class.forName(JDBCConfig.Driver_NAME);
				con = DriverManager.getConnection(DbUrl,user,password);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			
		}
		return con;
	}
}
