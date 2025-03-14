/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectbadclasses;

/**
 *
 * @author amade
 */
public class Staff {
   private String staffName, password, staffID;
   
   public Staff(String staffname, String pass, String staffID){
       this.staffName = staffname;
       this.password = pass;
       this.staffID=staffID;
   }

   public Staff(String staffname, String pass){
       this.staffName = staffname;
       this.password = pass;
      
   }
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
   
}
