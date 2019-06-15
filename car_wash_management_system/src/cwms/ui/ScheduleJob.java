/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.ui;

import cwms.DataLayer.Common.ConnectionType;
import cwms.DataLayer.Common.DataBase;
import cwms.DataLayer.DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author Asanka
 */
public class ScheduleJob extends javax.swing.JFrame {
int jobid;
int empid;
boolean cboxcondition=true;
String currentdate="16 Nov 2017";//;new java.util.Date().toString().substring(9,11)+new java.util.Date().toString().substring(4,7)+new java.util.Date().toString().substring(23,28);
int cnt=0;      
    /**
     * Creates new form JobScheduling
     */
    public ScheduleJob() {
        initComponents();
       // viewHandler();
        //comboLoad();
        empidmoreLbl.setVisible(false);
        empidMoreCmb.setVisible(false);
       
    }
  public ScheduleJob(int job_id)
   {
       this();
       System.out.println("at constructor count ="+cnt);
       jobidLbl.setText(Integer.toString(job_id));
       this.jobid=job_id;
       if(cnt==0)
       {
           
           loadPresentEmployees(currentdate);
           
           cnt++;
           System.out.println("at if true count ="+cnt);
          return;
       }    
       else
       {
           firstDeleteView();
           notfviewHandler();
           comboLoad();
           cnt++;
           System.out.println("at if false count ="+cnt);
           
           
       }
      
       System.out.println("at end count ="+cnt);
       System.out.println(currentdate);
   }
private void notfviewHandler()
{
    try
    {
    DataBase db=new DataBase(ConnectionType.MYSQL);
            Connection conn=db.getConnection();
   // String query="DROP VIEW  if exists cpemployees_present";
 
         
    
            PreparedStatement pstmtDrop = conn.prepareStatement("  CREATE VIEW cpemployees_presenttt"
                    + " AS"
                    + " SELECT attendance.emp_id as emp_id"
                    + " FROM attendance,employee"
                    + " WHERE employee.emp_id = attendance.emp_id "
                    + "AND `date_of_work` LIKE ? "
                    + "AND emp_designation in (\"Permanent\",\"Casual\")");
            //pstmtDrop.executeUpdate(query);
            pstmtDrop.setString(1,currentdate);
            //PreparedStatement ps=conn.prepareStatement(q);
            pstmtDrop.executeUpdate();
            pstmtDrop.close();
    }
    catch(SQLException e)
    {
        System.err.println(e);
    }
    catch(Exception e1)
    {
        System.err.println(e1);
    }
    
}
private void firstDeleteView()
{
    try
    {
            DataBase db=new DataBase(ConnectionType.MYSQL);
            Connection conn=db.getConnection();

            PreparedStatement pstmtDrop = conn.prepareStatement("DROP VIEW IF EXISTS `cpemployees_presenttt`");
         
            pstmtDrop.executeUpdate();
            pstmtDrop.close();
    }
    catch(SQLException e)
    {
        System.err.println(e);
    }
    catch(Exception e1)
    {
        System.err.println(e1);
    }
}
private void loadPresentEmployees(String cdate)
{
    try{
        DataBase db=new DataBase(ConnectionType.MYSQL);
        Connection conn=db.getConnection();
        PreparedStatement pstmt=conn.prepareStatement( "SELECT attendance.emp_id as emp_id"
                    + " FROM attendance,employee"
                    + " WHERE employee.emp_id = attendance.emp_id "
                    + "AND `date_of_work` LIKE ? "
                    + "AND emp_designation in (\"Permanent\",\"Casual\")");
        pstmt.setString(1,cdate);
             
        ResultSet rs=pstmt.executeQuery();
        empidoneCmb.removeAllItems();

            
            while(rs.next())
            {
                empid=rs.getInt(1);
                System.out.println(empid);
                empidoneCmb.addItem(Integer.toString(empid));
                empidoneCmb.setSelectedItem(null);
            }
            rs.close();
            conn.close();
    }
    catch(SQLException e)
    {
        System.err.println(e);
    }
    catch(Exception e1)
    {
        System.err.println(e1);
    }   
}




 
   private void comboLoad()
   {
        ResultSet res = null;
        
       try
        {
            DataBase db=new DataBase(ConnectionType.MYSQL);
            Connection conn=db.getConnection();
            
            
           
            
            PreparedStatement prepst=conn.prepareStatement("SELECT job_has_employee.emp_id FROM cpemployees_presenttt, job_has_employee WHERE cpemployees_presenttt.emp_id = job_has_employee.emp_id GROUP BY job_has_employee.emp_id HAVING count(job_has_employee.emp_id)<3");

          
            res = prepst.executeQuery();
            empidoneCmb.removeAllItems();

            
            while(res.next())
            {
                empid=res.getInt(1);
                System.out.println(empid);
                empidoneCmb.addItem(Integer.toString(empid));
                empidoneCmb.setSelectedItem(null);
            }
            res.close();
            conn.close();
        }
       catch(SQLException e)
        {
            System.err.println(e);
        }
        catch(Exception e1)
        {
            System.err.println(e1);
        }
      
   }    
   
