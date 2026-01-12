package com.student;

import java.util.Date;

public class Student {
    // 成员属性，对应数据库表字段
    private int studentId;        // 学生ID
    private String studentName;   // 学生姓名
    private String gender;        // 性别
    private String className;     // 班级
    private double mathScore;     // 高数成绩
    private double javaScore;     // Java成绩
    private Date createTime;      // 数据录入时间
    
    // 无参构造方法
    public Student() {
    }
    
    // 有参构造方法（不含自增字段和默认值字段）
    public Student(String studentName, String gender, String className, double mathScore, double javaScore) {
        this.studentName = studentName;
        this.gender = gender;
        this.className = className;
        this.mathScore = mathScore;
        this.javaScore = javaScore;
    }
    
    // 完整构造方法
    public Student(int studentId, String studentName, String gender, String className, double mathScore, double javaScore, Date createTime) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.className = className;
        this.mathScore = mathScore;
        this.javaScore = javaScore;
        this.createTime = createTime;
    }
    
    // Getter和Setter方法
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public double getMathScore() {
        return mathScore;
    }
    
    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }
    
    public double getJavaScore() {
        return javaScore;
    }
    
    public void setJavaScore(double javaScore) {
        this.javaScore = javaScore;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    // 重写toString方法，方便打印学生信息
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", gender='" + gender + '\'' +
                ", className='" + className + '\'' +
                ", mathScore=" + mathScore +
                ", javaScore=" + javaScore +
                ", createTime=" + createTime +
                '}';
    }
}