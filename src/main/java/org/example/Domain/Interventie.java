package org.example.Domain;



public class Interventie extends Entity{
    private int idClient;
    private String data;
    private String dimensiune;
    private float mc;
    private float clorMasurat, duritateMasurata, phMasurat, alcalinitMasurat, tempMasurata, salinitateMasurata;
    private float clorGranule, clorTablete, clorLichid, phGranule, phLichid, antialgic;
    private float anticalcar, floculant, sare, bicarbonat;




    public Interventie(int id, int idClient, String dimensiune, float mc, float clorMasurat,
                       float duritateMasurata, float phMasurat, float alcalinitMasurat, float tempMasurata,
                       float salinitateMasurata, String data, float clorGranule, float clorTablete, float clorLichid,
                       float phGranule, float phLichid, float antialgic, float anticalcar, float floculant,
                       float sare, float bicarbonat) {
        super(id);
        this.idClient = idClient;
        this.data = data;
        this.dimensiune = dimensiune;
        this.mc = mc;
        this.clorMasurat = clorMasurat;
        this.duritateMasurata = duritateMasurata;
        this.phMasurat = phMasurat;
        this.alcalinitMasurat = alcalinitMasurat;
        this.tempMasurata = tempMasurata;
        this.salinitateMasurata = salinitateMasurata;
        this.clorGranule = clorGranule;
        this.clorTablete = clorTablete;
        this.clorLichid = clorLichid;
        this.phGranule = phGranule;
        this.phLichid = phLichid;
        this.antialgic = antialgic;
        this.anticalcar = anticalcar;
        this.floculant = floculant;
        this.sare = sare;
        this.bicarbonat = bicarbonat;
    }

    public float getClorGranule() {
        return clorGranule;
    }

    public void setClorGranule(float clorGranule) {
        this.clorGranule = clorGranule;
    }

    public float getClorTablete() {
        return clorTablete;
    }

    public void setClorTablete(float clorTablete) {
        this.clorTablete = clorTablete;
    }

    public float getClorLichid() {
        return clorLichid;
    }

    public void setClorLichid(float clorLichid) {
        this.clorLichid = clorLichid;
    }

    public float getPhGranule() {
        return phGranule;
    }

    public void setPhGranule(float phGranule) {
        this.phGranule = phGranule;
    }

    public float getPhLichid() {
        return phLichid;
    }

    public void setPhLichid(float phLichid) {
        this.phLichid = phLichid;
    }

    public float getAntialgic() {
        return antialgic;
    }

    public void setAntialgic(float antialgic) {
        this.antialgic = antialgic;
    }

    public float getAnticalcar() {
        return anticalcar;
    }

    public void setAnticalcar(float anticalcar) {
        this.anticalcar = anticalcar;
    }

    public float getFloculant() {
        return floculant;
    }

    public void setFloculant(float floculant) {
        this.floculant = floculant;
    }

    public float getSare() {
        return sare;
    }

    public void setSare(float sare) {
        this.sare = sare;
    }

    public float getBicarbonat() {
        return bicarbonat;
    }

    public void setBicarbonat(float bicarbonat) {
        this.bicarbonat = bicarbonat;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Interventie{" +
                "idClient=" + idClient +
                ", data='" + data + '\'' +
                ", dimensiune='" + dimensiune + '\'' +
                ", mc=" + mc +
                ", clorMasurat=" + clorMasurat +
                ", duritateMasurata=" + duritateMasurata +
                ", phMasurat=" + phMasurat +
                ", alcalinitMasurat=" + alcalinitMasurat +
                ", tempMasurata=" + tempMasurata +
                ", salinitateMasurata=" + salinitateMasurata +
                ", clorGranule=" + clorGranule +
                ", clorTablete=" + clorTablete +
                ", clorLichid=" + clorLichid +
                ", phGranule=" + phGranule +
                ", phLichid=" + phLichid +
                ", antialgic=" + antialgic +
                ", anticalcar=" + anticalcar +
                ", floculant=" + floculant +
                ", sare=" + sare +
                ", bicarbonat=" + bicarbonat +
                '}';
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(String dimensiune) {
        this.dimensiune = dimensiune;
    }

    public float getMc() {
        return mc;
    }

    public void setMc(float mc) {
        this.mc = mc;
    }

    public float getClorMasurat() {
        return clorMasurat;
    }

    public void setClorMasurat(float clorMasurat) {
        this.clorMasurat = clorMasurat;
    }

    public float getDuritateMasurata() {
        return duritateMasurata;
    }

    public void setDuritateMasurata(float duritateMasurata) {
        this.duritateMasurata = duritateMasurata;
    }

    public float getPhMasurat() {
        return phMasurat;
    }

    public void setPhMasurat(float phMasurat) {
        this.phMasurat = phMasurat;
    }

    public float getAlcalinitMasurat() {
        return alcalinitMasurat;
    }

    public void setAlcalinitMasurat(float alcalinitMasurat) {
        this.alcalinitMasurat = alcalinitMasurat;
    }

    public float getTempMasurata() {
        return tempMasurata;
    }

    public void setTempMasurata(float tempMasurata) {
        this.tempMasurata = tempMasurata;
    }


    public float getSalinitateMasurata() {
        return salinitateMasurata;
    }

    public void setSalinitateMasurata(float salinitateMasurata) {
        this.salinitateMasurata = salinitateMasurata;
    }
}
