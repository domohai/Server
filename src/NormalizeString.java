import java.util.Arrays;

public class NormalizeString {
    public static final char[] separators = {'.', ';', '?', '!', ','};
    public static String normalize(String input) {
        // change all chars to lower case and remove all spaces on the left and right
        input = input.toLowerCase().trim();
        // remove all extra spaces in string
        input = input.replaceAll("\\s+", " ");
//        input = input.replaceAll("\\.+", ".");
//        input = input.replaceAll("\\?+", "?");
//        input = input.replaceAll("\\!+", "!");
//        input = input.replaceAll("\\,+", ",");
//        input = input.replaceAll("\\;+", ";");
        int length = input.length();
        // loop through each char in string
        for (int i = 0; i < length; i++) {
//            if (input.charAt(i) == '.') {
//                input = input.substring(0, i).trim() + "." + toUpperAfterDot(input.substring(i+1));
//            } else if (input.charAt(i) == '?') {
//                input = input.substring(0, i).trim() + "?" + toUpperAfterDot(input.substring(i+1));
//            }  else if (input.charAt(i) == '!') {
//                input = input.substring(0, i).trim() + "!" + toUpperAfterDot(input.substring(i+1));
//            }
            if (i >= input.length()) break;
            // find if the char is one of the separator
            if (Arrays.binarySearch(separators, input.charAt(i)) > -1) {
                input = input.substring(0, i).trim() + handleSeparator(input.substring(i));
            }
        }
        // make the first char upper case (in a dirty way tho) :')
        input = input.substring(0, 1).toUpperCase() + input.substring(1);
        return input;
    }
    
    private static String handleSeparator(String input) {
        if (input == null || input.equals("")) return "";
        input = input.trim();
        // if there is (are) separator(s) at the beginning of string
        if (Arrays.binarySearch(separators, input.charAt(0)) > -1) {
            // there is an extra space between two separator
            // ". ?"
            // and also check if the length is greater than 3
            if (input.length() > 3 && input.charAt(1) == ' '
                && (Arrays.binarySearch(separators, input.charAt(2)) > -1)) {
                input = input.charAt(0) + input.substring(2);
            }
            // keep remove the second char if it is one of the separator
            while (Arrays.binarySearch(separators, input.charAt(1)) > -1) {
                input = input.charAt(0) + input.substring(2);
                if (input.length() < 2) break;
            }
            // to upper case?
            if (input.charAt(0) == '.' || input.charAt(0) == '?' || input.charAt(0) == '!') {
                // if there's nothing left then just return the separator
                if (input.length() < 2) return input.charAt(0) + "";
                // if there's still text behind it then turn the first char to be upper case then return
                input = input.charAt(0) + " " + input.substring(1, 2).toUpperCase() + input.substring(2);
            }
        }
        return input;
    }
}
