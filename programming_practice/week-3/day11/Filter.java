import java.util.Scanner;

public class Filter {

    private final Scanner scanner;
    private final Logs logs;

    public Filter(Logs logs) {
        this.logs = logs;
        this.scanner = new Scanner(System.in);
    }

    public Logs filter() {
        System.out.println("필터 기준을 선택하세요. (L: 로그 레벨, P: 프로세스)");
        String criteria = scanner.nextLine();

        if (criteria.equalsIgnoreCase("L")) {
            System.out.println("원하는 로그 레벨을 입력하세요: ");
            String level = scanner.nextLine();
            System.out.println("로그 시각으로 정렬할까요? (Y/N)");
            String sortChoice = scanner.nextLine();
            if (sortChoice.equalsIgnoreCase("Y")) {
                return logs.filterByLogLevel(level).sortByTime();
            } else {
                return logs.filterByLogLevel(level);
            }
        } else if (criteria.equalsIgnoreCase("P")) {
            System.out.println("원하는 프로세스 이름을 입력하세요: ");
            String process = scanner.nextLine();
            System.out.println("프로세스 이름으로 정렬할까요? (Y/N)");
            String sortChoice = scanner.nextLine();
            if (sortChoice.equalsIgnoreCase("Y")) {
                return logs.filterByProcess(process).sortByProcess();
            } else {
                return logs.filterByProcess(process);
            }
        } else {
            throw new IllegalArgumentException("지원하지 않는 기준입니다.");
        }
    }

}
