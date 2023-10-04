package io.github.codexrm_mobile.EILibrary;

import android.os.Environment;

import io.github.codexrm_mobile.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ExportBibTeX implements Export {

    private final String vl = "  volume = {";
    private final String ad = "  address = {";

    @Override
    public void writeValue(ArrayList<Reference> referenceList, String path) throws IOException {

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), path);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);


        for (Reference reference : referenceList) {
            identifyType(reference, writer);
        }
        writer.flush();
        writer.close();
    }

    private void identifyType(Reference reference, final OutputStreamWriter writer) throws IOException {

        if (reference instanceof ArticleReference)
            writeArticleReference((ArticleReference) reference, writer);
        else
            if (reference instanceof BookSectionReference)
                writeBookSectionReference((BookSectionReference) reference, writer);
            else
                if (reference instanceof BookReference)
                    writeBookReference((BookReference) reference, writer);
                else
                    if (reference instanceof ThesisReference)
                        writeThesisReference((ThesisReference) reference, writer);
                    else
                        if (reference instanceof BookLetReference)
                            writeBookLetReference((BookLetReference) reference, writer);
                        else
                            if (reference instanceof ConferenceProceedingReference)
                                writeConferenceProceedingsReference((ConferenceProceedingReference) reference, writer);
                             else
                                if (reference instanceof ConferencePaperReference)
                                    writeConferencePaperReference((ConferencePaperReference) reference, writer);
                                 else
                                    if (reference instanceof WebPageReference)
                                        writeWebPageReference((WebPageReference) reference, writer);
    }

    private void writeAuthors(String author, OutputStreamWriter writer)
            throws IOException {

        if (author != null) {
            writer.write("  author = {");
            String[] authors = author.split(";");
            for (int i = 0; i < authors.length; i++) {

                if (i == 0) {
                    writer.write(authors[i]);
                } else {
                    writer.write(" and ");
                    writer.write(authors[i]);
                }
            }
            writer.write("},");
            writer.write("\n");
        }
    }

    private void writeEditors(String editor, OutputStreamWriter writer)
            throws IOException {

        if (editor != null) {
            writer.write("  editor = {");
            String[] authors = editor.split(";");
            for (int i = 0; i < authors.length; i++) {

                if (i == 0) {
                    writer.write(authors[i]);
                } else {
                    writer.write(" and ");
                    writer.write(authors[i]);
                }
            }
            writer.write("},");
            writer.write("\n");
        }
    }

    private void commonField(Reference reference, OutputStreamWriter writer) throws IOException {

        writer.write("\n");

        if (reference.getTitle() != null) {
            writer.write("  title = {" + reference.getTitle() + "},");
            writer.write("\n");
        }
        if (reference.getMonth() != null) {
            writer.write("  month = " + reference.getMonth() + ",");
            writer.write("\n");
        }
        if(reference.getYear() != null){
            writer.write("  year = {" + reference.getYear() + "},");
            writer.write("\n");
        }
        if (reference.getNote() != null) {
            writer.write("  note = {" + reference.getNote() + "},");
            writer.write("\n");
        }
    }

    private void closeReference(OutputStreamWriter writer) throws IOException {

        writer.write("\n");
        writer.write("}");
        writer.write("\n");
        writer.write("");
        writer.write("\n");
    }

    private void writeArticleReference(ArticleReference reference, OutputStreamWriter writer)
            throws IOException {

        writer.write("@article{" + reference.getId() + ",");
        commonField(reference, writer);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),writer);
        }
        if (reference.getJournal() != null) {
            writer.write("  journal = {" + reference.getJournal() + "},");
            writer.write("\n");
        }
        if (reference.getVolume() != null) {
            writer.write(vl + reference.getVolume() + "},");
            writer.write("\n");
        }
        if (reference.getNumber() != null) {
            writer.write("  number = {" + reference.getNumber() + "},");
            writer.write("\n");
        }
        if (reference.getPages() != null) {
            writer.write("  pages = {" + reference.getPages() + "},");
            writer.write("\n");
        }
        if (reference.getIssn() != null) {
            writer.write("  issn = {" + reference.getIssn() + "}");
        }

        closeReference(writer);
    }

    private void writeBooks(BookReference reference, OutputStreamWriter writer) throws IOException {

        commonField(reference, writer);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),writer);
        }
        if (reference.getEditor() != null) {
            writeEditors(reference.getEditor(),writer);
        }
        if (reference.getPublisher() != null) {
            writer.write("  publisher = {" + reference.getPublisher() + "},");
            writer.write("\n");
        }
        if (reference.getVolume() != null) {
            writer.write(vl + reference.getVolume() + "},");
            writer.write("\n");
        }
        if (reference.getNumber() != null) {
            writer.write("  number = {" + reference.getNumber() + "},");
            writer.write("\n");
        }
        if (reference.getSeries() != null) {
            writer.write("  series = {" + reference.getSeries() + "},");
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(ad + reference.getAddress() + "},");
            writer.write("\n");
        }
        if (reference.getEdition() != null) {
            writer.write("  edition = {" + reference.getEdition() + "},");
            writer.write("\n");
        }
        if (reference.getIsbn() != null) {
            writer.write("  isbn = {" + reference.getIsbn() + "}");
        }
    }

    private void writeBookReference(BookReference reference, OutputStreamWriter writer) throws IOException {

        writer.write("@book{" + reference.getId() + ",");
        writeBooks(reference, writer);
        closeReference(writer);
    }

    private void writeBookSectionReference(BookSectionReference reference, OutputStreamWriter writer) throws IOException {

        writer.write("@inbook{" + reference.getId() + ",");

        if (reference.getChapter() != null) {
            writer.write("\n");
            writer.write("  chapter = {" + reference.getChapter() + "},");
        }
        if (reference.getPages() != null) {
            writer.write("\n");
            writer.write("  pages = {" + reference.getPages() + "},");
        }
        if (reference.getType() != null) {
            writer.write("\n");
            writer.write("  type = {" + reference.getType().toString() + "},");
        }

        writeBooks(reference, writer);
        closeReference(writer);
    }

    private void writeBookLetReference(BookLetReference reference, OutputStreamWriter writer) throws IOException {

        writer.write("@booklet{" + reference.getId() + ",");
        commonField(reference, writer);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),writer);
        }
        if (reference.getHowpublished() != null) {
            writer.write("  howpublished = {" + reference.getHowpublished() + "},");
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(ad + reference.getAddress() + "}");
        }
        closeReference(writer);
    }

    private void writeThesisReference(ThesisReference reference, OutputStreamWriter writer) throws IOException {

        if (reference.getType().equals("MASTERS")) {
            writer.write("@mastersthesis{" + reference.getId() + ",");
        } else {
            writer.write("@phdthesis{" + reference.getId() + ",");
        }
        commonField(reference, writer);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),writer);
        }
        if (reference.getSchool() != null) {
            writer.write("  school = {" + reference.getSchool() + "},");
            writer.write("\n");
        }
        if (reference.getType() != null) {
            writer.write("  type = {" + reference.getType() + "},");
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(ad + reference.getAddress() + "}");
        }
        closeReference(writer);
    }

    private void writeConferenceProceedingsReference(ConferenceProceedingReference reference, OutputStreamWriter writer) throws IOException {

        writer.write("@proceedings{" + reference.getId() + ",");
        commonField(reference, writer);

        if (reference.getEditor() != null) {
            writeEditors(reference.getEditor(),writer);
        }
        if (reference.getVolume() != null) {
            writer.write(vl + reference.getVolume() + "},");
            writer.write("\n");
        }
        if (reference.getNumber() != null) {
            writer.write("  number = {" + reference.getNumber() + "},");
            writer.write("\n");
        }
        if (reference.getSeries() != null) {
            writer.write("  series = {" + reference.getSeries() + "},");
            writer.write("\n");
        }
        if (reference.getPublisher() != null) {
            writer.write("  publisher = {" + reference.getPublisher() + "},");
            writer.write("\n");
        }
        if (reference.getOrganization() != null) {
            writer.write("  organization = {" + reference.getOrganization() + "},");
            writer.write("\n");
        }
        if (reference.getIsbn() != null) {
            writer.write("  isbn = {" + reference.getIsbn() + "},");
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(ad + reference.getAddress() + "}");
        }
        closeReference(writer);
    }

    private void writeConferencePaperReference(ConferencePaperReference reference, OutputStreamWriter writer) throws IOException {

        writer.write("@InProceedings{" + reference.getId() + ",");
        commonField(reference, writer);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),writer);
        }
        if (reference.getEditor() != null) {
            writeEditors(reference.getEditor(),writer);
        }
        if (reference.getBookTitle() != null) {
            writer.write("  booktitle = {" + reference.getBookTitle() + "},");
            writer.write("\n");
        }
        if (reference.getVolume() != null) {
            writer.write(vl + reference.getVolume() + "},");
            writer.write("\n");
        }
        if (reference.getNumber() != null) {
            writer.write("  number = {" + reference.getNumber() + "},");
            writer.write("\n");
        }
        if (reference.getSeries() != null) {
            writer.write("  series = {" + reference.getSeries() + "},");
            writer.write("\n");
        }
        if (reference.getPublisher() != null) {
            writer.write("  publisher = {" + reference.getPublisher() + "},");
            writer.write("\n");
        }
        if (reference.getOrganization() != null) {
            writer.write("  organization = {" + reference.getOrganization() + "},");
            writer.write("\n");
        }
        if (reference.getAddress() != null) {
            writer.write(ad + reference.getAddress() + "},");
            writer.write("\n");
        }
        if (reference.getPages() != null) {
            writer.write("  pages = {" + reference.getPages() + "}");
        }
        closeReference(writer);
    }

    private void writeWebPageReference(WebPageReference reference, OutputStreamWriter writer) throws IOException {

        writer.write("@misc{" + reference.getId() + ",");
        commonField(reference, writer);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),writer);
        }
        if (reference.getUrl() != null) {
            writer.write("  howpublished = {" + reference.getUrl() + "}");
        }
        closeReference(writer);
    }
}
