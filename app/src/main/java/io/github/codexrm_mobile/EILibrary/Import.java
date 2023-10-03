package io.github.codexrm_mobile.EILibrary;

import org.jbibtex.ParseException;

import java.io.IOException;
import java.util.ArrayList;

import io.github.codexrm_mobile.model.Reference;

public interface Import {

    ArrayList<Reference> readFile(String path) throws IOException, ParseException;
}
