package uz.pdp.springdavrtest.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
