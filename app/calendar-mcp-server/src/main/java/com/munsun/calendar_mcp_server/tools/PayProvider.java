package com.munsun.calendar_mcp_server.tools;

import java.math.BigDecimal;

public interface PayProvider {
    BigDecimal calculate(Long salary, Integer countDays);
}