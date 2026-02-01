package com.munsun.calendar_mcp_server.tools;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class SimplePayProvider implements PayProvider {
    private static final BigDecimal AVERAGE_COUNT_DAYS_MONTH = new BigDecimal("29.3");
    private static final int DEFAULT_ROUND_MODE = 2;
    private static final BigDecimal TAX_PERCENT = new BigDecimal("0.13");

    @Override
    public BigDecimal calculate(Long salary, Integer countDays) {
        BigDecimal hundredPercent = new BigDecimal(1);
        return BigDecimal.valueOf(salary)
                .multiply(hundredPercent.add(TAX_PERCENT))
                .divide(AVERAGE_COUNT_DAYS_MONTH, DEFAULT_ROUND_MODE)
                .multiply(BigDecimal.valueOf(countDays))
                .multiply(hundredPercent.subtract(TAX_PERCENT))
                .setScale(DEFAULT_ROUND_MODE, RoundingMode.HALF_EVEN);
    }
}
