package com.kh.dao;

import com.kh.util.Common;
import com.kh.vo.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<BoardVO> boardSelect() {
        List<BoardVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM BOARD";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String boardName = rs.getString("BOARD_NAME");
                BoardVO boardVO = new BoardVO(boardName);
                list.add(boardVO);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void boardInsert() {
        System.out.println("게시판 이름을 입력하세요");
        System.out.print("게시판 이름: ");
        String boardName = sc.next();

        String sql = "INSERT INTO BOARD(BOARD_NAME)" +
                "VALUES(?)";
        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardName);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void boardUpdate() {
        System.out.println("변경할 게시판 이름을 입력하세요");
        System.out.print("이름 : ");
        String boardName = sc.next();
        System.out.print("새로운 게시판 이름을 입력하세요");
        System.out.print("이름 : ");
        String newName = sc.next();
        String sql = "UPDATE BOARD SET BOARD_NAME = ? WHERE BOARD_NAME = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newName);
            pstmt.setString(2, boardName);
            int ret = pstmt.executeUpdate();
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void boardDelete() {
        System.out.print("삭제할 게시판 이름을 입력하세요 : ");
        String boardName = sc.next();
        String sql = "DELETE FROM BOARD WHERE BOARD_NAME = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardName);
            int ret = pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void boardSelectRst(List<BoardVO> list) {
        int cnt = 1;
        for(BoardVO e : list) {
            System.out.print("[" + (cnt++) + "]" + e.getBoardName() + " ");
            System.out.println();
        }
    }
}