/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author amade
 */
public class Connect {
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String DATABASE = "pos";
    private final String HOST = "localhost:3306";
    private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
    
    public Connection con;
    public Statement st;
    private static Connect connect;
    
    public ResultSet rs;
    public ResultSetMetaData rsm;
    
    public static Connect getInstance(){
        if(connect == null) return new Connect();
        return connect;
    }
    
    private Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            st = con.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    public ResultSet execQuery(String query){
        try {
            rs = st.executeQuery(query);
            rsm = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    
    public void execUpdate(String query){
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
}
