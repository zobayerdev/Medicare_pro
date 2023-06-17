package com.trodev.medicarepro.models;

public class MedicineData {

  //  public String name, indica, dosage, inter, warnings, condi, effect, key; //image ,
  //  public  String brand_name , type, strength, dosage_form, generic , manufacturer, package_container, indication, dosage, precautions, pregnancy_and_lactation, description, side_effects, storage, conditions ;

    public  String brand, type, strength, form, generic, manufacturer, price, indication, dosage, precautions, pregnancy, side_effects, storage;
    private boolean expandable;

    public MedicineData() {
    }

    public MedicineData(String brand, String type, String strength, String form, String generic, String manufacturer, String price, String indication, String dosage, String precautions, String pregnancy, String side_effects, String storage) {
        this.brand = brand;
        this.type = type;
        this.strength = strength;
        this.form = form;
        this.generic = generic;
        this.manufacturer = manufacturer;
        this.price = price;
        this.indication = indication;
        this.dosage = dosage;
        this.precautions = precautions;
        this.pregnancy = pregnancy;
        this.side_effects = side_effects;
        this.storage = storage;
        this.expandable = false;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    /*  public MedicineData(String name, String indica, String dosage, String inter, String warnings, String condi, String effect, String key) {
        this.name = name;
        this.indica = indica;
        this.dosage = dosage;
        this.inter = inter;
        this.warnings = warnings;
        this.condi = condi;
        this.effect = effect;
        this.key = key;
        this.expandable = false;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
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


//    public String toString(){
//        return "MedicineData{ " +
//                "indica= ' " + indica +
//                "dosage= ' " + dosage +
//                "inter= ' " + inter +
//                "warnings= ' " + warnings +
//                "condi= ' " + condi +
//                "effect= ' " + effect
//                + '}';
//    }*/

}
