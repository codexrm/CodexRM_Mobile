package io.github.codexrm_mobile.model;

import io.github.codexrm_mobile.utils.FieldValidations;

public class WebPageReference extends Reference {

    private String author;
    private String url;

    private final FieldValidations validations = new FieldValidations();

    public WebPageReference() {}

    public WebPageReference(String title, String year, String month, String note, String author, String url, boolean isNew) {
        super(title, year, month, note, isNew);

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateUrl(url))
        this.url = url;
    }

    public WebPageReference(Integer id, String title, String year, String month, String note, String author, String url, boolean isNew) {
        super(id, title, year, month, note, isNew);

        if(validations.validateAuthorOrEditor(author))
            this.author = author;

        if(validations.validateUrl(url))
            this.url = url;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) {
        if(validations.validateUrl(url))
        this.url = url;
    }
}
