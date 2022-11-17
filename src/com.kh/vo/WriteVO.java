package com.kh.vo;

public class WriteVO {
    private String boardName;
    private int writeNum;
    private String writeName;
    private int memberNum;
    private String writeContents;
    private String writeDate;

    public WriteVO(String boardName, int writeNum, String writeName, int memberNum,
                   String writeContents, String writeDate) {
        this.boardName = boardName;
        this.writeNum = writeNum;
        this.writeName = writeName;
        this.memberNum = memberNum;
        this.writeContents = writeContents;
        this.writeDate = writeDate;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getWriteNum() {
        return writeNum;
    }

    public void setWriteNum(int writeNum) {
        this.writeNum = writeNum;
    }

    public String getWriteName() {
        return writeName;
    }

    public void setWriteName(String writeName) {
        this.writeName = writeName;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getWriteContents() {
        return writeContents;
    }

    public void setWriteContents(String writeContents) {
        this.writeContents = writeContents;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }
}