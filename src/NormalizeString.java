public class NormalizeString {
    public static String normalize(String input) {
        input = input.toLowerCase().trim();
        input = input.replaceAll("\\s+", " ");
        input = input.replaceAll("\\.+", ".");
        input = input.replaceAll("\\?+", "?");
        input = input.replaceAll("\\!+", "!");
        input = input.replaceAll("\\ .+", ".");
        String temp = input;
        for (int i = 0; i < temp.length(); i++) {
            if (input.charAt(i) == '.') {
                input = input.substring(0, i) + "." + toUpperAfterDot(input.substring(i+1));
            }
        }
        input = input.substring(0, 1).toUpperCase() + input.substring(1);
        return input;
    }
    
    private static String toUpperAfterDot(String input) {
        if (input == null || input.equals("")) return "";
        input = input.trim();
        String firstChar = String.valueOf(input.charAt(0));
        firstChar = firstChar.toUpperCase();
        return " " + firstChar + input.substring(1);
    }
}
