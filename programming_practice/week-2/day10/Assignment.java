public record Assignment(String column, String value) {

    @Override
    public String toString() {
        return column + " = " + value;
    }

}