/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectbadclasses;

import java.util.List;

/**
 *
 * @author User
 */

public  class Product {
            
            private String productName;
            private String productID;
            private String productType;
            private double productPrice;
            private static int itemStock=50;
            private String imageurl;

    public Product(String productName, String productID, String productType, double productPrice, String imageurl) {
        this.productName = productName;
        this.productID = productID;
        this.productType = productType;
        this.productPrice = productPrice;
        this.imageurl = imageurl;
    }

            
            public Product(){
            
            }

    public String getImageurl() {
        return imageurl;
    }

   
            public double getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(double productPrice) {
                this.productPrice = productPrice;
            }
            

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductID() {
                return productID;
            }

            public void setProductID(String productID) {
                this.productID = productID;
            }

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }
             public void setImageurl(String imageurl) {
                 this.imageurl = imageurl;
             }


            public static int getItemStock() {
                return itemStock;
            }

            public static void setItemStock(int itemStock) {
                Product.itemStock = itemStock;
            }
            

    public  void decreaseStock(int quantity){
        
            itemStock= itemStock- quantity;
            
    
     }

   
    
    
}

