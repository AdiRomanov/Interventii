package org.example.Repository;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import org.example.Domain.Client;
import org.example.Exceptions.InvalidId;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GoogleSheetsClientRepository implements Repository<Client, Integer> {

    private static final String APPLICATION_NAME = "InterventiiChimicale";
    private static final String SPREADSHEET_ID = "1KL221F9Cg_BcVBJGZSqMal5yC02WX61TJoIIr6qNh18";

    private static final String RANGE = "A2:E";


    private Sheets sheetsService = null;

    public GoogleSheetsClientRepository() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // Path to the service account credentials JSON file
        // String credentialsFilePath = "D:\\Proiecte\\Interventie\\src\\main\\resources\\credentials.json";
        ClassLoader classLoader = GoogleSheetsClientRepository.class.getClassLoader();
        URL resourceUrl = classLoader.getResource("credentials.json");
        String path = resourceUrl.getPath();


        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(path))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        sheetsService = new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }



    @Override
    public Client add(Client client) {
        List<List<Object>> newRows = new ArrayList<>();
        newRows.add(clientToRow(client));

        ValueRange valueRange = new ValueRange().setValues(newRows);

        try {
            sheetsService.spreadsheets().values()
                    .append(SPREADSHEET_ID, RANGE, valueRange)
                    .setValueInputOption("RAW")
                    .execute();
            return client;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Client delete(Integer id) throws InvalidId {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            List<List<Object>> updatedData = new ArrayList<>();

            boolean found = false;
            for (List<Object> row : data) {
                Integer clientId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(clientId, id)) {
                    found = true;
                } else {
                    updatedData.add(row);
                }
            }

            if (found) {
                updateSheetWithData(updatedData, SPREADSHEET_ID, RANGE);  // Update the sheet with the updated data
                return null; //getClientById(id, data);  // Return the deleted client
            } else {
                throw new InvalidId("The client with ID " + id + " does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to retrieve the deleted client by ID
    private Client getClientById(Integer id, List<List<Object>> data) {
        for (List<Object> row : data) {
            Integer clientId = Integer.parseInt(row.get(0).toString());
            if (Objects.equals(clientId, id)) {
                String firstName = row.get(1).toString();
                String lastName = row.get(2).toString();
                String address = row.get(3).toString();
                String phoneNumber = row.get(4).toString();
                return new Client(id, firstName, lastName, address, phoneNumber);
            }
        }
        return null; // Return null if client not found (should not happen)
    }

    // Helper method to update the sheet with the updated data
    private void updateSheetWithData(List<List<Object>> data, String spreadsheetId, String range) throws IOException {
        ClearValuesRequest clearRequest = new ClearValuesRequest();
        sheetsService.spreadsheets().values()
                .clear(spreadsheetId, range, clearRequest)
                .execute();

        ValueRange body = new ValueRange()
                .setValues(data);
        sheetsService.spreadsheets().values()
                .update(spreadsheetId, range, body)
                .setValueInputOption("RAW")
                .execute();
    }



    @Override
    public Client update(Integer id, Client newClient) {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            for (int i = 0; i < data.size(); i++) {
                List<Object> row = data.get(i);
                Integer clientId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(clientId, id)) {
                    data.set(i, clientToRow(newClient));
                    updateSheetWithData(data, SPREADSHEET_ID, RANGE);
                    return newClient;
                }
            }
            throw new InvalidId("The client with ID " + id + " does not exist.");
        } catch (IOException | InvalidId e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client getEntity(Integer id) {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            for (List<Object> row : data) {
                Integer clientId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(clientId, id)) {
                    return rowToClient(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Client> getEntities() throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();

        List<List<Object>> values = response.getValues();
        List<Client> clients = new ArrayList<>();

        if (values != null && !values.isEmpty()) {
            for (List<Object> row : values) {
                String id = row.get(0).toString();
                int idInt = Integer.parseInt(id);
                String firstName = row.get(1).toString();
                String lastName = row.get(2).toString();
                String address = row.get(3).toString();
                String phoneNumber = row.get(4).toString();
                Client client = new Client(idInt, firstName, lastName, address, phoneNumber);
                clients.add(client);
            }
        }

        return clients;
    }

    private List<List<Object>> getDataRange(String range) throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        return response.getValues();
    }

    private void updateSheetWithData(List<List<Object>> data) throws IOException {
        List<ValueRange> updatedData = new ArrayList<>();
        updatedData.add(new ValueRange()
                .setRange(RANGE)
                .setValues(data));
        BatchUpdateValuesRequest batchUpdateRequest = new BatchUpdateValuesRequest()
                .setValueInputOption("RAW")
                .setData(updatedData);

        sheetsService.spreadsheets().values()
                .batchUpdate(SPREADSHEET_ID, batchUpdateRequest)
                .execute();
    }

    private List<Object> clientToRow(Client client) {
        List<Object> row = new ArrayList<>();
        row.add(client.getId());
        row.add(client.getFirstName());
        row.add(client.getLastName());
        row.add(client.getAddress());
        row.add(client.getPhoneNumber());
        return row;
    }

    private Client rowToClient(List<Object> row) {
        Integer id = Integer.parseInt(row.get(0).toString());
        String firstName = row.get(1).toString();
        String lastName = row.get(2).toString();
        String address = row.get(3).toString();
        String phoneNumber = row.get(4).toString();
        return new Client(id, firstName, lastName, address, phoneNumber);
    }
}
