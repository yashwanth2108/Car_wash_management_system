/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.DataLayer.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DataConnection implements IDataConnection{

    @Override
    public Connection getConnection() {
        //loading driver
        loadDriver();
           
     //Intialize connection object to null
        Connection conn = null;
      
     try{
            //Create the connection object from driver
       conn = DriverManager.getConnection(ConstantsNeeded.MYSQL_URL,ConstantsNeeded.MYSQL_USERNAME,ConstantsNeeded.MYSQL_PASSWORD);
        }
        catch(SQLException e)
        {
            System.err.println("SQL Error(s) as follows:");
        
            while (e != null) 
            {
     
                System.err.println("SQL Return Code: " + e.getSQLState());
     
                System.err.println("  Error Message: " + e.getMessage());
      
                System.err.println(" Vendor Message: " + e.getErrorCode());
                    
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        return conn;
    }
     private void loadDriver(){
        
     try {
            Class.forName(ConstantsNeeded.MYSQL_DRIVER);
            System.out.println("You have loaded a driver!\n");
	} 
        catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException = " + ConstantsNeeded.MYSQL_DRIVER);
            System.err.println(e.getMessage());
        }
    
    }
    
}
