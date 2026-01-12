package com.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    // 数据库连接对象
    private Connection conn;
    
    // 构造方法，获取数据库连接
    public StudentManager() {
        this.conn = DatabaseConnection.getConnection();
    }
    
    // 关闭数据库连接
    public void close() {
        DatabaseConnection.closeConnection(conn);
    }
    
    // （1）添加学生信息到数据库
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (student_name, gender, class_name, math_score, java_score) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, student.getStudentName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());
            
            // 执行插入操作
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("添加学生失败！错误信息：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // （2）根据ID查询学生信息
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        Student student = null;
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 从结果集中获取数据并创建Student对象
                    student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentName(rs.getString("student_name"));
                    student.setGender(rs.getString("gender"));
                    student.setClassName(rs.getString("class_name"));
                    student.setMathScore(rs.getDouble("math_score"));
                    student.setJavaScore(rs.getDouble("java_score"));
                    student.setCreateTime(rs.getTimestamp("create_time"));
                }
            }
        } catch (SQLException e) {
            System.err.println("查询学生失败！错误信息：" + e.getMessage());
            e.printStackTrace();
        }
        
        return student;
    }
    
    // （3）显示所有学生信息
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> studentList = new ArrayList<>();
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                // 从结果集中获取数据并创建Student对象
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class_name"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                student.setCreateTime(rs.getTimestamp("create_time"));
                
                // 添加到学生列表
                studentList.add(student);
            }
        } catch (SQLException e) {
            System.err.println("获取所有学生失败！错误信息：" + e.getMessage());
            e.printStackTrace();
        }
        
        return studentList;
    }
    
    // （4）计算学生各科目的平均分数
    public double[] calculateAverageScores() {
        String sql = "SELECT AVG(math_score) AS avg_math, AVG(java_score) AS avg_java FROM students";
        double[] averages = new double[2]; // 索引0：高数平均分，索引1：Java平均分
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                // 获取各科目的平均分
                averages[0] = rs.getDouble("avg_math");
                averages[1] = rs.getDouble("avg_java");
            }
        } catch (SQLException e) {
            System.err.println("计算平均分失败！错误信息：" + e.getMessage());
            e.printStackTrace();
        }
        
        return averages;
    }
    
    // 测试StudentManager类的功能
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        
        try {
            // 测试添加学生
            System.out.println("=== 测试添加学生 ===");
            Student newStudent = new Student("孙八", "男", "Java2601班", 82.5, 88.0);
            boolean added = manager.addStudent(newStudent);
            if (added) {
                System.out.println("学生添加成功！");
            } else {
                System.out.println("学生添加失败！");
            }
            
            // 测试根据ID查询学生
            System.out.println("\n=== 测试根据ID查询学生 ===");
            Student student = manager.getStudentById(1);
            if (student != null) {
                System.out.println("查询到学生：" + student);
            } else {
                System.out.println("未找到该学生！");
            }
            
            // 测试显示所有学生信息
            System.out.println("\n=== 测试显示所有学生 ===");
            List<Student> allStudents = manager.getAllStudents();
            for (Student s : allStudents) {
                System.out.println(s);
            }
            
            // 测试计算平均分
            System.out.println("\n=== 测试计算平均分 ===");
            double[] averages = manager.calculateAverageScores();
            System.out.printf("高数平均分：%.1f\n", averages[0]);
            System.out.printf("Java平均分：%.1f\n", averages[1]);
            
        } finally {
            // 关闭数据库连接
            manager.close();
        }
    }
}