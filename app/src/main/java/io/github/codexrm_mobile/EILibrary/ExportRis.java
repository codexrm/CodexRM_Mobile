package io.github.codexrm_mobile.EILibrary;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import io.github.codexrm_mobile.model.*;

public class ExportRis implements Export {

    private static final String TI = "TI  - ";
    private static final String VL = "VL  - ";
    private static final String AD = "CY  - ";
    private static final String PB = "PB  - ";
    private static final String PY = "PY  - ";

    public ExportRis() {
        // Do nothing
    }

    @Override
    public void writeValue(ArrayList<Reference> referenceList, String path) throws IOException {

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), path);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

        for (final Reference entry : referenceList) {
            identifyType(entry, writer);
        }
        writer.flush();
        writer.close();
    }

    private void identifyType(final Reference reference, final OutputStreamWriter writer) throws IOException {

        if(reference instanceof ArticleReference)
            writeArticle((ArticleReference) reference, writer);
            else
                if(reference instanceof  BookSectionReference)
                    writeBookSection((BookSectionReference) reference, writer);
                else
                    if(reference instanceof  BookReference)
                    writeBook((BookReference) reference, writer);
                    else
                        if(reference instanceof ThesisReference)
                            writeThesis((ThesisReference) reference, writer);
                        else
                            if(reference instanceof ConferenceProceedingReference)
                                writeConferenceProceeding((ConferenceProceedingReference) reference, writer);
                            else
                                if(reference instanceof ConferencePaperReference)
                                    writeConferencePaper((ConferencePaperReference) reference, writer);
                                else
                                    if(reference instanceof WebPageReference)
                                    writeWebPage((WebPageReference) reference, writer);
    }

    private void commonField(final Reference reference, final OutputStreamWriter writer) throws IOException {

        if (reference.getNote() != null) {
            writer.write("\n");
            writer.write("N1  - " + reference.getNote());
            writer.write("\n");
        }
    }

    private void closeReference(final OutputStreamWriter writer) throws IOException {

        writer.write("\n");
        writer.write("ER  - ");
        writer.write("\n");
    }

    private void writeAuthorsOrEditors(final String person, final OutputStreamWriter writer, final String field) throws IOException {

        String[] authors = person.split(";");
        for (int i =0; i < authors.length; i++){
            if (authors[i] != null) {
                switch (field) {
                    case "AU":
                        writer.write("AU  - " + person);
                        writer.write("\n");
                        break;
                    case "A2":
                        writer.write("A2  - " + person);
                        writer.write("\n");
                        break;
                }
            }
        }
    }

    private void writeArticle(final ArticleReference reference, final OutputStreamWriter writer) throws IOException {

        writer.write("TY  - JOUR");
        commonField(reference, writer);
        writeAuthorsOrEditors(reference.getAuthor(), writer, "AU");

        if (reference.getTitle() != null) {
            writer.write(TI + reference.getTitle());
            writer.write("\n");
        }
        if (reference.getJournal() != null) {
            writer.write("T2  - " + reference.getJournal());
            writer.write("\n");
        }
        if (reference.getYear() != null) {
            writer.write(PY + reference.getYear());
            writer.write("\n");
        }
        if (reference.getVolume() != null) {
            writer.write(VL + reference.getVolume());
            writer.write("\n");
        }
        if (reference.getNumber() != null) {
            writer.write("C7  - " + reference.getNumber());
            writer.write("\n");
        }
        if (reference.getIssn() != null) {
            writer.write("SN  - " + reference.getIssn());
            writer.write("\n");
        }
        if (reference.getPages() != null) {
            writer.write("SP  - " + reference.getPages());
        }
        closeReference(writer);
    }

    private void writeBook(final BookReference reference, final OutputStreamWriter writer) throws IOException {

        writer.write("TY  - BOOK");
        commonField(reference, writer);
        writeAuthorsOrEditors(reference.getAuthor(), writer, "AU");
        writeAuthorsOrEditors(reference.getEditor(), writer, "A3");

        if (reference.getTitle() != null) {
            writer.write(TI + reference.getTitle());
            writer.write("\n");
        }
        if (reference.getPublisher() != null) {
            writer.write(PB + reference.getPublisher());
            writer.write("\n");
        }
        if (reference.getYear() != null) {
            writer.write(PY + reference.getYear());
            writer.write("\n");
        }
        if (reference.getVolume() != null) {
            writer.write(VL + reference.getVolume());
            writer.write("\n");
        }
        if (reference.getNumber() != null) {
            writer.write( "NV  - " + reference.getNumber());
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(AD + reference.getAddress());
            writer.write("\n");
        }
        if (reference.getEdition() != null) {
            writer.write("ET  - " + reference.getEdition());
            writer.write("\n");
        }
        if (reference.getIsbn() != null) {
            writer.write("SN  - " + reference.getIsbn());
            writer.write("\n");
        }
        if (reference.getSeries() != null) {
            writer.write("T2  - " + reference.getSeries());
        }
        closeReference(writer);
    }

    private void writeBookSection(final BookSectionReference reference, final OutputStreamWriter writer) throws IOException {

        writer.write("TY  - CHAP");
        commonField(reference, writer);
        writeAuthorsOrEditors(reference.getAuthor(), writer, "AU");
        writeAuthorsOrEditors(reference.getEditor(), writer, "A2");

        if (reference.getTitle() != null) {
            writer.write(TI + reference.getTitle());
            writer.write("\n");
        }
        if (reference.getPublisher() != null) {
            writer.write(PB + reference.getPublisher());
            writer.write("\n");
        }
        if (reference.getYear() != null) {
            writer.write(PY + reference.getYear());
            writer.write("\n");
        }
        if (reference.getNumber() != null) {
            writer.write("IS  - " + reference.getNumber());
            writer.write("\n");
        }
        if (reference.getVolume() != null) {
            writer.write(VL + reference.getVolume());
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(AD + reference.getAddress());
            writer.write("\n");
        }
        if (reference.getEdition() != null) {
            writer.write("ET  - " + reference.getEdition());
            writer.write("\n");
        }
        if (reference.getSeries() != null) {
            writer.write("T3  - " + reference.getSeries());
            writer.write("\n");
        }
        if (reference.getChapter() != null) {
            writer.write("SE  - " + reference.getChapter());
            writer.write("\n");
        }
        if (reference.getIsbn() != null) {
            writer.write("SN  - " + reference.getIsbn());
            writer.write("\n");
        }
        if (reference.getPages() != null) {
            writer.write("SP  - " + reference.getPages());
        }
        closeReference(writer);
    }

    private void writeThesis(final ThesisReference reference, final OutputStreamWriter writer) throws IOException {

        writer.write("TY  - THES");
        commonField(reference, writer);
        writeAuthorsOrEditors(reference.getAuthor(), writer, "AU");

        if (reference.getTitle() != null) {
            writer.write(TI + reference.getTitle());
            writer.write("\n");
        }
        if (reference.getSchool() != null) {
            writer.write(PB + reference.getSchool());
            writer.write("\n");
        }
        if (reference.getYear() != null) {
            writer.write(PY + reference.getYear());
            writer.write("\n");
        }
        if (reference.getType() != null) {
            writer.write("M3  - " + reference.getType());
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(AD + reference.getAddress());
        }
        closeReference(writer);
    }

    private void writeConferenceProceeding(final ConferenceProceedingReference reference, final OutputStreamWriter writer) throws IOException {

        writer.write("TY  - CONF");
        commonField(reference, writer);
        writeAuthorsOrEditors(reference.getEditor(), writer, "A2");

        if (reference.getTitle() != null) {
            writer.write(TI + reference.getTitle());
            writer.write("\n");
        }
        if (reference.getYear() != null) {
            writer.write("C2  - "  + reference.getYear());
            writer.write("\n");
        }
        if (reference.getVolume() != null) {
            writer.write(VL + reference.getVolume());
            writer.write("\n");
        }
        if (reference.getPublisher() != null) {
            writer.write(PB + reference.getPublisher());
            writer.write("\n");
        }
        if (reference.getNumber() != null) {
            writer.write("NV  - " + reference.getNumber());
            writer.write("\n");
        }
        if (reference.getSeries() != null) {
            writer.write("T3  - " + reference.getSeries());
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(AD + reference.getAddress());
        }
        closeReference(writer);
    }

    private void writeConferencePaper(final ConferencePaperReference reference, final OutputStreamWriter writer) throws IOException {

        writer.write("TY  - CPAPER");
        commonField(reference, writer);
        writeAuthorsOrEditors(reference.getAuthor(), writer, "AU");
        writeAuthorsOrEditors(reference.getEditor(), writer, "A2");

        if (reference.getTitle() != null) {
            writer.write(TI + reference.getTitle());
            writer.write("\n");
        }
        if (reference.getYear() != null) {
            writer.write(PY + reference.getYear());
            writer.write("\n");
        }
        if (reference.getVolume() != null) {
            writer.write(VL + reference.getVolume());
            writer.write("\n");
        }
        if (reference.getPublisher() != null) {
            writer.write(PB + reference.getPublisher());
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(AD + reference.getAddress());
            writer.write("\n");
        }
        if (reference.getPages() != null) {
            writer.write("SP  - " + reference.getPages());
        }
        closeReference(writer);
    }

    private void writeWebPage(final WebPageReference reference, final OutputStreamWriter writer) throws IOException {

        writer.write("TY  - ELEC");
        commonField(reference, writer);
        writeAuthorsOrEditors(reference.getAuthor(), writer, "AU");

        if (reference.getTitle() != null) {
            writer.write(TI + reference.getTitle());
            writer.write("\n");
        }
        if (reference.getYear() != null) {
            writer.write(PY + reference.getYear());
            writer.write("\n");
        }

        if (reference.getUrl() != null) {
            writer.write("UR  - " + reference.getUrl());
        }
        closeReference(writer);
    }
}
