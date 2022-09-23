package repositories.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Core.MysqlBD;
import entities.Cheque;
import entities.Client;
import entities.Compte;
import entities.Epargne;
import entities.TypeCompte;
import repositories.IClientRepository;
import repositories.ICompteRepository;

public class CompteRepository extends MysqlBD implements ICompteRepository {

    private  final String SQL_INSERT="INSERT INTO `compte` ( `numero`, `solde`, `type`, `taux`, `frais`, `client_id`, `carte_id`, `agence_id`) VALUES ( ?, ?, ?, ?, ?, ?, NULL, ?);";
    private final String SQL_SELECT_BY_TEL="SELECT c.* FROM `compte` c,user u WHERE c.client_id = u.id and u.tel like ?";
    //couplage faible
    IClientRepository clientRepository; 
    // injection de dependance
    public CompteRepository(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Compte insert(Compte compte) {
       
        this.ouvrirConnexionBD();
        try {
            ps=conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,compte.getNumero());
            ps.setDouble(2,compte.getSolde());
            ps.setString(3,compte.getTypeCompte().name());
            if(compte.getTypeCompte()==TypeCompte.Cheque){
                ps.setNull(4, java.sql.Types.FLOAT);
                // downcasting
                ps.setDouble(5, ((Cheque)compte).getFrais());
            }else{
                ps.setDouble(4, ((Epargne)compte).getTaux());
                ps.setNull(5, java.sql.Types.FLOAT);

            }
            
            ps.setInt(6,compte.getClient().getId());
            ps.setInt(8,compte.getAgence().getId());
            ps.executeUpdate();

            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                compte.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return compte;
    }

    @Override
    public List<Compte> findByClient(String tel) {
        List<Compte>  Comptes=new ArrayList<>(); 
        this.ouvrirConnexionBD();
        try {
            conn.prepareStatement(SQL_SELECT_BY_TEL);
            ps.setString(1, tel);
            ResultSet rs=ps.executeQuery();
            Compte compte;
            Client client=null;
            while(rs.next()){
               if(rs.getString("type").compareTo("Cheque")==0){
                 compte=new Cheque(rs.getInt("id"),
                                         rs.getString("numero"),
                                         rs.getDouble("solde"), 
                                         rs.getDouble("frais"));
               
               }else{
                compte=new Epargne (rs.getInt("id"),
                                         rs.getString("numero"),
                                         rs.getDouble("solde"), 
                                         rs.getDouble("frais"));
               }
              
               if(client !=null){
                 client = clientRepository.findById(rs.getInt("client_id"));
               }
               compte.setClient(client);
               Comptes.add(compte);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return null;
    }

   
    
}
