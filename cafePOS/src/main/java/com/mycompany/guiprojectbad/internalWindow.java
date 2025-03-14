/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guiprojectbad;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 *
 * @author User
 */
public class internalWindow extends Region {
    private Node contentNode;
    private Scene scene;
    
    public Scene getInternalScene(){
        return this.scene;
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }


    public void setContent(Node contentNode) {
        getChildren().clear(); // Clear existing content if any
        this.contentNode = contentNode;
        getChildren().add(contentNode);
    }
  public void setRoot(Node node) {
        getChildren().add(node);
} 

        //just for encapsulation
       private static class Delta {
           double x, y;
                }

                //we can select nodes that react drag event
                public void makeDragable(Node what) {
                    final Delta dragDelta = new Delta();
                    what.setOnMousePressed(mouseEvent -> {
                        dragDelta.x = getLayoutX() - mouseEvent.getScreenX();
                        dragDelta.y = getLayoutY() - mouseEvent.getScreenY();
                        //also bring to front when moving
                        toFront();
                    });
                    what.setOnMouseDragged(mouseEvent -> {
                        setLayoutX(mouseEvent.getScreenX() + dragDelta.x);
                        setLayoutY(mouseEvent.getScreenY() + dragDelta.y);
                    });
       }
                    //CURRENT STATE
                    private boolean RESIZE_BOTTOM;
                    private boolean RESIZE_RIGHT;
 public void makeResizeable(double mouseBorderWidth){
             this.setOnMouseMoved(mouseEvent-> 
                    {
                    //koordinat local window
                        double mouseX=mouseEvent.getX();
                        double mouseY=mouseEvent.getY();
                    //size window
                        double width= this.getBoundsInLocal().getWidth();
                        double height= this.getBoundsInLocal().getHeight();
                    //if on the edge, change stage and cursor
                    if (Math.abs(mouseX-mouseY)<mouseBorderWidth && Math.abs(mouseY-height)<mouseBorderWidth){
                               RESIZE_RIGHT=true;
                               RESIZE_BOTTOM=true; 
                              this.setCursor(Cursor.NW_RESIZE);
                            }
                    else{
                                RESIZE_RIGHT=false;
                                RESIZE_BOTTOM=false; 
                                 this.setCursor(Cursor.DEFAULT);
                    }
                    }
             
             );
             this.setOnMouseDragged(mouseEvent->
                    {

                        //resize root
                        //ambil content region dalam window
                        Region region= (Region)getChildren().get(0);
                        //reset logic based on state
                        if(RESIZE_BOTTOM&&RESIZE_RIGHT){
                        region.setPrefSize(mouseEvent.getX(),mouseEvent.getY());
                        }
                        else if (RESIZE_RIGHT) {
                        region.setPrefWidth(mouseEvent.getX());
                        }
                        else if (RESIZE_BOTTOM) {
                        region.setPrefHeight(mouseEvent.getY());
                        }
                        
                    });
    
 }
 

public void setCloseButton(Button b){
b.setOnAction(e->((Pane)getParent()).getChildren().remove(this));
 ProductIdGenerator.decrementCounter();
}


}
