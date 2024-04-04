/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.math.BigDecimal;

public class Food {

    private int id;
    private String name;
    private int inventory;
    private double price;
    private Date expirationDate;
    private int demand;
    private Boolean isDonation;
    private Boolean isSurplus;
    private int retailerId;

    public Food() {
        // Default constructor
    }

    public Food(int id, String name, int inventory, double price, Date expirationDate, int demand, Boolean isDonation, Boolean isSurplus, int retailerId) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.price = price;
        this.expirationDate = expirationDate;
        this.demand = demand;
        this.isDonation = isDonation;
        this.isSurplus = isSurplus;
        this.retailerId = retailerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInventory() {
        return inventory;
    }

    public double getPrice() {
        return price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getDemand() {
        return demand;
    }

    public Boolean getIsDonation() {
        return isDonation;
    }

    public Boolean getIsSurplus() {
        return isSurplus;
    }

    public int getRetailerId() {
        return retailerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public void setIsDonation(Boolean isDonation) {
        this.isDonation = isDonation;
    }

    public void setIsSurplus(Boolean isSurplus) {
        this.isSurplus = isSurplus;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }
    
    


}
