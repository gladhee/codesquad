import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputQRCode() {
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            // 시작과 끝의 대괄호 제거
            if (line.startsWith("[")) {
                line = line.substring(1);
            }
            if (line.endsWith("]")) {
                line = line.substring(0, line.length() - 1);
                break;
            }
            // 끝에 쉼표가 있으면 제거
            if (line.endsWith(",")) {
                line = line.substring(0, line.length() - 1);
            }
            // 따옴표 제거
            line = line.trim();
            if (line.startsWith("\"") && line.endsWith("\"")) {
                line = line.substring(1, line.length() - 1);
            }
            // 유효한 줄이면 리스트에 추가
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }
        scanner.close();

        return lines;
    }

}
