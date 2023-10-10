package io.github.codexrm_mobile.model.eilibrary;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXParser;
import org.jbibtex.TokenMgrException;

import org.jbibtex.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import io.github.codexrm_mobile.model.models.*;
import io.github.codexrm_mobile.model.utils.ValidateReference;

public class ImportBibTeX implements Import {

    private final ValidateReference validation;

    public ImportBibTeX() {
        this.validation = new ValidateReference();
    }

    @Override
    public ArrayList<Reference> readFile(File file) throws IOException, TokenMgrException, ParseException {

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(input);

        BibTeXParser bibtexParser = new BibTeXParser();
        BibTeXDatabase database = bibtexParser.parseFully(reader);
        Map<Key, BibTeXEntry> entryMap = database.getEntries();
        Collection<BibTeXEntry> entries = entryMap.values();
        ArrayList<Reference> referenceList = new ArrayList<>();

        Reference reference = new Reference();
        for (BibTeXEntry entry : entries) {

            if (entry.getType().toString().equalsIgnoreCase("article")) {
                reference = createArticleReference(entry);
            } else {
                if (entry.getType().toString().equalsIgnoreCase("book")) {
                    reference = createBookReference(entry);
                } else {
                    if (entry.getType().toString().equalsIgnoreCase("inbook")) {
                        reference = createBookSectionReference(entry);
                    } else {
                        if (entry.getType().toString().equalsIgnoreCase("booklet")) {
                            reference = createBookLetReference(entry);
                        } else {
                            if (entry.getType().toString().equalsIgnoreCase("mastersthesis")
                                    || entry.getType().toString().equalsIgnoreCase("phdthesis")) {
                                reference = createThesisReference(entry);
                            } else {
                                if (entry.getType().toString().equalsIgnoreCase("proceedings")) {
                                    reference = createConferenceProceedingsReference(entry);
                                } else {
                                    if (entry.getType().toString().equalsIgnoreCase("conference") || entry.getType().toString().equalsIgnoreCase("InProceedings")) {
                                        reference = createConferencePaperReference(entry);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (reference != null) {
                if (reference.isNew())
                    referenceList.add(reference);
            }
            reference = null;
        }
        return referenceList;
    }

    private String createAuthorOrEditorField(String content) {

        String[] people = content.split(" and ", 2);
        StringBuilder person = new StringBuilder(people[0]);

        for (int i = 1; i < people.length; i++)
            person.append(";").append(people[i]);

        return person.toString();
    }

    private void commonField(BibTeXEntry entry, Reference baseR) {

        Value value = entry.getField(BibTeXEntry.KEY_TITLE);
        if (value != null)
            baseR.setTitle(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_YEAR);
        if (value != null)
            baseR.setYear(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_MONTH);
        if (value != null)
            baseR.setMonth(getMonth(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_NOTE);
        if (value != null)
            baseR.setNote(value.toUserString());

        baseR.setNew(true);
    }

    private String getMonth(String date) {

        switch (date) {

            case "01":
            case "jan":
            case "January":
                return "jan";

            case "feb":
            case "February":
            case "02":
                return "feb";

            case "mar":
            case "March":
            case "03":
                return "mar";

            case "apr":
            case "April":
            case "04":
                return "apr";

            case "may":
            case "May":
            case "05":
                return "may";

            case "jun":
            case "June":
            case "06":
                return "jun";

            case "jul":
            case "July":
            case "07":
                return "jul";

            case "aug":
            case "August":
            case "08":
                return "aug";

            case "sep":
            case "September":
            case "09":
                return "sep";

            case "oct":
            case "October":
            case "10":
                return "oct";

            case "nov":
            case "November":
            case "11":
                return "nov";

            case "dec":
            case "December":
            case "12":
                return "dec";
        }
        return null;
    }

    private Reference createArticleReference(BibTeXEntry entry) {
        ArticleReference article = new ArticleReference();
        commonField(entry, article);

        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null)
            article.setAuthor(createAuthorOrEditorField(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_JOURNAL);
        if (value != null)
            article.setJournal(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null)
            article.setVolume(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null)
            article.setNumber(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null)
            article.setPages(value.toUserString());

        return validation.validateRequiredArticle(article);
    }

    private Reference createBookReference(BibTeXEntry entry) {
        BookReference book = new BookReference();
        createBook(entry, book);
        return validation.validateRequiredBook(book);
    }

    private void createBook(BibTeXEntry entry, BookReference book) {
        commonField(entry, book);

        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null)
            book.setAuthor(createAuthorOrEditorField(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_EDITOR);
        if (value != null)
            book.setEditor(createAuthorOrEditorField(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
        if (value != null)
            book.setPublisher(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null)
            book.setVolume(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null)
            book.setNumber(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_SERIES);
        if (value != null)
            book.setSeries(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null)
            book.setAddress(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_EDITION);
        if (value != null)
            book.setEdition(value.toUserString());
    }

    private Reference createBookSectionReference(BibTeXEntry entry) {
        BookSectionReference bookSection = new BookSectionReference();
        createBook(entry, bookSection);

        Value value = entry.getField(BibTeXEntry.KEY_CHAPTER);
        if (value != null)
            bookSection.setChapter(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null)
            bookSection.setPages(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_TYPE);
        if (value != null)
            bookSection.setType(value.toUserString());

        return validation.validateRequiredBookSection(bookSection);
    }

    private Reference createBookLetReference(BibTeXEntry entry) {
        BookLetReference bookLet = new BookLetReference();
        commonField(entry, bookLet);

        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null)
            bookLet.setAuthor(createAuthorOrEditorField(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_HOWPUBLISHED);
        if (value != null)
            bookLet.setHowpublished(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null)
            bookLet.setAddress(value.toUserString());

        return validation.validateRequiredBookLet(bookLet);
    }

    private Reference createThesisReference(BibTeXEntry entry) {
        ThesisReference thesis = new ThesisReference();
        commonField(entry, thesis);

        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null)
            thesis.setAuthor(createAuthorOrEditorField(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_SCHOOL);
        if (value != null)
            thesis.setSchool(value.toUserString());

        if (entry.getType().getValue().equals("mastersthesis"))
            thesis.setType("MASTERS");
        else
            thesis.setType("PHD");

        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null)
            thesis.setAddress(value.toUserString());

        return validation.validateRequiredThesis(thesis);
    }

    private Reference createConferenceProceedingsReference(BibTeXEntry entry) {

        ConferenceProceedingReference proceedings = new ConferenceProceedingReference();
        commonField(entry, proceedings);

        Value value = entry.getField(BibTeXEntry.KEY_EDITOR);
        if (value != null)
            proceedings.setEditor(createAuthorOrEditorField(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null)
            proceedings.setVolume(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null)
            proceedings.setNumber(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_SERIES);
        if (value != null)
            proceedings.setSeries(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null)
            proceedings.setAddress(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
        if (value != null)
            proceedings.setPublisher(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_ORGANIZATION);
        if (value != null)
            proceedings.setOrganization(value.toUserString());

        return validation.validateRequiredConferenceProceedings(proceedings);
    }

    private Reference createConferencePaperReference(BibTeXEntry entry) {

        ConferencePaperReference paper = new ConferencePaperReference();
        commonField(entry, paper);

        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null)
            paper.setAuthor(createAuthorOrEditorField(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_BOOKTITLE);
        if (value != null)
            paper.setBookTitle(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_EDITOR);
        if (value != null)
            paper.setEditor(createAuthorOrEditorField(value.toUserString()));

        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null)
            paper.setVolume(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null)
            paper.setNumber(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_SERIES);
        if (value != null)
            paper.setSeries(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null)
            paper.setPages(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null)
            paper.setAddress(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_ORGANIZATION);
        if (value != null)
            paper.setOrganization(value.toUserString());

        value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
        if (value != null)
            paper.setPublisher(value.toUserString());

        return validation.validateRequiredConferencePaper(paper);
    }
}
