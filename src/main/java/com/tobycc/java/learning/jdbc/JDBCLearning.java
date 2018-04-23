package com.tobycc.java.learning.jdbc;

import java.sql.*;

/**
 * Created by toby.christey-clover on 17/04/2018.
 */
public class JDBCLearning {

    public void connectToAndQueryDatabase(String username, String password) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:myDriver:myDatabase",
                username,
                password);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1");

        while (rs.next()) {
            int x = rs.getInt("a");
            String s = rs.getString("b");
            float f = rs.getFloat("c");
        }
    }

    public void sproc(int carNo, int empNo) throws SQLException {
            Connection con = DriverManager.getConnection(
                        "jdbc:default:connection");

            PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE EMPLOYEES " +
                            "SET CAR_NUMBER = ? " +
                            "WHERE EMPLOYEE_NUMBER = ?");

            pstmt.setInt(1, carNo);
            pstmt.setInt(2, empNo);
            pstmt.executeUpdate();
            pstmt.close();
    }
}
