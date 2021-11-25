package cl.tenpo.challenge.web.controller;

import cl.tenpo.challenge.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/multiply")
    public ResponseEntity<BigDecimal> multiply(@RequestParam BigDecimal multiplier, @RequestParam  BigDecimal multiplicand) {
        BigDecimal product  = calculatorService.multiply(multiplier, multiplicand);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
