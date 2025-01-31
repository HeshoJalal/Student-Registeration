/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentregistration;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



public class Enrolment extends javax.swing.JFrame {
    ConnectionDB con=new ConnectionDB();
    DefaultTableModel model;

  
    public Enrolment() {
        initComponents();
        con.connect();
        setResizable(false);
        setLocation(300,200);
        loadStudents();
        loadCourses();
        loadTable();

    }
     
    void resetFields(){
        selectCourse.setSelectedIndex(0);
        selectStudent.setSelectedIndex(0);
        
    }
    void reloadTable(){
        model.setRowCount(0);
        loadTable();
    }
    void loadStudents(){
        
        try {
           Statement st = con.c.createStatement();
           String selectSt="Select *from students";
           ResultSet rs= st.executeQuery(selectSt);
           while(rs.next()){ 
               String nameGet=rs.getString("name");
               selectStudent.addItem(nameGet);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enrolment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void loadCourses(){
        
        try {
           Statement st = con.c.createStatement();
           String selectSt="Select *from course";
           ResultSet rs= st.executeQuery(selectSt);
           while(rs.next()){ 
               String titleGet=rs.getString("title");
               selectCourse.addItem(titleGet);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enrolment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     String returnCell(){
        int row=jTable1.getSelectedRow();
        String cell=jTable1.getModel().getValueAt(row, 0).toString();
        
    return cell;}
    
     void loadTable(){
        
       model =(DefaultTableModel)jTable1.getModel();
        try {
            Statement st=con.c.createStatement();
            String selectSt="select student_course.ID,title,name from students,course,student_course where students.ID=student_ID and course.ID=course_ID ";
            ResultSet rs= st.executeQuery(selectSt);
            while(rs.next()){
               int student_course=rs.getInt("student_course.ID");
               String id=rs.getString("name");
               String nameGet=rs.getString("title");
               
               model.addRow(new Object[]{student_course,id,nameGet});
               resetFields();

            }
            

        } catch (SQLException ex) {
            Logger.getLogger(InsertStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
                //leraya aw 2 query anjam ayayn bo away ID aw talabayay select krawa 
           //lagal aw coursay select krawa bhenenawa
     int getSelectdStudentId(){
         int id = 0; 
         try {
            String getSeletectedStID="SELECT ID from students where name=? ";
           PreparedStatement pst_selectStID = con.c.prepareStatement(getSeletectedStID);
           pst_selectStID.setString(1, selectStudent.getSelectedItem().toString());
           ResultSet rs= pst_selectStID.executeQuery();
           while(rs.next()){
               id=rs.getInt("ID");         
            }
     }catch (SQLException e) {
         }
     
     return id;}
      int getCourseSelectdId(){
         int id = 0; 
         try {
            String getSeletectedStID="SELECT ID from course where title=? ";
           PreparedStatement pst_selectStID = con.c.prepareStatement(getSeletectedStID);
           pst_selectStID.setString(1, selectCourse.getSelectedItem().toString());
           ResultSet rs= pst_selectStID.executeQuery();
           while(rs.next()){
               id=rs.getInt("ID");         
            }
     }catch (Exception e) {
         }
     
     return id;}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        selectStudent = new javax.swing.JComboBox<>();
        selectCourse = new javax.swing.JComboBox<>();
        enroll = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setText("Enroll");

        selectStudent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Student....." }));
        selectStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectStudentActionPerformed(evt);
            }
        });

        selectCourse.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select course....." }));

        enroll.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        enroll.setText("Enroll");
        enroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Student name", "Course title"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        delete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enroll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete)))))
                .addGap(101, 101, 101)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(selectStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectCourse)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enroll)
                            .addComponent(delete)
                            .addComponent(update))
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectStudentActionPerformed

    private void enrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollActionPerformed
       
        if(selectStudent.getSelectedIndex()==0){
          JOptionPane.showMessageDialog(this, "Please select a student","Failed",JOptionPane.INFORMATION_MESSAGE);

        }
         if(selectCourse.getSelectedIndex()==0){
          JOptionPane.showMessageDialog(this, "Please select a course","Failed",JOptionPane.INFORMATION_MESSAGE);

        }
         else{
        try {
         String sqlPre="INSERT INTO student_course(student_ID,course_ID) VALUES(?,?)";
         PreparedStatement pst = con.c.prepareStatement(sqlPre);
         pst.setInt(1, getSelectdStudentId());
         pst.setInt(2, getCourseSelectdId());
         pst.executeUpdate();
         reloadTable();
         JOptionPane.showMessageDialog(this, "student enrolled for the course Successfully","SuccessFul",JOptionPane.INFORMATION_MESSAGE);
                
        } catch (SQLException ex) {
            Logger.getLogger(Enrolment.class.getName()).log(Level.SEVERE, null, ex);
        }
              
         }
          
    }//GEN-LAST:event_enrollActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
         String sqlPre="DELETE FROM student_course WHERE ID=?";
        try {
            PreparedStatement pst=con.c.prepareStatement(sqlPre);
            pst.setString(1, returnCell());
            pst.execute();
            reloadTable();

           JOptionPane.showMessageDialog(this, "Record deleted Successfully","SuccessFul",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(InsertStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i =jTable1.getSelectedRow();
        TableModel tableModel=jTable1.getModel();
        selectStudent.setSelectedItem(tableModel.getValueAt(i, 1).toString());
        selectCourse.setSelectedItem(tableModel.getValueAt(i, 2).toString());

    }//GEN-LAST:event_jTable1MouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
       
        if(selectStudent.getSelectedIndex()==0){
          JOptionPane.showMessageDialog(this, "Please select a student","Failed",JOptionPane.INFORMATION_MESSAGE);

        }
         if(selectCourse.getSelectedIndex()==0){
          JOptionPane.showMessageDialog(this, "Please select a course","Failed",JOptionPane.INFORMATION_MESSAGE);

        }
         else{
        try {
         String sqlPre="UPDATE student_course SET student_ID=? , course_ID=? WHERE ID=?";
         PreparedStatement pst = con.c.prepareStatement(sqlPre);
         pst.setInt(1, getSelectdStudentId());
         pst.setInt(2, getCourseSelectdId());
         pst.setString(3, returnCell());

         pst.executeUpdate();
         reloadTable();
         JOptionPane.showMessageDialog(this, "Updated successfully","SuccessFul",JOptionPane.INFORMATION_MESSAGE);
                
        } catch (SQLException ex) {
            Logger.getLogger(Enrolment.class.getName()).log(Level.SEVERE, null, ex);
        }
              
         }
    }//GEN-LAST:event_updateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JButton enroll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> selectCourse;
    private javax.swing.JComboBox<String> selectStudent;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
