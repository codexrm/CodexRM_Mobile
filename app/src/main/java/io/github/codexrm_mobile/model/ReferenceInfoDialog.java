package io.github.codexrm_mobile.model;

public class ReferenceInfoDialog {

    public ReferenceInfoDialog() { }

    public CharSequence[] createReferenceInfoDialog(Reference reference) {

        if (reference instanceof ArticleReference)
            return createArticleInfoDialog((ArticleReference) reference);

        else{
            if (reference instanceof BookSectionReference)
                return createBookSectionInfoDialog((BookSectionReference) reference);

            else{
                if (reference instanceof BookLetReference)
                    return createBookLetInfoDialog((BookLetReference) reference);

                else{
                    if (reference instanceof BookReference)
                        return createBookInfoDialog((BookReference) reference);

                    else{
                        if (reference instanceof ThesisReference)
                            return createThesisInfoDialog((ThesisReference) reference);

                        else{
                            if (reference instanceof ConferencePaperReference)
                                return createConferencePaperInfoDialog((ConferencePaperReference) reference);

                            else{
                                if (reference instanceof ConferenceProceedingReference)
                                    return createConferenceProceedingInfoDialog((ConferenceProceedingReference) reference);

                                else
                                    return createWebPageInfoDialog((WebPageReference) reference);
                            }
                        }
                    }
                }
            }
        }
    }

    private CharSequence[] createArticleInfoDialog(ArticleReference reference) {
        final CharSequence[] items = new CharSequence[10];

        if(reference.getAuthor()!= null) items[0] = "Autor: " + reference.getAuthor();
        else items[0] = "Autor: ";

        if(reference.getTitle()!= null) items[1] = "Título: " + reference.getTitle();
        else items[1] = "Título: ";

        if(reference.getJournal()!= null) items[2] = "Revista: " + reference.getJournal();
        else items[2] = "Revista: ";

        if(reference.getYear()!= null) items[3] = "Año: " + reference.getYear();
        else items[3] = "Año: ";

        if(reference.getVolume()!= null) items[4] = "Volumen: " + reference.getVolume();
        else items[4] = "Volumen: ";

        if(reference.getNumber()!= null) items[5] = "Número: " + reference.getNumber();
        else items[5] = "Número: ";

        if(reference.getPages()!= null) items[6] = "Páginas: " + reference.getPages();
        else items[6] = "Páginas: ";

        if(reference.getMonth()!= null) items[7] = "Mes: " + reference.getMonth();
        else items[7] = "Mes: ";

        if(reference.getIssn()!= null) items[8] = "ISSN: " + reference.getIssn();
        else items[8] = "ISSN: ";

        if(reference.getNote()!= null) items[9] = "Nota: " + reference.getNote();
        else items[9] = "Nota: ";

        return items;
    }

    private CharSequence[] createBookInfoDialog(BookReference reference) {
        final CharSequence[] items = new CharSequence[13];

        if(reference.getAuthor()!= null) items[0] = "Autor: " + reference.getAuthor();
        else items[0] = "Autor: ";

        if(reference.getEditor()!= null) items[1] = "Editor: " + reference.getEditor();
        else items[1] = "Editor: ";

        if(reference.getTitle()!= null) items[2] = "Título: " + reference.getTitle();
        else items[2] = "Título: ";

        if(reference.getPublisher()!= null) items[3] = "Editorial: " + reference.getPublisher();
        else items[3] = "Editorial: ";

        if(reference.getYear()!= null) items[4] = "Año: " + reference.getYear();
        else items[4] = "Año: ";

        if(reference.getVolume()!= null) items[5] = "Volumen: " + reference.getVolume();
        else items[5] = "Volumen: ";

        if(reference.getNumber()!= null) items[6] = "Número: " + reference.getNumber();
        else items[6] = "Número: ";

        if(reference.getSeries()!= null) items[7] = "Serie: " + reference.getSeries();
        else items[7] = "Serie: ";

        if(reference.getAddress()!= null) items[8] = "Dirección: " + reference.getAddress();
        else items[8] = "Dirección: ";

        if(reference.getEdition()!= null) items[9] = "Edición: " + reference.getEdition();
        else items[9] = "Edición: ";

        if(reference.getMonth()!= null) items[10] = "Mes: " + reference.getMonth();
        else items[10] = "Mes: ";

        if(reference.getIsbn()!= null) items[11] = "ISBN: " + reference.getIsbn();
        else items[11] = "ISBN: ";

        if(reference.getNote()!= null) items[12] = "Nota: " + reference.getNote();
        else items[12] = "Nota: ";

        return items;
    }

