package repositories.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import entities.Agence;
import repositories.IAgenceRepository;

public class AgenceRepository implements IAgenceRepository{

    @Override
    public List<Agence> findAll() {
        List<Agence> agences=new ArrayList<>();
         //1-charger le driver
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
         //2-ouvrir la connexion
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_l3_ism_2022", "root", "");
            System.out.println("connexion reussi");
            //3-Executer la requette
            Statement stm= conn.createStatement();
            // 4-Recuperer les resultats
           ResultSet rs= stm.executeQuery("select * from agence");
            while(rs.next()){
                // rs une ligne de la table ou un enregistrement ou un tuple => relationnel:BD
                    // colonne 1(int) => rs.getInt(1) ou rs.getInt("id")
                    // colonne 2(varchar) => rs.getString(2) ou rs.getString("numero")
                    Agence ag=new Agence(rs.getInt("id"),
                                         rs.getString("numero"), 
                                         rs.getString("adresse"),
                                         rs.getString("tel"));
                    agences.add(ag);
                //requette select Relation vers un objet(JAVA)

            }
            //5-Fermer la connexion
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return agences;
    }

    @Override
    public Agence insert(Agence agence) {
        String sql="INSERT INTO `agence` (`numero`, `adresse`, `tel`) VALUES (?,?,?);";
          //1-charger le driver
          try {
            Class.forName("com.mysql.cj.jdbc.Driver");
         //2-ouvrir la connexion
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_l3_ism_2022", "root", "");
            System.out.println("connexion reussi");
            //3-Executer la requette
            PreparedStatement pstm= conn.prepareStatement(sql);
            // remplacer les parametres de requetesnpar leurs valeurs
            pstm.setString(1, agence.getNumero());
            pstm.setString(2, agence.getAdresse());
            pstm.setString(3, agence.getTel());
            // 4-Recuperer les resultats
            int nbreLigne=pstm.executeUpdate();
            //5-Fermer la connexion
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return agence;
    }

    @Override
    public Agence findByNumero(String num) {
        Agence agence=null;
        //1-charger le driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
         //2-ouvrir la connexion
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_l3_ism_2022", "root", "");
            System.out.println("connexion reussi");
            //3-Executer la requette
            Statement stm= conn.createStatement();
            // 4-Recuperer les resultats
           ResultSet rs= stm.executeQuery("select * from agence where numero='"+num+"'");
            if(rs.next()){
                 agence=new Agence(rs.getInt("id"),
                rs.getString("numero"), 
                rs.getString("adresse"),
                rs.getString("tel"));
                //requette select Relation vers un objet(JAVA)
            }
            //5-Fermer la connexion
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return agence;
    }
    
}
