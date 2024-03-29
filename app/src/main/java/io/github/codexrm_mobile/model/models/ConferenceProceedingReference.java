package io.github.codexrm_mobile.model.models;

import io.github.codexrm_mobile.model.utils.FieldValidations;

public class ConferenceProceedingReference extends Reference {

    private String editor;
    private String volume;
    private String number;
    private String series;
    private String address;
    private String publisher;
    private String organization;
    private String isbn;

    private final FieldValidations validations = new FieldValidations();

    public ConferenceProceedingReference() {
    }

    public ConferenceProceedingReference(String title, String year, String month, String note, String editor, String volume, String number, String series, String address, String publisher, String isbn, String organization,
                                         boolean isNew) {
        super(title, year, month, note, isNew);
        this.publisher = publisher;
        this.organization = organization;

        if (validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;

        if (validations.validateNumber(number))
            this.number = number;

        if (validations.validateSeries(series))
            this.series = series;

        if (validations.validateAddress(address))
            this.address = address;

        if (validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    public ConferenceProceedingReference(Integer id, String title, String year, String month, String note, String editor, String volume, String number, String series, String address, String publisher, String isbn,
                                         String organization, boolean isNew) {
        super(id, title, year, month, note, isNew);
        this.publisher = publisher;
        this.organization = organization;

        if (validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;

        if (validations.validateNumber(number))
            this.number = number;

        if (validations.validateSeries(series))
            this.series = series;

        if (validations.validateAddress(address))
            this.address = address;

        if (validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (validations.validateAddress(address))
            this.address = address;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        if (validations.validateSeries(series))
            this.series = series;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        if (validations.validateAuthorOrEditor(editor))
            this.editor = editor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (validations.validateNumber(number))
            this.number = number;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}