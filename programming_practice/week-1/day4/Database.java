import java.util.List;

public class Database {

    private final static String FILE_NAME = "database.csv";
    private final List<String> data;

    public Database()  {
        this.data = loadCsv();
    }

    private List<String> loadCsv() {
        List<String> data = FileReader.readLines(FILE_NAME);
        data.removeFirst();

        if (data.isEmpty()) {
            throw new IllegalArgumentException("Database is empty");
        }

        return data;
    }

    public List<String> getData() {
        return data;
    }

}
