public class ParseException extends RuntimeException {

    public ParseException(String message, int pos) {
        super(message + " at position " + pos);
    }

}