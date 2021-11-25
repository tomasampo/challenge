package cl.tenpo.challenge.aop;

import cl.tenpo.challenge.service.TrackingService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cl.tenpo.challenge.security.SecurityUtils.getCurrentUserLogin;


@Aspect
public class TrackingAspect {
    private final TrackingService trackingService;

    public TrackingAspect(TrackingService trackingService) {
        this.trackingService= trackingService;

    }

    @Pointcut("execution(public * cl.tenpo.challenge.web.controller..*.*(..))")
    public void logRequestMapping() {
    }

    @Before("logRequestMapping()")
    public void logRequestBody() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        trackingService.createAndSave(getCurrentUserLogin().get(), request.getRequestURI(), response.getStatus());
    }
}