public class MarsCalendar {
    private final int year;
    private final int month;
    private final int highlightDay;
    private final int monthLength;
    private final int firstDayOfWeek = 0;

    public MarsCalendar(int year, int month, int highlightDay) {
        this.year = year;
        this.month = month;
        this.highlightDay = highlightDay;
        this.monthLength = calculateMonthLength();
    }

    // 해당 화성월의 일수 계산
    private int calculateMonthLength() {
        if (month % 6 == 0) {
            boolean isLeapMars = (year % 2 == 0);
            if (!isLeapMars || month != 24) {
                return 27;
            }
        }
        return 28;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("     %d년 %d월\n", year, month));
        sb.append("Su Lu Ma Me Jo Ve Sa\n");

        int currentDay = 1;
        int dayOfWeek = firstDayOfWeek;
        for (int i = 0; i < firstDayOfWeek; i++) {
            sb.append("   ");
        }

        while (currentDay <= monthLength) {
            String dayStr;
            if (currentDay == highlightDay) {
                dayStr = String.format("\u001B[31m%2d\u001B[0m", currentDay);
            } else {
                dayStr = String.format("%2d", currentDay);
            }
            sb.append(dayStr).append(" ");
            dayOfWeek++;
            if (dayOfWeek == 7) {
                sb.append("\n");
                dayOfWeek = 0;
            }
            currentDay++;
        }
        if (dayOfWeek != 0) {
            sb.append("\n");
        }
        return sb.toString();
    }
}