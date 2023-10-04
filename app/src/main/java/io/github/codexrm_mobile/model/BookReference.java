package io.github.codexrm_mobile.model;

import io.github.codexrm_mobile.utils.FieldValidations;

public class BookReference extends Reference{

    private String author;
    private String editor;
    private String publisher;
    private String volume;
    private String number;
    private String series;
    private String address;
    private String edition;
    private String isbn;

    private final FieldValidations validations = new FieldValidations();

    public BookReference() {}

    public BookReference(String title, String year, String month, String note, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn, boolean isNew) {
        super(title, year, month, note, isNew);
        this.publisher = publisher;

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;

        if(validations.validateChapterOrVolume(volume))
        this.volume = volume;

        if(validations.validateNumber(number))
        this.number = number;

        if(validations.validateSeries(series))
        this.series = series;

        if(validations.validateAddress(address))
        this.address = address;

        if(validations.validateEdition(edition))
        this.edition = edition;

        if(validations.validateIsbn(isbn))
        this.isbn = isbn;
    }

    public BookReference(Integer id, String title, String year, String month, String note, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn, boolean isNew) {
        super(id, title, year, month, note, isNew);
        this.publisher = publisher;

        if(validations.validateAuthorOrEditor(author))
            this.author = author;

        if(validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if(validations.validateChapterOrVolume(volume))
            this.volume = volume;

        if(validations.validateNumber(number))
            this.number = number;

        if(validations.validateSeries(series))
            this.series = series;

        if(validations.validateAddress(address))
            this.address = address;

        if(validations.validateEdition(edition))
            this.edition = edition;

        if(validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    public String getEdition() {return edition;}

    public void setEdition(String edition) {
        if(validations.validateEdition(edition))
        this.edition = edition;
    }

    public String getAddress() {return address;}

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getSeries() {return series;}

    public void setSeries(String series) {
        if(validations.validateSeries(series))
        this.series = series;
    }

    public String getVolume() {return volume;}

    public void setVolume(String volume) {
        if(validations.validateChapterOrVolume(volume))
        this.volume = volume;
    }

    public String getPublisher() {return publisher;}

    public void setPublisher(String publisher) {this.publisher = publisher;}

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getEditor() { return editor; }

    public void setEditor(String editor) {
        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) {
        if(validations.validateNumber(number))
        this.number = number;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        if(validations.validateIsbn(isbn))
        this.isbn = isbn;
    }
}