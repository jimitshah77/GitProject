package com.lynda.javatraining.db;

import java.sql.*;
import java.text.NumberFormat;
import java.util.Scanner;

public class PreparedStatementJDBC {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Jimitkshah75@";
    private static final String CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);

            String sql = "SELECT tourId,tourName,price FROM tours WHERE price <= ?";
            ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

            double maxPrice;
            Scanner input = new Scanner (System.in);
            System.out.println("Enter the Max. Price you want to spend: ");
            maxPrice = input.nextDouble();

            ps.setDouble(1,maxPrice);
            rs = ps.executeQuery();

            while(rs.next()){
                StringBuffer buffer = new StringBuffer();
                buffer.append("TourID: " + rs.getInt("tourId") + "\t");
                buffer.append("Tour Name: " + rs.getString("tourName") + "\t");

                double price = rs.getDouble("price");
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                String formatted_price = nf.format(price);
                buffer.append("Price: " + formatted_price + "\t");

                System.out.println(buffer.toString());
            }

        } catch (SQLException e) {
            //exception handling
            System.err.println("Error Message: " + e.getMessage());
            System.err.println("Error Code   : " + e.getErrorCode());
            System.err.println("SQL State    : " + e.getSQLState());

        } finally{
            //closing objects in reverse order
            if(rs !=null){
                rs.close();
            }
            if(ps !=null){
                ps.close();
            }
            if(conn !=null){
                conn.close();
            }
        }
    }
}
