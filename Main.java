package com.lynda.javatraining.db;

import java.sql.*;
import java.text.NumberFormat;

public class Main {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "Jimitkshah75@";
	private static final String CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		try //use try with block so that you don't have to handle closing of objects created
		{
			//Connecting Database
			conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
			//System.out.println("Database Connected!");

			//executing static query
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT stateId,stateName FROM states");
			rs.last(); //scrollable cursor
			//System.out.println("Number of rows: "+ rs.getRow());

			//looping through the ResultSet
			rs1 = stmt.executeQuery("SELECT tourId,tourName,price FROM tours LIMIT 0,5");
			while(rs1.next()){
				StringBuffer buffer = new StringBuffer();
				buffer.append("TourID: " + rs1.getInt("tourId") + "\t");
				buffer.append("Tour Name: " + rs1.getString("tourName") + "\t");

				double price = rs1.getDouble("price");
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				String formatted_price = nf.format(price);
				buffer.append("Price: " + formatted_price + "\t");

				//System.out.println(buffer.toString());
			}

		} catch (SQLException e) {
			//exception handling
			System.err.println("Error Message: " + e.getMessage());
			System.err.println("Error Code   : " + e.getErrorCode());
			System.err.println("SQL State    : " + e.getSQLState());

		} finally {
			//closing objects in reverse order
			if(rs1 !=null){
				rs1.close();
			}
			if(rs !=null){
				rs.close();
			}
			if(stmt !=null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}
		}
	}
}
