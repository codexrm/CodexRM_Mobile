package io.github.codexrm_mobile.model.dto;

public class ReferenceDTO {

    protected String referenceType;
    protected String title;
    protected String year;
    protected String month;
    protected String note;
    protected Integer id;

    public ReferenceDTO() {}

    public ReferenceDTO( String title, String year, String month, String note, Integer id) {
        this.referenceType = getClass().getSimpleName();
        this.title = title;
        this.year = year;
        this.month = month;
        this.note = note;
        this.id = id;
    }

    public ReferenceDTO( String title, String year, String month, String note) {
        this.referenceType = getClass().getName();
        this.title = title;
        this.year = year;
        this.month = month;
        this.note = note;
    }

    public String getReferenceType() { return referenceType; }

    public void setReferenceType(String referenceType) { this.referenceType = referenceType; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
}
