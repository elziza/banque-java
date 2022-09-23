package repositories.listes;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import entities.Compte;
import repositories.ICompteRepository;

public class CompteRepository implements ICompteRepository{
    List<Compte> comptes=new ArrayList<>();
    @Override
    public Compte insert(Compte compte){
        comptes.add(compte);
        return compte;
    }

    @Override
   public List<Compte> findByClient(String tel){
        return comptes
        .stream()
        .filter(cpt->cpt.getClient().getTel().compareTo(tel)==0)
        .collect(Collectors.toList());
    }
}
