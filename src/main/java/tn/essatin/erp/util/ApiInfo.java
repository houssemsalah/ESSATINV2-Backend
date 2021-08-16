package tn.essatin.erp.util;

import tn.essatin.erp.payload.response.MessageResponse;

import java.util.List;

public class ApiInfo {
    String url;
    String methode;
    String description;
    Object exempleRequete;
    String typeReponse;
    List<MessageResponse> responses;


    public ApiInfo(String url, String methode, String description, Object exempleRequete, String typeReponse, List<MessageResponse> responses) {
        this.url = url;
        this.methode = methode;
        this.description = description;
        this.exempleRequete = exempleRequete;
        this.typeReponse = typeReponse;
        this.responses = responses;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        methode = methode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getExempleRequete() {
        return exempleRequete;
    }

    public void setExempleRequete(Object exempleRequete) {
        this.exempleRequete = exempleRequete;
    }

    public String getTypeReponse() {
        return typeReponse;
    }

    public void setTypeReponse(String typeReponse) {
        this.typeReponse = typeReponse;
    }

    public List<MessageResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<MessageResponse> responses) {
        this.responses = responses;
    }
}
