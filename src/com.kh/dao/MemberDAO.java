package com.kh.dao;
import com.kh.util.Common;
import com.kh.vo.MemberVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MemberDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    Scanner sc = new Scanner(System.in);


    public List<MemberVO> memberSelect(int memberNum) {
        List<MemberVO> list = new ArrayList<>();
        int memberNumd = memberNum;
        try {
            String sql = "SELECT * FROM MEMBER WHERE MEMBER_NUM = ?";
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNumd);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                memberNumd = rs.getInt("MEMBER_NUM");
                String nickName = rs.getString("NICKNAME");
                String pwd = rs.getString("PWD");
                String regDate = rs.getString("REG_DATE");
                MemberVO memberVO = new MemberVO(memberNumd, nickName, pwd, regDate);
                list.add(memberVO);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void memberInsert() {
        System.out.println("닉네임을 입력 하세요");
        System.out.print("닉네임 : ");
        String nickName = sc.next();
        System.out.println("비밀번호를 입력 하세요");
        System.out.print("비밀번호 : ");
        String pwd = sc.next();
        String sql = "INSERT INTO MEMBER(MEMBER_NUM, NICKNAME, PWD) " +
                "VALUES(MEMBER_NUM.NEXTVAL, ?, ?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nickName);
            pstmt.setString(2, pwd);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    public void memberUpdate(int memberNum) {
        int memberNumb = memberNum;
        System.out.println("회원 정보 입력");
//        System.out.print("회원번호 : ");
//        int memberNum = sc.nextInt();
        System.out.print("새 닉네임 : ");
        String nickName = sc.next();
        System.out.print("새 비밀번호: ");
        String pwd = sc.next();

        String sql = "UPDATE MEMBER SET NICKNAME = ?, PWD = ? WHERE MEMBER_NUM = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nickName);
            pstmt.setString(2, pwd);
            pstmt.setInt(3,memberNum);
            int ret = pstmt.executeUpdate();
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void memberDelete() {
        System.out.print("삭제할 회원번호 : ");
        int memberNum = sc.nextInt();
        String sql = "DELETE FROM MEMBER WHERE MEMBER_NUM = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNum);
            int ret = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    public void memberSelectRst(List<MemberVO> list) {
        System.out.println("회원번호 닉네임  비밀번호       가입일자");
        for(MemberVO e : list) {
            System.out.print(e.getMemberNum() + "    ");
            System.out.print(e.getNickName() + "   ");
            System.out.print(e.getPwd() + "   ");
            System.out.print(e.getRegDate() + "     ");
            System.out.println();
        }
    }

}
