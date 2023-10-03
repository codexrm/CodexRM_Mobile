package io.github.codexrm_mobile.EILibrary;

public class ImportFactory {

    public ImportFactory() {
        // Do nothing
    }

    public Import getImport(String type) {

        if (type.equals("RIS")) {
            return  new ImportRis();
        } else {
            return new ImportBibTeX();
        }
    }
}
