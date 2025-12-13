package com.example.project_2_paw.typeConverters;

import androidx.room.TypeConverter;

import java.time.LocalDate;
public class LocalDateTypeConverter {
    @TypeConverter
    public static LocalDate toDate(String value) {
        return value == null ? null : LocalDate.parse(value);
    }

    @TypeConverter
    public static String toString(LocalDate date) {
        return date == null ? null : date.toString();
    }
}
