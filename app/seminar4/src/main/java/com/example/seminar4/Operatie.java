package com.example.seminar4;

public class Operatie {
    String  tip;
    String nOpeartie;
    String nOperator;

    Float durataOre;

    Boolean completata;


    public Operatie(String tip, String nOpeartie, String nOperator, Float durataOre, Boolean completata) {
        this.tip = tip;
        this.nOpeartie = nOpeartie;
        this.nOperator = nOperator;
        this.durataOre = durataOre;
        this.completata = completata;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getnOpeartie() {
        return nOpeartie;
    }

    public void setnOpeartie(String nOpeartie) {
        this.nOpeartie = nOpeartie;
    }

    public String getnOperator() {
        return nOperator;
    }

    public void setnOperator(String nOperator) {
        this.nOperator = nOperator;
    }

    public Float getDurataOre() {
        return durataOre;
    }

    public void setDurataOre(Float durataOre) {
        this.durataOre = durataOre;
    }

    public Boolean getCompletata() {
        return completata;
    }

    public void setCompletata(Boolean completata) {
        this.completata = completata;
    }

    @Override
    public String toString() {
        return "Operație: " + nOpeartie + " (Operator: " + nOperator + ")\n" +
                "Tip: " + tip + " | Durată (ore): " + durataOre + "\n" +
                "Completată: " + completata;
    }
}
