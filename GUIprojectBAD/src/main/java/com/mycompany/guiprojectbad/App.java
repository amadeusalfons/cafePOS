package com.mycompany.guiprojectbad;

import com.mycompany.projectbadclasses.Beverage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.mycompany.projectbadclasses.Orders;
import com.mycompany.projectbadclasses.Product;
import com.mycompany.projectbadclasses.Food;
import java.util.ArrayList;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import com.mycompany.projectbadclasses.Orders;
import com.mycompany.projectbadclasses.OrderDetail;
import com.mycompany.projectbadclasses.Staff;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import util.Connect;


/**
 * JavaFX App
 */
public class App extends Application{
    //image
    
    private Vector <Product> product;
    private Vector <Staff> staffList;
    private Vector <Orders> orderData;
    
    private Connect connect = Connect.getInstance();
    
    private internalWindow signInWindow;
    private internalWindow mainWindow;
Scene scene, sceneLogin;
    ScrollPane sp = new ScrollPane();
    ScrollPane historyPane= new ScrollPane();

    
    
    //login
 GridPane gridPane;
    String StaffID;
      String staffName;
        Label userLabel ;
        TextField userTextField ;
        Button signInButton;
        Label passLabel;
        PasswordField passField ;
       
     
static StackPane pane;
BorderPane bp, bpTabel;
GridPane gp  ;
HBox top;
FlowPane fp;
MenuBar menubar;
Menu menu;
MenuItem addNewProduct;
MenuItem signOut, udpateDelete, History ;
TableView<OrderDetail> tabelOrder=new TableView<>();
TableView<OrderDetail> tabelCurrentOrder=new TableView<>();

//MENU add product
Label productnameL, productPriceL, productIdL, productImagel, productTypel;
TextField productname,productprice,productImageUrl;
BorderPane bpAdd;
GridPane gpAdd;
Scene sceneAdd;
Text productId;
Button submitAddNew;
ComboBox<String> productType,bevType,foodType;
//add
Label judul,foodtypel,bevtypel;
ArrayList <Product> productData= new ArrayList<>();

String currentId= "";
HBox buttoncontainer;

//order
GridPane OrderButtonBox;
Button submitOrderB , deleteOrderB;
ArrayList<Orders> orderList= new ArrayList<>();
TextField custEmail, custName;
Label custEmailL, custNameL;
//Order page
VBox judulOrder;
 Label JudulOrderLabel;
Label namaStaff;
Scene sceneOrder;
BorderPane bpOrder;
GridPane gpOrder;
ComboBox<String> paymentType ;
Label orderStatussL, paymentTypeL;
TextField orderStatuss ;
Label subTotalL, totalL, taxL;
Label subtotal, total;
Button confirmPaymentB;
String userName, userPass;
Button backtomenuButton;
//update

Label updateproductnameL, updateproductPriceL, updateproductIdL, updateproductImagel, updateproductTypel, updatejudul,updatefoodtypel,updatebevtypel;
TextField updateproductname,updateproductprice,updateproductImageUrl;
BorderPane bpUpdate;
GridPane gpUpdate;
Scene sceneUpdate;
ComboBox<String> updateProductId;
ArrayList<String> idproduct= new ArrayList();
Button submitUpdate, submitDelete;
ComboBox<String> updateproductType,updatebevType,updatefoodType;
HBox updatetop;
HBox updatebuttoncontainer;

//history
Scene sceneHistory;

BorderPane bpHistory;
TableView <Orders> tabelHistory= new TableView<>() ;
Label judulHistory;
Button toMenu;
VBox headerBox;
HBox HistorybuttonBox;
Label totalPenjualanL;
TextField totalPenjualan= new TextField();

 ArrayList <Product> productHistory = new ArrayList<>();
 


public App(){
        this.product = new Vector<>();
        this.staffList = new Vector<>();
        this.orderData= new Vector <>();
        
}
    private String generateProductID() throws SQLException{
         String query= "SELECT SUBSTRING(productID,2,3 ) AS productID FROM product ORDER BY productID DESC LIMIT 1 ";
         connect.rs=connect.execQuery(query);
         String lastID= "FR004";
         
            if (connect.rs.next()){
            lastID=connect.rs.getString("productID");


            }
            //ambil numeric dan increment
            int numericPart= Integer.parseInt(lastID);
            numericPart++;
            
            //di formatt
            String newID= String.format("P%03d", numericPart);
            
            //diset
           return newID;
         }
    
         private String generateOrderID() throws SQLException {
            String query = "SELECT SUBSTRING(orderID, 2, 3) AS orderID FROM orders ORDER BY orderID DESC LIMIT 1";
            connect.rs = connect.execQuery(query);
            String lastID = "O000"; // Default initial ID

                    if (connect.rs.next()) {
                        String lastIDPart = connect.rs.getString("orderID");
                        if (lastIDPart != null && !lastIDPart.isEmpty()) {
                            lastID = "O" + lastIDPart;
                        }
                    }

                    // ambil numeric part terus increment
            int numericPart = Integer.parseInt(lastID.substring(1));
            numericPart++;

            // Format  new ID
            String newID = String.format("O%03d", numericPart);

            // Return  new ID
            return newID;
            
        }
         private String generateCustID(String email ,String name) throws SQLException {
                String checkEmailQuery= "SELECT customerID from customer WHERE customerEmail ='"+ email +"'";
                 connect.rs = connect.execQuery(checkEmailQuery);

              if (connect.rs.next()) {
                  // kalo Email uda ada, return the existing customer ID
                  return connect.rs.getString("customerID");
              }

                  String query = "SELECT SUBSTRING(customerID, 2, 3) AS customerID FROM customer ORDER BY customerID DESC LIMIT 1";
                  connect.rs = connect.execQuery(query);
                  String lastID = "C000"; // Default initial ID

                          if (connect.rs.next()) {
                              String lastIDPart = connect.rs.getString("customerID");
                              if (lastIDPart != null && !lastIDPart.isEmpty()) {
                                  lastID = "C" + lastIDPart;
                              }
                          }

                          // Extract numeric part and increment
                          int numericPart = Integer.parseInt(lastID.substring(1));
                          numericPart++;

                          // Format the new ID
                          String newID = String.format("C%03d", numericPart);
                            addCustData( name ,  email, newID);
                          // Return the new ID
                          return newID;
                      }
         
      


