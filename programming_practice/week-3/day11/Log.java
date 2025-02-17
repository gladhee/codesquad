public class Log {

    private final String level;
    private final String timestamp;
    private final String process;
    private  String message;

    private Log(String level, String timestamp, String process, String message) {
        this.level = level;
        this.timestamp = timestamp;
        this.process = process;
        this.message = message;
    }

    public static Log parse(String line) {
        String[] parts = line.split("\t", 4);
        return new Log(parts[0], parts[1], parts[2], parts[3]);
    }

    public String getLevel() {
        return level;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getProcess() {
        return process;
    }

    public String getMessage() {
        return message;
    }

    public void appendMessage(String message) {
        this.message += message;
    }

    @Override
    public String toString() {
        return level + "\t" + timestamp + "\t" + process + "\t" + message;
    }

}
