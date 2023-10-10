package io.github.codexrm_mobile.model.eilibrary;

import org.jbibtex.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.github.codexrm_mobile.model.models.Reference;

public interface Import {

    ArrayList<Reference> readFile(File file) throws IOException, ParseException;
}
