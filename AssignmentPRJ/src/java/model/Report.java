package model;

import java.sql.Date;

public class Report {

    private int reportID;
    private User user;
    private Plant plant;
    private String title;
    private String description;
    private String reportStatus;
    private String adminReply;
    private Date createdAt;

    public Report() {
    }

    public Report(int reportID, User user, Plant plant, String title, String description, String reportStatus, String adminReply, Date createdAt) {
        this.reportID = reportID;
        this.user = user;
        this.plant = plant;
        this.title = title;
        this.description = description;
        this.reportStatus = reportStatus;
        this.adminReply = adminReply;
        this.createdAt = createdAt;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getAdminReply() {
        return adminReply;
    }

    public void setAdminReply(String adminReply) {
        this.adminReply = adminReply;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Report{" + "reportID=" + reportID + ", user=" + user + ", plant=" + plant + ", title=" + title + ", description=" + description + ", reportStatus=" + reportStatus + ", adminReply=" + adminReply + ", createdAt=" + createdAt + '}';
    }

}
