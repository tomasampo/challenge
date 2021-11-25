package cl.tenpo.challenge.repository;

import cl.tenpo.challenge.domain.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {
    Optional<TokenBlacklist> findOneByToken(String token);
    void deleteAllByExpiryLessThan(Instant date);
}
