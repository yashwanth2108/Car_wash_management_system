/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.controller;

import cwms.DataLayer.DataAccess;
import cwms.model.Login;
import cwms.ui.*;
import java.sql.Connection;
import java.util.*;
import javax.swing.JOptionPane;


public class LoginHandler {
    public String dbUser; 
    private String dbPass;
    private String dbAccessType;
    private boolean loginPanel = false;
    
    public LoginHandler(String inUsername, char[] inPassword) {
        DataAccess dataAccess  = new DataAccess();
        
        ArrayList<Login> loginList =new ArrayList<>();
        loginList=dataAccess.GetLogin(inUsername);
        
        for (Login login : loginList) {
            dbUser = login.getUsername();
            dbPass = login.getPassword();
            dbAccessType = login.getAccessType();
        }
        
        
       if(isPasswordCorrect(inPassword))
       {
            
            loginPanel = true;
            Main main = new Main(dbAccessType);
            main.setVisible(true);
            
           // GRNMain grn=new GRNMain(dbAccessType);
      }
        else {
            JOptionPane.showMessageDialog(null, 
                "The password or username is incorrect please try again",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
        
     
        
    }

    private boolean isPasswordCorrect(char[] input )  {
        boolean isCorrect;
        
        //Connection conn=new Connection();
        char[] correctPass = this.dbPass.toCharArray();
        
        if( input.length != correctPass.length ) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(input, correctPass);
        }
        
        //clears the array
        Arrays.fill(correctPass,'0');
        
        return isCorrect;
    }
   
    
    public boolean isSuccessful(){
        return loginPanel;
    }
}
