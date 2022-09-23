package services;

import java.util.List;

import entities.Agence;
import entities.Client;
import entities.Compte;
import repositories.IAgenceRepository;
import repositories.IClientRepository;
import repositories.ICompteRepository;

// Metier
public class BanqueService implements IBanqueService {
    //Dependance 
    //Couplage Fort => dependance sous forme de classe
    //Couplage Faible  => sous forme d'interface
    IAgenceRepository agenceRepository;
    ICompteRepository compteRepository;
    IClientRepository clientRepository;

    public BanqueService(IAgenceRepository agenceRepository, ICompteRepository compteRepository,
            IClientRepository clientRepository) {
        this.agenceRepository = agenceRepository;
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
    }
    @Override
    public List<Agence> ListerAgence(){
        return agenceRepository.findAll();
    }
    @Override
    public Agence ajouterAgence(Agence agence){
      return  agenceRepository.insert(agence);
    }
    @Override
    public Agence rechercherAgenceParNum(String num){
        return  agenceRepository.findByNumero(num);
      }
      @Override
    public Compte creerCompte(Compte compte){
        return compteRepository.insert(compte);
    }
    @Override
    public Client creerClient(Client client){
        return clientRepository.insert(client);
    }
    @Override
    public Client rechercherClientParTel(String tel){
        return clientRepository.findByTel(tel);
      }
    @Override
   public List<Compte> LesCompteUnClient(String tel){
        return compteRepository.findByClient(tel);
    }

    
}
