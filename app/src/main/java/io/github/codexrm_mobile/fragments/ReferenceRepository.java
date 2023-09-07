package io.github.codexrm_mobile.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.codexrm_mobile.model.ArticleReference;
import io.github.codexrm_mobile.model.BookLetReference;
import io.github.codexrm_mobile.model.BookReference;
import io.github.codexrm_mobile.model.BookSectionReference;
import io.github.codexrm_mobile.model.ConferencePaperReference;
import io.github.codexrm_mobile.model.ConferenceProceedingReference;
import io.github.codexrm_mobile.model.Reference;
import io.github.codexrm_mobile.model.ThesisReference;
import io.github.codexrm_mobile.model.WebPageReference;

/**
 * Repositorio ficticio de leads
 */
public class ReferenceRepository {
    private static ReferenceRepository repository = new ReferenceRepository();
    private HashMap<Integer, Reference> references = new HashMap<>();

    public static ReferenceRepository getInstance() {
        return repository;
    }
    private ReferenceRepository() {
        saveReference(new ArticleReference(1,"Proyecto de medio ambiente", "2008", "marzo","aa", "Medina,Juan", "Ciencia y educacion", "1", "3", "10", "3842-4802"));
        saveReference(new BookReference(2, "Relacion de las carreras", "2020--2021", "abril","bb", "Navarro,Enrique", "Diaz,Mercedes", "Prencite Hall", "2", "First", "SLND",
                "NY, EU", "2.", "978-3-16-148410-0"));
        saveReference(new BookSectionReference(3,"La educacion secundaria", "2019", "noviembre","cc", "Soler,Marco", "Ulloa,Alicia", "K.Madriz", "5", "1", "SND",
                "DF, Mexico", "2.", "9789872562021", "6", "30-50", "DataCD"));
        saveReference(new BookLetReference(4,"Introduccion a las funciones", "2016", "diciembre","dd", "Fernandez,Julia","Int. Ciencias", "Cienfuegos, Cuba"));
        saveReference(new ThesisReference(5,"Imagenes", "2023", "junio","ee", "Musa,Berta", "iberoamericana", "Doctorado", "Quito, Ecuador"));
        saveReference(new ConferenceProceedingReference(6,"Cambio climatico", "2020", "mayo","ff", "Medina,Juan", "Ciencia y educacion", "1", "3", "10", "3842-4802", "", ""));
        saveReference(new ConferencePaperReference(7,"Primefaces", "2012", "febrero","gg", "Medina,Juan", "Ciencia y educacion", "1", "3", "10", "3842-4802", "", "","",""));
        saveReference(new WebPageReference(8,"JavaFX", "2023", "agosto","hh", "Medina,Juan", "Ciencia y educacion"));
        saveReference(new WebPageReference(9,"Android", "2001", "agosto","ii", "Medina,Juan", "Ciencia y educacion"));

    }

    private void saveReference(Reference reference) {
        references.put(reference.getId(), reference);
    }

    public List<Reference> getReference() {
        return new ArrayList<>(references.values());
    }
}
