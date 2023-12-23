package com.ecostore.utils;

public class RandomString {
    public static String getAlphaNumericString(int length) {
        String alphaNumericString = "QWERTYUIOPLKJHGFDSAZXCVBNM"
                + "0123456789"
                + "qwertyuioplkjhgfdsazxcvbnm";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * alphaNumericString.length());
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
