package tn.essatin.erp.payload.response;

public class MessageResponse {

    private String message;
    private int Code;

    public MessageResponse(String message) {
        this.message = message;
        this.Code = 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
