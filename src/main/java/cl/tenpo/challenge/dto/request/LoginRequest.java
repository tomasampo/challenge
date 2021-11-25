package cl.tenpo.challenge.dto.request;

import cl.tenpo.challenge.config.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class LoginRequest {

    @NotNull
    @Size(min = 1, max = 50)
    @Pattern(regexp = Constants.LOGIN_REGEX)
    private String login;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginRequest{");
        sb.append("login='").append(login).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
