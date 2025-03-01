package org.openhab.binding.modbus.sungrow.internal.mapper.impl;

import java.math.BigDecimal;

import org.openhab.binding.modbus.sungrow.internal.mapper.ToStringMapper;

public class OutputTypeMapper implements ToStringMapper {

    private static final OutputTypeMapper INSTANCE = new OutputTypeMapper();

    public static OutputTypeMapper instance() {
        return INSTANCE;
    }

    private OutputTypeMapper() {
        // use instance()
    }

    @Override
    public String map(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return switch (value.intValue()) {
            case 0 -> "SINGLE";
            case 1 -> "3P4L";
            case 2 -> "3P3L";
            default -> "UNKNOWN: " + value.toPlainString();
        };
    }
}