    private CharSequence[] createBookSectionInfoDialog(BookSectionReference reference) {
        final CharSequence[] items = new CharSequence[16];

        if(reference.getChapter()!= null) items[0] = "Capítulo: " + reference.getChapter();
        else items[0] = "Capítulo: ";

        if(reference.getPages()!= null) items[1] = "Páginas: " + reference.getPages();
        else items[1] = "Páginas: ";

        if(reference.getAuthor()!= null) items[2] = "Autor: " + reference.getAuthor();
        else items[2] = "Autor: ";

        if(reference.getEditor()!= null) items[3] = "Editor: " + reference.getEditor();
        else items[3] = "Editor: ";

        if(reference.getTitle()!= null) items[4] = "Título: " + reference.getTitle();
        else items[4] = "Título: ";

        if(reference.getPublisher()!= null) items[5] = "Editorial: " + reference.getPublisher();
        else items[5] = "Editorial: ";

        if(reference.getYear()!= null) items[6] = "Año: " + reference.getYear();
        else items[6] = "Año: ";

        if(reference.getVolume()!= null) items[7] = "Volumen: " + reference.getVolume();
        else items[7] = "Volumen: ";

        if(reference.getNumber()!= null) items[8] = "Número: " + reference.getNumber();
        else items[8] = "Número: ";

        if(reference.getSeries()!= null) items[9] = "Serie: " + reference.getSeries();
        else items[9] = "Serie: ";

        if(reference.getType()!= null) items[10] = "Tipo: " + reference.getType();
        else items[10] = "Tipo: ";

        if(reference.getAddress()!= null) items[11] = "Dirección: " + reference.getAddress();
        else items[11] = "Dirección: ";

        if(reference.getEdition()!= null) items[12] = "Edición: " + reference.getEdition();
        else items[12] = "Edición: ";

        if(reference.getMonth()!= null) items[13] = "Mes: " + reference.getMonth();
        else items[13] = "Mes: ";

        if(reference.getIsbn()!= null) items[14] = "ISBN: " + reference.getIsbn();
        else items[14] = "ISBN: ";

        if(reference.getNote()!= null) items[15] = "Nota: " + reference.getNote();
        else items[15] = "Nota: ";

        return items;
    }

    private CharSequence[] createBookLetInfoDialog(BookLetReference reference) {
        final CharSequence[] items = new CharSequence[7];

        if(reference.getTitle()!= null) items[0] = "Título: " + reference.getTitle();
        else items[0] = "Título: ";

        if(reference.getAuthor()!= null) items[1] = "Autor: " + reference.getAuthor();
        else items[1] = "Autor: ";

        if(reference.getHowpublished()!= null) items[2] = "Publicación: " + reference.getHowpublished();
        else items[2] = "Publicación: ";

        if(reference.getAddress()!= null) items[3] = "Dirección: " + reference.getAddress();
        else items[3] = "Dirección: ";

        if(reference.getMonth()!= null) items[4] = "Mes: " + reference.getMonth();
        else items[4] = "Mes: ";

        if(reference.getYear()!= null) items[5] = "Año: " + reference.getYear();
        else items[5] = "Año: ";

        if(reference.getNote()!= null) items[6] = "Nota: " + reference.getNote();
        else items[6] = "Nota: ";

        return items;
    }


    private CharSequence[] createThesisInfoDialog(ThesisReference reference) {
        final CharSequence[] items = new CharSequence[8];

        if(reference.getAuthor()!= null) items[0] = "Autor: " + reference.getAuthor();
        else items[0] = "Autor: ";

        if(reference.getTitle()!= null) items[1] = "Título: " + reference.getTitle();
        else items[1] = "Título: ";

        if(reference.getSchool()!= null) items[2] = "Escuela: " + reference.getSchool();
        else items[2] = "Escuela: ";

        if(reference.getYear()!= null) items[3] = "Año: " + reference.getYear();
        else items[3] = "Año: ";

        if(reference.getType()!= null) items[4] = "Tipo: " + reference.getType();
        else items[4] = "Tipo: ";

        if(reference.getAddress()!= null) items[5] = "Dirección: " + reference.getAddress();
        else items[5] = "Dirección: ";

        if(reference.getMonth()!= null) items[6] = "Mes: " + reference.getMonth();
        else items[6] = "Mes: ";

        if(reference.getNote()!= null) items[7] = "Nota: " + reference.getNote();
        else items[7] = "Nota: ";

        return items;
    }

