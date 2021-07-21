package tn.essatin.erp.payload.response;

public class CombinedResponse {
    MessageResponse message;
    String natureDesDonnee;
    Object donneeSupplementaire;

    public CombinedResponse(MessageResponse message, String natureDesDonnee, Object donneeSupplementaire) {
        this.message = message;
        this.natureDesDonnee = natureDesDonnee;
        this.donneeSupplementaire = donneeSupplementaire;
    }

    public MessageResponse getMessage() {
        return message;
    }

    public void setMessage(MessageResponse message) {
        this.message = message;
    }

    public String getNatureDesDonnee() {
        return natureDesDonnee;
    }

    public void setNatureDesDonnee(String natureDesDonnee) {
        this.natureDesDonnee = natureDesDonnee;
    }

    public Object getDonneeSupplementaire() {
        return donneeSupplementaire;
    }

    public void setDonneeSupplementaire(Object donneeSupplementaire) {
        this.donneeSupplementaire = donneeSupplementaire;
    }
}
