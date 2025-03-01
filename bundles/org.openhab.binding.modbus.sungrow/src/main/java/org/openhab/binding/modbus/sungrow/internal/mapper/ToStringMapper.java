package org.openhab.binding.modbus.sungrow.internal.mapper;

import java.math.BigDecimal;

/**
 * Defines methods for mapping a given value (from the registers) to a corresponding String.
 */
public interface ToStringMapper {

    /**
     * Maps from the given {@link BigDecimal} to the {@link String} label.
     * 
     * @param value the value from the register
     * @return the corresponding String value
     */
    String map(BigDecimal value);
}
