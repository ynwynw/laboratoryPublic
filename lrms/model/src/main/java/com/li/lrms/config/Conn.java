package com.li.lrms.config;

import com.li.lrms.model.url.isai;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Conn {
    public Connection getConn() {

        Connection conn = null;
        try {
            isai u = new isai();
            Class.forName("com.mysql.jdbc.Driver");
            String url = u.tome();
            String username = u.isu();
            String password = u.isp();
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConn(Connection conn) {

        try {
            if (conn != null && !conn.isClosed()) {

                conn.close();

            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
