package studentregistration;

import java.sql.*;
public class ConnectionDB {
 Connection c=null;
Statement st=null;
   

void connect(){
     try{
         Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
         c= DriverManager.getConnection("jdbc:ucanaccess://C://Users/Best Technology/StudentReg1.accdb");
    }catch(Exception e){
        
    }

}
    
}
