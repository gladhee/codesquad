public record ColumnDefinition(String columnName, String dataType) {

    @Override
    public String toString() {
        return columnName + " " + dataType;
    }

}

