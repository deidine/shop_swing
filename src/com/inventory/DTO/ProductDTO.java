/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.DTO;

/**
 *
 * @author asjad
 */

// Data Transfer Object (DTO) class for Products

public class ProductDTO {

    private int prodID, salleID, quantity, userID;
    private double costPrice, sellPrice;
    private Double totalCost, totalPayee, totalRcue,totalChange;
    private String prodCode, prodName, date, suppCode, custCode, custName, brand;
private int mesure_rakor;
int getMesureRakor(){
    return this.mesure_rakor;
}
void setMesureRakor(int mesure_rakor){
    this.mesure_rakor=mesure_rakor;
}
    public int getProdID() {
        return prodID;
    }

    public void setSalleID(int salleID) {
        this.salleID = salleID;
    }
    
    public int getSalleID() {
        return salleID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalPayee() {
        return totalPayee;
    }

    public void setTotalPayee(Double totalPayee) {
        this.totalPayee = totalPayee;
    }

    public Double getTotalRcue() {
        return totalRcue;
    }

    public void setTotalRcue(Double totalRcue) {
        this.totalRcue = totalRcue;
    }

    public Double getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(Double totalChange) {
        this.totalChange = totalChange;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
