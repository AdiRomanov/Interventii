package org.example.UserInterface;


import org.example.Domain.Client;
import org.example.Domain.Interventie;
import org.example.Exceptions.InvalidId;
import org.example.Exceptions.InvalidName;

import org.example.Service.ClientService;
import org.example.Service.InterventieService;
import org.example.Validator.ClientValidator;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    ClientService clientService;
    ClientValidator userValidator;
    InterventieService interventieService;



    public UserInterface(ClientService clientService, ClientValidator userValidator,
                         InterventieService interventieService) {
        this.clientService = clientService;
        this.userValidator = userValidator;
        this.interventieService = interventieService;
    }

    static void showMenu() {
        System.out.println("1. Add user");
        System.out.println("2. Update user");
        System.out.println("3. Delete user");
        System.out.println("4. Show users");
        System.out.println();
        System.out.println("5. Add interventie");
        System.out.println("6. Update interventie");
        System.out.println("7. Delete interventie");
        System.out.println("8. Show interventii");
        System.out.println();
        System.out.println("9. Get interventions by client id");
        System.out.println("10. Get recommandation for intervention by intervention id");
//        System.out.println();
//        System.out.println("11. Add chimicaleFolosite");
//        System.out.println("12. Update chimicaleFolosite");
//        System.out.println("13. Delete chimicaleFolosite");
//        System.out.println("14. Show chimicaleFolosite");
//        System.out.println("15. Show meniul pentru chimicale");


        System.out.println();
        System.out.println("0. Exit");
    }

    public void run() throws IOException, InvalidId {
        boolean ok = true;

        Scanner in = new Scanner(System.in);
        String opt;


        while (ok) {
            System.out.println();
            showMenu();
            System.out.println("Dati optiunea: ");
            opt = in.nextLine();


            if (Objects.equals(opt, "1")) uiAddUser();
            if (Objects.equals(opt, "2")) uiUpdateUser();
            if (Objects.equals(opt, "3")) uiDeleteUser();
            if (Objects.equals(opt, "4")) uiGetUsers();

            if (Objects.equals(opt, "5")) uiAddIntervention();
            if (Objects.equals(opt, "6")) uiUpdateIntervention();
            if (Objects.equals(opt, "7")) uiDeleteIntervention();
            if (Objects.equals(opt, "8")) uiGetInterventions();

            if (Objects.equals(opt, "9")) uiGetClientInterventions();

            // Nu stiu formula de calcul pentru tratament
            if (Objects.equals(opt, "10")) uiGenerateRecommendationForIntervention();

//
//            if (Objects.equals(opt, "11")) uiAddChimicale();
//            if (Objects.equals(opt, "12")) uiUpdateChimicale();
//            if (Objects.equals(opt, "13")) uiDeleteChimicale();
//            if (Objects.equals(opt, "14")) uiGetChimicale();
//            if (Objects.equals(opt, "15")) runChemicalsMenu();
//

            if (Objects.equals(opt, "0"))
                ok = false;

        }
    }

    static void showChemicalsMenu(){
        System.out.println("1. Set clor granule");
        System.out.println("2. Set clor tablete");
        System.out.println("3. Set clor lichid");
        System.out.println("4. Set ph granule");
        System.out.println("5. Set ph lichid");
        System.out.println("6. Set antialgic");
        System.out.println("7. Set anticalcar");
        System.out.println("8. Set floculant");
        System.out.println("9. Set sare");
        System.out.println("10. Set bicarbonat");
        System.out.println();
        System.out.println("0. Exit");


    }

    private void runChemicalsMenu() throws InvalidId {

        boolean ok = true;

        Scanner in2 = new Scanner(System.in);
        String opt2;


        while (ok) {
            System.out.println();
            showChemicalsMenu();
            System.out.println("Dati optiunea: ");
            opt2 = in2.nextLine();

            if (Objects.equals(opt2, "1")) uiSetClorGranule();
            if (Objects.equals(opt2, "2")) uiSetClorTablete();
            if (Objects.equals(opt2, "3")) uiSetClorLichid();
            if (Objects.equals(opt2, "4")) uiSetPhGranule();
            if (Objects.equals(opt2, "5")) uiSetPhLichid();
            if (Objects.equals(opt2, "6")) uiSetAntialgic();
            if (Objects.equals(opt2, "7")) uiSetAnticalcar();
            if (Objects.equals(opt2, "8")) uiSetFloculant();
            if (Objects.equals(opt2, "9")) uiSetSare();
            if (Objects.equals(opt2, "10")) uiSetBicarbonat();

            if (Objects.equals(opt2, "0"))
                ok = false;
        }

    }

    private void uiSetBicarbonat() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati bicarbonat: ");
        String bicarbonatString = in.nextLine();
        float bicarbonat = Float.parseFloat(bicarbonatString);

        //chimicaleFolositeService.serviceSetBicarbonat(id, bicarbonat);
    }

    private void uiSetSare() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati sare: ");
        String sareString = in.nextLine();
        float sare = Float.parseFloat(sareString);

        //chimicaleFolositeService.serviceSetSare(id, sare);
    }

    private void uiSetFloculant() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati floculant: ");
        String floculantString = in.nextLine();
        float floculant = Float.parseFloat(floculantString);

        //chimicaleFolositeService.serviceSetFloculant(id, floculant);
    }

    private void uiSetAnticalcar() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati anticalcar: ");
        String anticalcarString = in.nextLine();
        float anticalcar = Float.parseFloat(anticalcarString);

        //chimicaleFolositeService.serviceSetAnticalcar(id, anticalcar);
    }

    private void uiSetAntialgic() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati antialgic: ");
        String antialgicString = in.nextLine();
        float antialgic = Float.parseFloat(antialgicString);

        //chimicaleFolositeService.serviceSetAntialgic(id, antialgic);
    }

    private void uiSetPhLichid() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati ph lichid: ");
        String phLichidString = in.nextLine();
        float phLichid = Float.parseFloat(phLichidString);

        //chimicaleFolositeService.serviceSetPhLichid(id, phLichid);
    }

    private void uiSetPhGranule() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati ph granule: ");
        String phGranuleString = in.nextLine();
        float phGranule = Float.parseFloat(phGranuleString);

        //chimicaleFolositeService.serviceSetPhGranule(id, phGranule);
    }

    private void uiSetClorLichid() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati clor lichid: ");
        String clorLichidString = in.nextLine();
        float clorLichid = Float.parseFloat(clorLichidString);

        //chimicaleFolositeService.serviceSetClorLichid(id, clorLichid);
    }

    private void uiSetClorTablete() throws InvalidId {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati clor tablete: ");
        String clorTableteString = in.nextLine();
        float clorTablete = Float.parseFloat(clorTableteString);

        //chimicaleFolositeService.serviceSetClorTablete(id, clorTablete);
    }

    private void uiSetClorGranule() throws InvalidId {

        Scanner in = new Scanner(System.in);
        System.out.println("Dati id: ");
        String idString = in.nextLine();
        int id = Integer.parseInt(idString);

        System.out.println("Dati clor granule: ");
        String clorGranuleString = in.nextLine();
        float clorGranule = Float.parseFloat(clorGranuleString);

        //chimicaleFolositeService.serviceSetClorGranule(id, clorGranule);

    }



    private void uiGetChimicale() throws IOException {
//        System.out.println("The chemicals are: ");
//        List<ChimicaleFolosite> chimicale = chimicaleFolositeService.showChimicaleFolosite();
//        for(ChimicaleFolosite chimicala : chimicale) System.out.println(chimicala.toString());
    }

    private void uiDeleteChimicale() {
        Scanner in = new Scanner(System.in);
        System.out.println("Dati id-ul setului de chimicale: ");
        String idUserString = in.nextLine();
        int idChimicale = Integer.parseInt(idUserString);

        //chimicaleFolositeService.deleteChimicaleFolosite(idChimicale);


    }

    private void uiUpdateChimicale() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id: ");
            String idString = in.nextLine();
            int id = Integer.parseInt(idString);


            System.out.println("Dati id: ");
            String idStringNou = in.nextLine();
            int idNou = Integer.parseInt(idStringNou);

            System.out.println("Dati id interventie: ");
            String idClientString = in.nextLine();
            int idInterventie = Integer.parseInt(idClientString);

            System.out.println("Dati clor granule: ");
            String clorGranuleString = in.nextLine();
            float clorGranule = Float.parseFloat(clorGranuleString);

            System.out.println("Dati clor tablete: ");
            String clorTableteString = in.nextLine();
            float clorTablete = Float.parseFloat(clorTableteString);

            System.out.println("Dati clor lichid: ");
            String clorLichidString = in.nextLine();
            float clorLichid = Float.parseFloat(clorLichidString);

            System.out.println("Dati ph granule: ");
            String phGranuleString = in.nextLine();
            float phGranule = Float.parseFloat(phGranuleString);

            System.out.println("Dati ph lichid: ");
            String phLichidString = in.nextLine();
            float phLichid = Float.parseFloat(phLichidString);

            System.out.println("Dati antialgic: ");
            String antialgicString = in.nextLine();
            float antialgic = Float.parseFloat(antialgicString);

            System.out.println("Dati anticalcar: ");
            String anticalcarString = in.nextLine();
            float anticalcar= Float.parseFloat(anticalcarString);


            System.out.println("Dati floculant");
            String floculantString = in.nextLine();
            float floculant = Float.parseFloat(floculantString);

            System.out.println("Dati sare: ");
            String salinitateString = in.nextLine();
            float salinitate = Float.parseFloat(salinitateString);

            System.out.println("Dati bicarbonat: ");
            String bicarbonatString = in.nextLine();
            float bicarbonat = Float.parseFloat(bicarbonatString);

