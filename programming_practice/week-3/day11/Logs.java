import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Logs {

    private final List<Log> logs;

    private Logs(List<Log> logs) {
        this.logs = logs;
    }

    public static Logs of(List<Log> logs) {
        return new Logs(logs);
    }

    // 로그 레벨로 필터링
    public Logs filterByLogLevel(String level) {
        List<Log> filtered = logs.stream()
                .filter(log -> log.getLevel().equalsIgnoreCase(level))
                .collect(Collectors.toList());
        return new Logs(filtered);
    }

    // 프로세스 이름으로 필터링
    public Logs filterByProcess(String process) {
        List<Log> filtered = logs.stream()
                .filter(log -> log.getProcess().equalsIgnoreCase(process))
                .collect(Collectors.toList());

        return new Logs(filtered);
    }

    // 시간 기준 정렬
    public Logs sortByTime() {
        List<Log> sorted = logs.stream()
                .sorted(Comparator.comparing(Log::getTimestamp))
                .collect(Collectors.toList());

        return new Logs(sorted);
    }

    // 프로세스 이름 기준 정렬
    public Logs sortByProcess() {
        List<Log> sorted = logs.stream()
                .sorted(Comparator.comparing(Log::getProcess))
                .collect(Collectors.toList());

        return new Logs(sorted);
    }

    // 로그 레벨별 카운트
    public Map<String, Long> countByLogLevel() {
        return logs.stream()
                .collect(Collectors.groupingBy(Log::getLevel, Collectors.counting()));
    }

    // 프로세스별 카운트
    public Map<String, Long> countByProcess() {
        return logs.stream()
                .collect(Collectors.groupingBy(Log::getProcess, Collectors.counting()));
    }

    public List<Log> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(logs.stream()
                .map(Log::toString)
                .collect(Collectors.joining("\n")));
        sb.append("\n");
        sb.append("=== log level count ===\n");
        countByLogLevel().forEach((level, count) -> sb.append(level).append(": ").append(count).append("\n"));
        sb.append("=== process count ===\n");
        countByProcess().forEach((process, count) -> sb.append(process).append(": ").append(count).append("\n"));
        return sb.toString();

    }

}
