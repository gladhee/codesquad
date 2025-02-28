public class EarthDate {
    private final int year;
    private final int month;
    private final int day;

    private static final int EARTH_OFFSET = 15;

    public EarthDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private int computeDayOfYear() {
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 4 == 0) {
            monthDays[1] = 29;
        }
        int dayOfYear = 0;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += monthDays[i];
        }
        return dayOfYear + day;
    }

    public int getTotalDays() {
        int yearsBefore = year - 1;
        int total = yearsBefore * 365;
        total += yearsBefore / 4 - yearsBefore / 100 + yearsBefore / 400;
        total += computeDayOfYear();
        return total + EARTH_OFFSET;
    }

    public String formatTotalDays() {
        return String.format("%,d", getTotalDays());
    }

    public MarsDate toMarsDate() {
        return MarsDate.fromMarsDays(getTotalDays());
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}