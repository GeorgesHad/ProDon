package com.example.prodon.ui.sqliteHelper;

public class GroupModel {
    private String groupName;
    private int groupId,coachId;

    public GroupModel(String groupName, int groupId, int coachId) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.coachId = coachId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
}
