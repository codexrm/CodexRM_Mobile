package io.github.codexrm_mobile.controller;

import java.util.ArrayList;

import io.github.codexrm_mobile.model.AuthenticationData;
import io.github.codexrm_mobile.model.Reference;

public class ReferenceLibraryManager {

    private ArrayList<Reference> referenceList;
    private AuthenticationData authenticationData;


    public ReferenceLibraryManager(ArrayList<Reference> referenceList, AuthenticationData authenticationData) {
        this.referenceList = referenceList;
        this.authenticationData = authenticationData;
    }

    public ArrayList<Reference> getReferenceList() { return referenceList; }

    public void setReferenceList(ArrayList<Reference> referenceList) { this.referenceList = referenceList; }

    public AuthenticationData getAuthenticationData() { return authenticationData; }

    public void setAuthenticationData(AuthenticationData authenticationData) { this.authenticationData = authenticationData; }

}
