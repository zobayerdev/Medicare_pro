package com.trodev.medicarepro.models;

public class MedicineData {

    public String name , indica, dosage, inter, warnings, condi,  effect,  key; //image ,

    public MedicineData() {
    }

    public MedicineData(String name, String indica, String dosage, String inter, String warnings, String condi, String effect, String key) {
        this.name = name;
        this.indica = indica;
        this.dosage = dosage;
        this.inter = inter;
        this.warnings = warnings;
        this.condi = condi;
        this.effect = effect;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndica() {
        return indica;
    }

    public void setIndica(String indica) {
        this.indica = indica;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getCondi() {
        return condi;
    }

    public void setCondi(String condi) {
        this.condi = condi;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
