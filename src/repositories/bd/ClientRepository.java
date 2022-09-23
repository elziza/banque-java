package repositories.bd;

import java.sql.SQLException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Core.MysqlBD;
import entities.Client;
import entities.Role;
import repositories.IClientRepository;

public class ClientRepository extends MysqlBD implements IClientRepository  {
    private  final String SQL_INSERT="INSERT INTO `user` (`role`, `login`, `password`, `nom_complet`, `tel`) VALUES (?,?,?,?,?);";
    private final String SQL_SELECT_BY_TEL="SELECT * from `user` where `tel` LIKE ? ";
    private final String SQL_SELECT_BY_ID="SELECT * from `user` where `id` = ? ";
    
    @Override
    public Client insert(Client client) {

        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,client.getRole().name());
            ps.setString(2,client.getLogin());
            ps.setString(3,client.getPassword());
            ps.setString(4,client.getNomComplet());
            ps.setString(5,client.getTel());
            ps.executeUpdate();

            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                client.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return client;
    }

    @Override
    public Client findByTel(String tel) {
        Client client= null; 
        this.ouvrirConnexionBD();
        try {
            conn.prepareStatement(SQL_SELECT_BY_TEL);
            ps.setString(1, tel);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                client=new Client(rs.getInt("id"),
                                  rs.getString("role").compareTo("ROLE_CLIENT")==0? Role.Client:Role.Gestionnaire,
                                  rs.getString("login"),
                                  rs.getString("password"),
                                  rs.getString("nom_complet"),
                                  rs.getString("tel")
                                  );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return client;
    }

    @Override
    public Client findById(int id) {
        Client client=null;
        this.ouvrirConnexionBD();
        try {
            conn.prepareStatement(SQL_SELECT_BY_ID);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                client=new Client(rs.getInt("id"),
                                  rs.getString("role").compareTo("ROLE_CLIENT")==0? Role.Client:Role.Gestionnaire,
                                  rs.getString("login"),
                                  rs.getString("password"),
                                  rs.getString("nom_complet"),
                                  rs.getString("tel")
                                  );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return client;
    }
    
}
