package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 数据库连接参数
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USERNAME = "root"; // 请根据实际情况修改用户名
    private static final String PASSWORD = ""; // 密码为空
    
    // JDBC 4.0+ 支持自动加载驱动，无需显式调用Class.forName()
    
    // 获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("数据库连接失败！");
        }
        return conn;
    }
    
    // 关闭数据库连接
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("数据库连接已关闭！");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("关闭数据库连接失败！");
            }
        }
    }
}