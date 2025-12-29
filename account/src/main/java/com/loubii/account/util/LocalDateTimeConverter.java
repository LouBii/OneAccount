package com.loubii.account.util;

import org.greenrobot.greendao.converter.PropertyConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class LocalDateTimeConverter implements PropertyConverter<LocalDateTime, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public LocalDateTime convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        return LocalDateTime.parse(databaseValue, FORMATTER);
    }
    @Override
    public String convertToDatabaseValue(LocalDateTime entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return entityProperty.format(FORMATTER);
    }
}