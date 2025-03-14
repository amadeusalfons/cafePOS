/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectbadclasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Orders {
    private String orderID;
    private String customerID;
    private String staffID;
    
    private String orderDate;
    private String orderStatus;
    private List<OrderDetail> orderdetail= new ArrayList<>(); 

            public Orders(String orderID, String customerID, String staffID, String orderDate, String orderStatus, List orderdetail) {
                this.orderID = orderID;
                this.customerID = customerID;
                this.staffID = staffID;
                
                this.orderDate = orderDate;
                this.orderStatus = orderStatus;
                this.orderdetail=orderdetail;
            }
 public Orders(String orderID, String staffID, String customerID, String orderDate, String orderStatus) {
                this.orderID = orderID;
                this.staffID = staffID;
                this.customerID = customerID;
                this.orderDate = orderDate;
                this.orderStatus = orderStatus;
                
            }
            public List<OrderDetail> getOrderdetail() {
                return orderdetail;
            }

            public String getOrderID() {
                return orderID;
            }

            public void setOrderID(String orderID) {
                this.orderID = orderID;
            }

            public String getStaffID() {
                return staffID;
            }

            public void setStaffID(String staffID) {
                this.staffID = staffID;
            }

            public String getCustomerID() {
                return customerID;
            }

            public void setCustomerID(String customerEmail) {
                this.customerID = customerEmail;
            }

            public String getOrderDate() {
                return orderDate;
            }

            public void setOrderDate(String orderDate) {
                this.orderDate = orderDate;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }
            
            public void addOrderDetail(OrderDetail orderDetail) {
            orderdetail.add(orderDetail);
        }

        // Method to calculate subtotal for the order
        public double calculateSubtotal() {
            double subtotal = 0;
            for (OrderDetail detail : orderdetail) {
                subtotal += detail.calculateSubtotal();
            }
            return subtotal;
        }

    // Method to calculate total for the order (subtotal - tax 10%)
        public double calculateTotal() {
            double subtotal = calculateSubtotal();
            double tax = subtotal * 0.1; // Assuming tax is 10%
            return subtotal + tax;
        }    

}

