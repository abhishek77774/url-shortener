package com.assignment.urlshortener.util;

/**
 * Provides encoding and decoding methods for URL Conversion
 */
public class UrlConversionUtil {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final char[] allowedCharacters = allowedString.toCharArray();
    private static final int base = allowedCharacters.length;
    public static final String baseURL = "http://localhost:8080/";

    /**
     * @param input Base10 number of type long
     * @return base62 encoded string representation of input long
     */
    public static String encode(long input){
        var encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }

    /**
     * @param input Base62 encoded string
     * @return decoded Base10 number of type long
     */
    public static long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;
        var decoded = 0;
        var counter = 1;

        for (char character : characters) {
            decoded += allowedString.indexOf(character) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }

}
