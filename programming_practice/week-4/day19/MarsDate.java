public class MarsDate {
    private final int year;
    private final int month;
    private final int day;

    private static final int MARS_OFFSET = 672;

    public MarsDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static MarsDate fromMarsDays(int marsDays) {
        marsDays -= MARS_OFFSET;

        int cycle = marsDays / 1337;
        int remainder = marsDays % 1337;
        int marsYear, d;

        if (remainder < 668) {
            marsYear = 2 * cycle + 1;
            d = remainder + 1;
        } else {
            marsYear = 2 * cycle + 2;
            d = remainder - 668 + 1;
        }

        boolean isLeapMars = (marsYear % 2 == 0);
        int marsMonth = 0;
        int cumulative = 0;
        for (int m = 1; m <= 24; m++) {
            int monthLength = 28;
            if (m % 6 == 0) {
                if (!isLeapMars || m != 24) {
                    monthLength = 27;
                }
            }
            if (d <= cumulative + monthLength) {
                marsMonth = m;
                break;
            }
            cumulative += monthLength;
        }
        int marsDay = d - cumulative;
        return new MarsDate(marsYear, marsMonth, marsDay);
    }

    public MarsCalendar createCalendar() {
        return new MarsCalendar(year, month, day);
    }

    @Override
    public String toString() {
        return String.format("%d 화성년 %d월 %d일", year, month, day);
    }
}