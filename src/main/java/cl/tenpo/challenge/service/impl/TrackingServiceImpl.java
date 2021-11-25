package cl.tenpo.challenge.service.impl;

import cl.tenpo.challenge.domain.Tracking;
import cl.tenpo.challenge.dto.response.TrackingDto;
import cl.tenpo.challenge.repository.TrackingRepository;
import cl.tenpo.challenge.service.TrackingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@Transactional
public class TrackingServiceImpl implements TrackingService {

    private final TrackingRepository trackingRepository;

    public TrackingServiceImpl(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }
    @Override
    public TrackingDto createAndSave(String username, String url, int responseStatus) {
        Tracking tracking = new  Tracking();
        tracking.setDate(Instant.now());
        tracking.setUserName(username);
        tracking.setUrl(url);
        tracking.setResponseStatus(responseStatus);
        tracking = trackingRepository.save(tracking);
        return new TrackingDto(tracking);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrackingDto> findAll(Pageable pageable) {
        return trackingRepository.findAll(pageable).map(TrackingDto::new);
    }
}
