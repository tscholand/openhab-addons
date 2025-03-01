package org.openhab.binding.modbus.sungrow.internal.mapper.impl;

import java.math.BigDecimal;

import org.openhab.binding.modbus.sungrow.internal.mapper.ToStringMapper;

public class DeviceTypeMapper implements ToStringMapper {

    private static final DeviceTypeMapper INSTANCE = new DeviceTypeMapper();

    public static DeviceTypeMapper instance() {
        return INSTANCE;
    }

    private DeviceTypeMapper() {
        // use instance()
    }

    @Override
    public String map(BigDecimal value) {
        if (value == null) {
            return null;
        }
        String hex = String.format("0x%03X", value.intValue());
        return switch (hex) {
            case "0xD17" -> "SH3.0RS";
            case "0xD0D" -> "SH3.6RS";
            case "0xD18" -> "SH4.0RS";
            case "0xD0F" -> "SH5.0RS";
            case "0xD10" -> "SH6.0RS";
            case "0xD1A" -> "SH8.0RS";
            case "0xD1B" -> "SH10RS";
            case "0xE00" -> "SH5.0RT";
            case "0xE01" -> "SH6.0RT";
            case "0xE02" -> "SH8.0RT";
            case "0xE03" -> "SH10RT";
            case "0xE10" -> "SH5.0RT-20";
            case "0xE11" -> "SH6.0RT-20";
            case "0xE12" -> "SH8.0RT-20";
            case "0xE13" -> "SH10RT-20";
            case "0xE0C" -> "SH5.0RT-V112";
            case "0xE0D" -> "SH6.0RT-V112";
            case "0xE0E" -> "SH8.0RT-V112";
            case "0xE0F" -> "SH10RT-V112";
            case "0xE08" -> "SH5.0RT-V122";
            case "0xE09" -> "SH6.0RT-V122";
            case "0xE0A" -> "SH8.0RT-V122";
            case "0xE0B" -> "SH10RT-V122";
            case "0xE20" -> "SH5T-V11";
            case "0xE21" -> "SH6T-V11";
            case "0xE22" -> "SH8T-V11";
            case "0xE23" -> "SH10T-V11";
            case "0xE24" -> "SH12T-V11";
            case "0xE25" -> "SH15T-V11";
            case "0xE26" -> "SH20T-V11";
            case "0xE28" -> "SH25T-V11";
            default -> "UNKNOWN: " + hex;
        };
    }
}
