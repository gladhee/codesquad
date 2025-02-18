import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DiagramFactory {

    private final Input input;

    private final List<Map.Entry<Predicate<Integer>, Function<List<Point>, Diagram>>> creators =
            List.of(
                    new AbstractMap.SimpleEntry<>(
                            (Predicate<Integer>) (n -> n == 2),
                            points -> new Straight(points.get(0), points.get(1))
                    ),
                    new AbstractMap.SimpleEntry<>(
                            (Predicate<Integer>) (n -> n == 3),
                            points -> new Triangle(points.get(0), points.get(1), points.get(2))
                    )
            );

    private static final Pattern FULL_PATTERN = Pattern.compile("^\\(\\d+,\\d+\\)(-\\(\\d+,\\d+\\))+$");
    private static final Pattern POINT_PATTERN = Pattern.compile("\\((\\d+),(\\d+)\\)");

    public DiagramFactory(Input input) {
        this.input = input;
    }

    public Diagram createDiagramByInput() {
        while (true) {
            try {
                String inputStr = input.nextLine();
                return parse(inputStr);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Diagram parse(String input) {
        String trimmed = input.trim();
        validateInputFormat(trimmed);
        List<Point> points = extractPoints(trimmed);

        return createDiagramFromPoints(points);
    }

    private void validateInputFormat(String input) {
        if (!FULL_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(
                    "입력 형식이 올바르지 않습니다. 예: (1,3)-(3,4) 또는 (1,3)-(3,4)-(4,5) 등이어야 합니다.");
        }
    }

    private List<Point> extractPoints(String input) {
        Matcher matcher = POINT_PATTERN.matcher(input);

        return matcher.results()
                .map(mr -> new Point(
                        Integer.parseInt(mr.group(1)),
                        Integer.parseInt(mr.group(2))
                ))
                .collect(Collectors.toList());
    }

    private Diagram createDiagramFromPoints(List<Point> points) {
        int n = points.size();
        if (n < 2) {
            throw new IllegalArgumentException("최소 2개의 점이 필요합니다.");
        }

        return creators.stream()
                .filter(entry -> entry.getKey().test(n))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원되지 않는 점의 개수: " + n))
                .getValue()
                .apply(points);
    }
}