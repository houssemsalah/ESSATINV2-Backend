package tn.essatin.erp.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Le login est obligatoire")
    private String login;
    @NotBlank(message = "Le password est obligatoire")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
