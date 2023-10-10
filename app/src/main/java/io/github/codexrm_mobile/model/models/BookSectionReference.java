package io.github.codexrm_mobile.model.models;

import io.github.codexrm_mobile.model.utils.FieldValidations;

public class BookSectionReference extends BookReference {

    private String chapter;
    private String pages;
    private String type;

    private final FieldValidations validations = new FieldValidations();

    public BookSectionReference() {
    }

    public BookSectionReference(String title, String year, String month, String note, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn,
                                String chapter, String pages, String type, boolean isNew) {
        super(title, year, month, note, author, editor, publisher, volume, number, series, address, edition, isbn, isNew);
        this.type = type;

        if (validations.validateChapterOrVolume(chapter))
            this.chapter = chapter;

        if (validations.validatePages(pages))
            this.pages = pages;

    }

    public BookSectionReference(Integer id, String title, String year, String month, String note, String author, String editor, String publisher, String volume, String number, String series, String address, String edition,
                                String isbn, String chapter, String pages, String type, boolean isNew) {
        super(id, title, year, month, note, author, editor, publisher, volume, number, series, address, edition, isbn, isNew);
        this.type = type;

        if (validations.validateChapterOrVolume(chapter))
            this.chapter = chapter;

        if (validations.validatePages(pages))
            this.pages = pages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        if (validations.validatePages(pages))
            this.pages = pages;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        if (validations.validateChapterOrVolume(chapter))
            this.chapter = chapter;
    }
}