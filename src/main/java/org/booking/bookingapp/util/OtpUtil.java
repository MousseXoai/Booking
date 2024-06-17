package org.booking.bookingapp.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
  public static String getRandomNumber(int len) {
    Random rnd = new Random();
    String chars = "0123456789";
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
    }
    return sb.toString();
  }
}
