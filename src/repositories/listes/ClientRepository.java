package repositories.listes;

import java.util.ArrayList;
import java.util.List;

import entities.Client;
import repositories.IClientRepository;

public class ClientRepository implements IClientRepository {

    private List<Client> clients=new ArrayList<>();
    @Override
    public Client insert(Client client){
        clients.add(client);
        return client;
    }
    @Override
    public Client findByTel(String tel){
        return   clients
        .stream()
        .filter(cl->{
            return cl.getTel().compareTo(tel)==0;
        })
        .findFirst()
        .orElse(null);
    }
    @Override
    public Client findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
}
