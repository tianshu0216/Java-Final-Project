/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Message {
    private int id;
    private int userId; // User who posted the message
    private String message; // The content of the message
    private Date postedAt; // Timestamp when the message was posted
    private String status; // The status of the message (OPEN, CLAIMED)
    private Integer claimedBy; // User who claimed the message, can be null

    // Constructors, getters and setters

    public Message() {}

    public Message(int id, int userId, String message, Date postedAt, String status, Integer claimedBy) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.postedAt = postedAt;
        this.status = status;
        this.claimedBy = claimedBy;
    }

    // Getters and setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(Integer claimedBy) {
        this.claimedBy = claimedBy;
    }
}