        public List<Product> getData() {
                        List<Product> products = new ArrayList<>();
                        Connect db = Connect.getInstance();

                        String query = "SELECT p.productID, p.productName, p.productType, p.productPrice, p.productImg, " +
                                       "f.foodType, b.drinkType " +
                                       "FROM product p " +
                                       "LEFT JOIN food f ON p.productID = f.foodID " +
                                       "LEFT JOIN beverage b ON p.productID = b.beverageID";
                        
                        try {
                            ResultSet rs = db.execQuery(query);
                            while (rs.next()) {
                                String productID = rs.getString("productID");
                                String productName = rs.getString("productName");
                                String productType = rs.getString("productType");
                                double productPrice = rs.getDouble("productPrice");
                                String imageurl = rs.getString("productImg");
                                String foodType = rs.getString("foodType");
                                String drinkType = rs.getString("drinkType");

                                if ("Food".equals(productType)) {
                                    products.add(new Food(foodType, productName, productID, productType, productPrice, imageurl));
                                } else if ("Beverages".equals(productType)) {
                                    products.add(new Beverage(drinkType, productName, productID, productType, productPrice, imageurl));
                                } else {
                                    products.add(new Product(productName, productID, productType, productPrice, imageurl));
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
        return products;
    }



              

         private void getIdData(){
             productData.clear();
                idproduct.clear(); 
            
            String query = "SELECT * FROM product";
            connect.rs = connect.execQuery(query);
            
            try {
                while(connect.rs.next()){
                    String name = connect.rs.getString("productName");
                    String id = connect.rs.getString("productID");
                    String type = connect.rs.getString("productType");
                    Double price = connect.rs.getDouble("productPrice");
                    String img = connect.rs.getString("productImg");
                    
                    Product productTemp = new Product(name, id, type, price, img);
                    idproduct.add(id);
                    productData.add(productTemp); // Add to ArrayList
                }
            } catch (SQLException e) {
            }
        }
        
        private void getPass(){
        String query = "SELECT * FROM staff";
            connect.rs = connect.execQuery(query);
            
            try {
                while(connect.rs.next()){
                    String userName = connect.rs.getString("staffName");
                    String password = connect.rs.getString("password");
                    String id = connect.rs.getString("staffID");
                    
                    Staff staff = new Staff(userName, password, id);
                    staffList.add(staff);
                     
                     
                     
                }
            } catch (Exception e) {
            }
        }
        
      private void getOrderData() {
                    String query = "SELECT * FROM orders JOIN orderdetail ON orders.orderID = orderdetail.orderID JOIN product ON product.productID=orderdetail.productID ORDER BY orders.orderID";
                   
                    try {
                        connect.rs = connect.execQuery(query);

                        while (connect.rs.next()) {
                            String orderId = connect.rs.getString("orderID");
                            String custId = connect.rs.getString("customerID");
                            String staffId = connect.rs.getString("staffID");
                            String orderDate = connect.rs.getString("orderDate");
                            String orderStatus = connect.rs.getString("orderStatus");

                            // Check kalau ordernya dah ada di orderData
                            Orders order = getOrderFromList(orderId);

                            // kalau order gaada , bikin baru and masukin  ke orderData
                            if (order == null) {
                                order = new Orders(orderId, staffId, custId, orderDate, orderStatus);
                                orderData.add(order);
                            }

                            String productId = connect.rs.getString("productID");
                            String productName = connect.rs.getString("productName");
                            double productPrice = connect.rs.getDouble("productPrice");
                            String productImg = connect.rs.getString("productImg");
                            String productType = connect.rs.getString("productType");

                            Product currentProduct = new Product(productName, productId, productType, productPrice, productImg);

                            // Add order detail to the current order
                            OrderDetail orderDetail = new OrderDetail(connect.rs.getInt("orderQuantity"), currentProduct);
                            order.addOrderDetail(orderDetail);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                       
                        closeResources();
                    }
                }

                private Orders getOrderFromList(String orderId) {
                    for (Orders order : orderData) {
                        if (order.getOrderID().equals(orderId)) {
                            return order;
                        }
                    }
                    return null;
                }

                private void closeResources() {
               
                    if (connect.rs != null) {
                        try {
                            connect.rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                private void UpdateStatus(String orderID, String status){
                    String query=" UPDATE orders SET orderStatus = '"+ status+"'  WHERE orderID = '"+ orderID +"'  ";
                connect.execUpdate(query);
           
           }


        
        private void addData(String id, String name, String type, Double price, String url, String fbType, int Flag){
            // Construct SQL INSERT statement with proper formatting
            // 1 FOOD 2 BEV
            String query = "INSERT INTO product VALUES ('" + id + "', '" + name + "', '" + type + "', " + price + ", '" + url + "')";
            String query2 = "INSERT INTO food VALUES ('" + id + "','" + fbType + "')";
            String query3 = "INSERT INTO beverage VALUES ('" + id + "','" + fbType + "')";
            // Execute the SQL statement
           
            if(Flag == 1){
                 connect.execUpdate(query);
                connect.execUpdate(query2);
            }else if (Flag == 2){
                 connect.execUpdate(query);
                connect.execUpdate(query3);
            }

        }
        
        private void updateData(String id, String name, String type, Double price, String url, String fbType, int Flag){
            // Construct SQL INSERT statement with proper formatting
            // 1 FOOD 2 BEV
            String query = "UPDATE product SET productName ='"+ name+"' WHERE productID= '"+id +"' ";
            String query2 = "UPDATE product SET productType ='"+ type+"' WHERE productID= '"+id +"' ";
            String query3 = "UPDATE product SET productPrice ='"+ price+"' WHERE productID= '"+id +"' ";
            String query4 = "UPDATE product SET productImg ='"+ url+"' WHERE productID= '"+id +"' ";
           
            //query untuk update food / bev 
             String query5 = "UPDATE food set foodType = '"+fbType +"'WHERE foodID =  '"+ id +"' ";
            String query6 = "UPDATE beverage set drinkType = '"+fbType +"'WHERE beverageID =  '"+ id +"' ";
            // Execute the SQL statement
           
            if(Flag == 1){
                 connect.execUpdate(query);
                 connect.execUpdate(query2);
                 connect.execUpdate(query3);
                 connect.execUpdate(query4);
                 
                connect.execUpdate(query5);
            }else if (Flag == 2){
                  connect.execUpdate(query);
                 connect.execUpdate(query2);
                 connect.execUpdate(query3);
                 connect.execUpdate(query4);
                connect.execUpdate(query6);
            }

        }
        private void DeleteProduct (String ID ){
        
        String query = "DELETE FROM product WHERE productID = '"+ID +"'";
            connect.execUpdate(query);
        }
        
        private void addCustData( String custName, String custMail, String custID) throws SQLException{
           
            // Construct SQL INSERT statement with proper formatting
            // 1 FOOD 2 BEV
            String query = "INSERT INTO customer VALUES ('" + custID + "', '" + custName + "', '" + custMail + "')";
            
            // Execute the SQL statement
           
                 connect.execUpdate(query);
        }

        private void addOrderHeader(Orders order){
            
            String orderID=order.getOrderID();
            String customerID= order.getCustomerID();
            String staffID=order.getStaffID();

            String orderDate=order.getOrderDate();
            String orderStatus= order.getOrderStatus();
        String query = "INSERT INTO orders VALUES ('" + orderID + "', '" + customerID + "', '" + staffID + "', '" + orderDate + "', '"+ orderStatus + "')";
        connect.execUpdate(query);
        for (OrderDetail detail: order.getOrderdetail()){
            String orderDetailid= orderID+detail.getProduct().getProductID();
        String queryDetails= "INSERT INTO orderdetail VALUES ('"+ orderDetailid +"', '"+ orderID +"', '"+detail.getProduct().getProductID() +"','"+detail.getOrderQuantity()  + "' )";
            connect.execUpdate(queryDetails);
        }
          
        }
        
        public void initialisasiMain(){
        bp= new BorderPane();
        gp= new GridPane();
        fp=new FlowPane();
        
        History= new MenuItem(" See History ");
        addNewProduct= new MenuItem("Add New Product");
        signOut = new MenuItem("sign out");
        udpateDelete= new MenuItem(" Update and Delete Product");
        menubar= new MenuBar();
        menu= new Menu("Menu");
        bpTabel= new BorderPane();
        submitOrderB= new Button("Submit Order");
                custEmail=new TextField();
                custEmailL= new Label("Customer Email");
                custName=  new TextField();
                custNameL= new Label("Customer Name");
                
        OrderButtonBox= new GridPane();
        deleteOrderB= new Button("Delete Selection");
        }

        public void addMain(){
          
            ///masukin menu item // otw masukin menu" item lainnya
            menu.getItems().addAll(addNewProduct,udpateDelete,History, signOut);
            menubar.getMenus().add(menu);
        bp.setTop(menubar);
        sp.setContent(fp);
        
        bp.setCenter(sp);
   
        }
        
        public void alignIt(){
        fp.setHgap(50);
        fp.setVgap(50);
        menubar.setMinHeight(50);
  
        }
        
//method add Menu baru
        private internalWindow initializeAddMenu() throws SQLException{
            internalWindow internalwindow= new internalWindow();
            
         //MENU add product
          gpAdd= new GridPane();
          
          bpAdd= new BorderPane();
        
          top= new HBox();
         gpAdd.setVgap(10);
          judul= new Label ("Add New New Product Here");
         top.setStyle("-fx-background-color:black; ");
         judul.setStyle("-fx-text-fill: white;");
         buttoncontainer= new HBox();
        
        Button closeButton= new Button("X");
        
        foodType= new ComboBox<>();
            foodType.getItems().addAll("Main Course");
            foodType.getItems().addAll("Snacks");
            foodType.getItems().addAll("Desserts");
             foodType.getSelectionModel().selectFirst();
        bevType= new ComboBox<>();
            bevType.getItems().addAll("Hot","Iced");
            bevType.getSelectionModel().selectFirst();
            
        foodtypel= new Label("Food Type");
        setFontStyle(foodtypel);
        bevtypel=new Label("Beverage Type");
        setFontStyle(bevtypel);
        submitAddNew= new Button("add new product");
            productnameL= new Label("Product Name        :  ");
            setFontStyle(productnameL);
            
           productPriceL= new Label("Product Price       :  ");
            setFontStyle(productPriceL);
           productImagel= new Label("Product Image URL   :  ");
            setFontStyle(productImagel);
              productIdL= new Label("Product ID          :  ");
            setFontStyle(productIdL);
            productTypel= new Label("Product Type        :  ");
            setFontStyle(productTypel);
        productname=new TextField();
        productprice= new TextField();
        productImageUrl=new TextField();
        //sementara:0
        String prdctID=generateProductID();
        productId= new Text (prdctID);
        productType= new ComboBox<>();
            productType.getItems().addAll("Food");
            productType.getItems().addAll("Beverages");
            productType.getSelectionModel().selectFirst();
       //ADD LABEL
                gpAdd.add(productnameL, 0, 0);
                gpAdd.add(productPriceL, 0, 1);
                gpAdd.add(productImagel, 0, 2);
                gpAdd.add(productIdL, 0, 3);
                gpAdd.add(productTypel,0,4);
                
        //ADD FIELD
                 gpAdd.add(productname,1,0);
                 gpAdd.add(productprice,1,1); 
                 gpAdd.add(productImageUrl,1,2);
                 gpAdd.add(productId,1,3);
                 gpAdd.add(productType,1,4);
                 productname.setMinWidth(400);
              
                productType.setOnAction(e -> {
                    updateProductTypeFields();
                });

                // Initial call to set up fields based on the default selection
                updateProductTypeFields(); 
         
                 
                 submitAddNew.setMinWidth(100);
                 submitAddNew.setAlignment(Pos.CENTER);
                 buttoncontainer.getChildren().add(submitAddNew);
                 buttoncontainer.setAlignment(Pos.CENTER);
         top.getChildren().add(judul);
            judul.setMinWidth(600);
         top.getChildren().add(closeButton);
         bpAdd.setTop(top);
        bpAdd.setBottom(buttoncontainer);
        bpAdd.setCenter(gpAdd);
          BorderPane.setMargin(buttoncontainer,new Insets(10));
          BorderPane.setMargin(gpAdd,new Insets(8));
     
        gpAdd.setHgap(10);
        gpAdd.setVgap(10);
        
        
        
         internalwindow.setRoot(bpAdd);
         internalwindow.makeDragable(bpAdd);
        
         bpAdd.setStyle("-fx-background-color:#D3D3D3; ");
         internalwindow.makeResizeable(20);
         internalwindow.setCloseButton(closeButton);
 
        
         submitAddNew.setOnAction(eh->{
                    if(isValid()==true){
                         double price=Double.parseDouble(productprice.getText());


                          MenuDisplay menudisplay= new MenuDisplay();
                   new Alert(Alert.AlertType.INFORMATION," Item Added Successfully",ButtonType.OK).showAndWait();
                       if (productType.getValue().equals("Food")){
                                   Product currentproduk= new Food (foodType.getValue(),productname.getText(),prdctID,productType.getValue(),price,productImageUrl.getText());
                                  //menudisplay.addButton=new Button();

                                  addData(prdctID, productname.getText(), productType.getValue(), price, productImageUrl.getText(),foodType.getValue(),1);
                                   product.add(currentproduk);
                                      //SQL INSERT
                                   


                                    menudisplay.layoutWindow(currentproduk);
                                    refreshMenu();
//                                     menudisplay.PassButton().setOnAction(e->{
//
//                                   currentId=prdctID;
//                                       System.out.println(currentId);
//                                     Integer q= menudisplay.PassQuantity().getValue();
//
//                                        SearchBasedOnId(q);
//                                   });
                                    System.out.println(currentId);
                                   // fp.getChildren().add(menudisplay);
                                    pane.getChildren().remove(internalwindow); 
                                    }


                                    else if (productType.getValue().equals("Beverages")){
                                        Product currentproduct= new Beverage(bevType.getValue(),productname.getText(),prdctID,productType.getValue(),price,productImageUrl.getText());
                                    product.add(currentproduct);
                                    System.out.println(bevType.getValue());
                                    addData(prdctID, productname.getText(), productType.getValue(), price, productImageUrl.getText(),bevType.getValue(),2);
                                    //SQL INSERT
                                    System.out.println(bevType.getValue());
                                    menudisplay.layoutWindow(currentproduct);

                                    refreshMenu();
//                                     menudisplay.PassButton().setOnAction(e->{
//                                   currentId=prdctID;
//                                       System.out.println(currentId);
//                                        Integer q= menudisplay.PassQuantity().getValue();
//
//                                        SearchBasedOnId(q);
//                                   });
                                    System.out.println(currentId);
                                  //  fp.getChildren().add(menudisplay);
                                    pane.getChildren().remove(internalwindow); 
                             }

                        try {
                            productId.setText(generateProductID()); // Generate new Product ID
                        } catch (SQLException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                        }

         }
   
         });
     
         
        
        return internalwindow;
        
        
        }
        
        
        private internalWindow updateMenu() throws SQLException{
            
             getIdData();
            
            updateProductId=new ComboBox<>();
            ObservableList<String> ids= FXCollections.observableArrayList(idproduct);
                         updateProductId.getItems().addAll(ids);
                        
                         updateProductId.getSelectionModel().selectFirst();
                         
            submitDelete= new Button("delete");
                        bpUpdate= new BorderPane();
                        gpUpdate= new GridPane();
                        updatetop= new HBox();
                     gpUpdate.setVgap(10);
                      updatejudul= new Label ("Update Product Here");
                     updatetop.setStyle("-fx-background-color:black; ");
                     updatejudul.setStyle("-fx-text-fill: white;");
                     updatebuttoncontainer= new HBox();
                     sceneUpdate= new Scene(bpUpdate);
                    Button closeButton= new Button("X");

                    updatefoodType= new ComboBox<>();
                        updatefoodType.getItems().addAll("Main Course");
                         updatefoodType.getItems().addAll("Snacks");
                         updatefoodType.getItems().addAll("Desserts");
                          updatefoodType.getSelectionModel().selectFirst();
                          
                     updatebevType= new ComboBox<>();
                         updatebevType.getItems().addAll("Hot","Iced");
                         updatebevType.getSelectionModel().selectFirst();

                     updatefoodtypel= new Label("Food Type");
                    setFontStyle( updatefoodtypel);
                     updatebevtypel=new Label("Beverage Type");
                    setFontStyle(updatebevtypel);
                    submitUpdate= new Button("Update Product");
                         updateproductnameL= new Label("Product Name        :  ");
                        setFontStyle(updateproductnameL);

                        updateproductPriceL= new Label("Product Price       :  ");
                        setFontStyle(updateproductPriceL);
                        
                        updateproductImagel= new Label("Product Image URL   :  ");
                        setFontStyle(updateproductImagel);
                        
                       updateproductIdL= new Label("Product ID          :  ");
                        setFontStyle(updateproductIdL);
                         updateproductTypel= new Label("Product Type        :  ");
                        setFontStyle(updateproductTypel);
                     updateproductname=new TextField();
                     updateproductprice= new TextField();
                    updateproductImageUrl=new TextField();
                   
                     updateproductType= new ComboBox<>();
                         updateproductType.getItems().addAll("Food");
                         updateproductType.getItems().addAll("Beverages");
                         updateproductType.getSelectionModel().selectFirst();
                   //ADD LABEL
                            gpUpdate.add( updateproductnameL, 0, 0);
                            gpUpdate.add( updateproductPriceL, 0, 1);
                            gpUpdate.add( updateproductImagel, 0, 2);
                            gpUpdate.add( updateproductIdL, 0, 3);
                            gpUpdate.add( updateproductTypel,0,4);

                    //ADD FIELD
                             gpUpdate.add( updateproductname,1,0);
                             gpUpdate.add( updateproductprice,1,1); 
                             gpUpdate.add( updateproductImageUrl,1,2);
                             gpUpdate.add( updateProductId,1,3);
                             gpUpdate.add( updateproductType,1,4);
                              updateproductname.setMinWidth(400);



                            updateproductType.setOnAction(e -> {
                                updateProductTypeFieldsforUpdate();
                            });

                            // Initial call to set up fields based on the default selection
                            updateProductTypeFieldsforUpdate(); 


                             submitUpdate.setMinWidth(100);
                             submitUpdate.setAlignment(Pos.CENTER);
                             updatebuttoncontainer.getChildren().add(submitUpdate);
                             updatebuttoncontainer.getChildren().add(submitDelete);
                             updatebuttoncontainer.setAlignment(Pos.CENTER);
                             updatebuttoncontainer.setPadding(new Insets(10));
                     updatetop.getChildren().add( updatejudul);
                        updatejudul.setMinWidth(600);
                      updatetop.getChildren().add(closeButton);
                      
                       
                     bpUpdate.setTop(updatetop);
                    bpUpdate.setBottom(updatebuttoncontainer);
                    bpUpdate.setCenter(gpUpdate);
                    
                      BorderPane.setMargin( updatebuttoncontainer,new Insets(10));
                      
                      BorderPane.setMargin(gpUpdate,new Insets(8));
                    //align
                    gpUpdate.setHgap(10);
                    gpUpdate.setVgap(10);

        internalWindow updateMenu= new internalWindow();
            
         updateMenu.setRoot(bpUpdate);
         updateMenu.makeDragable(bpUpdate);
         //kasi jarak insetsmargin padding
         bpUpdate.setStyle("-fx-background-color:#D3D3D3; ");
         updateMenu.makeResizeable(20);
         updateMenu.setCloseButton(closeButton);
         
       

         setTextFieldOnID(updateProductId.getSelectionModel().getSelectedItem());
          updateProductId.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
        setTextFieldOnID(newValue);
        
    });
          submitUpdate.setOnAction(e->{
              String idd=updateProductId.getSelectionModel().getSelectedItem();
              double price=Double.parseDouble(updateproductprice.getText());
               new Alert(Alert.AlertType.INFORMATION," Item Updated Successfully",ButtonType.OK).showAndWait();
                       if (updateproductType.getValue().equals("Food")){
                                   Product currentproduk= new Food (updatefoodType.getValue(),updateproductname.getText(),idd,updateproductType.getValue(),price,updateproductImageUrl.getText());
                                 

                                  updateData(idd, updateproductname.getText(), updateproductType.getValue(), price, updateproductImageUrl.getText(),updatefoodType.getValue(),1);
                                 refreshMenu();
                                    
                                    }

                                    else if (updateproductType.getValue().equals("Beverages")){
                                        Product currentproduct= new Beverage(updatebevType.getValue(),updateproductname.getText(),idd,updateproductType.getValue(),price,updateproductImageUrl.getText());
                                  
                                   
                                    updateData(idd, updateproductname.getText(), updateproductType.getValue(), price, updateproductImageUrl.getText(),updatebevType.getValue(),2);
                                    pane.getChildren().remove(updateMenu); 
                                    refreshMenu();
                                   
                                  

                             }}    
                        );
          submitDelete.setOnAction ( eh-> {
          String idd=updateProductId.getSelectionModel().getSelectedItem();
           new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure You Want to Delete '"+idd+"'",ButtonType.OK).showAndWait();
           DeleteProduct(idd);
           refreshMenu();
          }
          
          
          );
          
        return updateMenu;
          
        }
        
        private void setTextFieldOnID(String idnya){
            if (productData.isEmpty()) {
        return; // Exit if productData is empty
            }
            for (Product p: productData){
                if(p.getProductID() == null ? idnya == null : p.getProductID().equals(idnya)){
                updateproductImageUrl.setText(p.getImageurl());
                updateproductname.setText(p.getProductName());
                updateproductprice.setText(String.valueOf(p.getProductPrice()));
                if (!updateproductType.getItems().contains(p.getProductType())) {
                updateproductType.getItems().add(p.getProductType());
            }
            updateproductType.getSelectionModel().select(p.getProductType());
        
                }
            
            
            }
 
        }
    
        private void setFontStyle(Label n){
        n.setFont(Font.font("Arial", 14));
       
        }
        
                   private void updateProductTypeFieldsforUpdate() {
                if (updateproductType.getValue().equals("Food")) {
                    if (!gpUpdate.getChildren().contains(updatefoodtypel) && !gpUpdate.getChildren().contains(updatefoodType)) {
                        gpUpdate.add(updatefoodtypel, 0, 5);
                        gpUpdate.add(updatefoodType, 1, 5);
                    }
                    gpUpdate.getChildren().removeAll(updatebevtypel, updatebevType);
                } else if (updateproductType.getValue().equals("Beverages")) {
                    if (!gpUpdate.getChildren().contains(updatebevtypel) && !gpUpdate.getChildren().contains(updatebevType)) {
                        gpUpdate.add(updatebevtypel, 0, 5);
                        gpUpdate.add(updatebevType, 1, 5);
                    }
                    gpUpdate.getChildren().removeAll(updatefoodtypel, updatefoodType);
                }
            }
            
                    private void updateProductTypeFields() {
                if (productType.getValue().equals("Food")) {
                    if (!gpAdd.getChildren().contains(foodtypel) && !gpAdd.getChildren().contains(foodType)) {
                        gpAdd.add(foodtypel, 0, 5);
                        gpAdd.add(foodType, 1, 5);
                    }
                    gpAdd.getChildren().removeAll(bevtypel, bevType);
                } else if (productType.getValue().equals("Beverages")) {
                    if (!gpAdd.getChildren().contains(bevtypel) && !gpAdd.getChildren().contains(bevType)) {
                        gpAdd.add(bevtypel, 0, 5);
                        gpAdd.add(bevType, 1, 5);
                    }
                    gpAdd.getChildren().removeAll(foodtypel, foodType);
                }
            }
                   
                   
                   private boolean isValid(){
                
                            if(productname.getText().isEmpty()||productprice.getText().isEmpty()||productImageUrl.getText().isEmpty()||productImageUrl.getText().isEmpty()||productType.getValue().isEmpty()){
                                new Alert(Alert.AlertType.ERROR,"Please Fill all the fields first",ButtonType.OK).showAndWait();
                                
                                return false;

                    }
                    else {

                     }
                                 try {
                                            Integer.valueOf(productprice.getText());
                                        } catch (NumberFormatException e) {
                                            new Alert(Alert.AlertType.ERROR, "Product price must be an integer", ButtonType.OK).showAndWait();
                                            return false;
                                        }
                                  
                   return true;
                   }
   
        

                    
                    
                    private void refreshMenu(){
                        List<Product> listofProducts = getData();
                         fp.getChildren().clear();
                         if(listofProducts!=null){
                             for (Product currentProduct: listofProducts){
                       
                                 MenuDisplay currentmenu= new MenuDisplay();
                            
                                currentmenu.layoutWindow(currentProduct);
                                fp.getChildren().add(currentmenu);
                                 fp.setMargin(currentmenu, new Insets(5,5,0,50));
                          currentmenu.PassButton().setOnAction(e-> {
                              
                               currentId=currentProduct.getProductID();
                              Integer q= currentmenu.PassQuantity().getValue();
                                 System.out.println(q);
                              SearchBasedOnId(q, listofProducts);}
                           )  ; }
                
                         }
                         
                      
                    }
              
                    public void CurrentOrder(){
                        setTableCurrentOrder();
                   
                    //HandleAddOrder();
                    bpTabel.setBottom(OrderButtonBox);
                    bpTabel.setCenter(tabelCurrentOrder);
                    
                    bpTabel.setPadding(new Insets(8));
                    custEmailL.setMinWidth(30);
                  
                   
                     BorderPane.setAlignment(OrderButtonBox,Pos.CENTER);
                     OrderButtonBox.add(custEmailL, 0, 0); 
                     OrderButtonBox.add(custEmail, 1, 0); 
                     OrderButtonBox.add(custNameL, 0, 1); 
                     OrderButtonBox.add(custName, 1, 1); 
                     OrderButtonBox.add(submitOrderB, 0, 2);
                     OrderButtonBox.add(deleteOrderB, 1, 2);
                  
                     BorderPane.setMargin(OrderButtonBox,new Insets(10));
                            OrderButtonBox.setPadding(new Insets(20));
                            OrderButtonBox.setAlignment(Pos.CENTER);
                            OrderButtonBox.setHgap(9);
                            OrderButtonBox.setVgap(9);
                            
                            
                        deleteOrderB.setOnAction(e -> 
                            tabelCurrentOrder.getItems().removeAll(tabelCurrentOrder.getSelectionModel().getSelectedItem()));
                        
                        submitOrderB.setOnAction(e->{
                            if(!custEmail.getText().isEmpty()){
                        String custemail=custEmail.getText();
                        String customername="guest";
                                try {
                                    
                                        if (custName.getText().isEmpty()){
                                           customername=custemail;
                                        }
                                        else {
                                        customername=custName.getText();
                                        }
                                        
                                        String custID= generateCustID(custemail,customername) ;
                                        addOrdersFromTabel(custID);
                                    
                                        
                                } catch (SQLException ex) {
                                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else{
                             new Alert(Alert.AlertType.ERROR,"Please Insert Customer Email First.",ButtonType.OK).showAndWait();
                            }
                         
                        
                        });
                 
                    
                    }
               
                    
                    private void addOrdersFromTabel(String custId) throws SQLException{
                       orderList.clear();
                        new Alert(Alert.AlertType.CONFIRMATION,"You Are About to Place the Order, Click OK to proceed",ButtonType.OK).showAndWait();
                                  //alert box insert nama cust then alert nama cust
                        //TABLENYA PAKE SELECT SQL
                            List<OrderDetail> orderDetails= new ArrayList<>(tabelCurrentOrder.getItems());
                            String orderId= generateOrderID();
                            String staffId= StaffID;
                            System.out.println(staffId);
                            String orderDate= LocalDate.now().toString();
                            String orderStatus= "waiting for payment";
                            
                            
                          Orders order= new Orders(orderId, custId,staffId , orderDate,orderStatus,orderDetails);
                         
                         addOrderHeader(order);
                         orderList.add(order);
                         
                         Stage stageOrder= (Stage) submitOrderB.getScene().getWindow(); //untuk mendapatkan stage object yang terasosiasi dgn scene yang contains the submitOrderB. button
                                stageOrder.setScene(sceneOrder);
                                OrderPage(orderId );
                                
                              // debug  System.out.println(orderList.get(0).getOrderdetail().get(0).getProduct().getProductID());
                               //debug  System.out.println(orderList.get(0).getCustomerEmail());
           
                    }
                    
     private void createTableColumns(TableView<OrderDetail> tableView) {
                TableColumn<OrderDetail, String> idColumn = new TableColumn<>("Product ID");
                idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduct().getProductID()));
                idColumn.setMinWidth(90);

                TableColumn<OrderDetail, String> nameColumn = new TableColumn<>("Product Name");
                nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduct().getProductName()));
                nameColumn.setMinWidth(90);

                TableColumn<OrderDetail, Double> priceColumn = new TableColumn<>("Price");
                priceColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getProduct().getProductPrice()).asObject());
                priceColumn.setMinWidth(90);

                TableColumn<OrderDetail, Integer> quantityColumn = new TableColumn<>("Quantity");
                quantityColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getOrderQuantity()).asObject());
                quantityColumn.setMinWidth(90);

