package com.kh.dao;

import com.kh.util.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LoginDAO {
    public int login() {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        String nickName, pwd;
        int memberNum;
        System.out.print("닉네임 : ");
        nickName = sc.next();
        System.out.print("비밀번호 : ");
        pwd = sc.next();

        try {
            String sql = "SELECT * FROM MEMBER WHERE NICKNAME = ?";
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nickName);
            // 결과를 받아오는 ResulSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String pwdb = rs.getString("PWD");
                if (pwdb.equals(pwd)) {
                    memberNum = rs.getInt("MEMBER_NUM");
                } else {
                    memberNum = 1;
                }
            } else {
                memberNum = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return memberNum;
    }
}







