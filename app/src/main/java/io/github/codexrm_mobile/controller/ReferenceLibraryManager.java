package io.github.codexrm_mobile.controller;

import org.jbibtex.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.codexrm_mobile.EILibrary.Export;
import io.github.codexrm_mobile.EILibrary.ExportFactory;
import io.github.codexrm_mobile.EILibrary.Import;
import io.github.codexrm_mobile.EILibrary.ImportFactory;
import io.github.codexrm_mobile.model.ArticleReference;
import io.github.codexrm_mobile.model.AuthenticationData;
import io.github.codexrm_mobile.model.BookLetReference;
import io.github.codexrm_mobile.model.BookReference;
import io.github.codexrm_mobile.model.BookSectionReference;
import io.github.codexrm_mobile.model.ConferencePaperReference;
import io.github.codexrm_mobile.model.ConferenceProceedingReference;
import io.github.codexrm_mobile.model.Reference;
import io.github.codexrm_mobile.model.ThesisReference;
import io.github.codexrm_mobile.model.UserLogin;
import io.github.codexrm_mobile.model.WebPageReference;

public class ReferenceLibraryManager {

    private HashMap<Integer, Reference> references;
    private AuthenticationData authenticationData;
    private ExportFactory exportFactory;
    private ImportFactory importFactory;
    private final String pathExported = "exportedReference.txt";
    private UserLogin userLogin;

    public ReferenceLibraryManager() {
        this.references = new HashMap<>();
        this.exportFactory = new ExportFactory();
        this.importFactory = new ImportFactory();
        saveReference(new ArticleReference(1,"Proyecto de medio ambiente", "2008", "marzo","aa", "Medina,Juan", "Ciencia y educacion", "1", "3", "10", "3842-4802", true));
        saveReference(new BookReference(2, "Relacion de las carreras", "2020--2021", "abril","bb", "Navarro,Enrique", "Diaz,Mercedes", "Prencite Hall", "2", "First", "SLND",
                "NY, EU", "2.", "978-3-16-148410-0", true));
        saveReference(new BookSectionReference(3,"La educacion secundaria", "20109", "noviembre","cc", "Soler,Marco", "Ulloa,Alicia", "K.Madriz", "5", "1", "SND",
                "DF, Mexico", "2.", "9789872562021", "6", "30-50", "DataCD", true));
        saveReference(new BookLetReference(4,"Introduccion a las funciones", "2016", "diciembre","dd", "Fernandez,Julia","Int. Ciencias", "Cienfuegos, Cuba", true));
        saveReference(new ThesisReference(5,"Imagenes", "2023", "junio","ee", "Musa,Berta", "iberoamericana", "Doctorado", "Quito, Ecuador", true));
        saveReference(new ConferenceProceedingReference(6,"Cambio climatico", "2020", "mayo","ff", "Medina,Juan", "1", "frist", "LDS", "Barcelona, España", "Ciencia y educacion", "978-980-14-2517-5", "Lucha contra el mundo", true));
        saveReference(new ConferencePaperReference(7,"Primefaces", "2012", "febrero","gg", "Medina,Juan", "Ciencia y educacion", "Medina,Juan", "3", "0", "Informatizacion", "3", "Barcelona, España","23,24","Informatizacion", true));
        saveReference(new WebPageReference(8,"JavaFX", "2023", "agosto","hh", "Medina,Juan", "https://Javafx", true));
    }

    public String getPathExported() { return pathExported; }

    public AuthenticationData getAuthenticationData() { return authenticationData; }

    public void setAuthenticationData(AuthenticationData authenticationData) { this.authenticationData = authenticationData; }

    public ExportFactory getExportFactory() { return exportFactory; }

    public void setExportFactory(ExportFactory exportFactory) { this.exportFactory = exportFactory; }

    public ImportFactory getImportFactory() { return importFactory; }

    public void setImportFactory(ImportFactory importFactory) { this.importFactory = importFactory; }

    public UserLogin getUserLogin() { return userLogin; }

    public void setUserLogin(UserLogin userLogin) { this.userLogin = userLogin; }

    private void saveReference(Reference reference) {
        references.put(reference.getId(), reference);
    }

    public List<Reference> getReference() {
        return new ArrayList<>(references.values());
    }


    //Reference
    public boolean syncReferences() {
        verificateExpiationDate();
       return false;
    }

    public void exportReferenceList(ArrayList<Reference> referenceList, String format) throws IOException {
        Export export = exportFactory.getExport(format);
        export.writeValue(referenceList, pathExported);
    }

    public void importReferences(String path, String format) throws IOException, ParseException {
        Import importer = importFactory.getImport(format);
        ArrayList<Reference> referenceImported = importer.readFile(path);
        for(Reference ref: referenceImported)
            references.put(ref.getId(), ref);
    }


    //User
    public boolean userLogin(AuthenticationData authenticationData) {
        if(authenticationData.getToken().isEmpty()){
            return false;
        }else{
            this.authenticationData = authenticationData;
            return true;
        }
    }

    public boolean userLogout() {
        return false;
    }

    public boolean verificateAutentication() {
        boolean isAuthentication = true;
        /*if(referenceLibrary.getAuthenticationData().getUsername().equals("guest")){
            isAuthentication = false;
        }
        else{
            if(referenceLibrary.getAuthenticationData().getRefreshTokenExpirationDate().before(new Date())){
                isAuthentication = false;
            }
        }*/
        return isAuthentication;
    }

    private void verificateExpiationDate() {
       /* if(referenceLibrary.getAuthenticationData().getTokenExpirationDate().before(new Date())){
            refreshToken();
        }*/
    }

    private void refreshToken(){
       /* TokenRefreshResponse response = service.refreshToken(new TokenRefreshRequest(referenceLibrary.getAuthenticationData().getRefreshToken()));
        referenceLibrary.getAuthenticationData().setToken(response.getTokenType()  + " " + response.getAccessToken());
        referenceLibrary.getAuthenticationData().setRefreshToken(response.getRefreshToken());
        referenceLibrary.getAuthenticationData().setTokenExpirationDate(response.getTokenExpirationDate());
        saveReferenceTable();*/
    }
}

