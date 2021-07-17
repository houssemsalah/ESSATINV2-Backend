package tn.essatin.erp.payload.response;

public class MessageResponse {

    private String message;
    private int code;

    public MessageResponse(String message) {
        this.message = message;
        this.code = 200;
    }

    public MessageResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
