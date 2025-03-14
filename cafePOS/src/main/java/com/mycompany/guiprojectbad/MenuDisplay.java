/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guiprojectbad;

import com.mycompany.projectbadclasses.Beverage;
import com.mycompany.projectbadclasses.Food;
import com.mycompany.projectbadclasses.Product;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 *
 * @author User
 */
public class MenuDisplay extends Region {
  Button addButton;
  Spinner<Integer> quantitySpinner;
    private Node contentNode;

    public void setContent(Node contentNode) {
        getChildren().clear(); // Clear existing content if any
        this.contentNode = contentNode;
        getChildren().add(contentNode);
    }
  public void setRoot(Node node) {
        getChildren().add(node);
}
  public void layoutWindow(Product product){
                Label lnama,lid,lprice,lfoodtype,lbevtype;
            BorderPane bp= new BorderPane();
            GridPane gp=new GridPane();
           quantitySpinner= new Spinner<>(1,50,1);
            quantitySpinner.setMaxWidth(80);
            quantitySpinner.setId("quantity-spinner");
          String name= product.getProductName();
          String ID= product.getProductID();
          double Price= product.getProductPrice();
                lnama=new Label("nama: "+name);
                lid=new Label("ID: "+ID);
                lprice=new Label("price: "+String.valueOf(Price));
                
         addButton=new Button("add");
           addButton.setMinWidth(150);
           addButton.setId("add-button");

          String url = product.getImageurl();

                 Image image = new Image(url);
                // Creating an image view from URL
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200); // Setting the width of the image
                imageView.setFitHeight(200); // Setting the height of the image
                imageView.setId("menu-image");
                 // Adding the image view to the grid pane
                gp.add(imageView, 0, 0);
                gp.add(lnama, 0, 1);
                gp.add(lid, 0, 2);
                gp.add(lprice,0,3);
              if (product instanceof Food){
                lfoodtype= new Label (((Food)product).getFoodType());
                 gp.add(lfoodtype,0,4);
                 lfoodtype.setFont(Font.font("Arial", 14));
                }
                else if(product instanceof Beverage) {
                lbevtype= new Label (((Beverage)product).getDrinkType());
                lbevtype.setFont(Font.font("Arial", 14));
                gp.add(lbevtype,0,4);  }
              //style
                bp.setStyle("-fx-background-color: #808080; ");
                bp.setStyle("-fx-border-color: black; ");
                lnama.setFont(Font.font("Arial", 14));
                lid.setFont(Font.font("Arial", 14));
                lprice.setFont(Font.font("Arial", 14));
                
                
                
           setRoot(bp);
           //align
           gp.setAlignment(Pos.CENTER);
           BorderPane bottomPane = new BorderPane(addButton);
           bottomPane.setCenter(addButton);
           
           bottomPane.setRight(quantitySpinner);
            addButton.setAlignment(Pos.CENTER);
            
            //
            bp.setBottom(bottomPane);
          bp.setCenter(gp);
          
          bp.setId("menu-display");
           bp.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
          
         
         
                
  
  

}
  public Button PassButton(){
  return addButton;
  
  }
    public Spinner<Integer> PassQuantity(){
        
  return quantitySpinner;
  
  }
          
}
