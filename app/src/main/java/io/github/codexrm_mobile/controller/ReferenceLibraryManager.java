package io.github.codexrm_mobile.controller;

import org.jbibtex.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.codexrm_mobile.model.dto.ReferenceDTO;
import io.github.codexrm_mobile.model.retrofit.SyncRequest;
import io.github.codexrm_mobile.model.retrofit.TokenRefreshResponse;
import io.github.codexrm_mobile.model.models.*;

public class ReferenceLibraryManager {

    private ReferenceLibrary referenceLibrary;
    private AuthenticationData authenticationData;

    public ReferenceLibraryManager() {
        this.referenceLibrary = new ReferenceLibrary();
        this.authenticationData = new AuthenticationData();
    }

    public ReferenceLibrary getReferenceLibrary() { return referenceLibrary; }

    public void setReferenceLibrary(ReferenceLibrary referenceLibrary) { this.referenceLibrary = referenceLibrary; }

    public AuthenticationData getAuthenticationData() { return authenticationData; }

    public void setAuthenticationData(AuthenticationData authenticationData) { this.authenticationData = authenticationData; }

    //Reference
    public List<Reference> getReference() { return referenceLibrary.getReference(); }

    public void exportReferenceList(ArrayList<Reference> referenceList, String format) throws IOException { referenceLibrary.exportReferenceList(referenceList, format); }

    public boolean importReferences(File file, String format) throws IOException, ParseException { return referenceLibrary.importReferences(file, format); }

    public SyncRequest createSyncRequest() { return referenceLibrary.createSyncRequest(); }

    public void syncReferences(ArrayList<ReferenceDTO> referenceList) { referenceLibrary.syncReferences(referenceList);}

    //User
    public boolean userLogin(AuthenticationData authenticationData) {
        if (authenticationData.getToken().isEmpty())
            return false;
        else {
            this.authenticationData = authenticationData;
            return true;
        }
    }

    public void userLogout() {
        authenticationData.setToken(null);
        authenticationData.setRefreshToken(null);
        authenticationData.setTokenExpirationDate(null);
        authenticationData.setRefreshTokenExpirationDate(new Date());
    }

    public void refreshToken(TokenRefreshResponse response) {
        authenticationData.setToken(response.getTokenType() + " " + response.getAccessToken());
        authenticationData.setRefreshToken(response.getRefreshToken());
        authenticationData.setTokenExpirationDate(response.getTokenExpirationDate());
    }

    public boolean verificateUserLogout() {
        if (authenticationData.getToken() == null)
            return true;
         else {
            if (authenticationData.getRefreshToken() != null) {
                if (verificateExpiationDate())
                    return true;
            }
        }
        return false;
    }

    public boolean verificateAutentication() {
        boolean isAuthentication = true;
        if (authenticationData.getUsername() == null)
            isAuthentication = false;
         else
             if (authenticationData.getRefreshTokenExpirationDate().before(new Date()))
                isAuthentication = false;

        return isAuthentication;
    }

    public boolean verificateExpiationDate() {
        if (authenticationData.getTokenExpirationDate().before(new Date()))
            return true;
        else return false;
    }
}

