package org.example.flow_analyzer.util;

import com.opencsv.bean.AbstractBeanField;

import java.math.BigDecimal;
import java.text.ParseException;

public class BigDecimalCommaConverter extends AbstractBeanField<BigDecimal, String> {

    @Override
    protected BigDecimal convert(String value) {
        if (value == null || value.isBlank()) {
            return BigDecimal.ZERO;
        }
        String normalized = value.replace(",", ".");

        return new BigDecimal(normalized);
    }
}
