package repositories;

import java.util.List;

import entities.Compte;

public interface ICompteRepository {
    public Compte insert(Compte compte);
   public List<Compte> findByClient(String tel);
       
}
