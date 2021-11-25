package cl.tenpo.challenge.repository;

import cl.tenpo.challenge.domain.Tracking;
import cl.tenpo.challenge.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

}
