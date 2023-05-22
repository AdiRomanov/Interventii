package org.example.Domain;



public class Interventie extends Entity{
    private int idClient;
    private String data;
    private String dimensiune;
    private float mc;
    private float clor, duritate, pH, alcalinit, temp, salinitate;




    public Interventie(int id, int idClient, String data, String dimensiune, float mc, float clor, float duritate, float pH, float alcalinit, float temp, float salinitate) {
        super(id);
        this.idClient = idClient;
        this.data = data;
        this.dimensiune = dimensiune;
        this.mc = mc;
        this.clor = clor;
        this.duritate = duritate;
        this.pH = pH;
        this.alcalinit = alcalinit;
        this.temp = temp;
        this.salinitate = salinitate;
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
                super.toString() + ", " + '\''+
                "idClient=" + idClient +
                ", dimensiune=" + dimensiune +
                ", mc=" + mc +
                ", clor=" + clor +
                ", duritate=" + duritate +
                ", pH=" + pH +
                ", alcalinit=" + alcalinit +
                ", temp=" + temp +
                ", salinitate=" + salinitate +
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

    public float getClor() {
        return clor;
    }

    public void setClor(float clor) {
        this.clor = clor;
    }

    public float getDuritate() {
        return duritate;
    }

    public void setDuritate(float duritate) {
        this.duritate = duritate;
    }

    public float getpH() {
        return pH;
    }

    public void setpH(float pH) {
        this.pH = pH;
    }

    public float getAlcalinit() {
        return alcalinit;
    }

    public void setAlcalinit(float alcalinit) {
        this.alcalinit = alcalinit;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }


    public float getSalinitate() {
        return salinitate;
    }

    public void setSalinitate(float salinitate) {
        this.salinitate = salinitate;
    }
}
