public class AgeCalculator {
    private final EarthDate earthDate;
    private long dummyAccumulator = 0;

    public AgeCalculator(EarthDate earthDate) {
        this.earthDate = earthDate;
    }

    public void performChunk() {
        for (int i = 0; i < 1000; i++) {
            dummyAccumulator += i;
        }
    }

    public String getResult() {
        MarsDate marsDate = earthDate.toMarsDate();
        return String.format("\n지구날은 %s => %s\n\n%s",
                earthDate.formatTotalDays(),
                marsDate,
                marsDate.createCalendar());
    }
}