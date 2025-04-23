package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

public class EmpDao {
	public static Connection getConnection()
	{
		Connection con=null;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
		
		}
	public static int save(Emp e)
	{
		int status=0;
		try
		{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement(
					"insert into user1234(id,name,password,email,city) values (?,?,?,?,?)");
			ps.setString(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getPassword());
			ps.setString(4, e.getEmail());
			ps.setString(5, e.getCity());
			
			status=ps.executeUpdate();
			con.close();
			
		}
		catch(Exception ex)
		{ex.printStackTrace();}
		return status;
	}
		public static List<Emp> getAllEmployees(){
			List<Emp> list=new ArrayList<Emp>();
			
			try {
				Connection con=EmpDao.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from user1234");
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					Emp e=new Emp();
					e.setId(rs.getString(1));
					e.setName(rs.getString(2));
					e.setPassword(rs.getString(3));
					e.setEmail(rs.getString(4));
					e.setCity(rs.getString(5));
					list.add(e);
				}
				con.close();
			}
			catch(Exception e) {e.printStackTrace();}
			return list;
		}
		public static int delete(int id)
		{
			int status=0;
			try {
				Connection con=EmpDao.getConnection();
				PreparedStatement ps=con.prepareStatement("delete from user1234 where id=?");
				ps.setInt(1,id);
				status=ps.executeUpdate();
				con.close();
			}
			catch(Exception e) {e.printStackTrace();}
			return status;
		}
		public static Emp getEmployeeById(int id)
		{
			Emp e=new Emp();
			
			try {
				Connection con=EmpDao.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from user1234 where id=?");
				ps.setInt(1,id);
				
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					
					e.setId(rs.getString(1));
					e.setName(rs.getString(2));
					e.setPassword(rs.getString(3));
					e.setEmail(rs.getString(4));
					e.setCity(rs.getString(5));
					
				}
				con.close();
			}
			catch(Exception ex) {ex.printStackTrace();}
			return e;
					
					
				}
		public static int update(Emp e)
		{
			int status=0;
			try {
				
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement(
	                       "update user1234 set name=?,password=?,email=?,city=? where id=?");

		ps.setString(1, e.getName());
		ps.setString(2, e.getPassword());
		ps.setString(3, e.getEmail());
		ps.setString(4, e.getCity());
		ps.setString(5, e.getId());
		
		status=ps.executeUpdate();
		con.close();
		
	}
	catch(Exception ex)
	{ex.printStackTrace();}
	return status;
		
		
			}
	}
	