//            chimicaleFolositeService.updateChimicaleFolosite(id, idNou, idInterventie,clorGranule, clorTablete, clorLichid,phGranule,
//                    phLichid,antialgic,anticalcar, floculant,salinitate, bicarbonat);

        }catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void uiAddChimicale() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id: ");
            String idString = in.nextLine();
            int id = Integer.parseInt(idString);

            System.out.println("Dati id interventie: ");
            String idClientString = in.nextLine();
            int idInterventie = Integer.parseInt(idClientString);

            System.out.println("Dati clor granule: ");
            String clorGranuleString = in.nextLine();
            float clorGranule = Float.parseFloat(clorGranuleString);

            System.out.println("Dati clor tablete: ");
            String clorTableteString = in.nextLine();
            float clorTablete = Float.parseFloat(clorTableteString);

            System.out.println("Dati clor lichid: ");
            String clorLichidString = in.nextLine();
            float clorLichid = Float.parseFloat(clorLichidString);

            System.out.println("Dati ph granule: ");
            String phGranuleString = in.nextLine();
            float phGranule = Float.parseFloat(phGranuleString);

            System.out.println("Dati ph lichid: ");
            String phLichidString = in.nextLine();
            float phLichid = Float.parseFloat(phLichidString);

            System.out.println("Dati antialgic: ");
            String antialgicString = in.nextLine();
            float antialgic = Float.parseFloat(antialgicString);

            System.out.println("Dati anticalcar: ");
            String anticalcarString = in.nextLine();
            float anticalcar= Float.parseFloat(anticalcarString);


            System.out.println("Dati floculant");
            String floculantString = in.nextLine();
            float floculant = Float.parseFloat(floculantString);

            System.out.println("Dati sare: ");
            String salinitateString = in.nextLine();
            float salinitate = Float.parseFloat(salinitateString);

            System.out.println("Dati bicarbonat: ");
            String bicarbonatString = in.nextLine();
            float bicarbonat = Float.parseFloat(bicarbonatString);

