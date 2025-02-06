public class Car implements Comparable<Car> {

    private final String name;
    private final CarCategory category;
    private final int passengerCapacity;
    private final int introductionYearMonth;
    private final int discontinuationYearMonth;
    private final boolean discontinued;

    private Car(Builder builder) {
        this.name = builder.name;
        this.category = builder.category;
        this.passengerCapacity = builder.passengerCapacity;
        this.introductionYearMonth = builder.introductionYearMonth;
        this.discontinuationYearMonth = builder.discontinuationYearMonth;
        this.discontinued = builder.discontinued;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isActiveIn(int year) {
        return introductionYearMonth <= year && year < discontinuationYearMonth;
    }

    public boolean canCarry(int passengerCount) {
        return passengerCapacity >= passengerCount;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Car car) {
        return name.compareTo(car.name);
    }

    @Override
    public String toString() {
        return String.format("%s%s(%s)", name, discontinued ? "*" : "", category.getCategory());
    }

    public static class Builder {
        private String name;
        private CarCategory category;
        private int passengerCapacity;
        private int introductionYearMonth;
        private int discontinuationYearMonth;
        private boolean discontinued;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(CarCategory category) {
            this.category = category;
            return this;
        }

        public Builder passengerCapacity(int passengerCapacity) {
            this.passengerCapacity = passengerCapacity;
            return this;
        }

        public Builder introductionYearMonth(int introductionYearMonth) {
            this.introductionYearMonth = introductionYearMonth;
            return this;
        }

        public Builder discontinuationYearMonth(int discontinuationYearMonth) {
            this.discontinuationYearMonth = discontinuationYearMonth;
            return this;
        }

        public Builder discontinued(boolean discontinued) {
            this.discontinued = discontinued;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

}
