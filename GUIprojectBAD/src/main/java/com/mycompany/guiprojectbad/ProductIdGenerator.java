/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guiprojectbad;

/**
 *
 * @author User
 */

public class ProductIdGenerator {
    private static int counter = 2;

    public static String generateID() {
        counter++;
        return String.format("P%03d", counter);
    }

   
    public static void decrementCounter() {
        
            counter--;
        
    }
      public static void incrementCounter() {
        
            counter++;
        
    }
    
    
}