package com.waviz.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DConnection {

	private static  Connection Conn;
	public static Connection getConnection() {
		  
		

        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "userdb";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            Conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
	
            
        }
        catch(Exception ex){
        
        	ex.printStackTrace();
        }

		return  Conn;
		
	}
	
	
	
	
	
	
	
	
}
