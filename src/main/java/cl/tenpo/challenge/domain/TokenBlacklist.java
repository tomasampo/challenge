package cl.tenpo.challenge.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "token_blacklist")
public class TokenBlacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    private String token;
    private Instant expiry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiry() {
        return expiry;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TokenBlacklist{");
        sb.append("id=").append(id);
        sb.append(", token='").append(token).append('\'');
        sb.append(", expiry=").append(expiry);
        sb.append('}');
        return sb.toString();
    }
}
