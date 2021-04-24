package hr.amaurov.niamu.orm_presentation.models;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateConverter implements PropertyConverter<LocalDateTime, Long> {
    @Override
    public LocalDateTime convertToEntityProperty(Long databaseValue) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(databaseValue), ZoneOffset.UTC);
    }

    @Override
    public Long convertToDatabaseValue(LocalDateTime entityProperty) {
        return entityProperty.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}