//            chimicaleFolositeService.addChimicaleFolosite(id, idInterventie,clorGranule, clorTablete, clorLichid,phGranule,
//                    phLichid,antialgic,anticalcar, floculant,salinitate, bicarbonat);

        }catch (Exception ex) {
            System.out.println(ex);
        }
    }



    private void uiGenerateRecommendationForIntervention() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id-ul interventiei: ");
            String idInterventionString = in.nextLine();
            int idIntervention = Integer.parseInt(idInterventionString);
            Interventie interventie = interventieService.getIntervention(idIntervention);
            System.out.println("Pentru interventia:");
            System.out.println(interventie.toString());
            for(String recommendation: interventieService.GenerateRecomandationForIntervention(idIntervention)){
                System.out.println(recommendation);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void uiGetClientInterventions() throws IOException {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id-ul clientului: ");
            String idCliendString = in.nextLine();
            int idClient = Integer.parseInt(idCliendString);

            Client client = clientService.getClient(idClient);


            System.out.println("The interventions for " + client.getFirstName() + " " + client.getLastName() + ": ");
            List<Interventie> interventii = interventieService.getInterventionsByClientId(idClient);
            if(!interventii.isEmpty())
                for (Interventie interventie : interventii) System.out.println(interventie.toString());
            else System.out.println("There are no interventions for this client.");


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void uiGetInterventions() throws IOException {
        System.out.println("The Interventions are: ");
        List<Interventie> interventii = interventieService.showInterventions();
        for(Interventie interventie : interventii) System.out.println(interventie.toString());
    }

    private void uiDeleteIntervention() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id-ul interventiei: ");
            String idInterventieString = in.nextLine();
            int idInterventie = Integer.parseInt(idInterventieString);

            interventieService.deleteInterventie(idInterventie);


        } catch (InvalidId invalidId) {
            System.out.println(invalidId.getMessage());
        }
    }

    private void uiUpdateIntervention() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id: ");
            String idString = in.nextLine();
            int id = Integer.parseInt(idString);

            System.out.println("Dati id nou: ");
            String idNouString = in.nextLine();
            int idNou = Integer.parseInt(idNouString);

            System.out.println("Dati id client: ");
            String idClientString = in.nextLine();
            int idClient = Integer.parseInt(idClientString);

            System.out.println("Dati dimensiune: ");
            String dimensiune = in.nextLine();

            System.out.println("Dati mc: ");
            String mcString = in.nextLine();
            float mc = Float.parseFloat(mcString);

            System.out.println("Dati clor: ");
            String clorString = in.nextLine();
            float clor = Float.parseFloat(clorString);

            System.out.println("Dati duritate: ");
            String duritateString = in.nextLine();
            float duritate = Float.parseFloat(duritateString);

            System.out.println("Dati ph: ");
            String phString = in.nextLine();
            float ph = Float.parseFloat(phString);

            System.out.println("Dati alcalinit: ");
            String alcalinitString = in.nextLine();
            float alcalinit = Float.parseFloat(alcalinitString);

            System.out.println("Dati temp: ");
            String tempString = in.nextLine();
            float temp = Float.parseFloat(tempString);

            System.out.println("Dati salinitate: ");
            String salinitateString = in.nextLine();
            float salinitate = Float.parseFloat(salinitateString);

            System.out.println("Dati data: ");
            String data = in.nextLine();



            System.out.println("Dati clorG: ");
            String str = in.nextLine();
            float clorG = Float.parseFloat(str);
            System.out.println("Dati clorT: ");
            String str1 = in.nextLine();
            float clorT = Float.parseFloat(str1);
            System.out.println("Dati clorL: ");
            String str2 = in.nextLine();
            float clorL = Float.parseFloat(str2);
            System.out.println("Dati phG: ");
            String str3 = in.nextLine();
            float phG = Float.parseFloat(str3);
            System.out.println("Dati phL: ");
            String str4 = in.nextLine();
            float phL = Float.parseFloat(str4);
            System.out.println("Dati antialgic: ");
            String str5 = in.nextLine();
            float antialgic = Float.parseFloat(str5);
            System.out.println("Dati anticalcar: ");
            String str6 = in.nextLine();
            float anticalcar = Float.parseFloat(str6);
            System.out.println("Dati floculant: ");
            String str7= in.nextLine();
            float floculant = Float.parseFloat(str7);
            System.out.println("Dati sare: ");
            String str8 = in.nextLine();
            float sare = Float.parseFloat(str8);
            System.out.println("Dati bicarbonat: ");
            String str9 = in.nextLine();
            float bicarbonat = Float.parseFloat(str9);




            interventieService.updateInterventie(id, idNou, idClient, dimensiune, mc, clor, duritate, ph, alcalinit, temp, salinitate, data, clorG, clorT, clorL, phG, phL, antialgic,
                    anticalcar, floculant, sare, bicarbonat);
        } catch (InvalidId ex) {
            System.out.println(ex);
        }
    }

    private void uiAddIntervention() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id: ");
            String idString = in.nextLine();
            int id = Integer.parseInt(idString);

            System.out.println("Dati id client: ");
            String idClientString = in.nextLine();
            int idClient = Integer.parseInt(idClientString);

            System.out.println("Dati dimensiune: ");
            String dimensiune = in.nextLine();

            System.out.println("Dati mc: ");
            String mcString = in.nextLine();
            float mc = Float.parseFloat(mcString);

            System.out.println("Dati clor: ");
            String clorString = in.nextLine();
            float clor = Float.parseFloat(clorString);

            System.out.println("Dati duritate: ");
            String duritateString = in.nextLine();
            float duritate = Float.parseFloat(duritateString);

            System.out.println("Dati ph: ");
            String phString = in.nextLine();
            float ph = Float.parseFloat(phString);

            System.out.println("Dati alcalinit: ");
            String alcalinitString = in.nextLine();
            float alcalinit = Float.parseFloat(alcalinitString);

            System.out.println("Dati temp: ");
            String tempString = in.nextLine();
            float temp = Float.parseFloat(tempString);

            System.out.println("Dati salinitate: ");
            String salinitateString = in.nextLine();
            float salinitate = Float.parseFloat(salinitateString);

            System.out.println("Dati data: ");
            String data = in.nextLine();



            System.out.println("Dati clorG: ");
            String str = in.nextLine();
            float clorG = Float.parseFloat(str);
            System.out.println("Dati clorT: ");
            String str1 = in.nextLine();
            float clorT = Float.parseFloat(str1);
            System.out.println("Dati clorL: ");
            String str2 = in.nextLine();
            float clorL = Float.parseFloat(str2);
            System.out.println("Dati phG: ");
            String str3 = in.nextLine();
            float phG = Float.parseFloat(str3);
            System.out.println("Dati phL: ");
            String str4 = in.nextLine();
            float phL = Float.parseFloat(str4);
            System.out.println("Dati antialgic: ");
            String str5 = in.nextLine();
            float antialgic = Float.parseFloat(str5);
            System.out.println("Dati anticalcar: ");
            String str6 = in.nextLine();
            float anticalcar = Float.parseFloat(str6);
            System.out.println("Dati floculant: ");
            String str7= in.nextLine();
            float floculant = Float.parseFloat(str7);
            System.out.println("Dati sare: ");
            String str8 = in.nextLine();
            float sare = Float.parseFloat(str8);
            System.out.println("Dati bicarbonat: ");
            String str9 = in.nextLine();
            float bicarbonat = Float.parseFloat(str9);


            interventieService.addInterventie(id, idClient, dimensiune, mc, clor, duritate, ph, alcalinit, temp, salinitate, data, clorG, clorT, clorL, phG, phL, antialgic,
                                                anticalcar, floculant, sare, bicarbonat);
        } catch (InvalidId ex) {
            System.out.println(ex);
        }
    }

    private void uiAddUser(){
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Dati id: ");
                String idString = in.nextLine();
                int id = Integer.parseInt(idString);
                System.out.println("Dati prenume: ");
                String firstName = in.nextLine();
                System.out.println("Dati nume: ");
                String lastName = in.nextLine();
                System.out.println("Dati adresa: ");
                String address = in.nextLine();
                System.out.println("Dati numarul de telefon: ");
                String phoneNumber = in.nextLine();

                userValidator.validate(new Client(id, firstName, lastName, address, phoneNumber));

                clientService.addClient(id, firstName, lastName, address, phoneNumber);
            } catch (InvalidId ex) {
                System.out.println(ex);
            } catch (InvalidName e) {
                throw new RuntimeException(e);
            }
        }
    private void uiUpdateUser(){
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id-ul userului: ");
            String idUserString = in.nextLine();
            int idUser = Integer.parseInt(idUserString);

            System.out.println("Dati noul id: ");
            String idString = in.nextLine();
            int newId = Integer.parseInt(idString);
            System.out.println("Dati prenume: ");
            String firstName = in.nextLine();
            System.out.println("Dati nume: ");
            String lastName = in.nextLine();
            System.out.println("Dati adresa: ");
            String address = in.nextLine();
            System.out.println("Dati numarul de telefon: ");
            String phoneNumber = in.nextLine();

            clientService.updateClient(idUser, newId, firstName, lastName, address, phoneNumber);


        } catch (InvalidId invalidId) {
            System.out.println(invalidId.getMessage());
        }
    }
    private void uiDeleteUser(){
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Dati id-ul userului: ");
            String idUserString = in.nextLine();
            int idUser = Integer.parseInt(idUserString);

            clientService.deleteClient(idUser);


        } catch (InvalidId invalidId) {
            System.out.println(invalidId.getMessage());
        }
    }
    private void uiGetUsers() throws IOException {
        System.out.println("The users are: ");
        List<Client> clients = clientService.showClients();
        for(Client client : clients) System.out.println(client.toString());
    }

}