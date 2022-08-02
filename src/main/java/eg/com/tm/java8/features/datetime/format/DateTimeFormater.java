/*
 * Copyright (C) 2016 mohamed_taman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eg.com.tm.java8.features.datetime.format;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 *
 * @author mohamed_taman
 */
public class DateTimeFormater {

    public static void main(String[] args) {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ISO_DATE;

        System.out.println("DateTimeFormatter.ISO_DATE "+df.format(currentDate));

        
        System.out.println("DateTimeFormatter.ISO_LOCAL_DATE "+DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(2018, 3, 9)));
        
        System.out.println( "DateTimeFormatter.ISO_LOCAL_DATE_TIME "+ DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDate.of(2018, 3, 9).atStartOfDay()));
        
        System.out.println("DateTimeFormatter.ISO_OFFSET_DATE " +  DateTimeFormatter.ISO_OFFSET_DATE.format(LocalDate.of(2018, 3, 9).atStartOfDay(ZoneId.of("UTC-3"))));
        
        System.out.println("DateTimeFormatter.RFC_1123_DATE_TIME "+DateTimeFormatter.RFC_1123_DATE_TIME.format(LocalDate.now().atStartOfDay(ZoneId.of("UTC-3"))));

        
        System.out.println("DateTimeFormatter.ISO_LOCAL_DATE "+DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now().plusDays(31)));
        
        
        ZonedDateTime anotherSummerDay = LocalDateTime.now().atZone(ZoneId.of("UTC-05:30"));
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(anotherSummerDay));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(anotherSummerDay));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(anotherSummerDay));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(anotherSummerDay));
        
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter dt = DateTimeFormatter.ISO_TIME;
        System.out.println(dt.format(currentTime));

        LocalDateTime currentDT = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println(dtf.format(currentDT));

        DateTimeFormatter f_long = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        System.out.println(f_long.format(currentDT));

        DateTimeFormatter f_short = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println(f_short.format(currentDT));
        
        String fr_short = f_short.withLocale(Locale.FRENCH).format(currentDT);
        String fr_long = f_long.withLocale(Locale.FRENCH).format(currentDT);
        
        System.out.println(fr_short);
        System.out.println(fr_long);
        
        DateTimeFormatterBuilder b = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.DAY_OF_YEAR)
                .appendLiteral("||")
                .appendValue(ChronoField.DAY_OF_MONTH)
                .appendLiteral("||")
                .appendValue(ChronoField.YEAR);
        
        DateTimeFormatter f = b.toFormatter();
        System.out.println(f.format(currentDT));
               
        
        LocalDate anotherDay = LocalDate.of(2016, 8, 23);
        LocalTime anotherTime = LocalTime.of(13, 12, 45);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(anotherDay, anotherTime, ZoneId.of("Asia/Kolkata"));
        System.out.println(
          DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
          .format(zonedDateTime));
        System.out.println(
          DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
          .format(zonedDateTime));
        System.out.println(
          DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
          .format(zonedDateTime));
        System.out.println(
          DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
          .format(zonedDateTime));

        
        ZonedDateTime dateTime = ZonedDateTime.from(
        		  DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
        		    .parse("Tuesday, August 23, 2016 at 1:12:45 PM India Standard Time"));
        		System.out.println(dateTime.plusHours(12));
        		
        		
        		String europeanDatePattern = "dd.MM.yyyy";
        		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        		System.out.println(europeanDateFormatter.format(LocalDate.now()));
        
        		
        		String customDateFormat = "d.MMMMM.uu";
        		DateTimeFormatter customDateFormatter = DateTimeFormatter.ofPattern(customDateFormat);
        		System.out.println(customDateFormatter.format(LocalDate.of(2021,1,31)));
        		
        		
        		DateTimeFormatter leapYearCheck = DateTimeFormatter.ofPattern("dd.MM.yy");
        		System.out.println(LocalDate.from(leapYearCheck.parse("15.08.16")).isLeapYear());
        		
        		String timeColonPattern = "hh:mm:ss SSS a";
        		DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
        		LocalTime colonTime = LocalTime.of(17, 35, 50).plus(1000,ChronoUnit.MILLIS);
        		System.out.println(timeColonFormatter.format(colonTime)); 
        		
        		String newYorkDateTimePattern = "dd.MM.yyyy HH:mm:ss SSS a z";
        		DateTimeFormatter newYorkDateFormatter = DateTimeFormatter.ofPattern(newYorkDateTimePattern);
        		LocalDateTime summerDay = LocalDateTime.of(2016, 7, 31, 14, 15);
        		System.out.println(newYorkDateFormatter.format(ZonedDateTime.of(summerDay, ZoneId.of("UTC-4")))); 
        		
        		
        		DateTimeFormatter zonedFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm z");
        		System.out.println(ZonedDateTime.from(zonedFormatter.parse("31.07.2016 14:15 GMT+02:00")).getOffset().getTotalSeconds());
    
        		ZonedDateTime customZonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Kolkata"));
        	        		
        		System.out.println(DateTimeFormatter.ISO_INSTANT.format(customZonedDateTime));
    
    
        		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault());
        	    ZonedDateTime formatZoneDateTime = ZonedDateTime.parse("2021-10-01T05:06:20Z", formatter);
        		
        	    
        	    LocalDateTime myBirthDay = LocalDateTime.of(1986, 11, 27, 17, 30, 59, 1200);
        	    
        	    System.out.println(myBirthDay.getDayOfWeek());
        	    
        	    
        	    LocalDate localDate = LocalDate.now();
        	     int duration = DayOfWeek.THURSDAY.getValue()-localDate.getDayOfWeek().getValue();
        	    System.out.println(localDate.minusDays(7-duration)); 
        	    System.out.println(localDate);
        	    
        	    ZoneId systemDefault = ZoneOffset.systemDefault();
        	    System.out.println(systemDefault.getId());
        	    System.out.println(systemDefault.getAvailableZoneIds());
        	    
        	    System.out.println(ZoneOffset.ofTotalSeconds(3600));
        	    System.out.println(ZoneId.systemDefault());
        	    
        	    ZoneId ofOffset = ZoneId.ofOffset("GMT", ZoneOffset.ofHours(-5));
				System.out.println(ofOffset.getId());
        	    
        	    ZonedDateTime zonedDateTime2 = ZonedDateTime.ofInstant(Instant.now(), ZoneOffset.systemDefault());
        	    
        	    System.out.println(zonedDateTime2.toString());
        	    
        	    System.out.println(Instant.now());
        	    
        	    System.out.println(ZonedDateTime.now().toInstant());        	    
         	    
    }
    
    
}
