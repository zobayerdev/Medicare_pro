package com.trodev.medicarepro.models;

public class MedicineData {

    private String name, details, indication, dosage, interaction, effect, warnings, conditions, key ;

    public MedicineData() {
    }

    public MedicineData(String name, String details, String indication, String dosage, String interaction, String effect, String warnings, String conditions, String key) {
        this.name = name;
        this.details = details;
        this.indication = indication;
        this.dosage = dosage;
        this.interaction = interaction;
        this.effect = effect;
        this.warnings = warnings;
        this.conditions = conditions;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
