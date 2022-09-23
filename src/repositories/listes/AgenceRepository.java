package repositories.listes;

import java.util.ArrayList;
import java.util.List;

import entities.Agence;
import repositories.IAgenceRepository;

public class AgenceRepository implements IAgenceRepository{
    
    //Base de donnees
    List<Agence> agences = new ArrayList<>();
    public AgenceRepository() {
        initListAgence();
    }
    public void initListAgence(){
       // Agence agence= new Agence("Point E","33 860 10 10")
       // agences.add(agence);
        agences.add(new Agence("Point E","33 860 10 10"));
        agences.add(new Agence("Fass ","33 860 10 11"));
        agences.add(new Agence("Colobane ","33 860 10 12"));
    }
    @Override
    public List<Agence> findAll(){
        return agences;
    }
@Override
    public Agence insert(Agence agence){
        agences.add(agence);
        return agence;
    } 
@Override
    public Agence findByNumero(String num){
        //Stream => pipe. permettent d'ecrire des requettes sur les listes ou collections
        return agences
        .stream()
        .filter(a->a.getNumero().compareTo(num)== 0)
        .findFirst()
        .orElse(null);

    }
}
