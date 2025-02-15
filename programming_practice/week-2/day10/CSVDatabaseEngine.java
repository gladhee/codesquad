import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class CSVDatabaseEngine implements ASTVisitor {

    private static final String CSV_EXTENSION = ".csv";

    // 기존 코드 내부에서 visit() 메서드들로 분할
    @Override
    public void visit(CreateTableNode node) throws IOException {
        String tableName = node.tableName();
        List<ColumnDefinition> columns = node.columns();
        List<String> header = columns.stream()
                .map(ColumnDefinition::columnName)
                .collect(Collectors.toList());
        Path filePath = Paths.get(tableName + CSV_EXTENSION);
        if (Files.exists(filePath)) {
            throw new IOException("Table " + tableName + " already exists.");
        }
        String headerLine = String.join(",", header) + "\n" + "-----------" + "\n";


        Files.write(filePath, headerLine.getBytes(), StandardOpenOption.CREATE_NEW);
        System.out.println("Created table: " + tableName);
    }

    @Override
    public void visit(DropTableNode node) throws IOException {
        String tableName = node.tableName();

        Path filePath = Paths.get(tableName + CSV_EXTENSION);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
            System.out.println("Dropped table: " + tableName);
        } else {
            throw new IOException("Table " + tableName + " does not exist.");
        }
    }

    @Override
    public void visit(InsertNode node) throws IOException {
        String tableName = node.tableName();
        List<String> values = node.values();
        Path filePath = Paths.get(tableName + ".csv");

        if (!Files.exists(filePath)) {
            throw new IOException("Table " + tableName + " does not exist.");
        }

        // CSV 한 줄(레코드) + 줄바꿈
        String row = String.join(",", values) + System.lineSeparator();

        // 파일에 이어쓰기(APPEND)
        Files.write(filePath, row.getBytes(), StandardOpenOption.APPEND);

        System.out.println("Inserted row into " + tableName + ": " + row);
    }

    @Override
    public void visit(UpdateNode node) throws IOException {
        String tableName = node.tableName();
        List<Assignment> assignments = node.assignments();
        Expression whereClause = node.whereClause();
        Path filePath = Paths.get(tableName + CSV_EXTENSION);
        if (!Files.exists(filePath)) {
            throw new IOException("Table " + tableName + " does not exist.");
        }
        List<String> lines = Files.readAllLines(filePath);
        if (lines.isEmpty()) return;
        String headerLine = lines.get(0);
        String[] headers = headerLine.split(",");
        List<String> newLines = new ArrayList<>();
        newLines.add(headerLine);
        for (int i = 1; i < lines.size(); i++) {
            String row = lines.get(i);
            String[] values = row.split(",");
            Map<String, String> rowMap = new HashMap<>();
            for (int j = 0; j < headers.length && j < values.length; j++) {
                rowMap.put(headers[j], values[j]);
            }
            if (evaluateWhere(whereClause, rowMap)) {
                for (Assignment assign : assignments) {
                    rowMap.put(assign.column(), assign.value());
                }
            }
            String updatedRow = Arrays.stream(headers)
                    .map(h -> rowMap.getOrDefault(h, ""))
                    .collect(Collectors.joining(","));
            newLines.add(updatedRow);
        }
        Files.write(filePath, newLines);
        System.out.println("Updated rows in " + tableName + " matching condition: " + whereClause);
    }

    @Override
    public void visit(DeleteNode node) throws IOException {
        String tableName = node.tableName();
        Expression whereClause = node.whereClause();
        Path filePath = Paths.get(tableName + CSV_EXTENSION);
        if (!Files.exists(filePath)) {
            throw new IOException("Table " + tableName + " does not exist.");
        }
        List<String> lines = Files.readAllLines(filePath);
        if (lines.isEmpty()) return;
        String headerLine = lines.getFirst();
        String[] headers = headerLine.split(",");
        List<String> newLines = new ArrayList<>();
        newLines.add(headerLine);
        for (int i = 1; i < lines.size(); i++) {
            String row = lines.get(i);
            String[] values = row.split(",");
            Map<String, String> rowMap = new HashMap<>();
            for (int j = 0; j < headers.length && j < values.length; j++) {
                rowMap.put(headers[j], values[j]);
            }
            evaluateWhere(whereClause, rowMap);
            newLines.add(row);
        }
        Files.write(filePath, newLines);
        System.out.println("Deleted rows from " + tableName + " matching condition: " + whereClause);
    }

    // 간단한 WHERE evaluator: 단순 이항 표현식만 지원
    private boolean evaluateWhere(Expression expr, Map<String, String> row) {
        // WHERE 절이 없으면 false (조건 미충족 → UPDATE/DELETE 안 함)
        if (expr == null) return false;

        if (expr instanceof BinaryExpression(Expression left, String operator, Expression right)) {

            if (left instanceof IdentifierExpression(String name) && right instanceof LiteralExpression(String value)) {
                String rowValue = row.get(name);
                if (rowValue == null) return false;

                return switch (operator) {
                    case "=" -> rowValue.equals(value);
                    case ">" -> compare(rowValue, value) > 0;
                    case "<" -> compare(rowValue, value) < 0;
                    default -> false;
                };
            }
        }
        return false;
    }

    private int compare(String rowValue, String literal) {
        try {
            double dRow = Double.parseDouble(rowValue);
            double dLit = Double.parseDouble(literal);
            return Double.compare(dRow, dLit);
        } catch (NumberFormatException e) {
            return rowValue.compareTo(literal);
        }
    }

}