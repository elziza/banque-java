import java.util.Scanner;

import repositories.IAgenceRepository;
import repositories.IClientRepository;
import repositories.ICompteRepository;
import repositories.bd.AgenceRepository;
import repositories.bd.ClientRepository;
import repositories.bd.CompteRepository;
import services.BanqueService;
import services.IBanqueService;
import views.VAgence;
import views.VCompte;

public class App {
    public static void main(String[] args) throws Exception {
         // Dependance => couplage fort
         // polymorphisme
         IAgenceRepository agenceRepository= new AgenceRepository();
         IClientRepository clientRepository=new ClientRepository();
         ICompteRepository compteRepository=new CompteRepository(clientRepository);
         IBanqueService banqueService=new BanqueService(agenceRepository,compteRepository,clientRepository);
         
         Scanner clavier =new Scanner(System.in);

         int choix;
 
         do{
             System.out.println("1-Agence");
             System.out.println("2-Compte");
             System.out.println("3-Quitter");
             System.out.println("faites votre choix");
             choix=clavier.nextInt();
             clavier.nextLine();

             switch (choix) {
                case 1:
                VAgence.menu(banqueService);
                    
                    break;
                case 2:        
                VCompte.menu(banqueService); 
                    break;
             
                default:
                    break;
             }
            } while (choix!=4);
    }
}
