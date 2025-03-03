import java.time.LocalTime;

public class TimeRepresentation {

    public final String ampm;
    public final String hour;
    public final String minuteTens;
    public final String minuteOnes;
    public final String second;

    private TimeRepresentation(String ampm, String hour, String minuteTens, String minuteOnes, String second) {
        this.ampm = ampm;
        this.hour = hour;
        this.minuteTens = minuteTens;
        this.minuteOnes = minuteOnes;
        this.second = second;
    }

    public static TimeRepresentation now() {
        LocalTime now = LocalTime.now();
        int hour24 = now.getHour();
        int minute = now.getMinute();
        int sec = now.getSecond();

        String ampm = (hour24 < 12) ? "오전" : "오후";
        int hour12 = hour24 % 12;
        if (hour12 == 0) hour12 = 12;
        String hourStr = convertHour(hour12) + "시";

        int tens = minute / 10;
        int ones = minute % 10;
        String minuteTens = (tens == 0) ? "영" : convertTens(tens);
        String minuteOnes = (ones == 0) ? "영" : convertDigit(ones);


        String secondStr = (sec < 10) ? "영" + convertDigit(sec)
                : convertTens(sec / 10) + convertDigit(sec % 10);
        return new TimeRepresentation(ampm, hourStr, minuteTens, minuteOnes, secondStr);
    }

    private static String convertHour(int hour) {
        return switch (hour) {
            case 1 -> "한";
            case 2 -> "두";
            case 3 -> "세";
            case 4 -> "네";
            case 5 -> "다섯";
            case 6 -> "여섯";
            case 7 -> "일곱";
            case 8 -> "여덟";
            case 9 -> "아홉";
            case 10 -> "열";
            case 11 -> "열한";
            case 12 -> "열두";
            default -> "";
        };
    }

    private static String convertTens(int t) {
        return switch (t) {
            case 1 -> "십";
            case 2 -> "이십";
            case 3 -> "삼십";
            case 4 -> "사십";
            case 5 -> "오십";
            default -> "";
        };
    }

    private static String convertDigit(int d) {
        return switch (d) {
            case 0 -> "영";
            case 1 -> "일";
            case 2 -> "이";
            case 3 -> "삼";
            case 4 -> "사";
            case 5 -> "오";
            case 6 -> "육";
            case 7 -> "칠";
            case 8 -> "팔";
            case 9 -> "구";
            default -> "";
        };
    }
}