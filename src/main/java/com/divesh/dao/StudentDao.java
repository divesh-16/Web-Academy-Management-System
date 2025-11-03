package com.divesh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.divesh.util.JDBCUtil;

public class StudentDao 
{
	private Connection con = JDBCUtil.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
	public Map<String,String> getStudentDetails(int sid,String password)
	{
		Map<String,String> student = new HashMap<>();
		try
		{
			ps = con.prepareStatement("select * from student where sid = ? and password = ?");
			ps.setInt(1, sid);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				student.put("sid", rs.getString("sid"));
				student.put("name", rs.getString("name"));
				student.put("address",rs.getString("address"));
				student.put("phoneno",rs.getString("phoneno"));
			}
			
			return student;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,String> getCoachDetails(int sid)
	{
		Map<String,String> coach = new HashMap<>();
		
		try
		{
			ps = con.prepareStatement("select name,phoneno,game from coach,batch,games where coach.cid=batch.cid and batch.gid=games.gid and sid=?");
			ps.setInt(1, sid);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				 coach.put("name", rs.getString("name"));
	             coach.put("phoneno", rs.getString("phoneno"));
	             coach.put("game", rs.getString("game"));
                 
			}
			
			return coach;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,String> getSportsDetails(int sid)
	{
		Map<String,String> sport = new HashMap<>();
		
		try
		{
			ps = con.prepareStatement("select name,game from coach,batch,games where batch.cid = coach.cid and batch.gid = games.gid and sid = ?");
			
			ps.setInt(1, sid);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				sport.put("name", rs.getString("name"));
                sport.put("game", rs.getString("game"));
			}
			
			return sport;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
