/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectbadclasses;

/**
 *
 * @author User
 */public class Food extends Product {
    private String foodType;

    public Food(String foodType,String productName, String productID, String productType, double productPrice, String imageurl) {
        super(productName, productID, productType, productPrice, imageurl);
        this.foodType=foodType;
    }

    
    public Food() {
    }

       
        public String getFoodType() {
            return foodType;
        }

        public void setFoodType(String foodType) {
            this.foodType = foodType;
        }


}