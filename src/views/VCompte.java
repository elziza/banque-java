package views;

import java.util.Scanner;

import entities.Agence;
import entities.Client;
import entities.Compte;
import entities.Epargne;
import services.BanqueService;
import services.IBanqueService;

public class VCompte {
    public static void menu(IBanqueService banqueService){
        Scanner clavier =new Scanner(System.in);
       
        int choix;

        do{
            System.out.println("1-Creer Compte");
            System.out.println("2-Lister les comptes d'un client ");
            System.out.println("4-quitter");
            System.out.println("faites votre choix");
            choix=clavier.nextInt();
            clavier.nextLine();
            switch (choix) {
                case 1:
                System.out.println("Entrer le telephone");
                String tel=clavier.nextLine();
                Client client=banqueService.rechercherClientParTel(tel);
                if(client==null){
                    System.out.println("Entrer le nom et Prenom");
                    String nomComplet=clavier.nextLine();
                    System.out.println("Entrer le login ");
                    String login=clavier.nextLine();
                    System.out.println("Entrer le password ");
                    String password=clavier.nextLine();
                    client=new Client(login, password, nomComplet, tel);
                    client=new Client(nomComplet,tel);
                    banqueService.creerClient(client);
                }
                banqueService.ListerAgence().forEach(System.out::println);
                System.out.println("Entrer l'id de l'agence");
                int idAgence= clavier.nextInt();
                Agence ag= new Agence(idAgence);

                System.out.println("Entrer le solde");
                Double solde=clavier.nextDouble();
                int type;
                do{
                        System.out.println("1-Epargne");
                        System.out.println("2-Cheque");
                        System.out.println("Entrer le type de compte");
                        type=clavier.nextInt();
                    }while(type !=1 && type !=2);
                    Compte cpte;
                    if(type==1){
                        System.out.println("Entrer le taux");
                        Double taux=clavier.nextDouble();
                        cpte=new Epargne(solde,taux);
                    }else{
                        System.out.println("Entrer les  frais");
                        Double frais=clavier.nextDouble();
                        cpte=new Epargne(solde,frais);
                    }

                    cpte.setAgence(ag);
                    cpte.setClient(client);
                    banqueService.creerCompte(cpte);
                    break;
                case 2:
                System.out.println("Entrer le telephone");
                 tel=clavier.nextLine();
                banqueService.LesCompteUnClient(tel)
                .forEach((cp)-> {
                    System.out.println(cp);
                });
                    break;
            
                default:
                    break;
            }
        } while (choix!=4);

    }
}
