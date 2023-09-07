package io.github.codexrm_mobile.model;

import io.github.codexrm_mobile.utils.FieldValidations;

public class BookLetReference extends Reference{

    private String author;
    private String howpublished;
    private String address;

    private final FieldValidations validations = new FieldValidations();

    public BookLetReference() {}

    public BookLetReference(String title, String year, String month, String note, String author, String howpublished, String address) {
        super(title, year, month, note);
        this.howpublished = howpublished;

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateAddress(address))
        this.address = address;
    }

    public BookLetReference(Integer id, String title, String year, String month, String note, String author, String howpublished, String address) {
        super(id, title, year, month, note);
        this.howpublished = howpublished;

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getAddress() {return address;}

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getHowpublished() {return howpublished;}

    public void setHowpublished(String howpublished) {this.howpublished = howpublished;}

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }
}