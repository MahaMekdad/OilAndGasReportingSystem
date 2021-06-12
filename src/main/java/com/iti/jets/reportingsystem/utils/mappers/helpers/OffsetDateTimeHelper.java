package com.iti.jets.reportingsystem.utils.mappers.helpers;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class OffsetDateTimeHelper {

    public static OffsetDateTime dateHelper(Date dateToConvert){
        LocalDate localDate = new java.sql.Date(dateToConvert.getTime()).toLocalDate();
        return localDate.atTime(0,0,0).atOffset(ZoneOffset.UTC);
    }
}
