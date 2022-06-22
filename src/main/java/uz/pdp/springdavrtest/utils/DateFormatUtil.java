package uz.pdp.springdavrtest.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

@Component
public class DateFormatUtil {
    public Date dateConvertor(String sana) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(sana);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parse;
    }

    public Date dateConvertorTime(String sana) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(sana);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parse;
    }

    // this method for TimeTable. Don't touch
    public Date parseTime(String str) {
        Date time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            time = dateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return time;
    }
    // this method for TimeTable. Don't touch
    public String parseTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String sss = dateFormat.format(date);
        System.out.println(sss);
        return sss;
    }











    // dateConvertorTime metodida Date eski edi 1970 ni kursata yotgandi
    // huddi shu metodni yangilab yozdim bunda date current buladi yani kalendarda qaysi sana bulsa shu buladi
    public Date dateConvertorTimeWithCurrentDate(String sana) {
        LocalDate localDate = LocalDate.now();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(localDate + " " + sana);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parse;
    }


    //bu metod ga time berilsa Stringda -> qaytaradi current date va berilgan soat
    public Date timeConvertorWithCurrentDate(String sana) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.parse(sana);
        Date date = Date.from(LocalDateTime.of(localDate, localTime).atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    //bu metod ga date berilsa Stringda -> qaytaradi current time va berilgan sana
    public Date dateConvertorWithCurrentTime(String sana) {
        LocalDate localDate = LocalDate.parse(sana);
        LocalTime localTime = LocalTime.now();
        Date date = Date.from(LocalDateTime.of(localDate, localTime).atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    // bu metod date formatidan String formatiga utib beradi qaytarish pattern -> yyyy-MM-dd faqat sana olib beradi
    public String dateConvertToString(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }

    //bu metod date formatidan String formatiga utib beradi qaytarish pattern -> HH:mm:ss.---
    public String timeConvertToString(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime().toString();
    }

    //bu metod date formatidan String formatiga utib beradi qaytarish pattern -> yyyy-MM-ddTHH:mm:ss.---
    public String dateTimeConvertToString(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString();
    }

    //bu metod Date formatidan Timestamp formatiga utib beradi
    public Timestamp dateConvertorToTimestamp(Date date) {
        return Timestamp.from(date.toInstant().atZone(ZoneId.systemDefault()).toInstant());
    }

    //bu metod Timestamp formatidan Date formatiga utib beradi
    public Date timestampConvertToDate(Timestamp timestamp) {
        return Date.from(timestamp.toInstant().atZone(ZoneId.systemDefault()).toInstant());
    }

    //bu metod Timestamp qaytaradi String berilsa || pattern -> (yyyy-MM-dd HH:mm:ss)
    public Timestamp stringConvertorToTimestamp(String sana) {
        return Timestamp.valueOf(sana);
    }

    //bu metod Timestamp qaytaradi Stringda vaqt berilsa current date bilan || pattern -> (HH:mm:ss)
    public Timestamp stringTimeConvertorToTimestamp(String sana) {
        LocalDate localDate = LocalDate.now();
        return Timestamp.valueOf(localDate+" "+sana);
    }

    //bu metod Timestamp qaytaradi Stringda date berilsa current time bilan || pattern -> (yyyy-MM-dd)
    public Timestamp stringDateConvertorToTimestamp(String sana) {
        LocalTime localTime = LocalTime.now();
        return Timestamp.valueOf(sana+" "+localTime);
    }

    // timestampni string qilib qaytaradi
    public String timestampConvertorToString(Timestamp timestamp){
        return timestamp.toLocalDateTime().toString();
    }

    // timestamp berilsa soatini qaydaradi stringda
    public String timestampConvertorToTimeString(Timestamp timestamp){
        return timestamp.toLocalDateTime().toLocalTime().toString();
    }

    // timestamp berilsa sananiini qaydaradi stringda
    public String timestampConvertorToDateString(Timestamp timestamp){
        return timestamp.toLocalDateTime().toLocalDate().toString();
    }
}
