/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectbadclasses;

/**
 *
 * @author User
 */
public class Beverage extends Product {
        private String drinkType;
        //hot or cold

    public Beverage(String drinkType, String productName, String productID, String productType, double productPrice, String imageurl) {
        super(productName, productID, productType, productPrice, imageurl);
        this.drinkType = drinkType;
    }

    
           
            public String getDrinkType() {
                return drinkType;
            }

            public void setDrinkType(String drinkType) {
                this.drinkType = drinkType;
            }

}