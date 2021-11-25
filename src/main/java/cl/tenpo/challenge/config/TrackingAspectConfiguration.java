package cl.tenpo.challenge.config;

import cl.tenpo.challenge.aop.TrackingAspect;
import cl.tenpo.challenge.service.TrackingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TrackingAspectConfiguration {

    private final TrackingService trackingService;

    public TrackingAspectConfiguration(TrackingService trackingService) {
        this.trackingService= trackingService;
    }

    @Bean
    public TrackingAspect trackingAspect() {
        return new TrackingAspect(trackingService);
    }
}
