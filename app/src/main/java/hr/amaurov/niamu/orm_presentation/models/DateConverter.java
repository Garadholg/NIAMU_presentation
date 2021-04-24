package hr.amaurov.niamu.orm_presentation.models;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateConverter implements PropertyConverter<LocalDate, Long> {
    @Override
    public LocalDate convertToEntityProperty(Long databaseValue) {
        return LocalDate.ofEpochDay(databaseValue);
    }

    @Override
    public Long convertToDatabaseValue(LocalDate entityProperty) {
        return entityProperty.toEpochDay();
    }
}