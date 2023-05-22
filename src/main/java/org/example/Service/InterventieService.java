package org.example.Service;


import org.example.Domain.Entity;
import org.example.Domain.Client;
import org.example.Domain.Interventie;
import org.example.Exceptions.InvalidId;
import org.example.Repository.GoogleSheetsClientRepository;
import org.example.Repository.GoogleSheetsInterventieRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterventieService {
    GoogleSheetsInterventieRepository repo;
    GoogleSheetsClientRepository clientRepo;


    public InterventieService(GoogleSheetsInterventieRepository repo,
                              GoogleSheetsClientRepository clientRepo) {
        this.repo = repo;
        this.clientRepo = clientRepo;
    }


    public void addInterventie(Integer id, Integer idClient, String data, String dimensiune, float mc, float clor, float duritate,
                               float ph, float alcalinit, float temp, float salinitate) throws InvalidId {
        Entity check = repo.getEntity(id);
        if(check != null)
            throw new InvalidId("Id exists already!");

        Interventie interventie = new Interventie(id, idClient, data, dimensiune, mc, clor, duritate, ph, alcalinit, temp, salinitate);
        repo.add(interventie);

    }


    public void deleteInterventie(Integer id) throws InvalidId {
        Entity check =  repo.getEntity(id);
        if(check == null)
            throw new InvalidId("Given id does not exist!");
        repo.delete(id);

    }

    public void updateInterventie(Integer oldId, Integer id, Integer idClient, String data, String dimensiune, float mc, float clor, float duritate,
                                  float ph, float alcalinit, float temp, float salinitate) throws InvalidId {
        Entity check =  repo.getEntity(oldId);
        if(check == null)
            throw new InvalidId("Given id does not exist!");
        Interventie interventie = new Interventie(id, idClient, data, dimensiune, mc, clor, duritate, ph, alcalinit, temp, salinitate);
        repo.update(oldId, interventie);

    }


    public List<Interventie> showInterventions() throws IOException {
        List<Interventie> interventii = new ArrayList<>();
        for(int i = 0; i < repo.getEntities().size(); i++)
            interventii.add(repo.getEntities().get(i));

        return interventii;
    }

    public List<Interventie> getInterventionsByClientId(Integer clientId) throws IOException {
        List<Interventie> interventii = new ArrayList<>();
        for(int i = 0; i < repo.getEntities().size(); i++)
            if(repo.getEntities().get(i).getIdClient() == clientId)
                interventii.add(repo.getEntities().get(i));

        return interventii;
    }

    public List<String> GenerateRecomandationForIntervention(int idInterventie){
        List<String> recomandations = new ArrayList<>();
        Interventie interventie = repo.getEntity(idInterventie);
        /*

        De implementat
        Exemplu provizoriu

         */
        recomandations.add("Clor Tab: 4");
        recomandations.add("Clor Gr: 0.5");
        recomandations.add("pH: 2");
        recomandations.add("Antialgic: 0");

        return recomandations;

    }


    public Interventie getIntervention(int id) {
        return repo.getEntity(id);
    }
}
