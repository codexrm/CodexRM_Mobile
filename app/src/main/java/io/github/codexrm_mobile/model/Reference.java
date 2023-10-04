package io.github.codexrm_mobile.model;

import io.github.codexrm_mobile.utils.FieldValidations;

public class Reference {

    private Integer id;
    private String title;
    private String year;
    private String month;
    private String note;
    private boolean isNew;

    private final FieldValidations validations = new FieldValidations();

    public Reference() {}

    public Reference(Integer id, String title, String year, String month, String note, boolean isNew) {
        this.id = id;
        this.title = title;
        this.month = month;
        this.note = note;
        this.isNew = isNew;

        if(validations.validateYear(year))
            this.year = year;
    }

    public Reference(String title, String year, String month, String note, boolean isNew) {
        this.title = title;
        this.month = month;
        this.note = note;
        this.isNew = isNew;

        if(validations.validateYear(year))
            this.year = year;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) {
        if(validations.validateYear(year))
            this.year = year;
    }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public boolean isNew() { return isNew; }

    public void setNew(boolean aNew) { isNew = aNew; }
}