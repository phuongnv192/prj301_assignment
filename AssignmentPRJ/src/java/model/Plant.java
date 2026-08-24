package model;

import java.sql.Date;

public class Plant {

    private int plantID;
    private String plantName;
    private Category category;
    private User owner;
    private String imageUrl;
    private String healthStatus;
    private String note;
    private Date createdAt;

    public Plant() {
    }

    public Plant(int plantID, String plantName, int categoryID, int userID, Category category, User owner, String imageUrl, String healthStatus, String note, Date createdAt) {
        this.plantID = plantID;
        this.plantName = plantName;
        this.category = category;
        this.owner = owner;
        this.imageUrl = imageUrl;
        this.healthStatus = healthStatus;
        this.note = note;
        this.createdAt = createdAt;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Plant{" + "plantID=" + plantID + ", plantName=" + plantName + ", category=" + category + ", owner=" + owner + ", imageUrl=" + imageUrl + ", healthStatus=" + healthStatus + ", note=" + note + ", createdAt=" + createdAt + '}';
    }

}
