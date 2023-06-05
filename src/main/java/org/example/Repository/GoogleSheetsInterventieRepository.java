package org.example.Repository;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;

import org.example.Domain.Interventie;
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

public class GoogleSheetsInterventieRepository implements Repository<Interventie, Integer> {

    private static final String APPLICATION_NAME = "InterventiiChimicale";
    private static final String SPREADSHEET_ID = "1UmEHYlavpyIDpFdfK1IPCkNokW57L4r_7uCDsrJ6ur0";

    private static final String RANGE = "A2:U";


    private final Sheets sheetsService;

    public GoogleSheetsInterventieRepository() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();


        ClassLoader classLoader = GoogleSheetsInterventieRepository.class.getClassLoader();
        URL resourceUrl = classLoader.getResource("credentials.json");
        String path = resourceUrl.getPath();


        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(path))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        sheetsService = new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    @Override
    public Interventie add(Interventie interventie) {
        List<List<Object>> newRows = new ArrayList<>();
        newRows.add(interventieToRow(interventie));

        ValueRange valueRange = new ValueRange().setValues(newRows);

        try {
            sheetsService.spreadsheets().values()
                    .append(SPREADSHEET_ID, RANGE, valueRange)
                    .setValueInputOption("RAW")
                    .execute();
            return interventie;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Interventie delete(Integer id) throws InvalidId {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            List<List<Object>> updatedData = new ArrayList<>();

            boolean found = false;
            for (List<Object> row : data) {
                Integer interventieId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(interventieId, id)) {
                    found = true;
                } else {
                    updatedData.add(row);
                }
            }

            if (found) {
                updateSheetWithData(updatedData, SPREADSHEET_ID, RANGE);  // Update the sheet with the updated data
                return null; //getClientById(id, data);  // Return the deleted client
            } else {
                throw new InvalidId("The intervention with ID " + id + " does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


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
    public Interventie update(Integer id, Interventie interventie) {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            for (int i = 0; i < data.size(); i++) {
                List<Object> row = data.get(i);
                Integer clientId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(clientId, id)) {
                    data.set(i, interventieToRow(interventie));
                    updateSheetWithData(data, SPREADSHEET_ID, RANGE);
                    return interventie;
                }
            }
            throw new InvalidId("The intervention with ID " + id + " does not exist.");
        } catch (IOException | InvalidId e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Interventie getEntity(Integer id) {
        try {
            List<List<Object>> data = getDataRange(RANGE);
            for (List<Object> row : data) {
                Integer clientId = Integer.parseInt(row.get(0).toString());
                if (Objects.equals(clientId, id)) {
                    return rowToInterventie(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Interventie> getEntities() throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();

        List<List<Object>> values = response.getValues();
        List<Interventie> interventii = new ArrayList<>();

        if (values != null && !values.isEmpty()) {
            for (List<Object> row : values) {

                Interventie interventie = rowToInterventie(row);
                interventii.add(interventie);
            }
        }

        return interventii;
    }

    private List<List<Object>> getDataRange(String range) throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        return response.getValues();
    }



    private List<Object> interventieToRow(Interventie interventie) {
        List<Object> row = new ArrayList<>();
        row.add(interventie.getId());
        row.add(interventie.getIdClient());
        row.add(interventie.getDimensiune());
        row.add(interventie.getMc());
        row.add(interventie.getClorMasurat());
        row.add(interventie.getDuritateMasurata());
        row.add(interventie.getPhMasurat());
        row.add(interventie.getAlcalinitMasurat());
        row.add(interventie.getTempMasurata());
        row.add(interventie.getSalinitateMasurata());
        row.add(interventie.getData());
        row.add(interventie.getClorGranule());
        row.add(interventie.getClorTablete());
        row.add(interventie.getPhGranule());
        row.add(interventie.getClorLichid());
        row.add(interventie.getPhGranule());
        row.add(interventie.getAntialgic());
        row.add(interventie.getAnticalcar());
        row.add(interventie.getFloculant());
        row.add(interventie.getSare());
        row.add(interventie.getBicarbonat());

                /*

        this.clorGranule = clorGranule;
        this.clorTablete = clorTablete;
        this.clorLichid = clorLichid;
        this.phGranule = phGranule;
        this.phLichid = phLichid;
        this.antialgic = antialgic;
        this.anticalcar = anticalcar;
        this.floculant = floculant;
        this.sare = sare;
        this.bicarbonat = bicarbonat;*/

        return row;
    }

    private Interventie rowToInterventie(List<Object> row) {
        int id = Integer.parseInt(row.get(0).toString());
        int idClient = Integer.parseInt(row.get(1).toString());
        String dimensiune = row.get(2).toString();
        float mc = Float.parseFloat(row.get(3).toString().replace(",", "."));
        float clorMasurat = Float.parseFloat(row.get(4).toString().replace(",", "."));
        float duritateMasurata = Float.parseFloat(row.get(5).toString().replace(",", "."));
        float phMasurat = Float.parseFloat(row.get(6).toString().replace(",", "."));
        float alcalinitMasurat = Float.parseFloat(row.get(7).toString().replace(",", "."));
        float tempMasurata = Float.parseFloat(row.get(8).toString().replace(",", "."));
        float salinitateMasurata = Float.parseFloat(row.get(9).toString().replace(",", "."));
        String data = row.get(10).toString();
        float clorGranule = Float.parseFloat(row.get(11).toString().replace(",", "."));
        float clorTablete = Float.parseFloat(row.get(12).toString().replace(",", "."));
        float clorLichid = Float.parseFloat(row.get(13).toString().replace(",", "."));
        float phGranule = Float.parseFloat(row.get(14).toString().replace(",", "."));
        float phLichid = Float.parseFloat(row.get(15).toString().replace(",", "."));
        float antialgic = Float.parseFloat(row.get(16).toString().replace(",", "."));
        float anticalcar = Float.parseFloat(row.get(17).toString().replace(",", "."));
        float floculant = Float.parseFloat(row.get(18).toString().replace(",", "."));
        float sare = Float.parseFloat(row.get(19).toString().replace(",", "."));
        float bicarbonat = Float.parseFloat(row.get(20).toString().replace(",", "."));


        return new Interventie(id, idClient, dimensiune, mc, clorMasurat, duritateMasurata, phMasurat, alcalinitMasurat, tempMasurata,
                salinitateMasurata, data, clorGranule, clorTablete, clorLichid, phGranule,phLichid,antialgic,anticalcar,floculant,
                sare,bicarbonat);
    }
}
