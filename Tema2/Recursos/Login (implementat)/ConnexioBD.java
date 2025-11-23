/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elteupackage;


import Utils.Xifrar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnexioBD{

    Connection laConnexio = null;

    public void connect() {
            
        try {
            String URL="jdbc:sqlite:usersLogin.db";
            
            laConnexio=DriverManager.getConnection(URL);

            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException ex) {
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }

    public void disConnect() {
        
        try {
            laConnexio.close();
            laConnexio=null;
        } catch (SQLException ex) {
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public boolean validaUser(String user){
        boolean trobat=false;
        try {
            
            
            String SQL="select * from users where username=?";
            
            PreparedStatement pst=laConnexio.prepareStatement(SQL);
            
            pst.setString(1, user);
            
            ResultSet rst=pst.executeQuery();
            
            if (rst.next())  // the user exists
                trobat=true;
            else
                trobat=false;
            
            rst.close();
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return trobat;

    }
    
    public boolean validaPass(String user, String pass){
        boolean validat=false;
        
         try {
            
            
            String SQL="select * from users where username=? and password=?";
            
            PreparedStatement pst=laConnexio.prepareStatement(SQL);
            
            pst.setString(1, user);
            pst.setString(2, Xifrar.sha1(pass));
            
            
            ResultSet rst=pst.executeQuery();
            
            if (rst.next())  // the user exists
                validat=true;
            else
                validat=false;
            
            rst.close();
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return validat;

    }
    
    public int insertUser(String user, String pass){
        int res=-1;
        
        try {

            String SQL="insert into users values(?,?)";
            
            PreparedStatement pst=laConnexio.prepareStatement(SQL);
            
            pst.setString(1, user);
            pst.setString(2, Xifrar.sha1(pass));
            
            res=pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
}
