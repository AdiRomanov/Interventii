package org.example.Service;


import org.example.Domain.Entity;
import org.example.Domain.Client;
import org.example.Exceptions.InvalidId;
import org.example.Repository.GoogleSheetsClientRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    GoogleSheetsClientRepository repo;


    public ClientService(GoogleSheetsClientRepository repo) {
        this.repo = repo;
    }


    public void addClient(Integer id, String firstName, String lastName, String address, String phoneNumber) throws InvalidId {
        Entity check = repo.getEntity(id);
        if(check != null)
            throw new InvalidId("Id exists already!");
        Client client = new Client(id, firstName, lastName, address, phoneNumber);
        repo.add(client);

    }


    public void deleteClient(Integer id) throws InvalidId {
        Entity check =  repo.getEntity(id);
        if(check == null)
            throw new InvalidId("Given id does not exist!");
        repo.delete(id);

    }

    public void updateClient(Integer id, Integer newId, String newFirstName, String newLastName, String newAddress, String newPhoneNumber) throws InvalidId {
        Entity check =  repo.getEntity(id);
        if(check == null)
            throw new InvalidId("Given id does not exist!");
        Client client = new Client(newId, newFirstName, newLastName, newAddress, newPhoneNumber);
        repo.update(id, client);

    }


    public List<Client> showClients() throws IOException {
        List<Client> clients = new ArrayList<>();
        for(int i = 0; i < repo.getEntities().size(); i++)
            clients.add(repo.getEntities().get(i));

        return clients;
    }

    public Client getClient(int id) {
        return repo.getEntity(id);
    }
}
