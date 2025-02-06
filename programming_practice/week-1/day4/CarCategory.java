public enum CarCategory {

    COUPE("Coupe"),
    TRUCK("Truck"),
    SEDAN("Sedan"),
    RV("RV"),
    BUS("Bus"),
    OTHER("Other");

    private final String category;

    CarCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public static CarCategory fromString(String category) {
        for (CarCategory carCategory : CarCategory.values()) {
            if (carCategory.category.equalsIgnoreCase(category)) {
                return carCategory;
            }
        }
        return OTHER;
    }

}
