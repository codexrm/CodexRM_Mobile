package io.github.codexrm_mobile.EILibrary;

public class ExportFactory {

    public ExportFactory() {
        // Do nothing
    }

    public Export getExport(String type) {

        if (type.equals("RIS")) {
            return new ExportRis();
        } else {
            return new ExportBibTeX();
        }
    }
}
