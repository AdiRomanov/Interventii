package org.example.Repository;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import org.example.Domain.ChimicaleFolosite;
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

public class GoogleSheetsChimicaleFolositeRepository implements Repository<ChimicaleFolosite, Integer> {

    private static final String APPLICATION_NAME = "InterventiiChimicale";
    private static final String SPREADSHEET_ID = "1IvSf-ySZhBs4SacHPVRLvGWcfj52yid8_z2GLn1AUAk";
    private static final String RANGE = "A2:L";


    private Sheets sheetsService = null;

    public GoogleSheetsChimicaleFolositeRepository() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();


        //String credentialsFilePath = "D:\\Proiecte\\Interventie\\src\\main\\resources\\credentials.json";
        ClassLoader classLoader = GoogleSheetsChimicaleFolositeRepository.class.getClassLoader();
        URL resourceUrl = classLoader.getResource("credentials.json");
        String path = resourceUrl.getPath();

        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(path))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        sheetsService = new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }



    @Override
    public ChimicaleFolosite add(ChimicaleFolosite chimicaleFolosite) {
        List<List<Object>> newRows = new ArrayList<>();
        newRows.add(chimicaleToRow(chimicaleFolosite));

        ValueRange valueRange = new ValueRange().setValues(newRows);

        try {
            sheetsService.spreadsheets().values()
                    .append(SPREADSHEET_ID, RANGE, valueRange)
                    .setValueInputOption("RAW")
                    .execute();
            return chimicaleFolosite;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ChimicaleFolosite delete(Integer id) throws InvalidId {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            List<List<Object>> updatedData = new ArrayList<>();

            boolean found = false;
            for (List<Object> row : data) {
                Integer chimicaleId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(chimicaleId, id)) {
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
    public ChimicaleFolosite update(Integer id, ChimicaleFolosite newChimicaleFolosite) {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            for (int i = 0; i < data.size(); i++) {
                List<Object> row = data.get(i);
                Integer chimicaleId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(chimicaleId, id)) {
                    data.set(i, chimicaleToRow(newChimicaleFolosite));
                    updateSheetWithData(data, SPREADSHEET_ID, RANGE);
                    return newChimicaleFolosite;
                }
            }
            throw new InvalidId("The client with ID " + id + " does not exist.");
        } catch (IOException | InvalidId e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ChimicaleFolosite getEntity(Integer id) {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            for (List<Object> row : data) {
                Integer clientId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(clientId, id)) {
                    return rowToChimicaleFolosite(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<ChimicaleFolosite> getEntities() throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();

        List<List<Object>> values = response.getValues();
        List<ChimicaleFolosite> chimicale = new ArrayList<>();

        if (values != null && !values.isEmpty()) {
            for (List<Object> row : values) {
                ChimicaleFolosite chimicaleFolosite = rowToChimicaleFolosite(row);
                chimicale.add(chimicaleFolosite);
            }
        }

        return chimicale;
    }

    private List<List<Object>> getDataRange(String range) throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        return response.getValues();
    }


    private List<Object> chimicaleToRow(ChimicaleFolosite chimicaleFolosite) {
        List<Object> row = new ArrayList<>();
        row.add(chimicaleFolosite.getId());
        row.add(chimicaleFolosite.getIdInterventie());
        row.add(chimicaleFolosite.getClorGranule());
        row.add(chimicaleFolosite.getClorTablete());
        row.add(chimicaleFolosite.getClorLichid());
        row.add(chimicaleFolosite.getPhGranule());
        row.add(chimicaleFolosite.getPhLichid());
        row.add(chimicaleFolosite.getAntialgic());
        row.add(chimicaleFolosite.getAnticalcar());
        row.add(chimicaleFolosite.getFloculant());
        row.add(chimicaleFolosite.getSare());
        row.add(chimicaleFolosite.getBicarbonat());
        return row;
    }

    private ChimicaleFolosite rowToChimicaleFolosite(List<Object> row) {
        
        Integer id = Integer.parseInt(row.get(0).toString());
        Integer idInterventie = Integer.parseInt(row.get(1).toString());
        float clorGranule = Float.parseFloat(row.get(2).toString().replace(",", "."));
        float clorTablete = Float.parseFloat(row.get(3).toString().replace(",", "."));
        float clorLichid = Float.parseFloat(row.get(4).toString().replace(",", "."));
        float phGranule = Float.parseFloat(row.get(5).toString().replace(",", "."));
        float phLichid = Float.parseFloat(row.get(6).toString().replace(",", "."));
        float antialgic = Float.parseFloat(row.get(7).toString().replace(",", "."));
        float anticalcar = Float.parseFloat(row.get(8).toString().replace(",", "."));
        float floculant = Float.parseFloat(row.get(9).toString().replace(",", "."));
        float sare = Float.parseFloat(row.get(10).toString().replace(",", "."));
        float bicarbonat = Float.parseFloat(row.get(11).toString().replace(",", "."));
        
        
        return new ChimicaleFolosite(id, idInterventie, clorGranule, clorTablete, clorLichid, phGranule,
                phLichid, antialgic,anticalcar, floculant, sare, bicarbonat);
    }
}
