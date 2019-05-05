package de.dhbw.studium.http;

public class TopListObject {
    private String groupName;
    private int duration;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return groupName + ":" + duration / 1000 + "s \n";
    }
}
