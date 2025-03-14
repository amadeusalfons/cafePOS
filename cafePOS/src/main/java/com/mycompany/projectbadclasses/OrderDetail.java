/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectbadclasses;

/**
 *
 * @author User
 */
public class OrderDetail {
   private String OrderID;
    private int orderQuantity;
    
    private Product product;


    public OrderDetail( int orderQuantity, Product product) {
       // this.OrderID = OrderID;
        this.orderQuantity = orderQuantity;
        this.product=product;
       
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }
    

  
   

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    
    
    public double calculateSubtotal (){
    double price= product.getProductPrice();
    double subtotal;
    
    subtotal=price * orderQuantity;
    return subtotal ;
    }
    
    


}