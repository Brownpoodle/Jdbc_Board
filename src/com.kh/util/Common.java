package com.kh.util;
import java.sql.*;

public class Common {
    final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final static String Oracle_ID = "scott";
    final static String Oracle_PW = "TIGER";
    final static String Oracle_DRV = "oracle.jdbc.OracleDriver";

    public static Connection getConnection()  {
        // 나중에 커넥션의 연결을 해제할 때 커넥션의 값이 null 이면 해제하지 말라는 조건을 넣기 위해 초기값을 null 로 설정해줌
        Connection conn = null;
        try {
            Class.forName(Oracle_DRV); // 드라이버 로딩
            // 연결 얻기
            conn = DriverManager.getConnection(ORACLE_URL, Oracle_ID, Oracle_PW);
            // conn.setAutoCommit(false); // 자바에서는 자동으로 커밋이 되지만 수동으로 하고 싶으면 false 를 넣어주면 줌
            System.out.println("오라클 DB 연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("연결 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
                System.out.println("Statement 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rset) {
        try {
            if(rset != null && !rset.isClosed()) {
                rset.close();
                System.out.println("Statement 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
