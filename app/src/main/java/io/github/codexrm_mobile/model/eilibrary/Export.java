package io.github.codexrm_mobile.model.eilibrary;

import java.io.IOException;
import java.util.ArrayList;

import io.github.codexrm_mobile.model.models.Reference;

public interface Export {

    void writeValue(ArrayList<Reference> referenceList, String path) throws IOException;
}
