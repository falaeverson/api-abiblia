package br.com.abiblia.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.junit.Ignore;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Custom Jackson deserializer for transforming a JSON object (using the ISO 8601 date formatwith optional time) to a JSR310 LocalDate object.
 */
@Ignore
public class JSR310DateTimeDeserializer extends JsonDeserializer<LocalDateTime>{

     public static final JSR310DateTimeDeserializer INSTANCE = new JSR310DateTimeDeserializer();

     private JSR310DateTimeDeserializer(){
     }

     private static final DateTimeFormatter ISO_DATE_TIME;

     static {
          ISO_DATE_TIME = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_DATE_TIME).toFormatter();
     }

     @Override
     public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {

          switch (parser.getCurrentToken()) {
               case START_ARRAY:
                    if (parser.nextToken() == JsonToken.END_ARRAY) {
                         return null;
                    }
                    int year = parser.getIntValue();

                    parser.nextToken();
                    int month = parser.getIntValue();

                    parser.nextToken();
                    int day = parser.getIntValue();
                    
                    parser.nextToken();
                    int hour = parser.getIntValue();

                    parser.nextToken();
                    int minute = parser.getIntValue();

                    parser.nextToken();
                    int seconde = parser.getIntValue();
                    
                    parser.nextToken();
                    int nanoOfSecond = parser.getIntValue();
                    
                    if (parser.nextToken() != JsonToken.END_ARRAY) {
                         throw context.wrongTokenException(parser, JsonToken.END_ARRAY, "Expected array to end.");
                    }
                    return LocalDateTime.of(year, month, day, hour, minute, seconde, nanoOfSecond);

               case VALUE_STRING:
                    String string = parser.getText().trim();
                    if (string.length() == 0) {
                         return null;
                    }
                    return LocalDateTime.parse(string, ISO_DATE_TIME);
          }
          throw context.wrongTokenException(parser, JsonToken.START_ARRAY, "Expected array or string.");
     }
}