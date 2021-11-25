package cl.tenpo.challenge.service.impl;

import cl.tenpo.challenge.service.CalculatorService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public BigDecimal multiply(BigDecimal multiplier, BigDecimal multiplicand) {
        return multiplier.multiply(multiplicand);
    }
}
