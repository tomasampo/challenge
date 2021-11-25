package cl.tenpo.challenge.dto.response;

public class JWTToken {

    private String idToken;

    public JWTToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JWTToken{");
        sb.append("idToken='").append(idToken).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
