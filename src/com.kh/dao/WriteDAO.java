package com.kh.dao;
import com.kh.util.Common;
import com.kh.vo.MemberVO;
import com.kh.vo.WriteVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    Scanner sc = new Scanner(System.in);
    static String boardName;
    static int memberNum;

    public List<WriteVO> writeSelect(int boardNum) {
        List<WriteVO> list = new ArrayList<>();
        int boardNumd = boardNum;
        switch (boardNumd) {
            case 1 :
                boardName = "맛집추천";
                break;
            case 2 :
                boardName = "성실회원";
                break;
            case 3 :
                boardName = "자유";
                break;
            case 4 :
                boardName = "질문";
                break;
            case 5 :
                boardName = "취업진로";
                break;
        }

        try {
            String sql = "SELECT * FROM WRITE WHERE BOARD_NAME = ?";
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardName);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                boardName = rs.getString("BOARD_NAME");
                int writeNum = rs.getInt("WRITE_NUM");
                String writeName = rs.getString("WRITE_NAME");
                int memberNum = rs.getInt("MEMBER_NUM");
                String writeContents = rs.getString("WRITE_CONTENTS");
                String writeDate = rs.getString("WRITE_DATE");
                WriteVO writeVO = new WriteVO(boardName, writeNum, writeName,
                        memberNum, writeContents, writeDate);
                list.add(writeVO);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeInsert(int boardNum, int memberNum) {
        int boardNumd = boardNum;
        this.memberNum = memberNum;

        switch (boardNumd) {
            case 1 :
                boardName = "맛집추천";
                break;
            case 2 :
                boardName = "성실회원";
                break;
            case 3 :
                boardName = "자유";
                break;
            case 4 :
                boardName = "질문";
                break;
            case 5 :
                boardName = "취업진로";
                break;
        }
        System.out.println("게시글을 작성하세요.");
        System.out.print("게시글 제목 : ");
        String writeName = sc.nextLine();
        System.out.print("게시글 내용 : ");
        String writeContents = sc.nextLine();

        String sql = "INSERT INTO WRITE(BOARD_NAME, WRITE_NUM, WRITE_NAME, MEMBER_NUM, WRITE_CONTENTS) " +
                "VALUES(?, WRITE_NUM.NEXTVAL, ?, ?, ?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardName);
            pstmt.setString(2, writeName);
            pstmt.setInt(3, memberNum);
            pstmt.setString(4, writeContents);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void writeUpdate() {
        System.out.println("변경할 게시글 정보 입력");
        System.out.print("새로운 게시판 이름 : ");
        String boardName = sc.next();
        sc.nextLine();
        System.out.print("새로운 게시글 제목 : ");
        String writeName = sc.nextLine();
        System.out.print("회원번호 : ");
        int memberNum = sc.nextInt();
        sc.nextLine();
        System.out.print("새로운 게시글 내용 : ");
        String writeContents = sc.nextLine();

        String sql = "UPDATE WRITE SET BOARD_NAME = ?, WRITE_NAME = ?, WRITE_CONTENTS = ?  WHERE MEMBER_NUM = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardName);
            pstmt.setString(2, writeName);
            pstmt.setString(3, writeContents);
            pstmt.setInt(4, memberNum);
            int ret = pstmt.executeUpdate();
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void writeDelete() {
        System.out.print("삭제할 게시글 번호를 입력하세요 : ");
        int writeNum = sc.nextInt();
        String sql = "DELETE FROM WRITE WHERE WRITE_NUM = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, writeNum);
            int ret = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    public void writeSelectRst(List<WriteVO> list) {
        for(WriteVO e : list) {
            System.out.print(e.getBoardName() + " ");
            System.out.print(e.getWriteNum() + " ");
            System.out.print(e.getWriteName() + " ");
            System.out.print(e.getMemberNum() + " ");
            System.out.print(e.getWriteContents() + " ");
            System.out.print(e.getWriteDate() + " ");
            System.out.println();
        }
    }

}