           /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        empidmoreLbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        schedjobBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jobidLbl = new javax.swing.JLabel();
        empidoneCmb = new javax.swing.JComboBox<String>();
        homeBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        chbox = new javax.swing.JCheckBox();
        emidoneLbl = new javax.swing.JLabel();
        empidMoreCmb = new javax.swing.JComboBox();

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Employee ID:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(25, 77, 25));

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Schedule Job");

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Job ID");

        empidmoreLbl.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        empidmoreLbl.setForeground(new java.awt.Color(255, 255, 255));
        empidmoreLbl.setText("Employee ID:");

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Do you wish to add more employees to the job?");

        schedjobBtn.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        schedjobBtn.setText("Schedule Job");
        schedjobBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedjobBtnActionPerformed(evt);
            }
        });

        cancelBtn.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        jobidLbl.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jobidLbl.setForeground(new java.awt.Color(255, 255, 255));

        empidoneCmb.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N

        homeBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\USER\\Desktop\\cwms_sherry\\project icons\\Home_26px.png")); // NOI18N
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });

        backBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\USER\\Desktop\\cwms_sherry\\project icons\\Back Arrow_26px.png")); // NOI18N
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("Microsoft JhengHei", 0, 11)); // NOI18N
        exitButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\USER\\Desktop\\cwms_sherry\\project icons\\Exit_26px.png")); // NOI18N
        exitButton.setText("Exit");

        chbox.setBackground(new java.awt.Color(25, 77, 25));
        chbox.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        chbox.setForeground(new java.awt.Color(255, 255, 255));
        chbox.setText("Yes");

        emidoneLbl.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        emidoneLbl.setForeground(new java.awt.Color(255, 255, 255));
        emidoneLbl.setText("Employee ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chbox))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(emidoneLbl)
                                        .addGap(62, 62, 62)
                                        .addComponent(empidoneCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jobidLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(empidmoreLbl)
                                        .addGap(63, 63, 63)
                                        .addComponent(empidMoreCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 194, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(schedjobBtn)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBtn)))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jobidLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emidoneLbl)
                    .addComponent(empidoneCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(chbox))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(empidMoreCmb)
                        .addGap(2, 2, 2))
                    .addComponent(empidmoreLbl))
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schedjobBtn)
                    .addComponent(cancelBtn))
                .addGap(18, 18, 18)
                .addComponent(exitButton)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void schedjobBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedjobBtnActionPerformed
        int jid=Integer.parseInt(jobidLbl.getText());
        int emid=Integer.parseInt(empidoneCmb.getSelectedItem().toString());
        DataAccess da=new DataAccess();
       boolean res=da.scheduleJob(jid, emid, currentdate);
       
        while(res)
        {
             this.dispose();
                     
             String cmessage = "Employee scheduled successfully. Do you wish to schedule more employees to this job?";   
             String conf = "Exit Message";
             int confirm = JOptionPane.showConfirmDialog(null, cmessage, conf,JOptionPane.YES_NO_OPTION);
             if(confirm == JOptionPane.YES_OPTION){
                 this.dispose();
                 ScheduleJob sc=new ScheduleJob(jobid);
                 sc.setVisible(true);
                 res=true;
                }
        else{
                this.dispose();
                res=false;
                break;
        }
        }
        
    }//GEN-LAST:event_schedjobBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
      Main m1=new Main();
        m1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
         JobMain m1=new JobMain();
        m1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScheduleJob.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScheduleJob.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScheduleJob.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScheduleJob.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScheduleJob().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JCheckBox chbox;
    private javax.swing.JLabel emidoneLbl;
    private javax.swing.JComboBox empidMoreCmb;
    private javax.swing.JLabel empidmoreLbl;
    private javax.swing.JComboBox<String> empidoneCmb;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jobidLbl;
    private javax.swing.JButton schedjobBtn;
    // End of variables declaration//GEN-END:variables
}
