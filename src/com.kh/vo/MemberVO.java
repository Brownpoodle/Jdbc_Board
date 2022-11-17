package com.kh.vo;

public class MemberVO {
    private int memberNum;
    private String nickName;
    private String pwd;
    private String regDate;

    public MemberVO(int memberNum, String nickName, String pwd, String regDate) {
        this.memberNum = memberNum;
        this.nickName = nickName;
        this.pwd = pwd;
        this.regDate = regDate;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
