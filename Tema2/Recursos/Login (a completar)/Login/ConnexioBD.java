/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercici1_TO_DO;


import java.sql.Connection;


/**
 *
 * @author joange
 */
public class ConnexioBD{

    Connection laConnexio = null;

    public void connect() {
            
            // TO DO

            System.out.println("Connection to SQLite has been established.");

       
    }

    public void disConnect() {
        
         // TO DO
        
    }
    
    
    public boolean validaUser(String user){
        
        boolean trobat=false;
         
        // TO DO
        
        return trobat;

    }
    
    /**
     * 
     * @param user
     * @return 0 if all correct
     * @return 1 if wrong user
     * @return 2 if wrong password
     */
    public int validaPass(String user,String pass){
        int res=-1;
        
        // TO DO
        
        return res;

    }
    
    public int insertUser(String user,String pass){
        int res=-1;
         // TO DO
        return res;

    }
    
}
