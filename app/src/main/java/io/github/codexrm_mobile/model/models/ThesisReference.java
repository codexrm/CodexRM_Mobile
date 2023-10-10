package io.github.codexrm_mobile.model.models;

import io.github.codexrm_mobile.model.utils.FieldValidations;

public class ThesisReference extends Reference {

    private String author;
    private String school;
    private String type;
    private String address;

    private final FieldValidations validations = new FieldValidations();

    public ThesisReference() {
    }

    public ThesisReference(String title, String year, String month, String note, String author, String school, String type, String address, boolean isNew) {
        super(title, year, month, note, isNew);
        this.school = school;
        this.type = type;

        if (validations.validateAuthorOrEditor(author))
            this.author = author;

        if (validations.validateAddress(address))
            this.address = address;
    }

    public ThesisReference(Integer id, String title, String year, String month, String note, String author, String school, String type, String address, boolean isNew) {
        super(id, title, year, month, note, isNew);
        this.school = school;
        this.type = type;

        if (validations.validateAuthorOrEditor(author))
            this.author = author;

        if (validations.validateAddress(address))
            this.address = address;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (validations.validateAuthorOrEditor(author))
            this.author = author;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (validations.validateAddress(address))
            this.address = address;
    }
}