                TableColumn<OrderDetail, Double> subtotalColumn = new TableColumn<>("Subtotal");
                subtotalColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().calculateSubtotal()).asObject());
                subtotalColumn.setMinWidth(90);

                tableView.getColumns().addAll(idColumn, nameColumn, priceColumn, quantityColumn, subtotalColumn);
                
                
                double scrollPaneWidth = tableView.getWidth(); 
                    double columnWidthO = scrollPaneWidth / 5; 

                    idColumn.setPrefWidth(columnWidthO);
                    nameColumn.setPrefWidth(columnWidthO);
                    priceColumn.setPrefWidth(columnWidthO);
                    quantityColumn.setPrefWidth(columnWidthO);
                    subtotalColumn.setPrefWidth(columnWidthO);
                  
                    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            }
     
     private void setTableCurrentOrder() {
            tabelCurrentOrder.getColumns().clear(); // Clear existing columns to avoid duplicates
            createTableColumns(tabelCurrentOrder); // Add columns using the helper method
        }
     private void setTableOrder() {
            tabelCurrentOrder.getColumns().clear(); // Clear existing columns to avoid duplicates
            createTableColumns(tabelOrder); // Add columns using the helper method
        }
     
                    

                    
                    public void SearchBasedOnId(Integer quantity, List<Product> p){
                            
                        if (!currentId.isEmpty()||!currentId.equals("")){
                          
                                
                             for (int i= 0;i<p.size();i++){
                                 if(currentId.equals(p.get(i).getProductID())){
                                     
                                  OrderDetail orderDetail = new OrderDetail(quantity, p.get(i)); // Assuming order quantity is 1 for simplicity
                                    tabelCurrentOrder.getItems().add(orderDetail);
                                       
                                 }
                             }
                            
                            
                            // match and retrieve data from arralist ( ADA search loop engine and then add data to Orders
                            currentId="";
                        }
                        
                    }
                    
                    
        public void InitializeOrderPage() {
                JudulOrderLabel = new Label("Order Receipt");
                
                JudulOrderLabel.setId("order-title");
                
                paymentTypeL = new Label("Payment Type");
                paymentType = new ComboBox();
                paymentType.getItems().addAll("Cash", "QRIS");
                paymentType.getSelectionModel().selectFirst();

                judulOrder = new VBox(10); 
                judulOrder.setStyle("-fx-background-color: #0078D7 ; ");
                judulOrder.setAlignment(Pos.CENTER); // Center alignment BUAT VBox
                bpOrder = new BorderPane();
                gpOrder = new GridPane();
                gpOrder.setHgap(10); 
                gpOrder.setVgap(10); 
                gpOrder.setPadding(new Insets(20)); 

                namaStaff = new Label();
                
                namaStaff.setStyle("-fx-text-fill: WHITE; ");
                subTotalL = new Label("SubTotal: ");
                totalL = new Label("Total: ");
                taxL = new Label("Tax: 10%");
                subtotal = new Label();
                total = new Label(); 
                confirmPaymentB = new Button("Payment Done");
                taxL.setMinWidth(80);

                orderStatuss = new TextField();
                orderStatuss.setText("waiting for payment");
                orderStatuss.setEditable(false);
                orderStatussL = new Label("Order Status");
                orderStatuss.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                orderStatussL.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                
                // Add components to gpOrder GridPane with alignment
                gpOrder.add(subTotalL, 0, 0);
                gpOrder.add(subtotal, 1, 0);
                gpOrder.add(taxL, 0, 1);
                gpOrder.add(new Label(), 1, 1); // Spacer
                gpOrder.add(totalL, 0, 2);
                gpOrder.add(total, 1, 2);
                gpOrder.add(paymentTypeL, 0, 3);
                gpOrder.add(paymentType, 1, 3);
                gpOrder.add(orderStatussL, 0, 4);
                gpOrder.add(orderStatuss, 1, 4);
                gpOrder.add(confirmPaymentB, 1, 5);

                // Configure alignment
                GridPane.setHalignment(subtotal, HPos.RIGHT);
                GridPane.setHalignment(total, HPos.RIGHT);
                GridPane.setHalignment(confirmPaymentB, HPos.RIGHT);

                JudulOrderLabel.setFont(Font.font("Arial", 30));
                judulOrder.getChildren().addAll(JudulOrderLabel, namaStaff);

                bpOrder.setTop(judulOrder);
                BorderPane.setAlignment(judulOrder, Pos.CENTER); // Center alignment for BorderPane top
                BorderPane.setMargin(tabelOrder, new Insets(20, 40, 10, 40));
                bpOrder.setCenter(tabelOrder);
                bpOrder.setBottom(gpOrder);
                BorderPane.setAlignment(gpOrder, Pos.CENTER); // Center alignment for BorderPane bottom
            }

           private void OrderPage(String ID) {
                double totalSub = 0.0;
                tabelOrder.setMaxSize(1000, 600);
                tabelOrder.getColumns().clear();
                createTableColumns(tabelOrder);

                tabelOrder.getItems().clear();
                for (Orders currentOrder : orderList) {
                    List<OrderDetail> orderdetails = currentOrder.getOrderdetail();
                    for (OrderDetail currentdetail : orderdetails) {
                        tabelOrder.getItems().add(currentdetail);
                        totalSub += currentdetail.calculateSubtotal();
                    }
                }

                subtotal.setText(String.format("$%.2f", totalSub));
                total.setText(String.format("$%.2f", totalSub * 1.10)); // 10% tax

                namaStaff.setText(staffName);

                backtomenuButton = new Button("Back to Menu");
                backtomenuButton.setId("backMENU-button");
                
                backtomenuButton.setOnAction(eh -> {
                     UpdateStatus(ID,"payment Done" );
                    new Alert(Alert.AlertType.INFORMATION, "Going back to Menu, Order's on the Kitchen", ButtonType.OK).showAndWait();
                    bpTabel.setCenter(tabelCurrentOrder);
                    
                    resetOrderPage();
                    Stage stage = (Stage) backtomenuButton.getScene().getWindow();
                    stage.setScene(scene);
                    refreshMenu();
                    refreshHistoryTable ();
                     gpOrder.getChildren().remove(backtomenuButton);
                   
                });

                confirmPaymentB.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure That The Payment Has Been Completed.", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            orderStatuss.setText("Order passed to kitchen");
                            
                            
                           
                            gpOrder.add(backtomenuButton, 2, 5);
                        }
                    });
                });

                setQrisOnPaymentType(paymentType.getSelectionModel().getSelectedItem());
                paymentType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    setQrisOnPaymentType(newValue);
                });
                 
    }
           
           

    private void resetOrderPage() {
            tabelCurrentOrder.getItems().clear();
            tabelOrder.getItems().clear();
            orderList.clear();
            custEmail.clear();
            custName.clear();
            paymentType.getSelectionModel().selectFirst();
            orderStatuss.setText("waiting for payment");
            subtotal.setText("$0.00");
            total.setText("$0.00");
        }

      
    
        private void setQrisOnPaymentType(String type) {
    bpOrder.setRight(null); // Clear any existing right node
    if (type.equals("QRIS")) {
        try {
            System.out.println("Setting QRIS image"); // Debugging statement
            Image image = new Image(getClass().getResourceAsStream("/images/qris.png"));
            if (image.isError()) {
                System.out.println("Error loading image: " + image.getException().getMessage());
            } else {
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(300); // Set the desired width
                imageView.setFitHeight(300); // Set the desired height
                imageView.setPreserveRatio(true); // Preserve the aspect ratio
                
                imageView.setId("QRISImg"); // Assign an ID to the image view for styling
                bpOrder.setRight(imageView);
                bpOrder.requestLayout(); // Force UI update
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    } else {
        System.out.println("Payment type is not QRIS, clearing QR image"); // Debugging statement
    }
}

    
    
    
    public void constructSignInPage() {

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getStyleClass().add("grid-pane");
        sceneLogin= new Scene (gridPane,800,800);
        
        // Apply CSS to the scene

        Image image = new Image(getClass().getResourceAsStream("/images/logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300); // Set the desired width
        imageView.setFitHeight(300); // Set the desired height
        imageView.setPreserveRatio(true); // Preserve the aspect ratio

        
        gridPane.setId("signInGrid"); // Assign an ID to the grid pane for styling
        imageView.setId("logoImg"); // Assign an ID to the image view for styling

        
        gridPane.add(imageView, 0, 0, 2, 1); // Add the image to the first row, spanning two columns
        GridPane.setHalignment(imageView, HPos.CENTER); // Center the image horizontally


        //Declarations
        userLabel = new Label("Username:");
        userTextField = new TextField();
        
      signInButton = new Button("Sign In");
       passLabel = new Label("Password:");
        passField = new PasswordField();
        
        gridPane.add(userLabel, 0, 1);
        gridPane.add(userTextField, 1, 1);
        gridPane.add(passLabel, 0, 2);
        gridPane.add(passField, 1, 2);
        gridPane.add(signInButton, 1, 3);
       
               // Align the nodes to the center of their cells
        GridPane.setHalignment(userLabel, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(passLabel, javafx.geometry.HPos.RIGHT);
        GridPane.setHalignment(signInButton, javafx.geometry.HPos.RIGHT);

                // Center the button horizontally within its cell
        HBox buttonBox = new HBox(signInButton);
        buttonBox.setAlignment(Pos.CENTER);
        gridPane.add(buttonBox, 1, 3);
        

        signInButton.setOnAction(e -> {
                    getPass();
                    
            userName = userTextField.getText();
            userPass = passField.getText();
            if(userName.isEmpty() || userPass.isEmpty()){
                
                showAlert(AlertType.ERROR, "Error", "All fields must be filled");
            }else if (authenticate(userName, userPass)) {
                
                new Alert(Alert.AlertType.INFORMATION, "Login successful!", ButtonType.OK).showAndWait();
               
                
             
                userTextField.clear();
                passField.clear();
                
                
                                    
                Stage stage = (Stage) signInButton.getScene().getWindow(); // Get the current stage
                stage.setScene(scene); // Set the main scene
                
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password.", ButtonType.OK).showAndWait();
        }

        });
        
                sceneLogin.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }
    
private boolean authenticate(String userName, String userPass) {
    for (Staff staff : staffList) {
        if (staff.getStaffName().equals(userName) && staff.getPassword().equals(userPass)) {
          StaffID=staff.getStaffID();
          staffName=staff.getStaffName();
            return true;
            
        }
    }
    return false;
}

        public void HistoryPage() {
    // Initialize components
   
    refreshHistoryTable ();
              
                judulHistory = new Label("History Penjualan");
                toMenu = new Button("Back to Menu");
              
                bpHistory = new BorderPane();
                sceneHistory = new Scene(bpHistory, 1400, 600);

                // Style the title
                judulHistory.setFont(Font.font("Serif", FontWeight.BOLD, 34));
                judulHistory.setAlignment(Pos.CENTER);
                judulHistory.setId("history-title");

                // Style the button
                
                toMenu.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                    toMenu.setId("back-button");
                // Layout for the header
                headerBox = new VBox(10, judulHistory);
                headerBox.setStyle("-fx-background-color: #0078D7;");
                headerBox.setAlignment(Pos.CENTER);
                headerBox.setPadding(new Insets(20, 0, 20, 0));
                totalPenjualanL=new Label ("TOTAL SALES: ");
                totalPenjualan.setEditable(false);
                // Layout for the button
                 HistorybuttonBox = new HBox(10, totalPenjualanL,totalPenjualan,toMenu);
                 
                HistorybuttonBox.setAlignment(Pos.CENTER);
                HistorybuttonBox.setPadding(new Insets(10, 0, 20, 0));

                // Add components to GridPane
                historyPane.setPadding(new Insets(20));
               
                historyPane.setContent(tabelHistory);
                
                     historyPane.setFitToWidth(true);  
                        historyPane.setFitToHeight(true); 
                
                // Set layout in BorderPane
                bpHistory.setTop(headerBox);
                bpHistory.setCenter(historyPane);
                bpHistory.setBottom(HistorybuttonBox);
                

                // Add margins around components
//                BorderPane.setMargin(headerBox, new Insets(10, 10, 10, 10));
                
                BorderPane.setMargin(HistorybuttonBox, new Insets(10, 10, 10, 10));
                
                toMenu.setOnAction(eh-> {
                Stage stage = (Stage) toMenu.getScene().getWindow();
                    stage.setScene(scene);
                    refreshMenu();
                
                });
                
                
               
                sceneHistory.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
                
                
                
    }
        public void setHistoryTable(){


                TableColumn<Orders, String> idColumn = new TableColumn<>("Order ID");
                idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrderID()));
                idColumn.setMinWidth(120);

                TableColumn<Orders, String> custColumn = new TableColumn<>("Customer ID");
                custColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCustomerID()));
                custColumn.setMinWidth(120);

                TableColumn<Orders, String> staffColumn = new TableColumn<>("Staff ID");
                staffColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStaffID()) );
                staffColumn.setMinWidth(120);

                TableColumn<Orders,String> dateColumn = new TableColumn<>("Order Date");
                dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrderDate()));
                dateColumn.setMinWidth(120);
                
                
                TableColumn<Orders,String> statusColumn = new TableColumn<>("Order Status");
                statusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrderStatus()));
                statusColumn.setMinWidth(120);

                TableColumn<Orders, Double> totalColumn = new TableColumn<>("Total");
                totalColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().calculateTotal()).asObject());
                totalColumn.setMinWidth(120);
                
                
                
                        //format untuk totalnya
                         totalColumn.setCellFactory(column -> new TableCell<Orders, Double>() {
                                            @Override
                                            protected void updateItem(Double item, boolean empty) {
                                                super.updateItem(item, empty);
                                                if (empty || item == null) {
                                                    setText(null);
                                                } else {
                                                    setText(String.format("%.2f", item));
                                                }
                                            }
                                        });
                    
                
                
                
                tabelHistory.getColumns().addAll(idColumn, custColumn, staffColumn, dateColumn, statusColumn,totalColumn);
                
                                double scrollPaneWidth = historyPane.getWidth(); 
                    double columnWidth = scrollPaneWidth / 6; 

                    idColumn.setPrefWidth(columnWidth);
                    custColumn.setPrefWidth(columnWidth);
                    staffColumn.setPrefWidth(columnWidth);
                    dateColumn.setPrefWidth(columnWidth);
                    statusColumn.setPrefWidth(columnWidth);
                    totalColumn.setPrefWidth(columnWidth);
                    tabelHistory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
             
            }
     
        private void refreshHistoryTable () {
             orderData.clear();
           getOrderData();
            if (orderData != null) {
                ObservableList<Orders> orderiObs = FXCollections.observableArrayList(orderData);
               
                Platform.runLater(()-> {
                    
                    tabelHistory.getItems().clear();
                   tabelHistory.setItems(orderiObs);
                    try {
                        calculateAndDisplayTotal();
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
             
            }
        }
        
            private void calculateAndDisplayTotal() throws SQLException {
                 double totalSUM = 0.0;
         String query= "SELECT SUM(total_order_cost) AS TotalSum\n" +
                        "FROM (\n" +
                        "    SELECT od.orderID, SUM(od.orderQuantity * p.productPrice) *1.1 AS total_order_cost\n" +
                        "    FROM orderdetail od\n" +
                        "    JOIN product p ON od.productID = p.productID\n" +
                        "    GROUP BY od.orderID\n" +
                        ") AS order_totals";
                 connect.rs=connect.execQuery(query);
      
         
            if (connect.rs.next()){
            totalSUM=connect.rs.getDouble("TotalSum");


            }
            
                // Display or use the total as needed (e.g., set it to a label)
                    totalPenjualan.setText(String.format("%.2f", totalSUM)); 
             
            }

        
            
    @Override
    public void start(Stage stage) throws SQLException {
        constructSignInPage();
       
        initialisasiMain();
        
        addMain();
        alignIt();
        HistoryPage();
        
        setTableCurrentOrder();
        setTableOrder();
         setHistoryTable();
        InitializeOrderPage();
        CurrentOrder();
        
        
                 sceneOrder= new Scene(bpOrder,1400,600); // lebarsceneOrder x pjg
        pane = new StackPane();
        sp.setContent(fp);
        bp.setCenter(sp);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        
        bp.setRight(bpTabel);
        pane.getChildren().add(bp);
        scene = new Scene(pane, 1240, 800);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
         sceneOrder.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        refreshMenu();
        
       
      
        stage.setScene(sceneLogin);
        stage.setTitle("POSin");
        
         addNewProduct.setOnAction(e->{try {
                    pane.getChildren().add(initializeAddMenu());
                   } catch (SQLException ex) {
                       Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                   }
});  
         
         udpateDelete.setOnAction(e->{
         
            try {
                pane.getChildren().add(updateMenu());
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
         
         }); 
         
         History.setOnAction(eh->{
         
         stage.setScene(sceneHistory);
         
         
         
         });
         
         
         
         
         
         signOut.setOnAction(e ->{stage.setScene(sceneLogin); new Alert(Alert.AlertType.INFORMATION, "Sign out successful!", ButtonType.OK).showAndWait();});
        stage.show();
    }

    
    private static void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
    
    

}