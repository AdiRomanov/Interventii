//package org.example.Service;
//
//
//import org.example.Domain.ChimicaleFolosite;
//import org.example.Domain.Entity;
//import org.example.Domain.ChimicaleFolosite;
//import org.example.Exceptions.InvalidId;
//import org.example.Repository.GoogleSheetsChimicaleFolositeRepository;
//import org.example.Repository.GoogleSheetsClientRepository;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ChimicaleFolositeService {
//    GoogleSheetsChimicaleFolositeRepository repo;
//
//
//    public ChimicaleFolositeService(GoogleSheetsChimicaleFolositeRepository repo) {
//        this.repo = repo;
//    }
//
//
//    public void addChimicaleFolosite(Integer id, Integer idInterventie, Float clGranule, Float clTablete, Float clLichid,
//                                     Float phGranule, Float phLichid, Float antialgic, Float anticalcar, Float floculant,
//                                     Float sare, Float bicarbonat) throws InvalidId {
//        Entity check = repo.getEntity(id);
//        if(check != null)
//            throw new InvalidId("Id exists already!");
//        ChimicaleFolosite chimicale = new ChimicaleFolosite(id, idInterventie, clGranule, clTablete, clLichid, phGranule,
//                phLichid, antialgic, anticalcar, floculant, sare, bicarbonat);
//        repo.add(chimicale);
//
//    }
//
//
//    public void deleteChimicaleFolosite(Integer id) throws InvalidId {
//        Entity check =  repo.getEntity(id);
//        if(check == null)
//            throw new InvalidId("Given id does not exist!");
//        repo.delete(id);
//
//    }
//
//    public void updateChimicaleFolosite(Integer oldId, Integer id, Integer idInterventie, Float clGranule, Float clTablete, Float clLichid,
//                             Float phGranule, Float phLichid, Float antialgic, Float anticalcar, Float floculant,
//                             Float sare, Float bicarbonat) throws InvalidId {
//        Entity check =  repo.getEntity(oldId);
//        if(check == null)
//            throw new InvalidId("Given id does not exist!");
//        ChimicaleFolosite chimicale = new ChimicaleFolosite(id, idInterventie, clGranule, clTablete, clLichid, phGranule,
//                phLichid, antialgic, anticalcar, floculant, sare, bicarbonat);
//        repo.update(oldId, chimicale);
//
//    }
//
//    private void updateChimicaleWithObject(int id, ChimicaleFolosite chimicale) throws InvalidId {
//        Entity check =  repo.getEntity(id);
//        if(check == null)
//            throw new InvalidId("Given id does not exist!");
//
//        repo.update(id, chimicale);
//    }
//
//
//    public List<ChimicaleFolosite> showChimicaleFolosite() throws IOException {
//        List<ChimicaleFolosite> chimicale = new ArrayList<>();
//        for(int i = 0; i < repo.getEntities().size(); i++)
//            chimicale.add(repo.getEntities().get(i));
//
//        return chimicale;
//    }
//
//    public ChimicaleFolosite getChimicaleFolosite(int id) {
//        return repo.getEntity(id);
//    }
//
//    public void serviceSetClorGranule(int id, float clorGranule) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setClorGranule(clorGranule);
//        this.updateChimicaleWithObject(id, chimicale);
//
//    }
//
//    public void serviceSetClorTablete(int id, float clorTablete) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setClorTablete(clorTablete);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//
//    public void serviceSetClorLichid(int id, float clorLichid) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setClorLichid(clorLichid);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//
//    public void serviceSetPhGranule(int id, float phGranule) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setPhGranule(phGranule);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//
//    public void serviceSetPhLichid(int id, float phLichid) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setPhLichid(phLichid);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//
//    public void serviceSetAntialgic(int id, float antialgic) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setAntialgic(antialgic);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//
//    public void serviceSetAnticalcar(int id, float anticalcar) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setAnticalcar(anticalcar);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//
//    public void serviceSetFloculant(int id, float floculant) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setFloculant(floculant);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//
//    public void serviceSetSare(int id, float sare) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setSare(sare);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//
//    public void serviceSetBicarbonat(int id, float bicarbonat) throws InvalidId {
//        ChimicaleFolosite chimicale = repo.getEntity(id);
//        chimicale.setBicarbonat(bicarbonat);
//        this.updateChimicaleWithObject(id, chimicale);
//    }
//}
