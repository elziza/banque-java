package services;

import java.util.List;

import entities.Agence;
import entities.Client;
import entities.Compte;

public interface IBanqueService {
    public List<Agence> ListerAgence();
    public Agence ajouterAgence(Agence agence);
  
    public Agence rechercherAgenceParNum(String num);
   
    public Compte creerCompte(Compte compte);
 
    public Client creerClient(Client client);
   
    public Client rechercherClientParTel(String tel);
    
   public List<Compte> LesCompteUnClient(String tel);
   
}
