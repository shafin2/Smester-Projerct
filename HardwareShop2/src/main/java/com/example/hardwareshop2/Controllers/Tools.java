package com.example.hardwareshop2.Controllers;

import java.io.*;

public class Tools implements Serializable {
    private static final long serialVersionUID = -3040096452457271695l;
    private String toolName;
    private int toolQuantity;
    private double pricePerUnit;


    private int toolId;

    private double totalAmount;

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Tools(String toolName, int toolQuantity, double pricePerUnit, int toolid) {
        this.toolId=toolid;
        this.toolName = toolName;
        this.toolQuantity = toolQuantity;
        this.pricePerUnit = pricePerUnit;
        totalAmount=toolQuantity*pricePerUnit;
    }
    public Tools(int toolid,String toolName, int toolQuantity, double pricePerUnit,double totalamnt) {
        this.toolId=toolid;
        this.toolName = toolName;
        this.toolQuantity = toolQuantity;
        this.pricePerUnit = pricePerUnit;
        this.totalAmount=totalamnt;
    }
    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getToolQuantity() {
        return toolQuantity;
    }

    public void setToolQuantity(int toolQuantity) {
        this.toolQuantity = toolQuantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }


}
