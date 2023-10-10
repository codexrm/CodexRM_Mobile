package io.github.codexrm_mobile.model.models;

import org.jbibtex.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import io.github.codexrm_mobile.model.dto.ReferenceDTO;
import io.github.codexrm_mobile.model.eilibrary.*;
import io.github.codexrm_mobile.model.enums.SortReference;
import io.github.codexrm_mobile.model.retrofit.SyncRequest;
import io.github.codexrm_mobile.model.utils.DTOConvert;

public class ReferenceLibrary {

    private Hashtable<Integer, Reference> references;
    private ExportFactory exportFactory;
    private ImportFactory importFactory;
    private DTOConvert dtoConvert;

    private final String pathExported = "exportedReference.txt";

    public ReferenceLibrary() {
        this.references = new Hashtable<>();
        this.exportFactory = new ExportFactory();
        this.importFactory = new ImportFactory();
        this.dtoConvert = new DTOConvert();
    }

    public List<Reference> getReference() {
        return new ArrayList<>(references.values());
    }

    public void exportReferenceList(ArrayList<Reference> referenceList, String format) throws IOException {
        Export export = exportFactory.getExport(format);
        export.writeValue(referenceList, pathExported);
    }

    public boolean importReferences(File file, String format) throws IOException, ParseException {
        Import importer = importFactory.getImport(format);
        ArrayList<Reference> referenceImported = importer.readFile(file);
        for (Reference ref : referenceImported) {
            ref.setId(maxValueKeys() + 1);
            references.put(ref.getId(), ref);
        }
        if(referenceImported.size() == 0) return false;
        else return true;
    }

    public SyncRequest createSyncRequest() {

        SyncRequest syncRequest = new SyncRequest();
        Enumeration<Reference> e = references.elements();
        ArrayList<Reference> referenceList = new ArrayList<>();

        while (e.hasMoreElements()) {
            referenceList.add(e.nextElement());
        }
        for (Reference reference : referenceList) {
            if (reference.isNew())
                syncRequest.setNewReference(dtoConvert.createReferenceDTO(reference));
        }
        syncRequest.setSortReference(SortReference.idAsc);
        return syncRequest;
    }

    public void syncReferences(ArrayList<ReferenceDTO> referenceList) {
        references.clear();
        for (ReferenceDTO referenceDTO : referenceList){
            Reference reference = dtoConvert.createReference(referenceDTO);
            if(reference != null) saveReference(reference);
        }
    }

    private void saveReference(Reference reference) {
        references.put(reference.getId(), reference);
    }

    private Integer maxValueKeys() {
        Enumeration<Integer> e = references.keys();
        ArrayList<Integer> listKeys = new ArrayList<>();

        while (e.hasMoreElements())
            listKeys.add(e.nextElement());

        int keyMax = -1;
        for (Integer keyValue : listKeys) {
            if (keyMax < keyValue)
                keyMax = keyValue;
        }
        return keyMax;
    }
}