    private CharSequence[] createConferencePaperInfoDialog(ConferencePaperReference reference) {
        final CharSequence[] items = new CharSequence[14];

        if(reference.getAuthor()!= null) items[0] = "Autor: " + reference.getAuthor();
        else items[0] = "Autor: ";

        if(reference.getTitle()!= null) items[1] = "Título: " + reference.getTitle();
        else items[1] = "Título: ";

        if(reference.getBookTitle()!= null) items[2] = "Título del libro: " + reference.getBookTitle();
        else items[2] = "Título del libro: ";

        if(reference.getYear()!= null) items[3] = "Año: " + reference.getYear();
        else items[3] = "Año: ";

        if(reference.getEditor()!= null) items[4] = "Editor: " + reference.getEditor();
        else items[4] = "Editor: ";

        if(reference.getVolume()!= null) items[5] = "Volumen: " + reference.getVolume();
        else items[5] = "Volumen: ";

        if(reference.getNumber()!= null) items[6] = "Número: " + reference.getNumber();
        else items[6] = "Número: ";

        if(reference.getSeries()!= null) items[7] = "Serie: " + reference.getSeries();
        else items[7] = "Serie: ";

        if(reference.getPages()!= null) items[8] = "Páginas: " + reference.getPages();
        else items[8] = "Páginas: ";

        if(reference.getAddress()!= null) items[9] = "Dirección: " + reference.getAddress();
        else items[9] = "Dirección: ";

        if(reference.getMonth()!= null) items[10] = "Mes: " + reference.getMonth();
        else items[10] = "Mes: ";

        if(reference.getOrganization()!= null) items[11] = "Organización: " + reference.getOrganization();
        else items[11] = "Organización: ";

        if(reference.getPublisher()!= null) items[12] = "Editorial: " + reference.getPublisher();
        else items[12] = "Editorial: ";

        if(reference.getNote()!= null) items[13] = "Nota: " + reference.getNote();
        else items[13] = "Nota: ";

        return items;
    }

    private CharSequence[] createConferenceProceedingInfoDialog(ConferenceProceedingReference reference) {
        final CharSequence[] items = new CharSequence[12];

        if(reference.getTitle()!= null) items[0] = "Título: " + reference.getTitle();
        else items[0] = "Título: ";

        if(reference.getYear()!= null) items[1] = "Año: " + reference.getYear();
        else items[1] = "Año: ";

        if(reference.getEditor()!= null) items[2] = "Editor: " + reference.getEditor();
        else items[2] = "Editor: ";

        if(reference.getVolume()!= null) items[3] = "Volumen: " + reference.getVolume();
        else items[3] = "Volumen: ";

        if(reference.getNumber()!= null) items[4] = "Número: " + reference.getNumber();
        else items[4] = "Número: ";

        if(reference.getSeries()!= null) items[5] = "Serie: " + reference.getSeries();
        else items[5] = "Serie: ";

        if(reference.getAddress()!= null) items[6] = "Dirección: " + reference.getAddress();
        else items[6] = "Dirección: ";

        if(reference.getPublisher()!= null) items[7] = "Editorial: " + reference.getPublisher();
        else items[7] = "Editorial: ";

        if(reference.getMonth()!= null) items[8] = "Mes: " + reference.getMonth();
        else items[8] = "Mes: ";

        if(reference.getOrganization()!= null) items[9] = "Organización: " + reference.getOrganization();
        else items[9] = "Organización: ";

        if(reference.getIsbn()!= null) items[10] = "ISBN: " + reference.getIsbn();
        else items[10] = "ISBN: ";

        if(reference.getNote()!= null) items[11] = "Nota: " + reference.getNote();
        else items[11] = "Nota: ";

        return items;
    }

    private CharSequence[] createWebPageInfoDialog(WebPageReference reference) {
        final CharSequence[] items = new CharSequence[6];

        if(reference.getAuthor()!= null) items[0] = "Autor: " + reference.getAuthor();
        else items[0] = "Autor: ";

        if(reference.getTitle()!= null) items[1] = "Título: " + reference.getTitle();
        else items[1] = "Título: ";

        if(reference.getMonth()!= null) items[2] = "Mes: " + reference.getMonth();
        else items[2] = "Mes: ";

        if(reference.getYear()!= null) items[3] = "Año: " + reference.getYear();
        else items[3] = "Año: ";

        if(reference.getUrl()!= null) items[4] = "URL: " + reference.getUrl();
        else items[4] = "URL: ";

        if(reference.getNote()!= null) items[5] = "Nota: " + reference.getNote();
        else items[5] = "Nota: ";

        return items;
    }
}
