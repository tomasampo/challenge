package cl.tenpo.challenge.web.controller;

import cl.tenpo.challenge.dto.response.TrackingDto;
import cl.tenpo.challenge.service.TrackingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static cl.tenpo.challenge.web.controller.utils.PaginationUtils.generatePaginationHttpHeaders;

@RestController
@RequestMapping("/api")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/trackings")
    public ResponseEntity<List<TrackingDto>> getAllTracking(Pageable pageable) {
        Page<TrackingDto> page = trackingService.findAll(pageable);
        HttpHeaders headers = generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
