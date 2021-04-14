package de.rieckpil.quickstarts.bbeans;

import jakarta.ejb.Stateless;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Stateless
public class ConverterBean {
    private final BigDecimal yenRate = new BigDecimal("104.34");
    private final BigDecimal euroRate = new BigDecimal("0.007");

    public BigDecimal dollarToYen(BigDecimal dollar) {
        return dollar.multiply(yenRate).setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal yenToEuro(BigDecimal yen) {
        return yen.multiply(euroRate).setScale(2, RoundingMode.CEILING);
    }
}
