package io.github.codexrm_mobile.model;

public class ReferenceDialog {

    public ReferenceDialog() { }

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
        final CharSequence[] items = new CharSequence[11];

        items[0] = "Tipo de referencia: Artículo";

        if(reference.getAuthor()!= null) items[1] = "Autor: " + reference.getAuthor();
        else items[1] = "Autor: ";

        if(reference.getTitle()!= null) items[2] = "Título: " + reference.getTitle();
        else items[2] = "Título: ";

        if(reference.getJournal()!= null) items[3] = "Revista: " + reference.getJournal();
        else items[3] = "Revista: ";

        if(reference.getYear()!= null) items[4] = "Año: " + reference.getYear();
        else items[4] = "Año: ";

        if(reference.getVolume()!= null) items[5] = "Volumen: " + reference.getVolume();
        else items[5] = "Volumen: ";

        if(reference.getNumber()!= null) items[6] = "Número: " + reference.getNumber();
        else items[6] = "Número: ";

        if(reference.getPages()!= null) items[7] = "Páginas: " + reference.getPages();
        else items[7] = "Páginas: ";

        if(reference.getMonth()!= null) items[8] = "Mes: " + reference.getMonth();
        else items[8] = "Mes: ";

        if(reference.getIssn()!= null) items[9] = "ISSN: " + reference.getIssn();
        else items[9] = "ISSN: ";

        if(reference.getNote()!= null) items[10] = "Nota: " + reference.getNote();
        else items[10] = "Nota: ";

