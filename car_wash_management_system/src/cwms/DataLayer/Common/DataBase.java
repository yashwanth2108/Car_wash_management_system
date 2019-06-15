/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.DataLayer.Common;

import java.sql.Connection;

/**
 *
 * @author USER
 */
public class DataBase {
     private Connection connection = null;
    
   
 public DataBase(ConnectionType connectionType){
        
   
     switch(connectionType){
            
           
         
   case MYSQL:connection = new DataConnection().getConnection();   
     
        } 
     }
    
    public Connection getConnection(){
      
  return connection;
    }
    
    
}
