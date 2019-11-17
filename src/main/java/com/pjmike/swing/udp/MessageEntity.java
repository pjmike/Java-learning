package com.pjmike.swing.udp;


/**
 * 消息实体
 *
 * @author pjmike
 */
public class MessageEntity {
    private String groupId;
    private String userName;
    private String text;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

