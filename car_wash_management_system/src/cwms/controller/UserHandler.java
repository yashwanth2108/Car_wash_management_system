/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.controller;

import cwms.DataLayer.DataAccess;
import cwms.model.Login;
import java.util.ArrayList;


public class UserHandler {
    DataAccess Dataaccess  = new DataAccess();
    
    public UserHandler() {
        Dataaccess.CreateProcedure();
    }
    
    public ArrayList<Login> getData() {
        ArrayList<Login> userList = Dataaccess.GetLogin();
        return userList;
    }
    
    public int UpdateUser(int id, String user, String pass, String access){
        int result = Dataaccess.UpdateUser(id, user, pass, access);
        return result;
    }
    
    public int removeUser(int id){
        int result = Dataaccess.RemoveUser(id);
        return result;
    }
    public void addUser(String uname,String pwd,String access) {
        Dataaccess.CreateUser(uname, pwd, access);
    }
}
