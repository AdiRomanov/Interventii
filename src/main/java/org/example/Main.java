package org.example;

import org.example.Exceptions.InvalidId;
import org.example.Repository.GoogleSheetsChimicaleFolositeRepository;
import org.example.Repository.GoogleSheetsClientRepository;
import org.example.Repository.GoogleSheetsInterventieRepository;
import org.example.Service.ChimicaleFolositeService;
import org.example.Service.ClientService;
import org.example.Service.InterventieService;
import org.example.UserInterface.UserInterface;
import org.example.Validator.ClientValidator;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) throws GeneralSecurityException, IOException, InvalidId {

        GoogleSheetsClientRepository clientRepo = new GoogleSheetsClientRepository();
        ClientService clientService = new ClientService(clientRepo);
        ClientValidator clientValidator = new ClientValidator();

        GoogleSheetsInterventieRepository interventieRepo = new GoogleSheetsInterventieRepository();
        InterventieService interventieService = new InterventieService(interventieRepo, clientRepo);

        GoogleSheetsChimicaleFolositeRepository chimicaleFolositeRepo = new GoogleSheetsChimicaleFolositeRepository();
        ChimicaleFolositeService chimicaleFolositeService = new ChimicaleFolositeService(chimicaleFolositeRepo);

        UserInterface userInterface = new UserInterface(clientService, clientValidator, interventieService, chimicaleFolositeService);

        userInterface.run();

    }
}