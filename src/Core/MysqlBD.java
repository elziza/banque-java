package Core;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlBD implements IDataBase {

    protected Connection conn;
    protected PreparedStatement ps;


    @Override
    public void ouvrirConnexionBD() {
         //1-charger le driver
          try {
            Class.forName("com.mysql.cj.jdbc.Driver");
         //2-ouvrir la connexion
            try {
                conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_l3_ism_2022",
                    "root", "");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        }
         catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void fermerConnexionBD() {
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

    
}
