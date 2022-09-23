package repositories;

import java.util.List;

import entities.Agence;

public interface IAgenceRepository {
    public List<Agence> findAll();
    public Agence insert(Agence agence);
    public Agence findByNumero(String num);
   
}
