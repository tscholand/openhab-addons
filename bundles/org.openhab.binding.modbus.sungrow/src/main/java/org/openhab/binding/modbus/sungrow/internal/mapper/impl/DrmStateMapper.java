package org.openhab.binding.modbus.sungrow.internal.mapper.impl;

import java.math.BigDecimal;

import org.openhab.binding.modbus.sungrow.internal.mapper.ToStringMapper;

public class DrmStateMapper implements ToStringMapper {

    private static final DrmStateMapper INSTANCE = new DrmStateMapper();

    public static DrmStateMapper instance() {
        return INSTANCE;
    }

    private DrmStateMapper() {
        // use instance()
    }

    @Override
    public String map(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return switch (value.intValue()) {
            // not sure, if the mapping is correct
            case 1 -> "DRM0";
            case 2 -> "DRM1";
            case 3 -> "DRM2";
            case 4 -> "DRM3";
            case 5 -> "DRM4";
            case 6 -> "DRM5";
            case 7 -> "DRM6";
            case 8 -> "DRM7";
            case 9 -> "DRM8";
            default -> "INVALID: " + value.toPlainString();
        };
    }
}
