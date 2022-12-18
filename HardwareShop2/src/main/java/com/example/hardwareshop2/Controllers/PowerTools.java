package com.example.hardwareshop2.Controllers;

import java.io.Serializable;

public class PowerTools extends Tools  {
    private static final long serialVersionUID = -949923485854571829l;
    public PowerTools(String toolName, int toolQuantity, double pricePerUnit,int productID) {
        super(toolName, toolQuantity, pricePerUnit,productID);
    }
}