        return items;
    }

    private CharSequence[] createBookInfoDialog(BookReference reference) {
        final CharSequence[] items = new CharSequence[14];

        items[0] = "Tipo de referencia: Libro";

        if(reference.getAuthor()!= null) items[1] = "Autor: " + reference.getAuthor();
        else items[1] = "Autor: ";

        if(reference.getEditor()!= null) items[2] = "Editor: " + reference.getEditor();
        else items[2] = "Editor: ";

        if(reference.getTitle()!= null) items[3] = "Título: " + reference.getTitle();
        else items[3] = "Título: ";

        if(reference.getPublisher()!= null) items[4] = "Editorial: " + reference.getPublisher();
        else items[4] = "Editorial: ";

        if(reference.getYear()!= null) items[5] = "Año: " + reference.getYear();
        else items[5] = "Año: ";

        if(reference.getVolume()!= null) items[6] = "Volumen: " + reference.getVolume();
        else items[6] = "Volumen: ";

        if(reference.getNumber()!= null) items[7] = "Número: " + reference.getNumber();
        else items[7] = "Número: ";

        if(reference.getSeries()!= null) items[8] = "Serie: " + reference.getSeries();
        else items[8] = "Serie: ";

        if(reference.getAddress()!= null) items[9] = "Dirección: " + reference.getAddress();
        else items[9] = "Dirección: ";

        if(reference.getEdition()!= null) items[10] = "Edición: " + reference.getEdition();
        else items[10] = "Edición: ";

        if(reference.getMonth()!= null) items[11] = "Mes: " + reference.getMonth();
        else items[11] = "Mes: ";

        if(reference.getIsbn()!= null) items[12] = "ISBN: " + reference.getIsbn();
        else items[12] = "ISBN: ";

        if(reference.getNote()!= null) items[13] = "Nota: " + reference.getNote();
        else items[13] = "Nota: ";

        return items;
    }

    private CharSequence[] createBookSectionInfoDialog(BookSectionReference reference) {
        final CharSequence[] items = new CharSequence[17];

        items[0] = "Tipo de referencia: Sección de libro";

        if(reference.getChapter()!= null) items[1] = "Capítulo: " + reference.getChapter();
        else items[1] = "Capítulo: ";

        if(reference.getPages()!= null) items[2] = "Páginas: " + reference.getPages();
        else items[2] = "Páginas: ";

        if(reference.getAuthor()!= null) items[3] = "Autor: " + reference.getAuthor();
        else items[3] = "Autor: ";

        if(reference.getEditor()!= null) items[4] = "Editor: " + reference.getEditor();
        else items[4] = "Editor: ";

        if(reference.getTitle()!= null) items[5] = "Título: " + reference.getTitle();
        else items[5] = "Título: ";

        if(reference.getPublisher()!= null) items[6] = "Editorial: " + reference.getPublisher();
        else items[6] = "Editorial: ";

        if(reference.getYear()!= null) items[7] = "Año: " + reference.getYear();
        else items[7] = "Año: ";

        if(reference.getVolume()!= null) items[8] = "Volumen: " + reference.getVolume();
        else items[8] = "Volumen: ";

        if(reference.getNumber()!= null) items[9] = "Número: " + reference.getNumber();
        else items[9] = "Número: ";

        if(reference.getSeries()!= null) items[10] = "Serie: " + reference.getSeries();
        else items[10] = "Serie: ";

        if(reference.getType()!= null) items[11] = "Tipo: " + reference.getType();
        else items[11] = "Tipo: ";

        if(reference.getAddress()!= null) items[12] = "Dirección: " + reference.getAddress();
        else items[12] = "Dirección: ";

        if(reference.getEdition()!= null) items[13] = "Edición: " + reference.getEdition();
        else items[13] = "Edición: ";

        if(reference.getMonth()!= null) items[14] = "Mes: " + reference.getMonth();
        else items[14] = "Mes: ";

        if(reference.getIsbn()!= null) items[15] = "ISBN: " + reference.getIsbn();
        else items[15] = "ISBN: ";

        if(reference.getNote()!= null) items[16] = "Nota: " + reference.getNote();
        else items[16] = "Nota: ";

        return items;
    }

    private CharSequence[] createBookLetInfoDialog(BookLetReference reference) {
        final CharSequence[] items = new CharSequence[8];

        items[0] = "Tipo de referencia: Folleto";

        if(reference.getTitle()!= null) items[1] = "Título: " + reference.getTitle();
        else items[1] = "Título: ";

        if(reference.getAuthor()!= null) items[2] = "Autor: " + reference.getAuthor();
        else items[2] = "Autor: ";

        if(reference.getHowpublished()!= null) items[3] = "Publicación: " + reference.getHowpublished();
        else items[3] = "Publicación: ";

        if(reference.getAddress()!= null) items[4] = "Dirección: " + reference.getAddress();
        else items[4] = "Dirección: ";

        if(reference.getMonth()!= null) items[5] = "Mes: " + reference.getMonth();
        else items[5] = "Mes: ";

        if(reference.getYear()!= null) items[6] = "Año: " + reference.getYear();
        else items[6] = "Año: ";

        if(reference.getNote()!= null) items[7] = "Nota: " + reference.getNote();
        else items[7] = "Nota: ";

        return items;
    }

    private CharSequence[] createThesisInfoDialog(ThesisReference reference) {
        final CharSequence[] items = new CharSequence[9];

        items[0] = "Tipo de referencia: Tesis";

        if(reference.getAuthor()!= null) items[1] = "Autor: " + reference.getAuthor();
        else items[1] = "Autor: ";

        if(reference.getTitle()!= null) items[2] = "Título: " + reference.getTitle();
        else items[2] = "Título: ";

        if(reference.getSchool()!= null) items[3] = "Escuela: " + reference.getSchool();
        else items[3] = "Escuela: ";

        if(reference.getYear()!= null) items[4] = "Año: " + reference.getYear();
        else items[4] = "Año: ";

        if(reference.getType()!= null) items[5] = "Tipo: " + reference.getType();
        else items[5] = "Tipo: ";

        if(reference.getAddress()!= null) items[6] = "Dirección: " + reference.getAddress();
        else items[6] = "Dirección: ";

        if(reference.getMonth()!= null) items[7] = "Mes: " + reference.getMonth();
        else items[7] = "Mes: ";

        if(reference.getNote()!= null) items[8] = "Nota: " + reference.getNote();
        else items[8] = "Nota: ";

        return items;
    }

    private CharSequence[] createConferencePaperInfoDialog(ConferencePaperReference reference) {
        final CharSequence[] items = new CharSequence[15];

        items[0] = "Tipo de referencia: Doc. de sesión";

        if(reference.getAuthor()!= null) items[1] = "Autor: " + reference.getAuthor();
        else items[1] = "Autor: ";

        if(reference.getTitle()!= null) items[2] = "Título: " + reference.getTitle();
        else items[2] = "Título: ";

        if(reference.getBookTitle()!= null) items[3] = "Título del libro: " + reference.getBookTitle();
        else items[3] = "Título del libro: ";

        if(reference.getYear()!= null) items[4] = "Año: " + reference.getYear();
        else items[4] = "Año: ";

        if(reference.getEditor()!= null) items[5] = "Editor: " + reference.getEditor();
        else items[5] = "Editor: ";

        if(reference.getVolume()!= null) items[6] = "Volumen: " + reference.getVolume();
        else items[6] = "Volumen: ";

        if(reference.getNumber()!= null) items[7] = "Número: " + reference.getNumber();
        else items[7] = "Número: ";

        if(reference.getSeries()!= null) items[8] = "Serie: " + reference.getSeries();
        else items[8] = "Serie: ";

        if(reference.getPages()!= null) items[9] = "Páginas: " + reference.getPages();
        else items[9] = "Páginas: ";

        if(reference.getAddress()!= null) items[10] = "Dirección: " + reference.getAddress();
        else items[10] = "Dirección: ";

        if(reference.getMonth()!= null) items[11] = "Mes: " + reference.getMonth();
        else items[11] = "Mes: ";

        if(reference.getOrganization()!= null) items[12] = "Organización: " + reference.getOrganization();
        else items[12] = "Organización: ";

        if(reference.getPublisher()!= null) items[13] = "Editorial: " + reference.getPublisher();
        else items[13] = "Editorial: ";

        if(reference.getNote()!= null) items[14] = "Nota: " + reference.getNote();
        else items[14] = "Nota: ";

        return items;
    }

    private CharSequence[] createConferenceProceedingInfoDialog(ConferenceProceedingReference reference) {
        final CharSequence[] items = new CharSequence[13];

        items[0] = "Tipo de referencia: Acta de congreso";

        if(reference.getTitle()!= null) items[1] = "Título: " + reference.getTitle();
        else items[1] = "Título: ";

        if(reference.getYear()!= null) items[2] = "Año: " + reference.getYear();
        else items[2] = "Año: ";

        if(reference.getEditor()!= null) items[3] = "Editor: " + reference.getEditor();
        else items[3] = "Editor: ";

        if(reference.getVolume()!= null) items[4] = "Volumen: " + reference.getVolume();
        else items[4] = "Volumen: ";

        if(reference.getNumber()!= null) items[5] = "Número: " + reference.getNumber();
        else items[5] = "Número: ";

        if(reference.getSeries()!= null) items[6] = "Serie: " + reference.getSeries();
        else items[6] = "Serie: ";

        if(reference.getAddress()!= null) items[7] = "Dirección: " + reference.getAddress();
        else items[7] = "Dirección: ";

        if(reference.getPublisher()!= null) items[8] = "Editorial: " + reference.getPublisher();
        else items[8] = "Editorial: ";

        if(reference.getMonth()!= null) items[9] = "Mes: " + reference.getMonth();
        else items[9] = "Mes: ";

        if(reference.getOrganization()!= null) items[10] = "Organización: " + reference.getOrganization();
        else items[10] = "Organización: ";

        if(reference.getIsbn()!= null) items[11] = "ISBN: " + reference.getIsbn();
        else items[11] = "ISBN: ";

        if(reference.getNote()!= null) items[12] = "Nota: " + reference.getNote();
        else items[12] = "Nota: ";

        return items;
    }

    private CharSequence[] createWebPageInfoDialog(WebPageReference reference) {
        final CharSequence[] items = new CharSequence[7];

        items[0] = "Tipo de referencia: Página web";

        if(reference.getAuthor()!= null) items[1] = "Autor: " + reference.getAuthor();
        else items[1] = "Autor: ";

        if(reference.getTitle()!= null) items[2] = "Título: " + reference.getTitle();
        else items[2] = "Título: ";

        if(reference.getMonth()!= null) items[3] = "Mes: " + reference.getMonth();
        else items[3] = "Mes: ";

        if(reference.getYear()!= null) items[4] = "Año: " + reference.getYear();
        else items[4] = "Año: ";

        if(reference.getUrl()!= null) items[5] = "URL: " + reference.getUrl();
        else items[5] = "URL: ";

        if(reference.getNote()!= null) items[6] = "Nota: " + reference.getNote();
        else items[6] = "Nota: ";

        return items;
    }
}
