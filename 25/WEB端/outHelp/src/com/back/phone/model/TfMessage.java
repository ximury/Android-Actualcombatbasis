package com.back.phone.model;

public class TfMessage {
    private String messageId;

    private String userId;

    private String messageName;

    private String messageContent;

    private String messageTime;

    private String messageRead;

    private String messageSpare1;

    private String messageSpare2;

    private String messageSpare3;

    private String messageSpare4;

    private String messageSpare5;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName == null ? null : messageName.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime == null ? null : messageTime.trim();
    }

    public String getMessageRead() {
        return messageRead;
    }

    public void setMessageRead(String messageRead) {
        this.messageRead = messageRead == null ? null : messageRead.trim();
    }

    public String getMessageSpare1() {
        return messageSpare1;
    }

    public void setMessageSpare1(String messageSpare1) {
        this.messageSpare1 = messageSpare1 == null ? null : messageSpare1.trim();
    }

    public String getMessageSpare2() {
        return messageSpare2;
    }

    public void setMessageSpare2(String messageSpare2) {
        this.messageSpare2 = messageSpare2 == null ? null : messageSpare2.trim();
    }

    public String getMessageSpare3() {
        return messageSpare3;
    }

    public void setMessageSpare3(String messageSpare3) {
        this.messageSpare3 = messageSpare3 == null ? null : messageSpare3.trim();
    }

    public String getMessageSpare4() {
        return messageSpare4;
    }

    public void setMessageSpare4(String messageSpare4) {
        this.messageSpare4 = messageSpare4 == null ? null : messageSpare4.trim();
    }

    public String getMessageSpare5() {
        return messageSpare5;
    }

    public void setMessageSpare5(String messageSpare5) {
        this.messageSpare5 = messageSpare5 == null ? null : messageSpare5.trim();
    }
}