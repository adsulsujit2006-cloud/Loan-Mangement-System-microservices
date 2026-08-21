package com.lms_user_servicess.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CustomerCodeGenerator {

    private static long seq = 1;

	public static String generateCustomerCodeWithDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMdd");
        String d = dformat.format(date);
        String st = "CUSTNO";
        String s = st.concat(d).concat(String.format("%06d", seq));
      

        seq++;
        return s;

    }
}