package model;

import java.sql.Date;

public class CareLog {

    private int logID;
    private Plant plant;
    private Date actionDate;
    private String actionType;
    private String description;

    public CareLog() {
    }

    public CareLog(int logID, Plant plant, Date actionDate, String actionType, String description) {
        this.logID = logID;
        this.plant = plant;
        this.actionDate = actionDate;
        this.actionType = actionType;
        this.description = description;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CareLog{" + "logID=" + logID + ", plant=" + plant + ", actionDate=" + actionDate + ", actionType=" + actionType + ", description=" + description + '}';
    }

}
