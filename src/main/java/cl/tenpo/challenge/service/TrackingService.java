package cl.tenpo.challenge.service;

import cl.tenpo.challenge.dto.response.TrackingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrackingService {
    TrackingDto createAndSave(String username, String url, int responseStatus);
    Page<TrackingDto> findAll(Pageable pageable);
}