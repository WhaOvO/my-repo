package com.student;

import java.util.List;
import java.util.Scanner;

public class Main {
    // 学生管理器对象
    private static StudentManager studentManager;
    // 扫描器对象，用于获取用户输入
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // 初始化学生管理器和扫描器
        studentManager = new StudentManager();
        scanner = new Scanner(System.in);
        
        try {
            // 显示欢迎信息
            System.out.println("========================================");
            System.out.println("       学生管理系统 - 命令行界面         ");
            System.out.println("========================================");
            
            // 主循环，显示菜单并处理用户输入
            boolean exit = false;
            while (!exit) {
                // 显示菜单
                showMenu();
                
                // 获取用户选择
                int choice = getUserChoice();
                
                // 根据用户选择执行相应操作
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        queryStudentById();
                        break;
                    case 3:
                        showAllStudents();
                        break;
                    case 4:
                        calculateAverageScores();
                        break;
                    case 5:
                        exit = true;
                        System.out.println("感谢使用学生管理系统，再见！");
                        break;
                    default:
                        System.out.println("无效的选择，请重新输入！");
                }
                
                // 如果不是退出操作，显示分隔线并等待用户确认
                if (!exit) {
                    System.out.println("\n按Enter键继续...");
                    scanner.nextLine(); // 清除输入缓冲区
                    scanner.nextLine(); // 等待用户按Enter
                }
            }
        } finally {
            // 关闭资源
            if (studentManager != null) {
                studentManager.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    // 显示菜单
    private static void showMenu() {
        System.out.println("\n请选择操作：");
        System.out.println("1. 添加学生信息");
        System.out.println("2. 根据ID查询学生");
        System.out.println("3. 显示所有学生");
        System.out.println("4. 计算各科平均分");
        System.out.println("5. 退出系统");
        System.out.print("请输入选项(1-5)：");
    }
    
    // 获取用户选择
    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            // 处理输入格式错误
            System.out.println("输入格式错误，请输入数字！");
            scanner.nextLine(); // 清除输入缓冲区
        }
        return choice;
    }
    
    // （1）添加学生信息
    private static void addStudent() {
        System.out.println("\n=== 添加学生信息 ===");
        scanner.nextLine(); // 清除输入缓冲区
        
        // 获取学生姓名
        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine();
        
        // 获取性别
        System.out.print("请输入性别(男/女)：");
        String gender = scanner.nextLine();
        
        // 获取班级
        System.out.print("请输入班级：");
        String className = scanner.nextLine();
        
        // 获取高数成绩
        double mathScore = getDoubleInput("请输入高数成绩：");
        
        // 获取Java成绩
        double javaScore = getDoubleInput("请输入Java成绩：");
        
        // 创建学生对象并添加到数据库
        Student student = new Student(name, gender, className, mathScore, javaScore);
        boolean success = studentManager.addStudent(student);
        
        if (success) {
            System.out.println("学生信息添加成功！");
        } else {
            System.out.println("学生信息添加失败！");
        }
    }
    
    // （2）根据ID查询学生
    private static void queryStudentById() {
        System.out.println("\n=== 根据ID查询学生 ===");
        
        // 获取学生ID
        int studentId = getIntInput("请输入学生ID：");
        
        // 查询学生信息
        Student student = studentManager.getStudentById(studentId);
        
        if (student != null) {
            System.out.println("查询到的学生信息：");
            System.out.println(student);
        } else {
            System.out.println("未找到该学生！");
        }
    }
    
    // （3）显示所有学生
    private static void showAllStudents() {
        System.out.println("\n=== 所有学生信息 ===");
        
        // 获取所有学生信息
        List<Student> students = studentManager.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("暂无学生信息！");
        } else {
            // 打印学生信息表格
            System.out.printf("%-5s %-10s %-5s %-15s %-10s %-10s %-20s\n", 
                "ID", "姓名", "性别", "班级", "高数成绩", "Java成绩", "录入时间");
            System.out.println("--------------------------------------------------------------");
            
            for (Student student : students) {
                System.out.printf("%-5d %-10s %-5s %-15s %-10.1f %-10.1f %-20s\n", 
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getGender(),
                    student.getClassName(),
                    student.getMathScore(),
                    student.getJavaScore(),
                    student.getCreateTime());
            }
        }
    }
    
    // （4）计算平均分
    private static void calculateAverageScores() {
        System.out.println("\n=== 各科平均分数 ===");
        
        // 计算平均分
        double[] averages = studentManager.calculateAverageScores();
        
        System.out.printf("高数平均分：%.1f\n", averages[0]);
        System.out.printf("Java平均分：%.1f\n", averages[1]);
    }
    
    // 获取整数输入
    private static int getIntInput(String prompt) {
        int value = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("输入格式错误，请输入整数！");
                scanner.nextLine(); // 清除输入缓冲区
            }
        }
        
        return value;
    }
    
    // 获取小数输入
    private static double getDoubleInput(String prompt) {
        double value = 0.0;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                value = scanner.nextDouble();
                valid = true;
            } catch (Exception e) {
                System.out.println("输入格式错误，请输入数字！");
                scanner.nextLine(); // 清除输入缓冲区
            }
        }
        
        return value;
    }
}