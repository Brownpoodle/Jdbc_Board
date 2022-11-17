package com.kh;
import com.kh.dao.*;
import com.kh.vo.BoardVO;
import com.kh.vo.MemberVO;
import com.kh.vo.WriteVO;


import java.util.List;
import java.util.Scanner;
public class JdbcMain {
    static Scanner sc = new Scanner(System.in);
    static int memberNum;
    static int boardNum;


    public static void main(String[] args) {
        loginMenu();
// 윤아 비밀번호 : 1557TJE2444
        menuSelect();
    }

    public static void loginMenu() {
        LoginDAO loginDAO = new LoginDAO();
        MemberDAO memberDAO = new MemberDAO();

        boolean istrue = true;
        while (istrue) {
            System.out.println("==== [KH 게시판] ====");
            System.out.println("[1]로그인 [2]회원가입 [3]종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    memberNum = loginDAO.login();
                    if (memberNum != 1 && memberNum != 2 && memberNum != -1) {
                        System.out.println("로그인 성공");
                        istrue = false;
                        continue;

                    } else if (memberNum == 1) {
                        System.out.println("비밀번호를 잘못 입력하였습니다.");
                        continue;
                    } else if (memberNum == 2) {
                        System.out.println("아이디를 잘못 입력하였습니다.");
                        continue;
                    }

                case 2:
                    memberDAO.memberInsert();

                case 3:
                    System.exit(1);

            }
        }
    }

    public static void menuSelect() {
        Scanner sc = new Scanner(System.in);
        MemberDAO memberDAO = new MemberDAO();
        BoardDAO boardDAO = new BoardDAO();
        WriteDAO writeDAO = new WriteDAO();
        boolean istrue = true;
        while (istrue) {
            System.out.println("==== [KH 게시판] ====");
            System.out.println("[1]회원정보 [2]게시판 목록 [3]게시글 쓰기 [4]종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    List<MemberVO> list = memberDAO.memberSelect(memberNum);
                    memberDAO.memberSelectRst(list);
                    System.out.println("회원정보를 수정하시겠습니까?");
                    System.out.println("[1]Y [2]N");
                    String st = sc.next();
                    if (st.equalsIgnoreCase("Y")) {
                        memberDAO.memberUpdate(memberNum);
                        continue;
                    } else if (st.equalsIgnoreCase("N")) {
                        continue;
                    }

                case 2:
                    List<BoardVO> blist = boardDAO.boardSelect();
                    System.out.println("게시판을 선택해주세요.");
                    boardDAO.boardSelectRst(blist);
                    boardNum = sc.nextInt();
                    List<WriteVO> wlist = writeDAO.writeSelect(boardNum);
                    writeDAO.writeSelectRst(wlist);
                    sc.nextLine();
                    System.out.println("메뉴를 선택해주세요.");
                    System.out.println("[1]게시글 작성 [2]게시글 수정 [3]게시글 삭제 [4]좋아요주기 [5]싫어요주기 " +
                            "[6]다른 게시판 선택 [7]종료");

                case 3:
                    blist = boardDAO.boardSelect();
                    System.out.println("게시판을 선택해주세요.");
                    boardDAO.boardSelectRst(blist);
                    boardNum = sc.nextInt();
                    writeDAO.writeInsert(boardNum, memberNum);
                    wlist = writeDAO.writeSelect(boardNum);
                    writeDAO.writeSelectRst(wlist);
                    System.out.println("메뉴를 선택해주세요.");
                    System.out.println("[1]게시글 작성 [2]게시글 수정 [3]게시글 삭제 [4]좋아요주기 [5]싫어요주기 " +
                            "[6] 다른 게시판 선택 [7]종료");

                case 4 :
                    System.exit(1);

            }
        }
    }

//    public static void memberMenu() {
//        Scanner sc = new Scanner(System.in);
//        MemberDAO memberDAO = new MemberDAO();
//        while (true) {
//            System.out.println("==== [Member Table] ====");
//            System.out.println("메뉴를 조회하세요");
//            System.out.print("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT : ");
//            int sel = sc.nextInt();
//            switch (sel) {
//                case 1:
//                    List<MemberVO> list = memberDAO.memberSelect();
//                    memberDAO.memberSelectRst(list);
//                    break;
//                case 2:
//                    memberDAO.memberInsert();
//                    break;
//                case 3:
//                    memberDAO.memberUpdate();
//                    break;
//                case 4:
//                    memberDAO.memberDelete();
//                    break;
//                case 5:
//                    System.out.println("메뉴를 종료합니다.");
//                    return;
//
//            }
//        }
//    }
//
//    public static void boardMenu() {
//        Scanner sc = new Scanner(System.in);
//        BoardDAO boardDAO = new BoardDAO();
//        while (true) {
//            System.out.println("==== [BOARD TABLE] ====");
//            System.out.println("메뉴를 조회하세요");
//            System.out.print("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT : ");
//            int sel = sc.nextInt();
//            switch (sel) {
//                case 1:
//                    List<BoardVO> list = boardDAO.boardSelect();
//                    boardDAO.boardSelectRst(list);
//                    break;
//                case 2:
//                    boardDAO.boardInsert();
//                    break;
//                case 3:
//                    boardDAO.boardUpdate();
//                    break;
//                case 4:
//                    boardDAO.boardDelete();
//                    break;
//                case 5:
//                    System.out.println("메뉴를 종료합니다.");
//                    return;
//            }
//        }
//    }
//
//    public static void writeMenu() {
//        Scanner sc = new Scanner(System.in);
//        WriteDAO writeDAO = new WriteDAO();
//        while(true) {
//            System.out.println("==== [WRITE TABLE] ====");
//            System.out.println("메뉴를 조회하세요");
//            System.out.print("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT : ");
//            int sel = sc.nextInt();
//            switch (sel) {
//                case 1 :
//                    List<WriteVO> list = writeDAO.writeSelect();
//                    writeDAO.writeSelectRst(list);
//                    break;
//                case 2 :
//                    writeDAO.writeInsert();
//                    break;
//                case 3 :
//                    writeDAO.writeUpdate();
//                    break;
//                case 4 :
//                    writeDAO.writeDelete();
//                    break;
//                case 5 :
//                    System.out.println("메뉴를 종료합니다.");
//                    return;
//            }
//        }
//    }
}

