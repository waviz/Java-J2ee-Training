package com.waviz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;



import com.waviz.domain.User;
@Repository
public class UserDaoImpl implements UserDao{

	private static Connection conn;
	
	public UserDaoImpl(){
				conn=DConnection.getConnection();
			}
	@Override
	public void insertData(User user)  {
		
		try
		{
				String qry = "insert into user(first_name,last_name, gender, city) VALUES ( ?, ?,?,?)";
		
		PreparedStatement preparedStatement=conn.prepareStatement(qry);
		preparedStatement.setString( 1, user.getFirstName() );
		preparedStatement.setString( 2, user.getLastName() );
		preparedStatement.setString( 3, user.getGender() );
		
		preparedStatement.setString(4, user.getCity());
		
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public List<User> getUserList() {
		
		List<User> list=new ArrayList<User>();
        try {
        	
            Statement st=  conn.createStatement();
            ResultSet rs = st.executeQuery("select * from user");
            while (rs.next()) {
            	User user=new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setGender(rs.getString("gender"));
                user.setCity(rs.getString("city"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		
		return list;
	}
	@Override
	public void updateData(User user) {
		 try {
			
	         String query="update user set first_name=?, last_name=?, gender=?, city=? where user_id=?";
	         PreparedStatement preparedStatement=conn.prepareStatement(query);
			 
			 
	            // Parameters start with 1
	            System.out.println("update user");
	            preparedStatement.setString(1, user.getFirstName());
	            preparedStatement.setString(2, user.getLastName());
	            preparedStatement.setString(3, user.getGender());
	            preparedStatement.setString(4, user.getCity());
	            preparedStatement.setInt(5, user.getUserId());
	            preparedStatement.executeUpdate();
	            System.out.println("update user12");
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

		
	}
	@Override
	public void deleteData(String id) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("delete from user where user_id=?");
        
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
	@Override
	public User getUser(String id) {
		  User user=new User();
		  try {
	            PreparedStatement preparedStatement = conn.
	                    prepareStatement("select * from user where user_id=? ");
	            preparedStatement.setString(1,id);
	            ResultSet rs =  (ResultSet) preparedStatement.executeQuery();

	            if (rs.next()) {
	            	
	                 user.setUserId(rs.getInt("user_id"));
	                user.setFirstName(rs.getString("first_name"));
	                user.setLastName(rs.getString("last_name"));
	                user.setGender(rs.getString("gender"));
	                 user.setCity(rs.getString("city"));
	                 
	            }
	            rs.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return  user ;
	}

	
	
}
