/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.DataLayer;


import cwms.DataLayer.Common.ConnectionType;
import cwms.DataLayer.Common.DataBase;
import cwms.model.Employee;
import cwms.model.Login;
import cwms.model.Supplier;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import cwms.ui.*;
import java.sql.SQLIntegrityConstraintViolationException;
/**
 *
 * @author USER
 */
public class DataAccess {
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;  
    Connection con = null;
    
    double basicsalary=0.0;
    double grosspay=0.0;
    double allowance=0.0;
    int otdays=0;
    int noofdays=0;
    double dailyrate=0.0;
    double otbonus=0.0;
    double deducted_epf=0.0;
    double deducted_etf=0.0;
    double comp_epf=0.0;
    
     public void CreateUser(String uname,String pwd,String access) {
        DataBase db=new DataBase(ConnectionType.MYSQL);
        
        
        try {
            Connection connect=db.getConnection();
            String query="insert into login(username,password,accesstype) values(?,?,?)";
            statement  = connect.prepareStatement(query); 
            statement.setString(1,uname);
            statement.setString(2,pwd);
            statement.setString(3, access);
            statement.executeUpdate();
            
                statement.close();
                connect.close();
            }
            catch(SQLException e) {
                System.err.println("SQL Error(s) as follows:");
                while (e != null) {
                    System.err.println("SQL Return Code: " + e.getSQLState());
                    System.err.println("  Error Message: " + e.getMessage());
                    System.err.println(" Vendor Message: " + e.getErrorCode());
                    e = e.getNextException();
                }	
            } 
            catch(Exception e) {
                System.err.println(e);
            }  
        
        
    }
    //updating existing account
    public int UpdateUser(int id, String user, String pass, String access){
        DataBase db = new DataBase(ConnectionType.MYSQL);
        int result = 0; 
        
        try {
            Connection conn = db.getConnection();
            CallableStatement cs = conn.prepareCall("{call sp_users(?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, user);
            cs.setString(3, pass);
            cs.setString(4, access);
            result = cs.executeUpdate();
            
            cs.close();
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        
        return result;        
        
        
        
    }
    
    //delete existing user
    public int RemoveUser(int id){
        DataBase db = new DataBase(ConnectionType.MYSQL);
        int result = 0; 
        
        try {
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM login WHERE id = ?");
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        
        return result;
                   
    }

    //create procedure to update user info
    public void CreateProcedure() {
        DataBase db = new DataBase(ConnectionType.MYSQL);
        
        String queryDrop = "DROP PROCEDURE IF EXISTS sp_users";
        
        String query = "CREATE PROCEDURE sp_users "
            + " ( "
            + " IN p_id INT(11),  "
            + " IN p_user	 VARCHAR(45), "
            + " IN p_pass	 VARCHAR(45), "
            + " IN p_access	VARCHAR(45) "
            + " ) "
            + "BEGIN "
            + " UPDATE login "
            + " SET "
            + " username = p_user, "
            + " password = p_pass,"
            + " accesstype = p_access"
            + " WHERE id = p_id ; "
            + "END ";
        
        try {
            Connection conn = db.getConnection();
            Statement pstmtDrop = conn.createStatement();
            pstmtDrop.execute(queryDrop);
            pstmtDrop.close();
            
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }      
        
        
        try {
            Connection conn = db.getConnection();
            Statement pstmt = conn.createStatement();
            pstmt.executeUpdate(query);
            pstmt.close();
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
    }
    //adding employee details to database
    public boolean addEmployee(String inempname,String inempnic,String inempadd,String inempphone,String inempgender,String inposition)
    {
       boolean result=false;
        DataBase db=new DataBase(ConnectionType.MYSQL);
          
            try
            {
                Connection con=db.getConnection();
                String query="INSERT INTO employee(emp_name,emp_nic,emp_address,emp_phone,emp_gender,emp_designation) values(?,?,?,?,?,?)";
                
                  statement  = con.prepareStatement(query); 
                  statement.setString(1,inempname);
                  statement.setString(2,inempnic);
                  statement.setString(3,inempadd );
                  statement.setString(4,inempphone);
                  statement.setString(5,inempgender);
                  statement.setString(6, inposition);
                  statement.executeUpdate();
                  
                  statement.close();
                  con.close();
                  result=true;
            }
            catch(SQLException e) 
            {
                result=false;
                System.err.println("SQL Error(s) as follows:");
                while (e != null)
                {
                    System.err.println("SQL Return Code: " + e.getSQLState());
                    System.err.println("  Error Message: " + e.getMessage());
                    System.err.println(" Vendor Message: " + e.getErrorCode());
                    e = e.getNextException();
                }	
            } 
            catch(Exception e)
            {
                 result=false;
                System.err.println(e);
            } 
            return result;
            
        }
    public boolean addFingerPrint(String ename,String fcode)
    {
        DataBase db = new DataBase(ConnectionType.MYSQL);
       boolean result = false; 
       
        try {
            Connection conn = db.getConnection();
             PreparedStatement psmt = conn.prepareStatement("UPDATE employee SET emp_fingerprint=? WHERE emp_name=?");
      
      psmt.setString(1, fcode);
      psmt.setString(2, ename);
     
      psmt.executeUpdate();
            result=true;
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        
       return result;
   
    }
    //deleting employee from employee and adding to past employees
    public int deleteEmployee(int inempid)
    {
        
        DataBase db = new DataBase(ConnectionType.MYSQL);
        int result = 0; 
      
        try {  
            Connection conn = db.getConnection();
              
            //getting the leaving employees to a resultset and then inserting to past_employees table
            
            
            Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = pstmt.executeQuery("SELECT * FROM employee where emp_id =" +inempid);
            
            
            Connection co=db.getConnection();
            String insquery="INSERT INTO past_employees VALUES(?,?,?,?,?,?,?,?,?,?)";
            statement  = co.prepareStatement(insquery);
            while(resultSet.next())
            {
                  statement.setInt(1,resultSet.getInt(1));
                  statement.setString(2,resultSet.getString(2));
                  statement.setString(3,resultSet.getString(3) );
                  statement.setString(4,resultSet.getString(4));
                  statement.setString(5, resultSet.getString(5));
                  statement.setString(6, resultSet.getString(6));
                  statement.setDouble(7, resultSet.getDouble(7));
                  statement.setDouble(8, resultSet.getDouble(8));
                  statement.setDouble(9, resultSet.getDouble(9));
                  statement.setDouble(10, resultSet.getDouble(10));
            }      
                  statement.executeUpdate();
            
            
           PreparedStatement psmt = conn.prepareStatement("DELETE FROM employee WHERE emp_id = ?");
            psmt.setInt(1, inempid);
            result = psmt.executeUpdate();
           
            pstmt.close();
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        
        return result;
    }
    
    
    //update employee details
   public boolean updateEmployee(int inempid,String inempname,String inempnic,String inempadd,String inempphone)
   {
       DataBase db = new DataBase(ConnectionType.MYSQL);
       boolean result = false; 
       
        try {
            Connection conn = db.getConnection();
             PreparedStatement psmt = conn.prepareStatement("UPDATE employee SET emp_name=?,emp_nic=?,emp_address=?,emp_phone=? WHERE emp_id=?");
      
      psmt.setString(1, inempname);
      psmt.setString(2, inempnic);
      psmt.setString(3, inempadd);
      psmt.setString(4, inempphone);
      psmt.setInt(5, inempid);
      psmt.executeUpdate();
            result=true;
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        
       return result;
   }
      public ArrayList<Employee> searchEmployeeByIDWithoutPosition(int inemid,boolean cboxcondition)
     {
        //getting the  employee details to a resultset and then inserting to table in search employee
         DataBase db = new DataBase(ConnectionType.MYSQL);
         ArrayList<Employee> emp= new ArrayList<>();
         
         try {  
            Connection conn = db.getConnection();
              
            
         if(!cboxcondition)
         {
             PreparedStatement pstmt=conn.prepareStatement("SELECT `emp_id`,`emp_name`,`emp_nic`,`emp_address`,`emp_phone`,`emp_gender`,`emp_designation` FROM employee where emp_id LIKE ? ESCAPE '!'");
             pstmt.setInt(1,inemid);
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                 emp.add(result);
                 
             }
             
             rs.close();
             pstmt.close();
         }
         else
         {
             
            PreparedStatement pstmt=conn.prepareStatement("SELECT `emp_id`,`emp_name`,`emp_nic`,`emp_address`,`emp_phone`,`emp_gender`,`emp_designation` FROM past_employees where emp_id LIKE ? ESCAPE '!'");
             pstmt.setInt(1,inemid);
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                 emp.add(result);
               
             }
             
             rs.close();
             pstmt.close();
         }
         } 
        catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                }  
         return emp;
     }  
     
    //search employee basic information using id
     public ArrayList<Employee> searchEmployeeByPosition(boolean cboxcondition,String position)
     {
        //getting the  employee details to a resultset and then inserting to table in search employee
         DataBase db = new DataBase(ConnectionType.MYSQL);
         ArrayList<Employee> emp= new ArrayList<>();
         
         try {  
            Connection conn = db.getConnection();
              
            
         if(!cboxcondition)
         {
             PreparedStatement pstmt=conn.prepareStatement("SELECT `emp_id`,`emp_name`,`emp_nic`,`emp_address`,`emp_phone`,`emp_gender`,`emp_designation` FROM employee where emp_designation = ?");
             pstmt.setString(1,position);
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                 emp.add(result);
                 
             }
             
             rs.close();
             pstmt.close();
         }
         else
         {
             
            PreparedStatement pstmt=conn.prepareStatement("SELECT `emp_id`,`emp_name`,`emp_nic`,`emp_address`,`emp_phone`,`emp_gender`,`emp_designation` FROM past_employees where emp_designation = ? ");
             pstmt.setString(1,position);
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                 emp.add(result);
               
             }
             
             rs.close();
             pstmt.close();
         }
         } 
        catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                }  
         return emp;
     }  
     
       //search employee basic information using name
      public ArrayList<Employee> searchEmployeeByNameWithoutPosition(String inemname,boolean cboxcondition)
     {
         //getting the  employee details to a resultset and then inserting to table in search employee
         DataBase db = new DataBase(ConnectionType.MYSQL);
         ArrayList<Employee> emp= new ArrayList<>();
        
         try {  
            Connection conn = db.getConnection();
              
            
         if(!cboxcondition)
         {
            
             PreparedStatement pstmt=conn.prepareStatement("SELECT `emp_id`,`emp_name`,`emp_nic`,`emp_address`,`emp_phone`,`emp_gender`,`emp_designation`  FROM employee where emp_name LIKE ? ESCAPE '!'");
             pstmt.setString(1,"%"+inemname+"%");
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                 
                 emp.add(result);
                 
             }
        
             rs.close();
             pstmt.close();
         }
         else
         {
           
            PreparedStatement pstmt=conn.prepareStatement("SELECT `emp_id`,`emp_name`,`emp_nic`,`emp_address`,`emp_phone`,`emp_gender`,emp_designation FROM past_employees where emp_name LIKE ? ESCAPE '!'");
             pstmt.setString(1,"%"+inemname+"%");
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                 emp.add(result);
                
             }
             
             rs.close();
             pstmt.close();
         }
         }
        catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                }  
         return emp;
    }
   public ArrayList<Employee> searchEmployeeByNameWithPosition(String inemname,boolean cboxcondition,String position)
     {
         //getting the  employee details to a resultset and then inserting to table in search employee
         DataBase db = new DataBase(ConnectionType.MYSQL);
         ArrayList<Employee> emp= new ArrayList<>();
        
         try {  
            Connection conn = db.getConnection();
              
            
         if(!cboxcondition)
         {
            
             PreparedStatement pstmt=conn.prepareStatement("SELECT `emp_id`,`emp_name`,`emp_nic`,`emp_address`,`emp_phone`,`emp_gender`,`emp_designation`  FROM employee where emp_name LIKE ? ESCAPE '!' AND emp_designation = ?");
             pstmt.setString(1,"%"+inemname+"%");
             pstmt.setString(2, position);
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                 
                 emp.add(result);
                 
             }
        
             rs.close();
             pstmt.close();
         }
         else
         {
           
            PreparedStatement pstmt=conn.prepareStatement("SELECT `emp_id`,`emp_name`,`emp_nic`,`emp_address`,`emp_phone`,`emp_gender`,emp_designation FROM past_employees where emp_name LIKE ? ESCAPE '!' AND emp_designation =?");
             pstmt.setString(1,"%"+inemname+"%");
             pstmt.setString(2, position);
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                 emp.add(result);
                
             }
             
             rs.close();
             pstmt.close();
         }
         }
        catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                }  
         return emp;
    }
    public int getNoOfDaysWorked(int empid,String month)
    {
        DataBase db = new DataBase(ConnectionType.MYSQL);
         ArrayList<Employee> emp= new ArrayList<>();
   
        int noofdays=0;
         try {  
            Connection conn = db.getConnection();
            PreparedStatement pstmt=conn.prepareStatement("SELECT count(*) FROM attendance where emp_id= ? and date_of_work LIKE ? ESCAPE '!'"); 
            pstmt.setInt(1,empid);
            pstmt.setString(2,"%"+month+"%");
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Employee result=new Employee(rs.getInt(1));
                 noofdays=rs.getInt(1);
                 emp.add(result);
            
             }
             
          
             rs.close();
             pstmt.close();
               }
        catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                }  
         return noofdays;
    }

    public double getBasicsalary() {
        return basicsalary;
    }

    public void setBasicsalary(double basicsalary) {
        this.basicsalary = basicsalary;
    }
      
    //salary,epf,etf added to the database
     public double calculateSalary(String emp_position,int empid,String currentmonth,double damage,double loan, double adv)
 {
     DataBase db = new DataBase(ConnectionType.MYSQL);
     noofdays=getNoOfDaysWorked(empid,currentmonth);
   
     
     if(emp_position.equalsIgnoreCase("Director"))
     {
         dailyrate=1100;
         basicsalary=dailyrate*noofdays;
         
         deducted_epf= 0.08*basicsalary ;
         setDeducted_epf(deducted_epf);
         
         
         deducted_etf=0.03*basicsalary;
         setDeducted_etf(deducted_etf);
         
         comp_epf=0.12*basicsalary;
         setComp_epf(comp_epf);
         
         setBasicsalary(basicsalary);
         allowance=dailyrate*0.5*noofdays;
         setAllowance(allowance);
         
         if(noofdays==26)
         {
             grosspay=allowance+basicsalary-(deducted_epf+damage+loan+adv);
         }
         if(noofdays<26)
         {
             grosspay=basicsalary+allowance-(deducted_epf+damage+loan+adv);
         }
         if(noofdays>26)
         {
              int otdays=noofdays-26;
             setOtdays(otdays);
             //System.out.println(otdays);
             double otbonus=otdays*0.5;
             setOtbonus(otbonus);
              grosspay=otbonus+basicsalary+(basicsalary/26.0*noofdays)+allowance-(deducted_epf+damage+loan+adv);
         }
     }
    
     if(emp_position.equalsIgnoreCase("Manager"))
     {
         dailyrate=1050;
         basicsalary=dailyrate*noofdays;
         
         deducted_epf= 0.08*basicsalary ;
         setDeducted_epf(deducted_epf);
         
         
         deducted_etf=0.03*basicsalary;
         setDeducted_etf(deducted_etf);
         
         comp_epf=0.12*basicsalary;
         setComp_epf(comp_epf);
         
         allowance=dailyrate*0.5*noofdays;
         setAllowance(allowance);
         
         if(noofdays==26)
         {
             grosspay=allowance+basicsalary-(deducted_epf+damage+loan+adv);
         }
         if(noofdays<26)
         {
             grosspay=basicsalary+allowance-(deducted_epf+damage+loan+adv);
         }
         if(noofdays>26)
         {
             int otdays=noofdays-26;
             setOtdays(otdays);
            
             double otbonus=otdays*0.5;
             setOtbonus(otbonus);
             grosspay=otbonus+(basicsalary/26.0*noofdays)+allowance-(deducted_epf+damage+loan+adv);
         }
     }
    
     if(emp_position.equalsIgnoreCase("Permanent"))
     {
         dailyrate=850;
         basicsalary=dailyrate*noofdays;
         deducted_epf= 0.08*basicsalary ;
         setDeducted_epf(deducted_epf);
         
         
         deducted_etf=0.03*basicsalary;
         setDeducted_etf(deducted_etf);
         
         comp_epf=0.12*basicsalary;
         setComp_epf(comp_epf);
         
         allowance=dailyrate*0.5*noofdays;
         setAllowance(allowance);
         
         if(noofdays==26)
         {
             grosspay=allowance+basicsalary-(deducted_epf+damage+loan+adv);
         }
         if(noofdays<26)
         {
              grosspay=basicsalary+allowance-(deducted_epf+damage+loan+adv);
         }
         if(noofdays>26)
         {
             int otdays=noofdays-26;
             setOtdays(otdays);
          
             double otbonus=otdays*0.5;
             setOtbonus(otbonus);
             grosspay=otbonus+(basicsalary/26.0*noofdays)+allowance-(deducted_epf+damage+loan+adv);
         }
     }
      if(emp_position.equalsIgnoreCase("Casual"))
     {
         dailyrate=800;
         basicsalary=dailyrate*noofdays;
         
         deducted_epf= 0.08*basicsalary ;
         setDeducted_epf(deducted_epf);
         
         
         deducted_etf=0.03*basicsalary;
         setDeducted_etf(deducted_etf);
         
         comp_epf=0.12*basicsalary;
         setComp_epf(comp_epf);
         
         allowance=dailyrate*0.5*noofdays;
         setAllowance(allowance);
         
         if(noofdays==26)
         {
             grosspay=allowance+basicsalary-(deducted_epf+damage+loan+adv);
         }
         if(noofdays<26)
         {
              grosspay=basicsalary+allowance-(deducted_epf+damage+loan+adv);
         }
         if(noofdays>26)
         {
             int otdays=noofdays-26;
             setOtdays(otdays);
             //System.out.println(otdays);
             double otbonus=otdays*0.5;
             setOtbonus(otbonus);
             grosspay=otbonus+(basicsalary/26.0*noofdays)+allowance-(deducted_epf+damage+loan+adv);
         }    
    }
     
     try
     {
         Connection conn = db.getConnection();
    
         PreparedStatement psmt = conn.prepareStatement("INSERT INTO salary(emp_id,date,emp_salary,emp_epf,emp_etf,emp_comp_epf) VALUES(?,?,?,?,?,?)");
         psmt.setInt(1,empid);
         psmt.setString(2,currentmonth);
         psmt.setDouble(3, grosspay);
         psmt.setDouble(4, deducted_epf);
         psmt.setDouble(5, deducted_etf);
         psmt.setDouble(6,comp_epf);
         psmt.executeUpdate();
         
         
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
     catch(Exception e) 
     {
              System.err.println(e);
     }  
    // System.out.println(grosspay);
     return grosspay;
     
 }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

   

    public double getDeducted_epf() {
        return deducted_epf;
    }

    public void setDeducted_epf(double deducted_epf) {
        this.deducted_epf = deducted_epf;
    }

  public int getOtdays() {
        return otdays;
    }

  public void setOtdays(int otdays) {
        this.otdays = otdays;
    }

  public double getOtbonus() {
        return otbonus;
    }

  public void setOtbonus(double otbonus) {
        this.otbonus = otbonus;
     }

    public double getDeducted_etf() {
        return deducted_etf;
    }

    public void setDeducted_etf(double ded_etf) {
        this.deducted_etf = ded_etf;
    }

    public double getComp_epf() {
        return comp_epf;
    }

    public void setComp_epf(double comp_epf) {
        this.comp_epf = comp_epf;
    }
     
     
 
 public void recordStartTimeofEmployee(int empid,String wdate,String starttime)
 {
        
        DataBase db=new DataBase(ConnectionType.MYSQL);
          
            try
            {
                Connection con=db.getConnection();
                String query="INSERT INTO attendance(emp_id,date_of_work,att_start_time) VALUES(?,?,?)";
                
                  statement  = con.prepareStatement(query); 
                  statement.setInt(1,empid);
                  statement.setString(2,wdate);
                  statement.setString(3,starttime );
                  
                  statement.executeUpdate();
                  
                  statement.close();
                  con.close();
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
            catch(Exception e)
            {
                System.err.println(e);
            } 
            
 }
 
 public void updateEndTimeOfEmployee(int empid,String wdate,String endtime)
 {
     DataBase db=new DataBase(ConnectionType.MYSQL);
      try{
         Connection conn = db.getConnection();
    
      PreparedStatement psmt = conn.prepareStatement("UPDATE attendance SET att_end_time= ? WHERE emp_id=? and date_of_work=?");
      psmt.setString(1,endtime);
      psmt.setInt(2, empid);
      psmt.setString(3,wdate);
      psmt.executeUpdate();
     }
     catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                }  
 
 }
 
 //add items to database
    public void addItem(String iitem_id,String iitem_name,String purc_date,String iitem_category,String unit_price, String qty,String discount)
    {
       DataBase db=new DataBase(ConnectionType.MYSQL);
          
            try
            {
                Connection con=db.getConnection();
                String query="INSERT INTO item (item_id,item_name,pur_date,item_category,unit_price,qty,discount) VALUES(?,?,?,?,?,?,?)";
                  statement  = con.prepareStatement(query); 
                  statement.setString(1,iitem_id);
                  statement.setString(2,iitem_name);
                  statement.setString(3,purc_date );
                  statement.setString(4,iitem_category);
                  statement.setString(5,unit_price);
                  statement.setString(6, qty);
                  statement.setString(7,discount );
                  statement.executeUpdate();
                  
                  statement.close();
                  con.close();
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
            catch(Exception e)
            {
                System.err.println(e);
            } 
            
    }
    
    //delete items from database
    public int deleteItem(String itemid)
    {
        int result=0;
        DataBase db=new DataBase(ConnectionType.MYSQL);
        try {
        Connection conn=db.getConnection();   
        PreparedStatement psmt = conn.prepareStatement("DELETE FROM item WHERE item_id=?");
            psmt.setString(1, itemid);
            result = psmt.executeUpdate();
           
            psmt.close();
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        return result;
    }
    
    //update item details
    public int updateItem(String iitem_id,String iitem_name,String purc_date,String unit_price, String qty,String discount)
    {
         DataBase db = new DataBase(ConnectionType.MYSQL);
         int result = 0; 
       
        try {
            Connection conn = db.getConnection();
             PreparedStatement psmt = conn.prepareStatement("UPDATE item SET item_name=?, pur_date=?, unit_price=?, qty=?, discount=? WHERE item_id=?");
             psmt.setString(1,iitem_name);
             psmt.setString(2, purc_date);
            
             psmt.setString(3, unit_price);
             psmt.setString(4, qty);
             psmt.setString(5, discount);
             psmt.setString(6, iitem_id);
             psmt.executeUpdate();

            conn.close();        ;
             
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        return result;
    }
    
    //add grn info to database
    public void addGRN(String grn_id,String sup_id,String sup_name,String purc_date,String total)
    {
       DataBase db=new DataBase(ConnectionType.MYSQL);
          
            try
            {
                Connection con=db.getConnection();
                String query="INSERT INTO grn (grn_id,sup_id,sup_name,pur_date,total) VALUES(?,?,?,?,?)";
                  statement  = con.prepareStatement(query); 
                  statement.setString(1,grn_id);
                  statement.setString(2,sup_id);
                  statement.setString(3,sup_name);
                  statement.setString(4,purc_date );
                  statement.setString(5,total);
                  
                  statement.executeUpdate();
                  
                  statement.close();
                  con.close();
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
            catch(Exception e)
            {
                System.err.println(e);
            } 
            
    }
    
    //delete grn from db
     public int deleteGRN(String grnid)
    {
        int result=0;
        DataBase db=new DataBase(ConnectionType.MYSQL);
        try {
        Connection conn=db.getConnection();   
        PreparedStatement psmt = conn.prepareStatement("DELETE FROM grn WHERE grn_id=?");
            psmt.setString(1, grnid);
            result = psmt.executeUpdate();
           
            psmt.close();
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        return result;
    }
     
     //update grn info
   public int updateGRN(String grn_id,String pdate,String tot)
    {
         DataBase db = new DataBase(ConnectionType.MYSQL);
         int result = 0; 
       
        try {
            Connection conn = db.getConnection();
             PreparedStatement psmt = conn.prepareStatement("UPDATE grn SET pur_date=?, total=? WHERE grn_id=?");
             psmt.setString(1,grn_id);
             psmt.setString(2, pdate);
            
             psmt.setString(3, tot);
      
            
             psmt.executeUpdate();

            conn.close();        ;
             
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        return result;
    } 
   
    public void addCustomer(String custname,String custemail, String custadd, String custcontact,String custgen,String custNic,String custland)
    {
                
        try {
            
            String sql = "INSERT INTO customer(cus_name,cus_nic,cus_phone,cus_land,cus_suffix,cus_address,cus_email) VALUES('"+custname+"','"+custNic+"','"+custcontact+"','"+custland+"','"+custgen+"','"+custadd+"','"+custemail+"')";
            DataBase db = new DataBase(ConnectionType.MYSQL);

            con = db.getConnection();
            statement = con.prepareStatement(sql);
            statement.execute();
            
        }
        catch(SQLException s){
            JOptionPane.showMessageDialog(null, s);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
            
    }

    public boolean deleteCustomer(String custid)
    {
     boolean result = false;
     
     try {
         DataBase db = new DataBase(ConnectionType.MYSQL);
         Connection c = db.getConnection();
         
         PreparedStatement delete = c.prepareStatement("DELETE FROM customer WHERE cus_id='"+custid+"'");
         delete.executeUpdate();
         delete.close();
         
         JOptionPane.showMessageDialog(null,"Deleted...");
         
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
     }
     
     return result;
 }

    public boolean updateCustomer(String custId , String name , String nic , String add , String phone , String land  ,String email){
        boolean result = false;
        
        try {
            
            DataBase db = new DataBase(ConnectionType.MYSQL);
            Connection c = db.getConnection();
            
            PreparedStatement update = c.prepareStatement("UPDATE customer SET cus_name='"+name+"' , cus_nic='"+nic+"' , cus_address='"+add+"' , cus_phone='"+phone+"' , cus_land='"+land+"' , cus_email='"+email+"' WHERE cus_id='"+custId+"' ");
            
            update.executeUpdate();
            //update.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return result;
    }
    
    public void searchCustomer(String custid,String name)
     {
     try {
         String sql = "SELECT * FROM customer where cus_id='"+custid+"' || cus_name='"+name+"' ";
         DataBase db = new DataBase(ConnectionType.MYSQL);
         con = db.getConnection();
         statement = con.prepareStatement(sql);
         resultSet=statement.executeQuery();
         
         SearchCustomer sc = new SearchCustomer();
         sc.searchTable.setModel(DbUtils.resultSetToTableModel(resultSet));
  
     }
     catch(SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        
        }
     catch (Exception e) {
         JOptionPane.showMessageDialog(null,e);
     }
     }
    
     public void addvehicle(String vrn,String vehbrand,String vehmodel,String vehcategory,int cusid){
        DataBase db = new DataBase(ConnectionType.MYSQL);
        try {
            Connection con = db.getConnection();
            String sql="INSERT INTO vehicle(veh_registration_number,veh_brand,veh_model,veh_category,cus_id)VALUES('"+vrn+"','"+vehbrand+"','"+vehmodel+"','"+vehcategory+"','"+cusid+"')";
           
            
            
            statement = con.prepareStatement(sql);
            statement.execute();
            
        }
        catch(SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    
    }
     
     public void deleteVehicle(String pvn) {
         DataBase db = new DataBase(ConnectionType.MYSQL);
         System.out.println(pvn);
         try {
            
         Connection con = db.getConnection();
           
            PreparedStatement psmt = con.prepareStatement("DELETE FROM vehicle WHERE veh_registration_number = ?");
            psmt.setString(1, pvn);
            psmt.executeUpdate();
           

         }
         catch(SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
     }
     
     public void updateVehicle(int cusid,String pvn) {
       
        
        try {
            
            DataBase db = new DataBase(ConnectionType.MYSQL);
            Connection c = db.getConnection();
            
            PreparedStatement upd = c.prepareStatement("UPDATE vehicle SET cus_id = ? WHERE veh_registration_number = ? ");
               upd.setInt(1, cusid);
               upd.setString(2, pvn);
        
            upd.executeUpdate();
            //update.close();
            
        }
        catch(SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     
   
     }  
     
      public void addSupplier(String insupname,String insupadd,String insupphone,String insupemail)
    {
       
        DataBase db=new DataBase(ConnectionType.MYSQL);
          
            try
            {
                Connection con=db.getConnection();
                String query="INSERT INTO supplier(sup_name,sup_comp_address,sup_phone,sup_email) values(?,?,?,?)";
                
                  statement  = con.prepareStatement(query); 
                  statement.setString(1,insupname);
                  statement.setString(2,insupadd);
                  statement.setString(3,insupphone );
                  statement.setString(4,insupemail);
                  statement.executeUpdate();
                  
                  statement.close();
                  con.close();
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
            catch(Exception e)
            {
                System.err.println(e);
            } 
            
            
        }
      
     public int deleteSupplier(int supid)
    {
        
        DataBase db = new DataBase(ConnectionType.MYSQL);
        int result = 0; 
      
        try {  
            Connection conn = db.getConnection();
              
            //getting the leaving employees to a resultset and then inserting to past_employees table
            
            
            Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = pstmt.executeQuery("SELECT * FROM supplier where sup_id =" + supid);
             
           PreparedStatement psmt = conn.prepareStatement("DELETE FROM supplier WHERE sup_id = ?");
            psmt.setInt(1,  supid);
            result = psmt.executeUpdate();
           
            pstmt.close();
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        
        return result;
    }
     
      public int updateSupplier(int insupid,String insupname,String insupadd,String insupphone,String insupemail)
   {
       DataBase db = new DataBase(ConnectionType.MYSQL);
       int result = 0; 
       
        try {
            Connection conn = db.getConnection();
             PreparedStatement psmt = conn.prepareStatement("UPDATE supplier SET sup_name=?,sup_comp_address=?,sup_phone=?,sup_email=? WHERE sup_id=?");
      
      psmt.setString(1, insupname);
      psmt.setString(2, insupadd);
      psmt.setString(3, insupphone);
      psmt.setString(4, insupemail);
     
      psmt.executeUpdate();

            conn.close();
        }
        catch(SQLException e) {
            System.err.println("SQL Error(s) as follows:");
            while (e != null) {
                System.err.println("SQL Return Code: " + e.getSQLState());
                System.err.println("  Error Message: " + e.getMessage());
                System.err.println(" Vendor Message: " + e.getErrorCode());
                e = e.getNextException();
            }	
        } 
        catch(Exception e) {
            System.err.println(e);
        }  
        
       return result;
   }
    public boolean scheduleJob(int servceid,int eid, String cdate)
    {
         DataBase db=new DataBase(ConnectionType.MYSQL);
         boolean r=false; 
            try
            {
                Connection con=db.getConnection();
                String query="INSERT INTO job_has_employee(job_id,emp_id,date_of_work) values(?,?,?)";
                
                  statement  = con.prepareStatement(query); 
                  statement.setInt(1,servceid);
                  statement.setInt(2, eid);
                  statement.setString(3,cdate );
                   statement.executeUpdate();
                  r=true;
                  statement.close();
                  con.close();
            }
            catch(SQLIntegrityConstraintViolationException ex)
            {
                JOptionPane.showMessageDialog(null,"This employee has already been assigned to the job");
            }
            catch(SQLException e) 
            {
                r=false;
                System.err.println("SQL Error(s) as follows:");
                while (e != null)
                {
                    System.err.println("SQL Return Code: " + e.getSQLState());
                    System.err.println("  Error Message: " + e.getMessage());
                    System.err.println(" Vendor Message: " + e.getErrorCode());
                    e = e.getNextException();
                }	
            } 
            catch(Exception e)
            {
                r=false;
                System.err.println(e);
            } 
            
            return r;
            
        
    }
     //get login details from db
    public  ArrayList<Login> GetLogin(){
            DataBase db = new DataBase(ConnectionType.MYSQL);
            ArrayList<Login> loginInfo = new ArrayList<>();
            
            try {
                Connection conn = db.getConnection();
                Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = pstmt.executeQuery("SELECT idLogin, username, password, accessType FROM login");
                
                while( rs.next() ){
                    Login result = new Login( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4) );
                    loginInfo.add(result);
                }
                
                rs.close();
                pstmt.close();
                conn.close();
            }
            catch(SQLException e) {
                System.err.println("SQL Error(s) as follows:");
                while (e != null) {
                    System.err.println("SQL Return Code: " + e.getSQLState());
                    System.err.println("  Error Message: " + e.getMessage());
                    System.err.println(" Vendor Message: " + e.getErrorCode());
                    e = e.getNextException();
                }	
            } 
            catch(Exception e) {
                System.err.println(e);
            }  
            
            return loginInfo;
        }
    
    
    //get login details from db passing username as parameter
        public ArrayList<Login> GetLogin(String user){
            DataBase db = new DataBase(ConnectionType.MYSQL);
            ArrayList<Login> loginInfo = new ArrayList<>();
            
            try {
                Connection conn = db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("SELECT username, password, accesstype FROM login WHERE username = ?");
                pstmt.setString(1, user);
                
                ResultSet rs = pstmt.executeQuery();
                
                while( rs.next() ){
                    Login result = new Login( rs.getString(1), rs.getString(2), rs.getString(3) );
                    loginInfo.add(result);
                }
                
                rs.close();
                pstmt.close();
                conn.close();
            }
            catch(SQLException e) {
                System.err.println("SQL Error(s) as follows:");
                while (e != null) {
                    System.err.println("SQL Return Code: " + e.getSQLState());
                    System.err.println("  Error Message: " + e.getMessage());
                    System.err.println(" Vendor Message: " + e.getErrorCode());
                    e = e.getNextException();
                }	
            } 
            catch(Exception e) {
                System.err.println(e);
            }  
            
            return loginInfo;
        }

public ArrayList<Supplier> searchSupplierByID(int suppid)
     {
        //getting the  employee details to a resultset and then inserting to table in search employee
         DataBase db = new DataBase(ConnectionType.MYSQL);
         ArrayList<Supplier> sup= new ArrayList<>();
         
         try {  
            Connection conn = db.getConnection();
              
       
             PreparedStatement pstmt=conn.prepareStatement("SELECT  s.sup_id , s.sup_name , s.sup_comp_address , s.sup_phone , s.sup_email , si.item_id  FROM supplier s INNER JOIN supplier_has_item si where s.sup_id=si.sup_id and s.sup_id = ?");
             pstmt.setInt(1,suppid);
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Supplier result=new Supplier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                 System.out.println(rs.getString(6));
                 sup.add(result);
                
             }
            
           
             rs.close();
             pstmt.close();
         
         } 
        catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                }  
         return sup;
     } 
       
//search employee basic information using name
      public ArrayList<Supplier> searchSupplierByName(String suppname)
     {
         //getting the  employee details to a resultset and then inserting to table in search employee
         DataBase db = new DataBase(ConnectionType.MYSQL);
         ArrayList<Supplier> sup= new ArrayList<>();
        
         try {  
            Connection conn = db.getConnection();
              
            
        
         
           
              PreparedStatement pstmt=conn.prepareStatement("SELECT  s.sup_id , s.sup_name , s.sup_comp_address , s.sup_phone , s.sup_email , si.item_id  FROM supplier s INNER JOIN supplier_has_item si where s.sup_id=si.sup_id and s.sup_name LIKE ? ESCAPE '!'");
             pstmt.setString(1,"%"+suppname+"%");
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Supplier result=new Supplier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                 System.out.println(rs.getString(5));
                 sup.add(result);
                
             }
             
             rs.close();
             pstmt.close();
         
            }
        catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                } 
         return sup;
    }
      //viewing employee profile when name is clicked
    public ArrayList<Supplier> viewSupplierDetails(String supname) {
        DataBase db = new DataBase(ConnectionType.MYSQL);
         ArrayList<Supplier> sup= new ArrayList<>();
        
         try {  
            Connection conn = db.getConnection();
            PreparedStatement pstmt=conn.prepareStatement("SELECT `sup_id`,`sup_name`,`sup_comp_address`,`sup_phone`,`sup_email` FROM supplier  sup_id = ?");
             pstmt.setString(1,supname);
             
             ResultSet rs=pstmt.executeQuery();
             while(rs.next())
             {
                 Supplier result=new Supplier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                 sup.add(result);
                
             }
                 
             rs.close();
             pstmt.close();
               }
        catch(SQLException e) {
                    System.err.println("SQL Error(s) as follows:");
                    while (e != null) {
                        System.err.println("SQL Return Code: " + e.getSQLState());
                        System.err.println("  Error Message: " + e.getMessage());
                        System.err.println(" Vendor Message: " + e.getErrorCode());
                        e = e.getNextException();
                    }	
                } 
       catch(Exception e) {
                    System.err.println(e);
                }  
         return sup;
    }
    //Connection co = null; //dbconnect
    ViewSales vs = new ViewSales();
    private PreparedStatement statemnt = null;
    private ResultSet resultSt = null;
   
    
    public void paidInvoice (int invId, String customerName, String paymentType, String date, String itemid, String itemname, String qty, double unitprice, double total, double ftotal,double paidamount, double balance)        
    {
          DataBase db = new DataBase(ConnectionType.MYSQL);
      // con = DBconnect.connect();                                                                                                                                         
            
        String qq = ("INSERT INTO purchase (inv_id, cus_name, payment_type, date, item_id, item_name, qty, unit_price, total, bill_total, paid, balance) VALUES ('"+invId+"','"+customerName+"','"+paymentType+"', '"+date+"', '"+itemid+"', '"+itemname+"', '"+qty+"', '"+unitprice+"', '"+total+"', '"+ftotal+"', '"+paidamount+"', '"+balance+"')");
        
            PreparedStatement pst = null;
            try {
                 Connection con = db.getConnection();
                pst = con.prepareStatement(qq);
                pst.execute();
                
                JOptionPane.showMessageDialog(null,"Data Added");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        
    }

    public void removeqty(String itemid, String qty) {
          DataBase db = new DataBase(ConnectionType.MYSQL);
        try {
            
            Connection con = db.getConnection();
            String qq = ("UPDATE item set item.qty= qty - ? WHERE item_id= ?");
            int item_id=Integer.parseInt(itemid);
            int quty=Integer.parseInt(qty);
            PreparedStatement pst = con.prepareStatement(qq);
            pst.setInt(1, quty);
            pst.setInt(0, item_id);
            pst.execute();
        
        } catch (Exception e) {
        }
    }
    
    public void searchInvoice(String invId)
     {DataBase db = new DataBase(ConnectionType.MYSQL);
       
     try {
          Connection con = db.getConnection();
         String sql = "SELECT * FROM purchase where inv_id='"+invId+"'";
         PreparedStatement pst = null;
          pst = con.prepareStatement(sql);
                pst.execute();
         
         statement = con.prepareStatement(sql);
         resultSet=statement.executeQuery();
         
    
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null,"DB Connection error.....");
     }
     }

    public void tableLoad() {
        
        ViewSales vs = new ViewSales();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try 
        {
            String sql = "SELECT inv_id, cus_name, payment_type, date, item_id, item_name, qty, unit_price, total, paid, balance  FROM purchase";
             pst = con.prepareStatement(sql); 
             rs = pst.executeQuery(); 
           //  ViewSales vs = new ViewSales();
             
        } 
        
        catch (Exception e) 
        {
            
        }
        
    }
    
    // Connection con = null;
    
   
      public void addJob(int sid, String jobdescription, double jobamount, String time, String date, String cusid, String cusname, String vehregno, String vehcategory, String jobstatus, double totalamount)
    {
        try {
             String sql = "INSERT INTO job(service_id,job_description, job_price, time, date, cus_id, cus_name, veh_registration_number, veh_category, job_status, total_amount) VALUES('"+sid+"','"+jobdescription+"', '"+jobamount+"', '"+time+"', '"+date+"', '"+cusid+"', '"+cusname+"', '"+vehregno+"', '"+vehcategory+"', '"+jobstatus+"', '"+totalamount+"')";
        
            DataBase db = new DataBase(ConnectionType.MYSQL);
        
             con = db.getConnection();
             statement = con.prepareStatement(sql);
             statement.execute();
             
             JOptionPane.showMessageDialog(null,"Job Added");
        } 
        catch (SQLException s) {
            
            JOptionPane.showMessageDialog(null, s);;
        }
        catch (Exception e) {
          
            System.err.println(e);
        }
        
    }
      
      public boolean endJob(String jobstatus, int sid)
      {
      boolean result = false;
        
            try {
            
            DataBase db = new DataBase(ConnectionType.MYSQL);
            Connection c = db.getConnection();
            
            PreparedStatement update = c.prepareStatement("UPDATE job SET job_status='"+jobstatus+"' where service_id='"+sid+"'");
            
            update.executeUpdate();
            update.close();
            JOptionPane.showMessageDialog(null,"Job Ended");
            
            
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }

        
        return result;
      }
      
      
      public void searchJob(String searchjob)
     {
     try {
         String sql = "SELECT * FROM job WHERE veh_registration_number  LIKE '%"+searchjob+"%' || cus_name LIKE '%"+searchjob+"%'";
         DataBase db = new DataBase(ConnectionType.MYSQL);
         con = db.getConnection();
         statement = con.prepareStatement(sql);
         resultSet=statement.executeQuery();
         
         SearchJob sc = new SearchJob();
         sc.jTable1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(resultSet));
  
     }
     catch(SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        
        }
     catch (Exception e) {
         JOptionPane.showMessageDialog(null,e);
     }
     }
              
              
      public boolean updateJob(String jid,String jobname, double jobprice, String time, String date, String cusname, String vehregno, String vehcategory, String jstatus)
    {
        boolean result = false;
        
            try {
            
            DataBase db = new DataBase(ConnectionType.MYSQL);
            Connection c = db.getConnection();
            
            PreparedStatement update;
            update = c.prepareStatement("UPDATE job SET job_description='"+jobname+"', job_price='"+jobprice+"' , time='"+time+"' , date='"+date+"', cus_name='"+cusname+"', veh_registration_number='"+vehregno+"', veh_category='"+vehcategory+"', job_status='"+jstatus+"'  WHERE job_id='"+jid+"'");
            
            update.executeUpdate();
            update.close();
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }

        
        return result;
    }
      
       public boolean deleteJob(String jobid)
    {
     boolean result = false;
     String sql = "DELETE FROM job WHERE job_id = '"+jobid+"'";
        try {
            DataBase db = new DataBase(ConnectionType.MYSQL);
            Connection c = db.getConnection();
            statement = c.prepareStatement(sql);
            statement.executeUpdate();
            
        } catch (Exception e) {
        }
        return result;
     }

       
       public void addJobPrices(String jobdescription, double jobprice)
     {
     try 
        {
            String sql = "INSERT INTO job_prices(job_description,job_price) VALUES('"+jobdescription+"','"+jobprice+"')";
            DataBase db = new DataBase(ConnectionType.MYSQL);
            
            con = db.getConnection();
            statement = con.prepareStatement(sql);
            statement.execute();
            
        } catch (SQLException s) {
            
            JOptionPane.showMessageDialog(null, s);;
        }
        catch(Exception e)
        {
        
        }
     }
     
     public void searchJobPrices(String searchservices)
     {
     try {
         String sql = "SELECT * FROM job_prices WHERE job_description LIKE '%"+searchservices+"%' || job_price = '"+searchservices+"'";
         DataBase db = new DataBase(ConnectionType.MYSQL);
         con = db.getConnection();
         statement = con.prepareStatement(sql);
         resultSet=statement.executeQuery();
         
         SearchJobPrices sc = new SearchJobPrices();
         sc.sJobPricesTable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(resultSet));
  
     }
     catch(SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        
        }
     }
     
     public boolean updateJobPrices(String jobno, String jobdescription,double jobprice )
        {
        boolean result = false;
        
            try {
            
            DataBase db = new DataBase(ConnectionType.MYSQL);
            Connection c = db.getConnection();
            
            try (PreparedStatement update = c.prepareStatement("UPDATE job_prices SET job_description='"+jobdescription+"' , job_price='"+jobprice+"' WHERE job_no='"+jobno+"'")) {
                update.executeUpdate();
            }
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }

        
        return result;
        }

    public boolean deleteJobPrices(String jobno)
    {   
         boolean result = false;
         String sql = "DELETE FROM job_prices WHERE job_no = '"+jobno+"'";
        try {
            
            DataBase db = new DataBase(ConnectionType.MYSQL);
            Connection c = db.getConnection();
            statement = c.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (Exception e) {
        }
        return result;
    }
    
 
      public boolean updateJobPayment(String pid, String jid,String type,String amount)
     {
         boolean result = false;
        
        try {
            
            DataBase db = new DataBase(ConnectionType.MYSQL);
            Connection c = db.getConnection();
            
            PreparedStatement update = c.prepareStatement("UPDATE job_payment SET payment_id='"+pid+"' , job_id='"+jid+"' , payment_type='"+type+"' , amount='"+amount+"' WHERE '"+pid+"'=payment_id ");
            
            update.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Successfully Updated...");
            
            update.close();
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return result;

     }
     
     
    public void addJobPayment(String jobid,String type,String payment)
    {
            String sql = "INSERT INTO job_payment(job_id,payment_type,amount) VALUES('"+jobid+"' , '"+type+"' , '"+payment+"') ";
            DataBase db = new DataBase(ConnectionType.MYSQL);
            try{
            con = db.getConnection();
            statement = con.prepareStatement(sql);
            statement.execute();
            statement.close();
            
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
      public boolean deleteJobPayment(String payid)
      {
     boolean result = false;
     
     try {
         DataBase db = new DataBase(ConnectionType.MYSQL);
         Connection c = db.getConnection();
         
         PreparedStatement delete = c.prepareStatement("DELETE FROM job_payment WHERE payment_id='"+payid+"'");
         delete.executeUpdate();
         delete.close();
         
         JOptionPane.showMessageDialog(null,"Successfully Deleted");
         
     } catch (Exception e) {
         System.err.println(e);
     }
     
     return result;
 }

}     
     

    

      

