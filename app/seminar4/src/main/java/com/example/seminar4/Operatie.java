package com.example.seminar4;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Operatie implements Parcelable {
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

    protected Operatie(Parcel in) {
        tip = in.readString();
        nOpeartie = in.readString();
        nOperator = in.readString();
        if (in.readByte() == 0) {
            durataOre = null;
        } else {
            durataOre = in.readFloat();
        }
        byte tmpCompletata = in.readByte();
        completata = tmpCompletata == 0 ? null : tmpCompletata == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tip);
        dest.writeString(nOpeartie);
        dest.writeString(nOperator);
        if (durataOre == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(durataOre);
        }
        dest.writeByte((byte) (completata == null ? 0 : completata ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Operatie> CREATOR = new Creator<Operatie>() {
        @Override
        public Operatie createFromParcel(Parcel in) {
            return new Operatie(in);
        }

        @Override
        public Operatie[] newArray(int size) {
            return new Operatie[size];
        }
    };

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
