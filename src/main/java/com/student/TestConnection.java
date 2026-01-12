package com.student;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        // 获取数据库连接
        Connection conn = DatabaseConnection.getConnection();
        
        // 关闭数据库连接
        DatabaseConnection.closeConnection(conn);
    }
}