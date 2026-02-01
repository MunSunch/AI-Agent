package com.munsun.calendar_mcp_server.tools;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DefaultVacationPayService {
    private final PayProvider provider;

    public DefaultVacationPayService(PayProvider provider) {
        this.provider = provider;
    }

    @McpTool(name = "calculate_vacation_pay",
             description = "Description: calculates employee's vacation pay;" +
                           " Arguments: salary - salary, countDays - number of vacation days;" +
                           " return value - vacation pay amount")
    public BigDecimal calculate(@McpToolParam(description = "salary", required = true) Long salary,
                                @McpToolParam(description = "number of vacation days", required = true) Integer countDays) {
        return provider.calculate(salary, countDays);
    }
}