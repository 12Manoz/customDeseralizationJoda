package main.java.com.manoj.deseralizaton;

package com.nmo.pdp.repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.nmo.pdp.dateutils.Constants;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class CustomLocalDateTimeDeserializer extends StdScalarDeserializer<LocalDateTime> {
    private final static DateTimeFormatter DATETIME_FORMAT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(Constants.DATE_FORMAT)
            .toFormatter();

    public CustomLocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        int yearStr = 0;
        int monthOfYearStr =0 ;
        int dayOfMonthStr = 0;
        int hourofDayStr =0  ;
        int minuteOfHouStr =0 ;
        int secondOfMinuteStr =0 ;
        int millisOfSecondStr=0 ;

        String fieldName = null;

        while (jp.hasCurrentToken()) {
            JsonToken token = jp.nextToken();

            if (token == JsonToken.FIELD_NAME) {
                fieldName = jp.getCurrentName();
                System.out.println(fieldName);
            }
            else if (token == JsonToken.VALUE_NUMBER_INT) {
                if (StringUtils.equals(fieldName, "year")) {
                    yearStr = jp.getValueAsInt();
                } else if (StringUtils.equals(fieldName, "monthOfYear")) {
                    monthOfYearStr = jp.getValueAsInt();
                }
                else if (StringUtils.equals(fieldName, "dayOfMonth")) {
                    dayOfMonthStr = jp.getValueAsInt();
                }
                else if (StringUtils.equals(fieldName, "hourOfDay")) {
                    hourofDayStr = jp.getValueAsInt();
                }
                else if (StringUtils.equals(fieldName, "minuteOfHour")) {
                    minuteOfHouStr = jp.getValueAsInt();
                }else if (StringUtils.equals(fieldName, "secondOfMinute")) {
                    secondOfMinuteStr= jp.getValueAsInt();
                }else if (StringUtils.equals(fieldName, "millisOfSecond")) {
                    millisOfSecondStr = jp.getValueAsInt();
                }

                else {
                    //    throw new JsonParseException("Unexpected field name", jp.getTokenLocation());
                }
            } else if (token == JsonToken.END_OBJECT) {
                break;
            }
        }
        if (true) {
            LocalDateTime dateTime = LocalDateTime.of(yearStr, monthOfYearStr,dayOfMonthStr,hourofDayStr,minuteOfHouStr,secondOfMinuteStr);
            return dateTime;
        }
        return null;
    }
}