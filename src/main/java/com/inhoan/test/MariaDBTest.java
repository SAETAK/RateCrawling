package com.inhoan.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBTest {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/crawling";
        String user = "root";
        String password = "57878";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("MariaDB 연결 성공!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("MariaDB 연결 실패: " + e.getMessage());
        }
    }
}

