package views;

import java.util.Scanner;

import entities.Agence;
import services.BanqueService;
import services.IBanqueService;

public class VAgence {
    
    public static void menu(IBanqueService banqueService){
        Scanner clavier =new Scanner(System.in);

        int choix;

        do{
            System.out.println("1-Lister");
            System.out.println("2-Ajouter");
            System.out.println("3-Rechercher");
            System.out.println("4-quitter");
            System.out.println("faites votre choix");
            choix=clavier.nextInt();
            clavier.nextLine();

            switch(choix){
                case 1 :
                // stream
                banqueService.ListerAgence().forEach(System.out::println);
                break;
                case 2 :
                // stream
                System.out.println("Entrer le telephone");
                String tel=clavier.nextLine();
                System.out.println("Entrer l'adresse");
                String adresse=clavier.nextLine();
                Agence agence=new Agence();
                agence.setAdresse(adresse);
                agence.setTel(tel);
                banqueService.ajouterAgence(agence);
                break;
                case 3 :
                // stream
                System.out.println("Entrer le numero ");
                String num=clavier.nextLine();
                Agence ag=banqueService.rechercherAgenceParNum(num);
                if(ag!=null){
                    System.out.println(ag);
                }else{
                    System.out.println("cette Agence n'existe pas");
                }
                break;

                default:
                    break;
            }
        } while (choix!=4);
    } 
}
